from urllib import request
import json
from urllib.request import urlopen
import requests

my_url = "http://food2fork.com/api/search"

def main():
<<<<<<< HEAD
    get_url("chicken", "511b874130772a9a0775f18fa6822b9e", 'r')
=======
    get_url("chicken", "511b874130772a9a0775f18fa6822b9e")
    print("hello")
>>>>>>> dfc820be6d6a2debdcbcc442c2b9c4fc1650444b
    print(my_url)

def get_url(q, api_id):
    global my_url
    my_url += "?key=" + api_id
    my_url += "&q=" + q
    return json.loads(urlopen(my_url, data=None, cafile=None, capath=None, cadefault=False, context=None))

<<<<<<< HEAD
    obj_str = urlopen("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&hd=True")
=======
main()


>>>>>>> dfc820be6d6a2debdcbcc442c2b9c4fc1650444b

    #return json.loads(obj_str);
    print(obj_str)
    return my_url

main()
