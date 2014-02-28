package com.paglubogngaraw.demohenry;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class LoadFileActivity extends Activity {
	
	String fileName;
	File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		
		setupActionBar();
		
		Intent intent = getIntent();
		fileName = intent.getExtras().getString("fileName");
		setTitle(fileName);
		
		file = new File(getFilesDir(),fileName);
		String fileContent = null;
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			fileContent = br.readLine();		
			TextView viewFile = (TextView) findViewById(R.id.textview_content);
			viewFile.setText(fileContent);
			br.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	private void uploadFile(){
		SharedPreferences sp = PreferenceManager.
		                getDefaultSharedPreferences(this);
		String urlString = sp.getString("prefServerPath",null);
		
		String Tag = "File Upload";
	    HttpURLConnection conn; 
		String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";      
        String filePath = getFilesDir() + "/" + fileName;
        
        try {

            FileInputStream fileInputStream = new FileInputStream(new File(filePath));

            // open a URL connection
            URL url = new URL(urlString);

            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();

            // Allow Inputs
            conn.setDoInput(true);

            // Allow Outputs
            conn.setDoOutput(true);

            // Don't use a cached copy.
            conn.setUseCaches(false);

            // Use a post method.
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: post-data; name=uploadedfile;filename="
                            + fileName + "" + lineEnd);
            dos.writeBytes(lineEnd);

            // create a buffer of maximum size
            int bytesAvailable = fileInputStream.available();
            //int maxBufferSize = 1000;
            int maxBufferSize = 1 * 1024 * 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bytesAvailable];

            // read file and write it into form...
            int bytesRead = fileInputStream.read(buffer, 0, bytesAvailable);
            while (bytesRead > 0) {
                dos.write(buffer, 0, bytesAvailable);
                bytesAvailable = fileInputStream.available();
                bytesAvailable = Math.min(bytesAvailable, bufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bytesAvailable);
            }

            // send multipart form data necesssary after file data...

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // close streams
            Toast.makeText(getApplicationContext(), "File is uploaded.", Toast.LENGTH_SHORT).show();
            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            Log.e(Tag, "error: " + ex.getMessage(), ex);
            new AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(ex.getMessage())
            .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { 
                    // do nothing
                }
            }).show();
        }

        catch (IOException ioe) {
            Log.e(Tag, "error: " + ioe.getMessage(), ioe);
            new AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(ioe.getMessage())
            .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { 
                    // do nothing
                }
            }).show();
        }
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load_file, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			case R.id.action_uploadFile:
				uploadFile();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}
