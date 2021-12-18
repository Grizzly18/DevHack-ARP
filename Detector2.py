import scapy.all as scapy


local_ip = ""
dict_ip_mac = dict()
set_mac = set()


def sniff():
    scapy.sniff(iface=scapy.conf.iface, store=False, prn=process_sniffed_packet)


def react_on_attack(ture_mac, curr_ip):
    print("You are under attack!!")


def process_sniffed_packet(packet):
    global dict_ip_mac, set_mac

    if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
        if packet[scapy.ARP].psrc != local_ip:

            cur_ip = packet[scapy.ARP].psrc
            cur_mac = packet[scapy.ARP].hwsrc

            if cur_ip in dict_ip_mac:

                true_mac = dict_ip_mac[cur_ip]

                if true_mac != cur_mac and cur_mac not in set_mac:

                    set_mac.remove(true_mac)
                    dict_ip_mac[cur_ip] = cur_mac

                elif true_mac != cur_mac and cur_mac in set_mac:

                    react_on_attack(true_mac, cur_ip)

            else:
                dict_ip_mac[cur_ip] = cur_mac


def get_local_ip():
    global local_ip
    local_ip = scapy.get_if_addr(scapy.conf.iface)


if __name__ == '__main__':
    get_local_ip()
    sniff()