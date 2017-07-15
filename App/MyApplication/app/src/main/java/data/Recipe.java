package data;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class Recipe
{
    private String _name;
    private String _time;
    private Ingredient[] _ingredients;
    private Step[] _steps;

    public Recipe(String name, String time, Ingredient[] ingredients, Step[] steps) // TODO Temp
    {
        _name = name;
        _time = time;
        _ingredients = ingredients;
        _steps = steps;
    }

    public String getTime()
    {
        return _time;
    }

    public String getName()
    {
        return _name;
    }

    public Ingredient[] getIngredients()
    {
        return _ingredients;
    }

    public Step[] getSteps()
    {
        return _steps;
    }

//    public static Recipe parse()
//    {
//        return new Recipe();
//    }
}
