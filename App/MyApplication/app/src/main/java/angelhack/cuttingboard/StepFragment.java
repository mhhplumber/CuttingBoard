package angelhack.cuttingboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import data.Step;
import data.Time;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class StepFragment extends Fragment implements View.OnClickListener
{
    public static final String ARG_STEPS = "arg_steps";
    private Step[] _steps;
    private int _currentIndex;
    private boolean _inProgress;
    // ui stuff
    private View _view;
    private TextView _title;
    private TextView _fullTime;
    private TextView _time;
    private TextView _instr;
    private Timer _timer;
    private Button _startButton;
    private ImageButton _leftButton;
    private ImageButton _rightButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _steps = (Step[]) getArguments().getSerializable(ARG_STEPS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        _view = inflater.inflate(R.layout.fragment_step, null);

        _title = (TextView) _view.findViewById(R.id.stepTitle);
        _fullTime = (TextView) _view.findViewById(R.id.stepFullTime);
        _instr = (TextView) _view.findViewById(R.id.stepInstr);
        _time = (TextView) _view.findViewById(R.id.stepTime);
        _startButton = (Button) _view.findViewById(R.id.stepStartButton);
        _startButton.setOnClickListener(this);
        _leftButton = (ImageButton) _view.findViewById(R.id.stepLeftButton);
        _leftButton.setOnClickListener(this);
        _rightButton = (ImageButton) _view.findViewById(R.id.stepRightButton);
        _rightButton.setOnClickListener(this);

        initViewForStep();
        return _view;
    }

    private void initViewForStep()
    {
        Step step = _steps[_currentIndex];
        _title.setText("Step " + (_currentIndex + 1));
        _fullTime.setText("Estimated Time : " + step.getTime().toString());
        _instr.setText(step.getInstr());
        _time.setText(step.getTime()
                .toString());
        if (_timer != null)
        {
            _inProgress = false;
            _second = 0;
        }

        _startButton.setText("Start");
        //        _timer = new Timer();
    }

    private long _second;
    private TimerTask countDownTask = new TimerTask()
    {
        @Override
        public void run()
        {
            getActivity().runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    if (_inProgress)
                    {
                        ++_second;
                        _time.setText(Time.fromSeconds(_steps[_currentIndex].getTime().toSeconds() - _second).toString());
                        if (_second >= _steps[_currentIndex].getTime().toSeconds())
                        {
                            Toast.makeText(getActivity(), "Step Finished", Toast.LENGTH_SHORT).show();
                            _timer.cancel();
                            _timer.purge();
                            _timer = null;
                        }
                    }
                }
            });
        }
    };

    @Override
    public void onStop()
    {
        super.onStop();
        if (_timer != null)
        {
            _timer.cancel();
            _timer.purge();
        }
    }

    public static StepFragment createFragment(Step[] steps)
    {
        StepFragment stepFragment = new StepFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_STEPS, steps);
        stepFragment.setArguments(bundle);
        return stepFragment;
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == _startButton.getId())
        {
            if (!_inProgress)
            {
                if (_timer == null)
                {
                    _timer = new Timer();
                    _timer.scheduleAtFixedRate(countDownTask, 0, 1000);
                }
                _startButton.setText("Pause");
            } else
            {
                _startButton.setText("Start");
            }
            _inProgress = !_inProgress;
        } else if (view.getId() == _leftButton.getId())
        {
            if (_currentIndex > 0)
            {
                --_currentIndex;
                initViewForStep();
            }
        } else if (view.getId() == _rightButton.getId())
        {
            if (_currentIndex < _steps.length - 1)
            {
                ++_currentIndex;
                initViewForStep();
            } else if (_currentIndex + 1 >= _steps.length)
            {
                FinishedFragment finishedFragment = new FinishedFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, finishedFragment);
                fragmentTransaction.commit();
            }
        }
    }
}
