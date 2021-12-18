from tkinter import *
from tkinter.ttk import Checkbutton
from Defence import Net
import scapy.all as scapy
from sys import platform
from threading import *


def launch():
    messages_flag = first_var.get()
    defense_flag = second_var.get()
    for i in scapy.get_if_list():
        if '{' in i and '}' in i and "win" in platform:
            Thread(target = Net("\\Device\\NPF_" + i).sniff, daemon=True).start()
        elif "win" not in platform:
            Thread(target = Net(i).sniff, daemon=True).start()


def kill():
    window.destroy()

window = Tk()
N = window.winfo_screenheight()
M = window.winfo_screenwidth()
window.geometry(f"350x250")
window.title("AntiArp Launcher")

window.columnconfigure(0, pad=3)
window.columnconfigure(1, pad=3)
window.columnconfigure(2, pad=3)
window.columnconfigure(3, pad=3)
window.columnconfigure(4, pad=3)
window.columnconfigure(5, pad=3)
window.rowconfigure(0, pad=3)
window.rowconfigure(1, pad=3)
window.rowconfigure(2, pad=3)
window.rowconfigure(3, pad=3)
window.rowconfigure(4, pad=3)

first_var = BooleanVar()
first_chk = Checkbutton(window, onvalue=1, variable=first_var, offvalue=0, text='Сообщить об атаках')
first_chk.grid(column=1, row=0)

second_var = BooleanVar()
second_chk = Checkbutton(window, onvalue=1, offvalue=0, variable=second_var, text='Защитить от атак       ', state=1)
second_chk.grid(column=5, row=0)

lbl1 = Label(window)
lbl1.grid(column=0,row=1)

start_btn = Button(window, text="Start AntiArp", command=launch)
start_btn.grid(column=1, row=4)

end_btn = Button(window, text="Stop AntiArp", command=kill)
end_btn.grid(column=5, row=4)

window.mainloop()