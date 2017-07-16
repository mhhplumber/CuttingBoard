package angelhack.cuttingboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import data.Parser;
import data.Recipe;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class MainFragment extends Fragment implements RecipeListAdapter.OnRecipeSelectedListener, View.OnClickListener
{
    // DATA
    protected ArrayList<Recipe> _recipes;

    //UI
    protected ListView _listView;
    protected RecipeListAdapter _adapter;
    protected View _view;
    protected Button _findButton;
    protected Button _savedButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        _view = inflater.inflate(R.layout.fragment_main, null);


        initRecipes();

        _listView = (ListView) _view.findViewById(R.id.listView);
        _adapter = new RecipeListAdapter(_recipes, getActivity());
        _adapter.setOnRecipeSelectedListener(this);
        _listView.setAdapter(_adapter);

        _findButton = (Button) _view.findViewById(R.id.findButton);
        _savedButton = (Button) _view.findViewById(R.id.savedButton);
        _savedButton.setOnClickListener(this);

        Log.i("MainFragment", "OncreateView() :  " + getFragmentManager().getBackStackEntryCount());
        return _view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    protected void initRecipes() //TODO PARSE JSON
    {
        _recipes = new ArrayList<>();

        _recipes.add(Parser.parse());
        _recipes.add(Parser.parse());
        _recipes.add(Parser.parse());
        _recipes.add(Parser.parse());
        _recipes.add(Parser.parse());

//        _recipes.add(generateRecipe("Recipe 1"));
//        _recipes.add(generateRecipe("Recipe 2"));
//        _recipes.add(generateRecipe("Recipe 3"));
//        _recipes.add(generateRecipe("Recipe 4"));
//        _recipes.add(generateRecipe("Recipe 5"));
    }

//    private Recipe generateRecipe(String name)
//    {
//        Ingredient[] ingredients1 = new Ingredient[1];
//        ingredients1[0] = new Ingredient("Ingredient", 10, "unit");
//        Step[] steps = new Step[1];
//        data.Time time = new data.Time(1, 30, 0);
//        steps[0] = new Step("Instructions", time);
//        Recipe recipe1 = new Recipe(name, "45:00", ingredients1, steps);
//        return recipe1;
//    }

    public static MainFragment createFragment()
    {
        return new MainFragment();
    }

    @Override
    public void onRecipeSelected(Recipe recipe)
    {
        RecipeFragment recipeFragment = RecipeFragment.createFragment(recipe);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, recipeFragment);
        fragmentTransaction.addToBackStack("Main");
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view)
    {

    }
}
