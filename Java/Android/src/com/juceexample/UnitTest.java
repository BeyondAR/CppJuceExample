package com.juceexample;

import com.jps.cpp.util.CppSetupHelper;
import com.juceexample.util.log.Logger;

public class UnitTest
{
    private static native void runUnitTestNative (UnitTestOutputListener listener);

    /**
     * Unit test the Cpp Common.
     * @param listener Listener to get the unit test events.
     * @return True if the unit test has been executed, false otherwise.
     */
    public static boolean runUnitTest (UnitTestOutputListener listener)
    {
        CppSetupHelper.setUp();
        try
        {
            runUnitTestNative (listener);
            return true;
        } catch (UnsatisfiedLinkError e)
        {
            Logger.e ("Error using the native library", e);
            return false;
        }
    }

    public static void runUnitTest()
    {
        runUnitTest (null);
    }

    /**
     * Listener for the progress of the unit test. Keep in mind that this code
     * is called from the c++ code and it is not running on the UI thread.
     */
    public static interface UnitTestOutputListener
    {
        public void onLogMessage (String message);
        
        public void onTestStarted();
        
        public void onTestFinished (boolean allTestsPassed, String message);
    }
}
