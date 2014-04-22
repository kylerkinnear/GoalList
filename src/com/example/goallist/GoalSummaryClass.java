package com.example.goallist;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.GridView;

public class GoalSummaryClass extends FragmentActivity{

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_goal_list);
		// Get GridView from xml
		GridView gridView = (GridView) findViewById(R.id.gridview);
 
		// Set Adapter for GridView
		gridView.setAdapter(new GoalAdapter(this));
	}
	
}
