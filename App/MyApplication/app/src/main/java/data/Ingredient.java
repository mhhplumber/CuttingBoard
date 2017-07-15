package data;

import java.io.Serializable;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class Ingredient implements Serializable
{
    private String _name;
    private int _quantity;
    private String _unit;

    public Ingredient(String name, int quantity, String unit)
    {
        _name = name;
        _quantity = quantity;
        _unit = unit;
    }

    public String getName()
    {
        return _name;
    }

    public int getQuantity()
    {
        return _quantity;
    }

    public String getUnit()
    {
        return _unit;
    }

//    private Ingredient()
//    {
//
//    }
//
//    public static Ingredient parse()
//    {
//        return new Ingredient();
//    }
}
