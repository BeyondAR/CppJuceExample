package com.juceexample.android;

import com.jps.cpp.util.CppSetupHelper;
import com.jps.cpp.util.DebugHelper;

import android.app.Application;

public class TestApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        CppSetupHelper.setNativeLibraryName("juceexample");
        CppSetupHelper.setUp();
        DebugHelper.prepareNativeCodeToBeDebugged (getApplicationContext());
//        try
//        {
//            // If you want to debug in genymotion you need this time out because the VM is too fast
//            Thread.sleep (1000);
//        } catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
        JniHelperAndroid.initialize(getApplicationContext());
    }
}
