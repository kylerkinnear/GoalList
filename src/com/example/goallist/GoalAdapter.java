package com.example.goallist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GoalAdapter extends BaseAdapter {

	private Context whoCalledOnMe;
	ArrayList<Goal> goals;
	int numGoals;

	public GoalAdapter(Context c) {
		whoCalledOnMe = c;
		goals = new ArrayList<Goal>();
		numGoals = 0;

	}

	public ArrayList<Goal>	getGoalArrayList(){
		return goals;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return numGoals;
	}

	@Override
	public Goal getItem(int index) {
		// TODO Auto-generated method stub

		return goals.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}



	static class ViewHolder{
		TextView goalName;
		TextView goalProgressText;
		ProgressBar goalProgressBar;
	}
	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(whoCalledOnMe).inflate(R.layout.view_holder_goal_item, null);

			holder = new ViewHolder();
			holder.goalName= (TextView) convertView.findViewById(R.id.holder_goal_name);
			holder.goalProgressText= (TextView) convertView.findViewById(R.id.holder_goal_progress_text);
			holder.goalProgressBar= (ProgressBar) convertView.findViewById(R.id.holder_goal_progress_bar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Goal nextItem=getItem(index);

		holder.goalName.setText(nextItem.getGoalName());
		String progressText = nextItem.getNumIncomplete()+"/"+nextItem.getNumComplete()+" complete";
		holder.goalProgressText.setText(progressText);

		int progress;
		if(nextItem.getNumComplete()!=0){
			double progressD= ((double)nextItem.getNumIncomplete())/((double)nextItem.getNumComplete())*100;
			progress =(int) progressD;
		}else{ 
			progress = 0;
		}
		holder.goalProgressBar.setProgress(progress);
		
		return convertView;
	}



	public void addGoal(Goal newGoal) {
		// TODO Auto-generated method stub
		goals.add(newGoal);
		numGoals++;
	}

}
