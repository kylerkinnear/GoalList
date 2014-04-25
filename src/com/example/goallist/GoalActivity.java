package com.example.goallist;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class GoalActivity extends Activity {

	ListView taskListView;
	TaskAdapter savedTasks;
	Goal goal;
	EditText mEdit;
	EditText editMonth;
	EditText editDay;
	EditText editYear;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal);
	}

	protected void onStart() {
		super.onStart();
		// add initial fragment
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.replaceableFragment, new FragmentTaskManager());
		ft.commit();

		// add tasks to ListView
		goal = getIntent().getParcelableExtra("parsableGoal");
		savedTasks = new TaskAdapter(this, goal.getGoalTasks());
		ListView taskListView = (ListView) findViewById(R.id.taskListView);
		taskListView.setAdapter(savedTasks);
	}

	/**
	 * This method changes the header based on the number of checkboxes which
	 * are selected
	 * 
	 * @param v
	 */
	public void onCheckboxClicked(View v) {
		int numSelected = savedTasks.getNumSelected();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if (numSelected == 0) {
			ft.replace(R.id.replaceableFragment, new FragmentTaskManager());
		} else if (numSelected == 1) {
			ft.replace(R.id.replaceableFragment, new FragmentTaskOneSelected());
		} else {
			ft.replace(R.id.replaceableFragment,
					new FragmentTaskMultipleSelected());
		}
		ft.commit();
	}

	/**
	 * Controls the buttons in the viewHolder
	 */
	public void onTaskButtonClicked(View v, int position) {

		switch (v.getId()) {
		case R.id.holder_remove_button:
			System.out.println("MADE IT!");
			goal.removeTask(position);
			refreshActivity();
			Toast.makeText(this, "Remove Task", Toast.LENGTH_SHORT).show();
			break;
		case R.id.holder_complete_button:
			goal.completeTask(position);
			refreshActivity();
			Toast.makeText(this, "Complete Task", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	/**
	 * This method handles logic of button clicks
	 * 
	 * @param v
	 */
	public void onButtonClick(View v) {
		ArrayList<Integer> positions = savedTasks.getSelectedPositions();
		switch (v.getId()) {
		case R.id.button_add_task:
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.replaceableFragment, new FragmentTaskManager());
			ft.commit();
			break;
		case R.id.button_add_task_details:
		case R.id.button_task_manager_add:
			String taskName;
			EditText txt = (EditText) findViewById(R.id.edittext_Task_Manager_Task_Name);
			taskName = txt.getText().toString();
			if (taskName == null) {
				Toast.makeText(this, "Needs Task Name", Toast.LENGTH_SHORT)
						.show();
			} else {
				String month, day, year;
				
				editMonth = (EditText) findViewById(R.id.edittext_Task_Manager_Month);
				editDay = (EditText) findViewById(R.id.edittext_Task_Manager_Day);
				editYear = (EditText) findViewById(R.id.edittext_Task_Manager_Year);

				month = editMonth.getText().toString();
				day = editDay.getText().toString();
				year = editYear.getText().toString();

				String completeDate = month + "-" + day + "-" + year;
				Task t = new Task(taskName, completeDate);
				goal.addTask(t);
				refreshActivity();

			}
			break;
		case R.id.edit_task:
		case R.id.edit_tasks:
			Toast.makeText(this, "Edit Task", Toast.LENGTH_SHORT).show();
			break;
		case R.id.remove_task:
		case R.id.remove_tasks:
			for (int i = 0; i < positions.size(); i++) {
				goal.removeTask(positions.get(i));
			}
			refreshActivity();
			Toast.makeText(this, "Remove Task", Toast.LENGTH_SHORT).show();
			break;
		case R.id.complete_task:
		case R.id.complete_tasks:
			for (int i = 0; i < positions.size(); i++) {
				goal.completeTask(positions.get(i));
			}
			refreshActivity();
			Toast.makeText(this, "Complete Task", Toast.LENGTH_SHORT).show();
			break;
		default:
			Toast.makeText(this, "How Did You Get Here?", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void refreshActivity() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}

	/**
	 * Code for saving a Goal data is performed here
	 */
	protected void onPause() {
		super.onPause();

		// open old file and store lines in an ArrayList of Strings
		ArrayList<String> rawGoalStrings = new ArrayList<String>();
		String FILENAME = getResources()
				.getString(R.string.user_goals_filename);
		try {

			FileInputStream fis = openFileInput(FILENAME);

			if (fis != null) {
				InputStreamReader inSR = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(inSR);
				String nextLine = br.readLine();
				while (nextLine != null) {
					rawGoalStrings.add(nextLine);
					nextLine = br.readLine();
				}
				fis.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("GoalActivity onStop() trying to open file to write:",
					"File not found: " + e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("GoalActivity onStop() trying to open file to write:",
					"Can not read file: " + e.toString());
		}
		// create String that will represent the goal being stored in the text
		// file
		String newGoal;
		StringBuilder sb = new StringBuilder();
		sb.append(this.goal.getGoalName());
		sb.append(";");
		sb.append(this.goal.getNumComplete());
		sb.append(";");
		sb.append(this.goal.getNumTotal());

		// appendTasks
		ArrayList<Task> gt = goal.getGoalTasks();
		for (int i = 0; i < gt.size(); i++) {
			sb.append(";");
			sb.append(gt.get(i).toString());
		}
		newGoal = sb.toString();

		// find position of goal that needs to be changed with index then
		// replace that string with a new one that represents the changes to the
		// goal.
		Iterator<String> itr = rawGoalStrings.iterator();
		String nextGoalString;
		String nextGoalName;
		int index = 0;
		while (itr.hasNext()) {
			nextGoalString = itr.next();
			nextGoalName = parseGoalStringOnPause(nextGoalString);
			if (nextGoalName.equals(this.goal.getGoalName())) {
				rawGoalStrings.set(index, newGoal);
			}
			index++;
		}

		// append rawGoalStrings into one string with /n new line characters
		// separating the Goals
		String newUserGoals;
		Iterator<String> itr2 = rawGoalStrings.iterator();
		StringBuilder sb2 = new StringBuilder();
		int i = 0;
		while (itr2.hasNext()) {
			sb2.append(itr2.next());
			sb2.append(System.getProperty("line.separator"));
			i++;
		}
		newUserGoals = sb2.toString();

		// write to FILENAME
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0);
			fos.write(newUserGoals.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// the goal name is the first line
	private String parseGoalStringOnPause(String nextGoalString) {
		String[] parseData = nextGoalString.split(";");
		if (parseData.length >= 1) {
			return parseData[0];
		}
		return null;
	}

	public void selectDate(View view) {
		DialogFragment newFragment = new SelectDateFragment();
		newFragment.show(getFragmentManager(), "DatePicker");
	}

	public void populateSetDate(int year, int month, int day) {
		editMonth = (EditText) findViewById(R.id.edittext_Task_Manager_Month);
		editDay = (EditText) findViewById(R.id.edittext_Task_Manager_Day);
		editYear = (EditText) findViewById(R.id.edittext_Task_Manager_Year);
		String monthString = new DateFormatSymbols().getMonths()[month-1];
		editMonth.setText(monthString);
		editDay.setText(Integer.toString(day));
		editYear.setText(Integer.toString(year));
	}

	public class SelectDateFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}

		public void onDateSet(DatePicker view, int yy, int mm, int dd) {
			populateSetDate(yy, mm + 1, dd);
		}
	}

	public void onBackPressed(){
		super.onBackPressed();
		overridePendingTransition(R.anim.slideright, R.anim.slideleft);
	}
}
