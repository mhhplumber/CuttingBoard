import json
from create_url import *
from steps import *
from flask import jsonify

api_id = "511b874130772a9a0775f18fa6822b9e"

def main():
    print(retrieve("beef"))


def retrieve(descriptor):
    dic = get_url(descriptor, api_id)
    data = json.loads(dic.text)
    # print(data)
    return create_json(data)

#creates a large json object containing the recipe title, image url, the steps
#
def create_json(data):
    ct = int(data['count'])
    recRead = data['recipes']
    recipes = {'recipes' : {}}
    for i in range(0, ct):
        ing_dir = get_steps(recRead[i]['source_url'])
        print('ING == ' + str(type(ing_dir)))
        tempR = {'title' : recRead[i]['title'], 
                'image_url' : recRead[i]['image_url'], 
                'recipe_id' : recRead[i]['recipe_id'], 
                'ingredients' : ing_dir['ingredients'],
                'directions' : ing_dir['directions'], 
                'source_url' : recRead[i]['source_url']}
        recipes['recipes']['' + str (i)] = tempR
    return jsonify(recipes)

main()
