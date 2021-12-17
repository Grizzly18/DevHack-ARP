import scapy.all as scapy
import os


local_ip = ""


def get_mac(ip):
    arp_request = scapy.ARP(pdst=ip)
    broadcast = scapy.Ether(dst="ff:ff:ff:ff:ff:ff:ff:ff")
    arp_request_broadcast = broadcast/arp_request
    answered_list = scapy.srp(arp_request_broadcast, timeout=1, verbose=False)[0]
    return answered_list[0][1].hwsrc


def sniff():
    scapy.sniff(store=False, prn=process_sniffed_packet)


def process_sniffed_packet(packet):
    if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
        if packet[scapy.ARP].pdst != local_ip:
            true_mac = get_mac(packet[scapy.ARP].psrc)
            if true_mac != packet[scapy.ARP].hwsrc:
                print('You are under attack!!')


def get_local_ip():
    os.system("ipconfig > config")
    config = []
    with open("config", "r", encoding='IBM866') as f:
        for line in f.readlines():
            config.append(line)
    local_ip = config[13].strip().split()[-1]


if __name__ == '__main__':
    get_local_ip()
    sniff()