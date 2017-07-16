package angelhack.cuttingboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by William Zulueta on 7/16/17.
 */

public class FinishedFragment extends Fragment implements View.OnClickListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_finish, null);
        Button savedButton = (Button) view.findViewById(R.id.saveButton);
        savedButton.setOnClickListener(this);
        Button closeButton = (Button) view.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view)
    {
        Log.i("FinishedFragment", "OnClick");
        getFragmentManager().popBackStack("Main", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
