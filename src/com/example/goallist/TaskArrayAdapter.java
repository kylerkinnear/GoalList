package com.example.goallist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskArrayAdapter extends ArrayAdapter<Task> {

	private Context whoCalledOnMe;
	ArrayList<Task> tasks;
	int numTasks;
	int resource;
	
	public TaskArrayAdapter(Context c, int r, ArrayList<Task> t) {
		super(c, r, t);
		whoCalledOnMe = c;
		tasks = new ArrayList<Task>();
		tasks = t;
		numTasks = tasks.size();
		resource=r;
		// TODO Auto-generated constructor stub
	}


	
//	//public TaskArrayAdapter(Context c,int resource, ArrayList<Task> t) {
//		whoCalledOnMe = c;
//		tasks = new ArrayList<Task>();
//		tasks = t;
//		numTasks = tasks.size();
//	}

	@Override
	public int getCount() {
		return numTasks;
	}

	@Override
	public Task getItem(int position) {
		return tasks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		TextView taskName;
		TextView taskDueDate;
		TextView completeness;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(whoCalledOnMe).inflate(
					R.layout.view_holder_task_item, null);

			holder = new ViewHolder();
			holder.taskName = (TextView) convertView
					.findViewById(R.id.holder_task_name);
			holder.taskDueDate = (TextView) convertView
					.findViewById(R.id.holder_task_due_date);
			holder.completeness = (TextView) convertView
					.findViewById(R.id.holder_completeness_flag);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Task nextItem = getItem(position);

		holder.taskName.setText(nextItem.getTaskName());
		if (nextItem.getTaskDueDate() != null) {
			holder.taskDueDate.setText(nextItem.getTaskDueDate());
		} else {
			holder.taskDueDate.setEnabled(false);
		}
		if (nextItem.getCompleteness()) {
			holder.completeness.setTextColor(whoCalledOnMe.getResources()
					.getColor(R.color.green_complete));
			holder.completeness.setText("C");
			
		} else {
			holder.completeness.setTextColor(whoCalledOnMe.getResources()
					.getColor(R.color.red_incomplete));
			holder.completeness.setText("I");
			
		}

		return convertView;
	}

}
