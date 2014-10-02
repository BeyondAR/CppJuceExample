package com.juceexample.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jps.cpp.memory.ReferenceConuter;
import com.juceexample.R;
import com.juceexample.UnitTest;
import com.juceexample.UnitTest.UnitTestOutputListener;
import com.juceexample.foo.Foo;

public class MainActivity extends Activity implements OnClickListener, UnitTestOutputListener
{
    private Button _getCppTextButton;
    private TextView _version;
    private TextView _outputText;
    private StringBuffer _logMessages;
    private ScrollView _scrollView;
    
    // Cpp bindings objects
    private Foo _foo;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        _logMessages = new StringBuffer();

        _getCppTextButton = (Button) findViewById (R.id.getCppText);
        _version = (TextView) findViewById (R.id.versionText);
        _outputText = (TextView) findViewById (R.id.outputText);
        _scrollView = (ScrollView) findViewById (R.id.outputScroll);

        _getCppTextButton.setOnClickListener (this);
        _version.setText(JniHelperAndroid.getVersion());
        
    }

    @Override
    protected void onResume()
    {
    	_foo = new Foo();
        super.onResume();
    }
    
    @Override
    protected void onPause() {
    	_foo.destroy();
    	super.onPause();
    }

    @Override
    public void onClick (View v)
    {
        if (v == _getCppTextButton)
        {
        	addLogMessage(_foo.getString());
        	ReferenceConuter.cleanCppReferences();
        }
    }

    @Override
    public void onLogMessage (final String message)
    {
        addLogMessage (message);
    }

    @Override
    public void onTestStarted()
    {
        _logMessages.delete (0, _logMessages.length());
    }

    int counter = 0;
    @Override
    public void onTestFinished (boolean allTestsPassed, final String message)
    {
        addLogMessage (message);
        if (allTestsPassed && counter < 0){
            new Thread (new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Thread.sleep (100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    
                    UnitTest.runUnitTest (MainActivity.this);
                    
                }
            }).start();
            counter ++;
        }
    }

    private void addLogMessage (String message)
    {
        _logMessages.append (message);
        _logMessages.append ("\n");
        _scrollView.post (new Runnable()
        {
            @Override
            public void run()
            {
                _outputText.setText (_logMessages.toString());
                _scrollView.fullScroll (View.FOCUS_DOWN);
            }
        });
    }
}
