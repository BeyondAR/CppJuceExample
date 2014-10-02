package com.juceexample.android;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

import com.juceexample.JniHelper;

/**
 * This is a helper class that allows the JNI code to access some native
 * functions for HTTP requests, get the device information or accessing files.
 */
public class JniHelperAndroid extends JniHelper
{
	private static JniHelperAndroid _this;
    private Context _context;
    
    public static void initialize (Context context)
    {
    	_this = new JniHelperAndroid(context);
    }
    
    public static JniHelperAndroid getInstance()
    {
    	return _this;
    }

    private JniHelperAndroid (Context context)
    {
    	super();
        _context = context.getApplicationContext();
    }
    
//==============================================================================
    public static class HTTPStream
    {
        public HTTPStream (HttpURLConnection connection_,
                           int[] statusCode, StringBuffer responseHeaders) throws IOException
        {
            connection = connection_;

            try
            {
                inputStream = new BufferedInputStream (connection.getInputStream());
            }
            catch (IOException e)
            {
                if (connection.getResponseCode() < org.apache.http.HttpStatus.SC_BAD_REQUEST)
                    throw e;
            }
            finally
            {
                statusCode[0] = connection.getResponseCode();
            }

            if (statusCode[0] >= org.apache.http.HttpStatus.SC_BAD_REQUEST)
                inputStream = connection.getErrorStream();
            else
                inputStream = connection.getInputStream();

            for (java.util.Map.Entry<String, java.util.List<String>> entry : connection.getHeaderFields().entrySet())
                if (entry.getKey() != null && entry.getValue() != null)
                    responseHeaders.append (entry.getKey() + ": "
                                             + android.text.TextUtils.join (",", entry.getValue()) + "\n");
        }

        public final void release()
        {
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {}

            connection.disconnect();
        }

        public final int read (byte[] buffer, int numBytes)
        {
            int num = 0;

            try
            {
                num = inputStream.read (buffer, 0, numBytes);
            }
            catch (IOException e)
            {}

            if (num > 0)
                position += num;

            return num;
        }

        public final long getPosition()                 { return position; }
        public final long getTotalLength()              { return -1; }
        public final boolean isExhausted()              { return false; }
        public final boolean setPosition (long newPos)  { return false; }

        private HttpURLConnection connection;
        private InputStream inputStream;
        private long position;
    }

    public static final HTTPStream createHTTPStream (String address,
                                                     boolean isPost, byte[] postData, String headers,
                                                     int timeOutMs, int[] statusCode,
                                                     StringBuffer responseHeaders)
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection) (new URL(address)
                    .openConnection());
            if (connection != null)
            {
                try
                {
                    if (isPost)
                    {
                        connection.setRequestMethod ("POST");
                        connection.setConnectTimeout (timeOutMs);
                        connection.setDoOutput (true);
                        connection.setChunkedStreamingMode (0);
                        OutputStream out = connection.getOutputStream();
                        out.write (postData);
                        out.flush();
                    }

                    return new HTTPStream (connection, statusCode, responseHeaders);
                }
                catch (Throwable e)
                {
                    connection.disconnect();
                }
            }
        }
        catch (Throwable e) {}

        return null;
    }

    public static final String getLocaleValue (boolean isRegion)
    {
        java.util.Locale locale = java.util.Locale.getDefault();

        return isRegion ? locale.getDisplayCountry (java.util.Locale.US) : locale
                .getDisplayLanguage (java.util.Locale.US);
    }

    // ==============================================================================
    private final class SingleMediaScanner implements MediaScannerConnectionClient
    {
        private MediaScannerConnection msc;
        private String file;
        
        public SingleMediaScanner (Context context, String filename)
        {
            file = filename;
            msc = new MediaScannerConnection (context, this);
            msc.connect();
        }

        @Override
        public void onMediaScannerConnected()
        {
            msc.scanFile (file, null);
        }

        @Override
        public void onScanCompleted (String path, Uri uri)
        {
            msc.disconnect();
        }
    }

    public final void scanFile (String filename)
    {
        new SingleMediaScanner (_context, filename);
    }
    // ==============================================================================
}
