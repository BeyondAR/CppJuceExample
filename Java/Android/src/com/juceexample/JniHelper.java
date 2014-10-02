package com.juceexample;

import com.jps.cpp.util.CppSetupHelper;
import com.juceexample.util.log.Logger;

public abstract class JniHelper
{
	private static native String getVersionNative();
    private static native int getVersionNumberNative();
    
	public JniHelper() {
		CppSetupHelper.setUp();
	}
	
	protected static void printException (Throwable e)
    {
        Logger.e ("Error using the native library: ", e);
    }
    
    public static String getVersion()
    {
        try
        {
            return getVersionNative();
        } catch (UnsatisfiedLinkError e)
        {
            printException (e);
        }
        return null;
    }
    
    public static int getVersionNumber()
    {
        try
        {
            return getVersionNumberNative();
        } catch (UnsatisfiedLinkError e)
        {
            printException (e);
        }
        return 0;
    }
}
