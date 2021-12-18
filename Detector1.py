import scapy.all as scapy
from threading import *


class Net:
    def __init__(self, type):
        self.type = type


    def get_mac(self, ip):
        arp_request = scapy.ARP(pdst=ip)
        broadcast = scapy.Ether(dst="ff:ff:ff:ff:ff:ff:ff:ff")
        arp_request_broadcast = broadcast/arp_request
        answered_list = scapy.srp(arp_request_broadcast, timeout=1, verbose=False)[0]
        return answered_list[0][1].hwsrc


    def sniff(self):
        scapy.sniff(iface=self.type, store=False, prn=self.process_sniffed_packet)


    def react_on_attack(self, true_mac, curr_ip):
        print("You are under attack!!")
        

    def process_sniffed_packet(self, packet):
        if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
            try:
                true_mac = self.get_mac(packet[scapy.ARP].psrc)
                curr_mac = packet[scapy.ARP].hwsrc
                if true_mac != curr_mac:
                    self.react_on_attack(true_mac, packet[scapy.ARP].psrc)
            except:
                pass


if __name__ == '__main__':
    for i in scapy.get_if_list():
        nn = Net("\\Device\\NPF_" + i)
        n = Thread(target = nn.sniff)
        n.start()