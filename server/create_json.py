import json
from create_url import *
from steps import *

api_id = "511b874130772a9a0775f18fa6822b9e"

def main():
    dic = get_url("chicken", api_id)
    data = json.loads(dic.text)
    #json = json.dumps(data, indent = 2
    #print(data['count'])
    #print(data['recipes'][2]['title'])
    create_json(data)

#creates a large json object containing the recipe title, image url, the steps
#
def create_json(data):
    count = data['count']
    json_string = '{\n'
    for i in range(count):
        json_string += '\t{\n\t \"title\": ' + '\"' +  data['recipes'][i]['title'] + '\",\n'
        # "title": "the title of the recipe"
        json_string += '\t \"image_url\": ' + '\"' + data['recipes'][i]['image_url'] + '\",\n\t'
        json_string += ' \"recipe_id\": ' + '\"' + data['recipes'][i]['recipe_id'] + '\"\n\t}'
        # "image_url": "The url of the image"
        #json_string += '\"steps\": ' + get_steps(data['recipes'][i]['source_url'])
        print(get_steps(data['recipes'][i]['source_url']))
        # "steps": "Whatever michael gives me back"
        if (i < count - 1):
            json_string += ','
        json_string += '\n'
    json_string += '}'
    #parsed = json.loads(json_string)
    #print(json.dumps(json_string, indent = 2))
    print(json_string)

main()
