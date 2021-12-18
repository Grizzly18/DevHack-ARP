import scapy.all as scapy


local_ip = ""


def get_mac(ip):
    arp_request = scapy.ARP(pdst=ip)
    broadcast = scapy.Ether(dst="ff:ff:ff:ff:ff:ff")
    arp_request_broadcast = broadcast/arp_request
    answered_list = scapy.srp(arp_request_broadcast, timeout=1, verbose=False)[0]
    return answered_list[0][1].hwsrc


def sniff():
    scapy.sniff(iface=scapy.conf.iface, store=False, prn=process_sniffed_packet)


def react_on_attack(ture_mac, curr_ip):
    print("You are under attack!!")


def process_sniffed_packet(packet):
    if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
        try:
            true_mac = get_mac(packet[scapy.ARP].psrc)
            curr_mac = packet[scapy.ARP].hwsrc
            print(true_mac, curr_mac, packet[scapy.ARP].psrc)
            if true_mac != curr_mac:
                react_on_attack(true_mac, packet[scapy.ARP].psrc)
        except Exception:
            pass


def get_local_ip():
    global local_ip
    local_ip = scapy.get_if_addr(scapy.conf.iface)
    print(local_ip)


if __name__ == '__main__':
    get_local_ip()
    sniff()