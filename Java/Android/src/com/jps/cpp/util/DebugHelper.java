package com.jps.cpp.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.juceexample.UnitTest;

/**
 * This class provides a set of tools to debug the native code. To sumarize you
 * need to call {@link DebugHelper#prepareNativeCodeToBeDebugged (Context)} and
 * {@link DebugHelper#runUnitTest()} at the very beginning of your app code (for
 * instance in the onCreate). Later to configure this options just change the
 * following flags:
 * <ul>
 * <li>{@link DebugHelper#DEBUG_WANTED}: To enable the tools to debug the native
 * code.</li>
 * <li>{@link DebugHelper#RUN_UNIT_TEST_ON_START}: To run the unit test.</li>
 * </ul>
 * 
 * The following code shows how to use the this utilities:
 * 
 * <pre>
 * {@code
 * public class MainActivity extends Activity {
 * 
 * 	{@literal @}Override
 * 	protected void onCreate (Bundle savedInstanceState) {
 * 		DebugHelper.prepareNativeCodeToBeDebugged (getApplicationContext());
 * 		DebugHelper.runUnitTest();
 * 	}
 * }
 * </pre>
 */
public class DebugHelper
{
    /**
     * Use this flag to tell the application that you want to debug the native
     * code. In order to use properly this flag the following line need to be
     * added at the very beginning of your application:
     * 
     * <pre>
     * {@code
     * 	DebugHelper.prepareNativeCodeToBeDebugged (context);
     * }
     * </pre>
     */
    public static final boolean DEBUG_WANTED = true;
    
    /**
     * This flag is should be used to check if the unit test must run once the
     * app is started.<br>
     * To be able to debug properly the c/c++ code before calling the unit test
     * the following code need to be called in order to wait for the debugger:
     * 
     * <pre>
     * {@code
     * 	android.os.Debug.waitForDebugger();
     * }
     * </pre>
     */
    public static final boolean RUN_UNIT_TEST_ON_START = false;

    private static Boolean isDebuggable;

    /** Check if the app can be debugged or not */
    public static boolean isDebugable (Context context)
    {
        if (isDebuggable == null)
        {
            if (context == null)
            {
            	throw new NullPointerException ("Context should not be null the first time.");
            }
            isDebuggable = (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
        }
        return isDebuggable;
    }

    // We only need to execute prepareNativeCodeToBeDebugged once.
    private static boolean executed = false;

    /**
     * This method provide a set of checks to make sure that the native code can
     * be debugged. Mainly it force to load the native library and check if the
     * app can be debugged according to the application settings. In order to
     * enable the debug code, set the flag {@link DebugHelper#DEBUG_WANTED} to
     * true.
     * 
     * @param context
     */
    @SuppressLint ("NewApi")
    public static void prepareNativeCodeToBeDebugged (Context context)
    {
        if (!DEBUG_WANTED || executed) return; 
        executed = true;
        System.loadLibrary (CppSetupHelper.getNativeLibraryName());
        try
        {
            if (!isDebugable (context)) { throw new IllegalStateException (
                    "The application is not debugable. Did you set android:debuggable=\"true\" in your manifest?"); }
        } catch (NullPointerException e)
        {
            throw e;
        }
        boolean isUnitTestRunning = false;
        if (android.os.Build.VERSION.SDK_INT >= 13)
        {
            isUnitTestRunning = ActivityManager.isRunningInTestHarness();
        }
            
        if (!android.os.Debug.waitingForDebugger() && !android.os.Debug.isDebuggerConnected() && !isUnitTestRunning)
        {
            android.os.Debug.waitForDebugger();
        }
    }

    /**
     * Call this method to run the unit test as soon as the app starts. To
     * enable it just set the {@link DebugHelper#RUN_UNIT_TEST_ON_START} to
     * true.
     */
    public static void runUnitTest()
    {
        if (!RUN_UNIT_TEST_ON_START) return;
        UnitTest.runUnitTest();
    }

}
