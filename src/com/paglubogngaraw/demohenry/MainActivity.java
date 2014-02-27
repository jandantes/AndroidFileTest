package com.paglubogngaraw.demohenry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.paglubogngaraw.demohenry.R;

public class MainActivity extends Activity {
	
	ArrayList<EditText> allText = new ArrayList<EditText>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		allText.add((EditText) findViewById(R.id.edittext_userId));
		allText.add((EditText) findViewById(R.id.edittext_firstPick));
		allText.add((EditText) findViewById(R.id.edittext_secondPick));
		allText.add((EditText) findViewById(R.id.edittext_thirdPick));
		allText.add((EditText) findViewById(R.id.edittext_amount));
		
		final Button saveButton = (Button) findViewById(R.id.button_submit);
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				StringBuilder sb = new StringBuilder();
				for(EditText s : allText){					
					if(sb.length() > 0)
						sb.append(",");
					sb.append(s.getText());
				}								
				String allItems = sb.toString();
				try{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd",java.util.Locale.getDefault());
					Date now = new Date();
					String fileName = formatter.format(now);	
					File heapFile = new File(getFilesDir(),fileName);
					String file = heapFile.getAbsolutePath();
					if (!heapFile.exists()) {
                        heapFile.createNewFile();
                        FileWriter heapFileWritter = new FileWriter(
                        		file, true);
                        BufferedWriter heapBufferWritter = new BufferedWriter(
                                heapFileWritter);
                        heapBufferWritter.write(allItems);
                        heapBufferWritter.close();
                    }else{
                        FileWriter heapFileWritter = new FileWriter(
                        		file, true);
                        BufferedWriter heapBufferWritter = new BufferedWriter(
                                heapFileWritter);
                        heapBufferWritter.write(allItems);
                        heapBufferWritter.close();              	
                    }			
					Toast.makeText(getApplicationContext(), R.string.message_sent, Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent(getBaseContext(), FilesActivity.class);
					//startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				LinearLayout layout = (LinearLayout) findViewById(R.id.container_form); 
				for (int i = 0, count = layout.getChildCount(); i < count; ++i) {
				    View view = layout.getChildAt(i);
				    if (view instanceof EditText) {
				        ((EditText)view).setText("");
				    }
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.action_viewFiles:
				Intent files = new Intent(getBaseContext(), FilesActivity.class);
				startActivity(files);
				break;
			case R.id.menu_settings:
				Intent settings = new Intent(getBaseContext(), SettingsActivity.class);
				startActivity(settings);
				break;
			default:
				break;
		}
		return true;
	}
	
}
