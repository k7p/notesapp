package com.example.notesapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LibraryActivity extends Activity {

	ArrayList<String> items;
	ArrayAdapter<String> itemsAdaptor;
	ListView lvItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library);
		// Show the Up button in the action bar.
		setupActionBar();
		lvItems = (ListView) findViewById(R.id.lvItems);

		items = new ArrayList();
		itemsAdaptor = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdaptor);

		try {

			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(

					openFileInput("samplefile")));

			String inputString;

			while ((inputString = inputReader.readLine()) != null) {

				items.add(inputString);

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		setupListViewListener();
	}

	private void setupListViewListener() {

		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long rowId) {

				items.remove(position);
				itemsAdaptor.notifyDataSetChanged();
				updateFile();
				return true;
			}

		});

	}

	private void updateFile() {

		FileOutputStream fos = null;
		try {
			fos = openFileOutput("samplefile", Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter pw = new PrintWriter(fos);

		for (String data : items) {
			pw.write(data);
			pw.write("\n");
		}

		pw.close();

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.library, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		AlertDialog.Builder builder = null;
		AlertDialog dialog = null;

		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;

		case R.id.instructions:

			// 1. Instantiate an AlertDialog.Builder with its constructor
			builder = new AlertDialog.Builder(this,
					AlertDialog.THEME_DEVICE_DEFAULT_DARK);

			// builder = new AlertDialog.Builder(new ContextThemeWrapper(this,
			// R.style.ListRow));
			// 2. Chain together various setter methods to set the dialog
			// characteristics
			builder.setMessage(R.string.instructions_message).setTitle(
					R.string.instructions_title);

			// 3. Get the AlertDialog from create()
			dialog = builder.create();

			dialog.show();

			return true;

		case R.id.about:

			// 1. Instantiate an AlertDialog.Builder with its constructor
			builder = new AlertDialog.Builder(this,
					AlertDialog.THEME_DEVICE_DEFAULT_DARK);

			// 2. Chain together various setter methods to set the dialog
			// characteristics
			builder.setMessage(R.string.about_message).setTitle(
					R.string.about_title);

			// 3. Get the AlertDialog from create()
			dialog = builder.create();

			dialog.show();

			return true;

		}
		return super.onOptionsItemSelected(item);
	}

}
