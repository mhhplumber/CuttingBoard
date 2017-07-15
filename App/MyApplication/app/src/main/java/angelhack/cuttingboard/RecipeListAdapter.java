package angelhack.cuttingboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import data.Recipe;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class RecipeListAdapter extends ArrayAdapter<Recipe> implements View.OnClickListener
{
    private ArrayList<Recipe> _recipes;
    private HashMap<Integer, Recipe> _recipeIds;
    private OnRecipeSelectedListener _onRecipeSelected;

    @Override
    public void onClick(View view)
    {
        if (_onRecipeSelected != null)
            _onRecipeSelected.onRecipeSelected(_recipeIds.get(view.getId()));
    }

    public interface OnRecipeSelectedListener
    {
        void onRecipeSelected(Recipe recipe);
    }

    public void setOnRecipeSelectedListener(OnRecipeSelectedListener onRecipeSelectedListener)
    {
        _onRecipeSelected = onRecipeSelectedListener;
    }

    public RecipeListAdapter(ArrayList<Recipe> recipes, Context context)
    {
        super(context, R.layout.list_card, recipes);
        _recipes = recipes;
        _recipeIds = new HashMap<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = View.inflate(getContext(), R.layout.list_card, null);
            convertView.setId(View.generateViewId());
            _recipeIds.put(convertView.getId(), _recipes.get(position));
        }

        TextView title = (TextView) convertView.findViewById(R.id.itemTitle);
        title.setText(_recipes.get(position)
                .getName());

        TextView time = (TextView) convertView.findViewById(R.id.itemTime);
        time.setText(_recipes.get(position)
                .getTime());

        convertView.setOnClickListener(this);

        return convertView;
    }

}