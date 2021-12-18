import scapy.all as scapy
import sys
from threading import *
import os
from sys import platform


class Net:
    def __init__(self, type, notify, defend):
        self.type = type
        self.flag_notify = notify
        self.flag_defend = defend

    def get_mac(self, ip):
        arp_request = scapy.ARP(pdst=ip)
        broadcast = scapy.Ether(dst="ff:ff:ff:ff:ff:ff:ff:ff")
        arp_request_broadcast = broadcast/arp_request
        answered_list = scapy.srp(arp_request_broadcast, timeout=1, verbose=False)[0]
        return answered_list[0][1].hwsrc

    def sniff(self):
        scapy.sniff(iface=self.type, store=False, prn=self.process_sniffed_packet)

    def notify(self):
        print('You are under attack')

    def defend(self, hwsrc, psrc, pdst, hwdst):
        if "win" in platform:
            try:
                while True:
                    scapy.sendp(scapy.Ether(src=hwsrc, dst=hwdst) / scapy.ARP(op=2, hwsrc=hwsrc, psrc=psrc, hwdst=hwdst, pdst=pdst))
            except KeyboardInterrupt:
                pass
        else:
            os.system(f"arp -s {hwsrc} {psrc}")

    def react_on_attack(self, hwsrc, psrc, pdst, hwdst):
        if self.flag_defend:
            self.defend(hwsrc, psrc, pdst, hwdst)
        if self.flag_notify:
            self.notify()

    def process_sniffed_packet(self, packet):
        if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
            try:
                true_mac = self.get_mac(packet[scapy.ARP].psrc)
                curr_mac = packet[scapy.ARP].hwsrc
                if true_mac != curr_mac:
                    self.react_on_attack(true_mac, packet[scapy.ARP].psrc, packet[scapy.ARP].pdst, packet[scapy.ARP].hwdst)
            except:
                pass


if __name__ == '__main__':
    notify, defend = sys.argv[1:]
    for i in scapy.get_if_list():
        if '{' in i and '}' in i and "win" in platform:
            Thread(target = Net("\\Device\\NPF_" + i, notify, defend).sniff).start()
        elif "win" not in platform:
            Thread(target = Net(i, notify, defend).sniff).start()