from tkinter import *
from tkinter.ttk import Checkbutton
from Defence import Net
import scapy.all as scapy
from sys import platform
from threading import *


class MainWindow:
    def __init__(self):
        self.window = Tk()
        self.window.geometry(f"350x250")
        self.window.title("AntiArp Launcher")
        self.window.columnconfigure(0, pad=3)
        self.window.columnconfigure(1, pad=3)
        self.window.columnconfigure(2, pad=3)
        self.window.columnconfigure(3, pad=3)
        self.window.columnconfigure(4, pad=3)
        self.window.columnconfigure(5, pad=3)
        self.window.rowconfigure(0, pad=3)
        self.window.rowconfigure(1, pad=3)
        self.window.rowconfigure(2, pad=3)
        self.window.rowconfigure(3, pad=3)
        self.window.rowconfigure(4, pad=3)
        self.first_var = BooleanVar()
        self.first_chk = Checkbutton(self.window, onvalue=1, variable=self.first_var, offvalue=0, text='Сообщить об атаках')
        self.first_chk.grid(column=1, row=0)
        self.second_var = BooleanVar()
        self.second_chk = Checkbutton(self.window, onvalue=1, offvalue=0, variable=self.second_var, text='Защитить от атак       ', state=1)
        self.second_chk.grid(column=5, row=0)
        self.lbl1 = Label(self.window)
        self.lbl1.grid(column=0,row=1)
        self.start_btn = Button(self.window, text="Start AntiArp", command=self.launch)
        self.start_btn.grid(column=1, row=4)
        self.end_btn = Button(self.window, text="Stop AntiArp", command=self.kill)
        self.end_btn.grid(column=5, row=4)
        self.window.mainloop()

    def launch(self):
        messages_flag = self.first_var.get()
        defense_flag = self.second_var.get()
        for i in scapy.get_if_list():
            if '{' in i and '}' in i and "win" in platform:
                Thread(target = Net("\\Device\\NPF_" + i, messages_flag, defense_flag).sniff, daemon=True).start()
            elif "win" not in platform:
                Thread(target = Net(i, messages_flag, defense_flag).sniff, daemon=True).start()

    def kill(self):
        self.window.destroy()


if __name__ == "__main__":
    MainWindow()