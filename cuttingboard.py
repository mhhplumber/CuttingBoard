from nltk.tokenize import word_tokenize
from steps import ingredients

def main():
    read_review("this is too spicy")

def read_review(string):
    
    words = word_tokenize(string)
    print(words)
    
    salty = ["salt"]
    sweet = ["sugar", "brown sugar", "powdered sugar", "vanilla""]
    spicy = ["pepper", "spice", "chile"]

             
    if "spicy"  in words:
        seasoning = words.index("spicy")
        change = adj_before(seasoning, words)
        amount(change,spicy)
             
    if "salty"  in words:
        seasoning = words.index("salty")
        change = adj_before(seasoning, words)\
        amount(change,salty)
        
    if "sweet" in words:
        seasoning = words.index("sweet")
        change = adj_before(seasoning, words)
        amount(change,sweet)
        

        ##########################################################

    if "spice"  in words:
        seasoning = words.index("spice")
        change = nouns_before(seasoning, words)
        amount(change,spicy)
             
    if "salt"  in words:
        seasoning = words.index("salt")
        change = nouns_before(seasoning, words)
        amount(change,salty)
        
        
    if "sugar" in words:
        seasoning = words.index("sugar")
        change = nouns_before(seasoning, words)
        amount(change,sweet)

def amount(change,list) 
    if ingredients[2].lower() in list
        if change==1
             ingredient[0]+=0.25
        if change==-1
             ingredient[0]-=0.25
        
        ##find the average of the changes

def nouns_before(i, words)
    if words[i-1] == "enough"
        if words[i-2] == "not"
            return 1
        else
            return -1
    if words[i-1] == "much"
        return -1
    if words[i-1] == "needs"
       return 1
    

def adj_before(i, words)
    if words[i-1] == "more"
        return 1
    if words[i-1] == "less"
        return -1




main()
