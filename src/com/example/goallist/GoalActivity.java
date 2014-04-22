package com.example.goallist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class GoalActivity extends Activity{

	ListView taskListView;
	
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goal);
		
		Goal goal = getIntent().getParcelableExtra("parsableGoal");
		TaskArrayAdapter savedTasks = new TaskArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,goal.getGoalTasks());
		ListView taskListView = (ListView) findViewById(R.id.taskListView);
		taskListView.setChoiceMode(taskListView.CHOICE_MODE_MULTIPLE);
		View view = getLayoutInflater().inflate(R.layout.task_footer_no_selections, null);
		taskListView.addFooterView(view);
		taskListView.setAdapter(savedTasks);
	}
	public void onButtonClick(View v) {
		ArrayList<Task> selectedTasks=new ArrayList<Task>();
		SparseBooleanArray checkedTasks=taskListView.getCheckedItemPositions();
		int checkedTasksCount = checkedTasks.size();
		switch (v.getId()) {
		case R.id.button_add_task:
		
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
}
