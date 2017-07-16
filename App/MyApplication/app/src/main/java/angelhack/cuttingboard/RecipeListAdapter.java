package angelhack.cuttingboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import data.ImageLoader;
import data.Recipe;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class RecipeListAdapter extends ArrayAdapter<Recipe> implements View.OnClickListener, ImageLoader.OnImageLoadedListener
{
    private ArrayList<Recipe> _recipes;
    private HashMap<Integer, Recipe> _recipeIds;
    private OnRecipeSelectedListener _onRecipeSelected;
    private HashMap<Recipe, Bitmap> _images;

    @Override
    public void onClick(View view)
    {
        if (_onRecipeSelected != null)
            _onRecipeSelected.onRecipeSelected(_recipeIds.get(view.getId()));
    }

    @Override
    public void onImageLoaded(Recipe recipe, Bitmap bitmap)
    {
        _images.put(recipe, bitmap);
        notifyDataSetChanged();
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
        _images = new HashMap<>();
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setOnImageLoadedListener(this);
        imageLoader.loadImage(recipes.toArray(new Recipe[recipes.size()]));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = View.inflate(getContext(), R.layout.list_card, null);
//            convertView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100 * getContext().getResources().getDisplayMetrics().densityDpi));
            convertView.setId(View.generateViewId());
            _recipeIds.put(convertView.getId(), _recipes.get(position));
        }

        if (_images.containsKey(_recipes.get(position)))
        {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.itemIcon);
//            int density = getContext().getResources().getDisplayMetrics().densityDpi;
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(100 * density, 100 * density));
            imageView.setImageBitmap(_images.get(_recipes.get(position)));
        }

        TextView title = (TextView) convertView.findViewById(R.id.itemTitle);
        title.setText(_recipes.get(position).getName());

        TextView time = (TextView) convertView.findViewById(R.id.itemTime);
        time.setText(_recipes.get(position).getTime());

        convertView.setOnClickListener(this);

        return convertView;
    }

}