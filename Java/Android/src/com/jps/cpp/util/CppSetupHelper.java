package com.jps.cpp.util;

import com.juceexample.android.LoggerAndroid;
import com.juceexample.util.log.Logger;

public class CppSetupHelper
{
    private static String _nativeLibraryName;
    
    private static boolean _isSetUp = false;
    private static Object _lock = new Object(); 

    /**
     * This methods loads the native library. <br>
     * If you want to debug the native code you will need to call this method in
     * your static constructor of the first class that is called, for instance: <br>
     * 
     * <pre>
     * {@code
     * public class MainActivity extends Activity {
     * 
     * 
     *  // ***************************** IMPORTANT!!!*****************************
     *  // The following lines are needed to allow GDB to load the native library as
     *  // soon as the application starts. Otherwise GDB will not load the library
     *  // and breakpoints will not work, making debugging a nightmare :(
     *   
     *  static {
     *      CppSetupHelper.loadNativeLibrary();
     *  }
     * 
     *  {@literal @}Override
     *  protected void onCreate (Bundle savedInstanceState) {
     *      // ...
     *  }
     * }
     * </pre>
     */
    private static void loadNativeLibrary()
    {
        try
        {
            Logger.i ("Loading c++ library");
            System.loadLibrary (_nativeLibraryName);
        } catch (UnsatisfiedLinkError e)
        {
            Logger.e ("Error loading the native library.", e);
        }
    }
    
    public static String getNativeLibraryName()
    {
    	return _nativeLibraryName;
    }
    
    public static void setNativeLibraryName (String libName)
    {
    	if (_nativeLibraryName != null) return;
    	_nativeLibraryName = libName;
    }
    
    /**
     * Call this method to load all platform specific classes.
     */
    public static void setUp()
    {
        if (!_isSetUp){
            synchronized (_lock)
            {
                if (!_isSetUp){
                    setUpPlatform();
                    loadNativeLibrary();
                    _isSetUp = true;
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    //                Place here your platform specific setup                  //
    /////////////////////////////////////////////////////////////////////////////
    
    /**
     * Use this method to specify the needed classes to work with the native platform
     */
    private static void setUpPlatform()
    {
    	Logger.log = new LoggerAndroid();
    }
    
}
