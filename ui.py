from tkinter import *
from tkinter.ttk import Checkbutton
import tkinter.messagebox as mb
import scapy.all as scapy
from sys import platform
from threading import *
import os

attack = False
messages_flag = False
defense_flag = False

class MainWindow:
    def __init__(self): 
        self.have_threads = False
        self.root = Tk()
        self.root.title("AntiArp Launcher")
        self.root.geometry("350x200")
        self.root.resizable(width=False, height=False)
        self.first_var = BooleanVar()
        Checkbutton(self.root, onvalue=1, variable=self.first_var, offvalue=0, text='Сообщить об атаках').place(x=15, y=5)
        self.second_var = BooleanVar()
        self.second_chk = Checkbutton(self.root, onvalue=1, offvalue=0, variable=self.second_var, text='Защитить от атак', state=1).place(x=190, y=5)
        Label(self.root, text='Start AntiArp', font="Times 14").place(x=10, y=30)
        Label(self.root, text='Stop AntiArp', font="Times 14").place(x=190, y=30)
        Button(self.root, text=' Start ', command=self.launch).place(x=40, y=75)
        Button(self.root, text=' Stop ', command=self.kill).place(x=220, y=75)
        Label(self.root, text="Вы не под защитой", font="Times 20").place(x=60, y=130)
        self.root.mainloop()

    def launch(self):
        global messages_flag, defense_flag
        messages_flag = self.first_var.get()
        defense_flag = self.second_var.get()
        if defense_flag or messages_flag:
            if not self.have_threads:
                for i in scapy.get_if_list():
                    if '{' in i and '}' in i and "win" in platform:
                        Thread(target = Net("\\Device\\NPF_" + i).sniff, daemon=True).start()
                    elif "win" not in platform:
                        Thread(target = Net(i).sniff, daemon=True).start()
            self.have_threads = True
            Label(self.root, text="                                    ", font="Times 20").place(x=50, y=130)
            Label(self.root, text="Вы под защитой", font="Times 20").place(x=75, y=130)
        else:
            Label(self.root, text="Вы не под защитой", font="Times 20").place(x=60, y=130)

    def kill(self):
        self.root.destroy()


class Net:
    def __init__(self, type):
        self.type = type

    def get_mac(self, ip):
        arp_request = scapy.ARP(pdst=ip)
        broadcast = scapy.Ether(dst="ff:ff:ff:ff:ff:ff")
        arp_request_broadcast = broadcast / arp_request
        answered_list = scapy.srp(arp_request_broadcast, timeout=1, verbose=False)[0]
        return answered_list[0][1].hwsrc

    def sniff(self):
        scapy.sniff(iface=self.type, store=False, prn=self.process_sniffed_packet)

    def notify(self):
        global attack
        if not attack:
            attack = True
            mb.showinfo("Attack", 'You are under attack')

    def react_on_attack(self, hwsrc, psrc, pdst, hwdst):
        global messages_flag, defense_flag
        if messages_flag:
            self.notify()
        if defense_flag:
            self.defend(hwsrc, psrc, pdst, hwdst)

    def change_mac_linux(self, psrc, hwsrc):
        os.system(f"arp -s {psrc} {hwsrc}")

    def change_mac_windows(self, hwsrc, psrc, hwdst, pdst):
        scapy.sendp(scapy.Ether(src=hwsrc, dst=hwdst) / scapy.ARP(op=2, hwsrc=hwsrc, psrc=psrc, hwdst=hwdst, pdst=pdst))

    def defend(self, hwsrc, psrc, pdst, hwdst):
        if "win" in platform:
            try:
                for i in range(1000):
                    self.change_mac_windows(hwsrc, psrc, hwdst, pdst)
            except KeyboardInterrupt:
                pass
        else:
            self.change_mac_linux(psrc, hwsrc)

    def process_sniffed_packet(self, packet):
        global defense_flag
        if packet.haslayer(scapy.ARP) and packet[scapy.ARP].op == 2:
            try:
                true_mac = self.get_mac(packet[scapy.ARP].psrc)
                curr_mac = packet[scapy.ARP].hwsrc
                if true_mac != curr_mac:
                    self.react_on_attack(true_mac, packet[scapy.ARP].psrc, packet[scapy.ARP].pdst,
                                         packet[scapy.ARP].hwdst)
                elif scapy.getmacbyip(packet[scapy.ARP].psrc) != true_mac and 'win' not in platform and defense_flag:
                    self.change_mac_linux(packet[scapy.ARP].psrc, true_mac)
                elif scapy.getmacbyip(packet[scapy.ARP].psrc) != true_mac and 'win' in platform and defense_flag:
                    self.change_mac_windows(true_mac, packet[scapy.ARP].psrc, packet[scapy.ARP].hwdst,
                                            packet[scapy.ARP].pdst)
            except Exception:
                pass


if __name__ == "__main__":
    MainWindow()