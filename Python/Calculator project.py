from tkinter import *


def button_press(num):

    global eqn_text
    eqn_text = eqn_text + str(num)
    eqn_label.set(eqn_text)


def equals():

    global eqn_text
    try:
        total = str(eval(eqn_text))
        eqn_label.set(total)
        eqn_text = total
    except ZeroDivisionError:
        eqn_label.set('Cannot divide by 0')
    except SyntaxError:
        eqn_label.set("Not possible")

def clear():

    global eqn_text
    eqn_label.set("")
    eqn_text = ''

w = Tk()
w.title('Calculator Program')
w.geometry('400x500')

eqn_text = ''
eqn_label = StringVar()
label = Label(w,
              textvariable=eqn_label,
              font=('consolas',20),
              bg='white',
              width=24,
              height=2)
label.pack()

frame = Frame()
frame.pack()

b1 = Button(frame, text=1, height=4, width=9, font=35, command=lambda: button_press(1))
b1.grid(row=0,column=0)

b2 = Button(frame, text=2, height=4, width=9, font=35, command=lambda: button_press(2))
b2.grid(row=0,column=1)

b3 = Button(frame, text=3, height=4, width=9, font=35, command=lambda: button_press(3))
b3.grid(row=0,column=2)

b4 = Button(frame, text=4, height=4, width=9, font=35, command=lambda: button_press(4))
b4.grid(row=1,column=0)

b5 = Button(frame, text=5, height=4, width=9, font=35, command=lambda: button_press(5))
b5.grid(row=1,column=1)

b6 = Button(frame, text=6, height=4, width=9, font=35, command=lambda: button_press(6))
b6.grid(row=1,column=2)

b7 = Button(frame, text=7, height=4, width=9, font=35, command=lambda: button_press(7))
b7.grid(row=2,column=0)

b8 = Button(frame, text=8, height=4, width=9, font=35, command=lambda: button_press(8))
b8.grid(row=2,column=1)

b9 = Button(frame, text=9, height=4, width=9, font=35, command=lambda: button_press(9))
b9.grid(row=2,column=2)

b0 = Button(frame, text=0, height=4, width=9, font=35, command=lambda: button_press(0))
b0.grid(row=3,column=0)

plus = Button(frame, text='+', height=4, width=9, font=35, command=lambda: button_press("+"))
plus.grid(row=0,column=3)

minus = Button(frame, text='-', height=4, width=9, font=35, command=lambda: button_press('-'))
minus.grid(row=1,column=3)

multiply = Button(frame, text='x', height=4, width=9, font=35, command=lambda: button_press('*'))
multiply.grid(row=2,column=3)

divide = Button(frame, text='/', height=4, width=9, font=35, command=lambda: button_press('/'))
divide.grid(row=3,column=3)

equal = Button(frame, text='=', height=4, width=9, font=35, command=equals)
equal.grid(row=3,column=2)

decimal = Button(frame, text='.', height=4, width=9, font=35, command=lambda: button_press('.'))
decimal.grid(row=3,column=1)

b8 = Button(w, text='CLEAR', height=4, width=19, font=35, command=clear)
b8.pack()




w.mainloop()
