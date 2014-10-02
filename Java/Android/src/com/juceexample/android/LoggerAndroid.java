package com.juceexample.android;

import android.util.Log;

import com.juceexample.util.log.LogHelper;

public class LoggerAndroid implements LogHelper
{

    public static final String TAG = "JuceExample";

    @Override
    public void d (String msg)
    {
        Log.d (TAG, msg);
    }

    @Override
    public void e (String msg)
    {
        Log.e (TAG, msg);
    }

    @Override
    public void e (String msg, Throwable e)
    {
        Log.e (TAG, msg, e);
    }

    @Override
    public void w (String msg)
    {
        Log.w (TAG, msg);
    }

    @Override
    public void i (String msg)
    {
        Log.i (TAG, msg);
    }
}
