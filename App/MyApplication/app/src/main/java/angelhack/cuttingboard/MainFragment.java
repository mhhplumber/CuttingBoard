package angelhack.cuttingboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import data.Ingredient;
import data.Recipe;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class MainFragment extends Fragment
{
    // DATA
    private ArrayList<Recipe> _recipes;

    //UI
    private ListView _listView;
    private RecipeListAdapter _adapter;
    private View _view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        _view = inflater.inflate(R.layout.fragment_main, null);

        initRecipes();

        _listView = (ListView) _view.findViewById(R.id.listView);
        _adapter = new RecipeListAdapter(_recipes, getActivity());
        _listView.setAdapter(_adapter);

        return _view;
    }

    private void initRecipes() //TODO PARSE JSON
    {
        _recipes = new ArrayList<>();
        Ingredient[] ingredients1 = new Ingredient[5];
        Recipe recipe1 = new Recipe("Recipe 1", "45:00", );
    }

    public static MainFragment createFragment()
    {
        return new MainFragment();
    }

    class RecipeListAdapter extends ArrayAdapter<Recipe>
    {
        private ArrayList<Recipe> _recipes;

        public RecipeListAdapter(ArrayList<Recipe> recipes, Context context)
        {
            super(context, R.layout.list_card, recipes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = View.inflate(getContext(), R.layout.list_card, null);
            }
            return convertView;
        }

    }
}
