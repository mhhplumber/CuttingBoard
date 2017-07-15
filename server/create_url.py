from urllib import request
import json
from urllib.request import urlopen

my_url = "http://food2fork.com/api/search"

def main():
    get_url("chicken", "511b874130772a9a0775f18fa6822b9e")
    print("hello")
    print(my_url)

def get_url(q, api_id):
    global my_url
    my_url += "?key=" + api_id
    my_url += "&q=" + q
    return json.loads(urlopen(my_url, data=None, cafile=None, capath=None, cadefault=False, context=None))

main()




