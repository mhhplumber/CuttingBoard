package data;

import java.io.Serializable;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class Recipe implements Serializable
{
    private String _name;
    private String _time;
    private String _url;
    private Ingredient[] _ingredients;
    private Step[] _steps;

    public Recipe(String name, String time, String url, Ingredient[] ingredients, Step[] steps) // TODO Temp
    {
        _name = name;
        _time = time;
        _url = url;
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

    public String getUrl()
    {
        return _url;
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
