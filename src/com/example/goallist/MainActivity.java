package com.example.goallist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {

	GoalAdapter savedGoals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		savedGoals = readUsersGoalsData(getResources().getString(
				R.string.user_goals_filename));
		if (savedGoals == null) {
			System.out.println("it Exists!");
			resetWithData();
		} else {
			System.out.println("it doesn't Exist");
			setContentView(R.layout.activity_main);
			String FILENAME = "testGoals.txt";
			savedGoals = readData(FILENAME);
			// Get GridView from xml
			GridView gridView = (GridView) findViewById(R.id.gridview);
			// Set Adapter for GridView
			gridView.setAdapter(savedGoals);

			gridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(getApplicationContext(),
							"Click ListItem Number " + position,
							Toast.LENGTH_LONG).show();
					Intent intent = new Intent(MainActivity.this,
							GoalActivity.class);
					Goal goal = savedGoals.getGoalArrayList().get(position);
					intent.putExtra("parsableGoal", goal);
					startActivity(intent);
					overridePendingTransition(R.anim.slideright, R.anim.slideleft);
				}

			});
			writeSavedGoals(FILENAME);
			// Intent intent = new Intent(MainActivity.this,
			// GoalActivity.class);
			// intent.putExtra("parsableGoal", goal);
		}

	}

	private void writeSavedGoals(String sourceFile) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();

		try {
			InputStream inSt = getResources().getAssets().open(sourceFile);

			if (inSt != null) {
				InputStreamReader inSR = new InputStreamReader(inSt);
				BufferedReader br = new BufferedReader(inSR);
				String nextLine = br.readLine();

				while (nextLine != null) {
					sb.append(nextLine);
					sb.append(System.getProperty("line.separator"));
					nextLine = br.readLine();
				}
				inSt.close();
			}
		} catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}
		String data = sb.toString();

		String FILENAME = getResources()
				.getString(R.string.user_goals_filename);
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);
			fos.write(data.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		Button myGoals = (Button) findViewById(R.id.button_My_Goals);
		Button myGoalsPadding = (Button) findViewById(R.id.button_My_Goals_Padding);
		myGoals.setBackgroundResource(R.drawable.imgbutton_mygoals);
		myGoalsPadding
				.setBackgroundResource(R.drawable.imgbutton_button_padding_mygoals);
		Button mySettings = (Button) findViewById(R.id.button_Settings);
		Button mySettingsPadding = (Button) findViewById(R.id.button_Settings_Padding);
		mySettings.setBackgroundResource(R.drawable.imgbutton_settings);
		mySettingsPadding
				.setBackgroundResource(R.drawable.imgbutton_button_padding_settings);
	}

public void onButtonClick(View v) {

		switch (v.getId()) {
		case R.id.button_My_Goals:
		case R.id.button_My_Goals_Padding:
			
			Button myGoals = (Button) findViewById(R.id.button_My_Goals);
			Button myGoalsPadding = (Button) findViewById(R.id.button_My_Goals_Padding);
			myGoals.setBackgroundResource(R.drawable.imgbutton_mygoals_clicked);
			myGoalsPadding.setBackgroundResource(R.drawable.imgbutton_button_padding_mygoals_clicked);
			Intent intentMyGoals = new Intent(MainActivity.this,MyGoalsActivity.class);
			startActivity(intentMyGoals);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);		

			break;

		case R.id.button_Settings:
		case R.id.button_Settings_Padding:

			Button mySettings = (Button) findViewById(R.id.button_Settings);
			Button mySettingsPadding = (Button) findViewById(R.id.button_Settings_Padding);
			mySettings.setBackgroundResource(R.drawable.imgbutton_settings_clicked);
			mySettingsPadding.setBackgroundResource(R.drawable.imgbutton_button_padding_settings_clicked);
			Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(intentSettings);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	

			break;

		case R.id.button_Share:

			Toast.makeText(this, "SHARE", Toast.LENGTH_SHORT).show();

			break;

		default:

			Toast.makeText(this, "goal clicked", Toast.LENGTH_SHORT).show();
		}
	}

	private GoalAdapter readData(String fileName) {
		// TODO Auto-generated method stub
		GoalAdapter savedGoals = new GoalAdapter(this);

		try {
			InputStream inSt = getResources().getAssets().open(fileName);

			if (inSt != null) {
				InputStreamReader inSR = new InputStreamReader(inSt);
				BufferedReader br = new BufferedReader(inSR);
				String nextLine = br.readLine();

				while (nextLine != null) {
					Goal nextGoal = new Goal(nextLine);
					savedGoals.addGoal(nextGoal);
					nextLine = br.readLine();
				}
				inSt.close();
			}
		} catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}
		return savedGoals;
	}

	protected void onRestart() {
		super.onRestart();
		resetWithData();
	}

	private void resetWithData() {
		// TODO Auto-generated method stub
		String FILENAME = getResources()
				.getString(R.string.user_goals_filename);

		savedGoals = readUsersGoalsData(FILENAME);
		// Get GridView from xml
		GridView gridView = (GridView) findViewById(R.id.gridview);
		// Set Adapter for GridView
		gridView.setAdapter(savedGoals);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + position, Toast.LENGTH_LONG)
						.show();
				Intent intent = new Intent(MainActivity.this,
						GoalActivity.class);
				Goal goal = savedGoals.getGoalArrayList().get(position);
				intent.putExtra("parsableGoal", goal);
				startActivity(intent);
			}

		});
	}

	private GoalAdapter readUsersGoalsData(String FILENAME) {
		GoalAdapter savedGoals = new GoalAdapter(this);

		try {
			FileInputStream fis = openFileInput(FILENAME);

			if (fis != null) {
				InputStreamReader inSR = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(inSR);
				String nextLine = br.readLine();
				while (nextLine != null) {
					Goal nextGoal = new Goal(nextLine);
					savedGoals.addGoal(nextGoal);
					nextLine = br.readLine();
				}
				fis.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedGoals;
	}
		@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.slideright, R.anim.slideleft);
	}
}
