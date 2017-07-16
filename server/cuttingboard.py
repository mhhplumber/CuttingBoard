from nltk.tokenize import word_tokenize
##from steps import ingredients ##
from steps import *
from create_url import *

def main():
    
    global ing
    url = ""
    get_url("chicken", "511b874130772a9a0775f18fa6822b9e", url)
    ##get the url from the app
    print(url)
    #dictionary = get_steps(url)
    #ing = dictionary[0]
    #print(dictionary)
    #read_review(ing)
    
def read_review(string):
    
    words = word_tokenize(string)
    print(string)
    salty = ["salt", "salty"]
    sweet = ["sugar", "brown sugar", "powdered sugar", "vanilla", "sugary"]
    spicy = ["pepper", "spice", "chile", "spicy"]
    counter_sweet = [0, 0, 0] #add, subtract, all
    counter_spicy = [0, 0, 0] #add, subtract, all
    counter_salty = [0, 0, 0] #add, subtract, all
    
    #adjectives 
    
    if "spicy"  in words:
        seasoning = words.index("spicy")
        change = adj_before(seasoning, words)
        amount(change, spicy, counter_spicy)
             
    if "salty"  in words:
        seasoning = words.index("salty")
        change = adj_before(seasoning, words)
        amount(change, salty, counter_salty)
        
    if "sweet" in words:
        seasoning = words.index("sweet")
        change = adj_before(seasoning, words)
        amount(change, sweet, counter_sweet)

    #nouns    

    if "spice"  in words:
        seasoning = words.index("spice")
        change = nouns_before(seasoning, words)
        amount(change, spicy, counter_spicy)
             
    if "salt"  in words:
        seasoning = words.index("salt")
        change = nouns_before(seasoning, words)
        amount(change, salty, counter_salty)
        
    if "sugar" in words:
        seasoning = words.index("sugar")
        change = nouns_before(seasoning, words)
        amount(change, sweet, counter_sweet)


def amount(change, wordList, count): 
    if ing[2].lower() in wordList:
        if change == 1:
            count[0]+=1
            
             #dictionary[0]+=0.25

        if change == -1:
            count[1]+=1
           
             #dictionary[0]-=0.25
        count[2]+=1
        
    add = count[0]/count[2] *100
    sub = count[1]/count[2] *100
    if add >= 70: #if 70 percent of people suggested a change only then will a change to the recipe be made.
        ing[0]+=.25
        count[0] = 0
        count[1] = 0
    if sub >= 70:
        ing[0]-=.25
        count[0] = 0
        count[1] = 0
        

def nouns_before(i, words):
    if words[i-1] == "enough":
        if words[i-2] == "not":
            return 1
        else:
            return -1
    if words[i-1] == "much":
        return -1
    if words[i-1] == "needs":
       return 1
    

def adj_before(i, words):
    if words[i-1] == "more":
        return 1
    if words[i-1] == "less":
        return -1

main()
