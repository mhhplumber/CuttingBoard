package data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class ImageLoader
{
    private OnImageLoadedListener _onImage;

    public interface OnImageLoadedListener
    {
        void onImageLoaded(Recipe recipe, Bitmap bitmap);
    }

    public void setOnImageLoadedListener(OnImageLoadedListener onImageLoadedListener)
    {
        _onImage = onImageLoadedListener;
    }

    public void loadImage(Recipe... recipes)
    {
        new BackgroundTask().execute(recipes);
    }

    public Bitmap load(String url)
    {

        URL netUrl = null;
        try
        {
            netUrl = new URL(url);
            Bitmap bitmap = BitmapFactory.decodeStream(netUrl.openStream());
            return bitmap;
        } catch (MalformedURLException e)
        {

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    class BackgroundTask extends AsyncTask<Recipe, String, BackgroundTask.Container[]>
    {

        class Container
        {
            Recipe recipe;
            Bitmap bitmap;
        }

        @Override
        protected Container[] doInBackground(Recipe... recipes)
        {
            Container[] containers = new Container[recipes.length];
            for (int i = 0; i < recipes.length; ++i)
            {
                Recipe recipe = recipes[i];
                Bitmap bitmap = load(recipe.getUrl());
                containers[i] = new Container();
                containers[i].recipe = recipe;
                containers[i].bitmap = bitmap;

            }
            return containers;
        }

        @Override
        public void onPostExecute(Container... containers)
        {
            for (Container container : containers)
                if (_onImage != null)
                    _onImage.onImageLoaded(container.recipe, container.bitmap);
        }
    }
}
