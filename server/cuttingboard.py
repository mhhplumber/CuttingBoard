from nltk.tokenize import word_tokenize

def main():
    read_review("this is too spicy")

def read_review(string):
    words = word_tokenize(string)
    print(words)
    
    if "spicy"  in words:
        seasoning = words.index("spicy")
        change = adj_before(seasoning, words)
        if change>0
            #add .25 from measurement
        if change<0
            #subtract .25 from measurement

    if "salty"  in words:
        seasoning = words.index("salty")
        change = adj_before(seasoning, words)
        if change>0
            #add .25 from measurement

        if change<0
            #subtract .25 from measurement
        
    if "sweet" in words:
        seasoning = words.index("sweet")
        change = adj_before(seasoning, words)
        if change>0
            #add .25 from measurement

        if change<0
            #subtract .25 from measurement

##########################################################

    if "spice"  in words:
        seasoning = words.index("spice")
        change = nouns_before(seasoning, words)
        if change>0
            #add .25 from measurement
        if change<0
            #subtract .25 from measurement

    if "salt"  in words:
        seasoning = words.index("salt")
        change = nouns_before(seasoning, words)
        if change>0
            #add .25 from measurement

        if change<0
            #subtract .25 from measurement
        
    if "sugar" in words:
        seasoning = words.index("sugar")
        change = nouns_before(seasoning, words)
        if change>0
            #add .25 from measurement

        if change<0
            #subtract .25 from measurement


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
