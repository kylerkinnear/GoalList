package com.example.goallist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {

	GoalAdapter savedGoals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		savedGoals = readData("testGoals.txt");
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
				Intent intent = new Intent(MainActivity.this,GoalActivity.class);
				Goal goal = savedGoals.getGoalArrayList().get(position);
				intent.putExtra("parsableGoal", goal);
				startActivity(intent);
			}

		});
		// Intent intent = new Intent(MainActivity.this, GoalActivity.class);
		// intent.putExtra("parsableGoal", goal);
	}

	public void onButtonClick(View v) {

		switch (v.getId()) {
		case R.id.button_My_Goals:
		case R.id.button_My_Goals_Padding:

			Toast.makeText(this, "My Goals", Toast.LENGTH_SHORT).show();

			break;

		case R.id.button_Settings:
		case R.id.button_Settings_Padding:

			Toast.makeText(this, "Do Settings", Toast.LENGTH_SHORT).show();

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
}
