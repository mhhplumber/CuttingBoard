from urllib import request
import json
from urllib.request import urlopen

my_url = "http://food2fork.com/api/search"

# def main():
#     get_url("chicken", "511b874130772a9a0775f18fa6822b9e", 'r')
#     print(my_url)

def get_url(q, api_id, sort):
    global my_url
    my_url += "?key="
    my_url += "&q=" + q
    if (sort == 'r' or sort == 't'):
        my_url += "&sort=" + sort

    obj_str = urlopen(my_url);

    return json.loads(obj_str);

# main()




