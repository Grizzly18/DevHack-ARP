import os
import re

def arp_spoofing():
    os.system('arp -a > .detector')
    mac_list = []
    with open(".detector", "r") as f:
        for line in f.readlines()[3:]:
            if line.split()[3] == "вЁзҐбЄЁ©":
                continue
            mac_addr = re.search(r'([0-9A-Fa-f]{1,2}[:-]){5}([0-9A-Fa-f]{1,2})', line, re.I).group()
            if mac_addr not in mac_list:
                mac_list.append(mac_addr)
            else:
                return True
    os.remove(".detector")
    return False


def main():
    if(arp_spoofing()):
        print("BAD")
    else:
        print("GOOD")

if __name__ == '__main__':
    main()
