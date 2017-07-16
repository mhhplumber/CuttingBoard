package angelhack.cuttingboard;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import data.ImageLoader;
import data.Ingredient;
import data.Recipe;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class RecipeFragment extends Fragment implements View.OnClickListener, ImageLoader.OnImageLoadedListener
{
    private static final String ARG_RECIPE = "arg_recipe";
    private Recipe _recipe;
    // views
    private View _view;
    private TextView _titleView;
    private TextView _timeView;
    private ImageView _imageView;
    private Button _readyButton;
    private LinearLayout _ingredientLayout;

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

        _titleView = (TextView) _view.findViewById(R.id.recipeTitle);
        _titleView.setText(_recipe.getName());

        _timeView = (TextView) _view.findViewById(R.id.recipeTime);
        _timeView.setText("Time to cook : " + _recipe.getTime());

        _imageView = (ImageView) _view.findViewById(R.id.recipeImageVeiw);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.setOnImageLoadedListener(this);
        imageLoader.loadImage(_recipe);

        _ingredientLayout = (LinearLayout) _view.findViewById(R.id.recipeIngredientView);
        addIngredients();

        _readyButton = (Button) _view.findViewById(R.id.recipeButton);
        _readyButton.setOnClickListener(this);

        return _view;
    }

    private void addIngredients()
    {
        _ingredientLayout.removeAllViews();
        for (Ingredient ingredient : _recipe.getIngredients())
        {
            TextView textView = new TextView(getActivity());
            textView.setTextSize(20);
            textView.setText(" - " + ingredient.getName() + " (" + ingredient.getQuantity() + " " + ingredient.getUnit() + ")");
            _ingredientLayout.addView(textView);
        }
    }

    public static RecipeFragment createFragment(Recipe recipe)
    {
        RecipeFragment recipeFragment = new RecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_RECIPE, recipe);
        recipeFragment.setArguments(bundle);
        return recipeFragment;
    }

    @Override
    public void onClick(View view)
    {
        StepFragment stepFragment = StepFragment.createFragment(_recipe.getSteps());
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack("Recipe");
        fragmentTransaction.replace(R.id.fragmentContainer, stepFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onImageLoaded(Recipe recipe, final Bitmap bitmap)
    {
        getActivity().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (bitmap != null)
                    _imageView.setImageBitmap(bitmap);
            }
        });
    }
}
