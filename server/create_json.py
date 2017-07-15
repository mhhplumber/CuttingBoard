import json
from create_url import *

api_id = "511b874130772a9a0775f18fa6822b9e"

def main():
    dic = get_url("chicken", "511b874130772a9a0775f18fa6822b9e", "r")
    create_json(dic)

def create_json(dictionary):
    json = json.dumps(dictionary)
    print(json)
