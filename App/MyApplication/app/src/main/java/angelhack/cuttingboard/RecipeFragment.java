package angelhack.cuttingboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import data.Recipe;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class RecipeFragment extends Fragment
{
    private static final String ARG_RECIPE = "arg_recipe";
    private Recipe _recipe;
    // views
    private View _view;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _recipe = (Recipe) getArguments().getSerializable(ARG_RECIPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        _view = inflater.inflate(R.layout.fragment_recipe, null);
        TextView title = (TextView) _view.findViewById(R.id.recipeTitle);
        title.setText(_recipe.getName());
        return _view;
    }

    public static RecipeFragment createFragment(Recipe recipe)
    {
        RecipeFragment recipeFragment = new RecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_RECIPE, recipe);
        recipeFragment.setArguments(bundle);
        return recipeFragment;
    }
}
