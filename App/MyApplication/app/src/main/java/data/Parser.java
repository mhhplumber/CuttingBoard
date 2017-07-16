package data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class Parser
{
    private static final String TAG = "JsonParser";
    private static final String DATA =
            "{\n" +
            "    \"Name\" : \"Tequila Lime Grilled Chicken\",\n" +
            "    \"url\" : \"http://static.food2fork.com/Tequila2BLime2BGrilled2BChicken2BClub2BSandwich2Bwith2BGuacamole2Band2BRoasted2BJalapeno2BMayo2B5002B0617b8b60d15.jpg\",\n" +
            "    \"Time\" : \"00:30\",\n" +
            "    \"Ingredient\" : [" +
                    "{\n" +
            "        \"Name\" : \"Tequila\",\n" +
            "        \"Quantity\" : \"1/4\",\n" +
            "        \"Unit\" : \"cup\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"Name\" : \"Lime juice\",\n" +
            "            \"Quantity\" : \"1/4\",\n" +
            "            \"Unit\" : \"cup\"\n" +
            "         },\n" +
            "         {\n" +
            "             \"Name\" : \"Oil\",\n" +
            "             \"Quantity\" : \"2\",\n" +
            "             \"Unit\" : \"tablespoons\"\n" +
            "          }],\n" +
            "\n" +
            "    \"Step\" : [{\n" +
            "        \"Time\" : \"00:05\",\n" +
            "        \"Instruction\" : \"Puree the tequila, lime juice, oil, lime zest, jalapeno, garlic, cilantro, cumin, salt and pepper in a food processor.\"\n" +
            "        }, \n" +
            "        {\n" +
            "        \"Time\" : \"12:00\",\n" +
            "        \"Instruction\" : \"Place both the chicken and the marinade in a freezer bag and marinate over night.\"\n" +
            "        }\n" +
            "        ]\n" +
            "\n" +
            "}";

    public static Recipe parse() // TODO TEMP
    {
        try
        {
            Log.i(TAG, "Character at 831 : " + DATA.charAt(831));
            JSONObject jsonObject = new JSONObject(DATA);

            String name = jsonObject.getString("Name");
            Log.i(TAG, "Parsed name : " + name);
            String url = jsonObject.getString("url");
            String time = jsonObject.getString("Time");

            JSONArray iArray = jsonObject.getJSONArray("Ingredient");
            Ingredient[] ingredients = new Ingredient[iArray.length()];
            for (int i = 0; i < ingredients.length; ++i)
            {
                JSONObject object = iArray.getJSONObject(i);
                String n = object.getString("Name");
                String q = object.getString("Quantity");
                String unit = object.getString("Unit");
                ingredients[i] = new Ingredient(n, q, unit);
            }

            JSONArray sArray = jsonObject.getJSONArray("Step");
            Step[] steps = new Step[sArray.length()];
            for (int i = 0; i < sArray.length(); ++i)
            {
                JSONObject object = sArray.getJSONObject(i);
                Time t = Time.parse(object.getString("Time"), "hh:mm");
                String inst = object.getString("Instruction");
                steps[i] = new Step(inst, t);
            }

//            Log.i(TAG, "Parsed object : " + url);
            return new Recipe(name, time, url, ingredients, steps);
        } catch (JSONException e)
        {
            Log.i(TAG, "Json exception : " + e.getLocalizedMessage());
        }
        return null;
    }
}
