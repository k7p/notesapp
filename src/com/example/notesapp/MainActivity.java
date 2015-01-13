package com.example.notesapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	
	
	private final String TAG = "Main Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		

		
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.save:
			Log.i(TAG, "Save button clicked");

			EditText box = (EditText) findViewById(R.id.editText1);
			
			try {
				FileOutputStream fOut = openFileOutput("samplefile",
						Context.MODE_APPEND);
				PrintWriter pw = new PrintWriter(fOut);
				//OutputStreamWriter osw = new OutputStreamWriter(fOut);
				pw.write(box.getText().toString());
				pw.write("\n");
				pw.close();
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			box.setText("");
			Toast.makeText(getBaseContext(), "Note Saved", Toast.LENGTH_SHORT).show();
			
			
			
			

			return true;
		case R.id.go_to_notes:
			Log.i(TAG, "Save button clicked");
			Intent intent = new Intent(this, LibraryActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
