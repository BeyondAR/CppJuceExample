package com.juceexample.util.log;

import com.jps.cpp.util.CppSetupHelper;

/**
 * This interface provides a template to create a platform specific Logger. To
 * use you must set you own implementation in the {@link CppSetupHelper} class
 */
public interface LogHelper
{
    public void d (String msg);

    public void e (String msg);

    public void e (String msg, Throwable e);

    public void w (String msg);

    public void i (String msg);
}
