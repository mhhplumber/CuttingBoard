package data;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class Ingredient
{
    private String _name;
    private int _quantity;
    private String _unit;

    private Ingredient()
    {

    }

    public static Ingredient parse()
    {
        return new Ingredient();
    }
}
