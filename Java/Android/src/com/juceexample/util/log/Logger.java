package com.juceexample.util.log;

public class Logger
{
    public static LogHelper log = null;

    public static void d (String msg)
    {
        if (log != null)
        {
            log.d (msg);
        }
    }

    public static void e (String msg)
    {
        if (log != null)
        {
            log.e (msg);
        }
    }

    public static void e (String msg, Throwable e)
    {
        if (log != null)
        {
            log.e (msg, e);
        }
    }

    public static void w (String msg)
    {
        if (log != null)
        {
            log.w (msg);
        }
    }

    public static void i (String msg)
    {
        if (log != null)
        {
            log.i (msg);
        }
    }

}