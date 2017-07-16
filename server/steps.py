import requests
from bs4 import BeautifulSoup
from fractions import Fraction

# def main():
# 	get_steps("http://www.closetcooking.com/2011/08/buffalo-chicken-grilled-cheese-sandwich.html")
global ingredients

def get_steps(URL):
	bs =  BeautifulSoup(requests.get(URL).content, 'html.parser')

	if ("closetcooking" in URL):
		ingredients = bs.find_all(itemprop="ingredients")
		directions = bs.find_all(itemprop="recipeInstructions")
	elif ("allrecipes" in URL):
		ingredients = bs.findall(itemprop="ingredients")
		directions = bs.findall({'class':"recipe-directions__list--item"})
	
	for ingredient in ingredients:
		ingredient = ingredient.get_text()
		ingredient = ingredient.split()
		if ("/" in ingredient[0]):
			ingredient[0] = float(Fraction(ingredient[0]))
		elif(ingredient[0].isdigit()):
			ingredient[0] = float(ingredient[0])


	for direction in directions:
		direction = direction.get_text();
		direction = 



# main();