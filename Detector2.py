import scapy.all as scapy
import os


local_ip = ""
dict_ip_mac = dict()
set_mac = set()


def sniff():
    scapy.sniff(store=False, prn=process_sniffed_packet)


def process_sniffed_packet(packet):
    global dict_ip_mac, set_mac

    if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
        if packet[scapy.ARP].pdst != local_ip:

            cur_ip = packet[scapy.ARP].psrc
            cur_mac = packet[scapy.ARP].hwsrc

            if cur_ip in dict_ip_mac:

                true_mac = dict_ip_mac[cur_ip]

                if true_mac != cur_mac and cur_mac not in set_mac:

                    set_mac.remove(true_mac)
                    dict_ip_mac[cur_ip] = cur_mac

                elif true_mac != cur_mac and cur_mac in set_mac:

                    print("You are under attack!!")

            else:
                dict_ip_mac[cur_ip] = cur_mac


def get_local_ip():
    global local_ip
    os.system("ipconfig > config")
    config = []
    with open("config", "r", encoding='IBM866') as f:
        config = f.readlines()
    local_ip = config[13].strip().split()[-1]


if __name__ == '__main__':
    get_local_ip()
    sniff()