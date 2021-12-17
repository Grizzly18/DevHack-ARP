import scapy.all as scapy


def sniff():
    # Сканирование входящих пакетов
    scapy.sniff(store=False, prn=process_sniffed_packet)


def process_sniffed_packet(packet):
    # Проверка является ли пакет ответом на ARP запрос
    if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
        print(packet.show())


if __name__ == '__main__':
    sniff()