package data;

import android.text.format.Time;

import java.io.Serializable;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class Step implements Serializable
{
    private Time _time;
    private String _instr;

    public Step(String instr, Time time)
    {
        _instr = instr;
        _time = time;
    }

    public Time getTime()
    {
        return _time;
    }

    public String getInstr()
    {
        return _instr;
    }
}
