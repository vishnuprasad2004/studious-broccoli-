import requests
from requests.exceptions import HTTPError
from tkinter import *

def getData(word):
    api_url="https://api.dictionaryapi.dev/api/v2/entries/en/{}".format(word)
    try:
        res = requests.get(api_url.format("apple"))
        data = res.json()
        if not res.ok:
            return {}
        # print("Word:",data[0]["word"])
        # print("Phonetics:",data[0]["phonetic"])
        # print("Meaning:",data[0]["meanings"][0]["definitions"][0]["definition"])
        
        # return {"word": data[0]["word"],
        #         "phonetic": data[0]["phonetic"],
        #         "meaning":data[0]["meanings"][0]["definitions"][0]["definition"]
        #         }
        return data[0]["word"],data[0]["phonetic"],data[0]["meanings"][0]["definitions"][0]["definition"]

    except HTTPError as http_err:
        print(f'HTTP error occurred: {http_err}')
        return {}
    except Exception as err:
        print(f'Other error occurred: {err}')
        return {}
    

def update_label():
    text = entry.get()
    m_label.config(text="Meaning: " + getData(text)["meaning"])
    p_label.config(text="Phonetics: " + getData(text)["phonetic"])

w = Tk()
w.title("Dictionary Program")

entry = Entry(w, width=30)
entry.pack(pady=10)

update_button = Button(w, text="Find Meaning", command=update_label)
update_button.pack(pady=10)

m_label = Label(w, text="")
p_label = Label(w, text="")
m_label.pack(pady=10)
p_label.pack(pady=10)

w.mainloop()
