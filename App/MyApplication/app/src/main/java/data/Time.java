package data;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by William Zulueta on 7/15/17.
 */

public class Time implements Serializable
{
    private int _hours;
    private int _minutes;
    private int _seconds;

    public Time(int hours, int minutes, int seconds)
    {
        _hours = hours;
        _minutes = minutes;
        _seconds = seconds;
    }

    public int getHours()
    {
        return _hours;
    }

    public int getMinutes()
    {
        return _minutes;
    }

    public int getSeconds()
    {
        return _seconds;
    }

    public void setHours(int hours)
    {
        _hours = hours;
    }

    public void setMinutes(int minutes)
    {
        _minutes = minutes;
    }

    public void setSeconds(int seconds)
    {
        _seconds = seconds;
    }

    public static Time parse(String time, String format)
    {
        int hours = Integer.parseInt(time.substring(format.indexOf('h'), format.lastIndexOf('h')));
        int minutes = Integer.parseInt(time.substring(format.indexOf('m'), format.lastIndexOf('m')));
        int seconds = Integer.parseInt(time.substring(format.indexOf('s'), format.lastIndexOf('s')));
        Log.i("TimeParser", "hours : " + hours + ", minutes : " + minutes + ", seconds " + seconds);
        return new Time(hours, minutes, seconds);
    }
}
