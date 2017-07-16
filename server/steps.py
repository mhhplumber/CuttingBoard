import requests
from bs4 import BeautifulSoup
from fractions import Fraction

# def main():
# 	get_steps("http://www.closetcooking.com/2011/08/buffalo-chicken-grilled-cheese-sandwich.html")


def get_steps(URL):
	bs =  BeautifulSoup(requests.get(URL).content, 'html.parser')
	ingredients =[]
	directions = []

	if ("closetcooking" in URL):
		ingredients = bs.find_all(itemprop="ingredients")
		directions = bs.find_all(itemprop="recipeInstructions")
	elif ("allrecipes" in URL):
		ingredients = bs.find_all(itemprop="ingredients")
		directions = bs.find_all({'class':"recipe-directions__list--item"})
	else:
		return URL

	for i in range(0, len(ingredients)):
		ingredients[i] = ingredients[i].get_text()
		ingredients[i] = ingredients[i].split()
		if ("/" in ingredients[i][0]):
			ingredients[i][0] = float(Fraction(ingredients[i][0]))
		elif(ingredients[i][0].isdigit()):
			ingredients[i][0] = float(ingredients[i][0])


	for i in range(0, len(directions)):
		directions[i] = directions[i].get_text()
		directions[i] = directions[i].split()
		time = [0, 0, 0]
		for j in range(0, len(directions[i])):
			temp = 0
			if directions[i][j].isdigit():
				temp = int(directions[i][j])

			if temp:
				if directions[i][j] == "hours" or directions[i][j] == "h" :
					time[0] = temp
					temp = 0
				elif directions[i][j] == "minutes" or directions[i][j] == "min" or directions[i][j] == "m":
					time[1] = temp
					temp = 0
				elif directions[i][j] =="seconds" or directions[i][j] == "secs" or directions[i][j] == "s":
					time[2] = temp
					temp = 0
		directions[i] = time + directions[i]


	return {'ingredients' : ingredients, 'directions' : directions}





# main();
