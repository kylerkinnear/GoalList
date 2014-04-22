package com.example.goallist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;

public class TaskAdapter extends BaseAdapter {

	private Context whoCalledOnMe;
	ArrayList<Task> tasks;
	int numTasks;

	public TaskAdapter(Context c, ArrayList<Task> t) {
		whoCalledOnMe = c;
		tasks = new ArrayList<Task>();
		tasks = t;
		numTasks = tasks.size();
	}

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
	
	public void toggleSelection(int position){
		Toast.makeText(whoCalledOnMe, "My Goals", Toast.LENGTH_SHORT).show();
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
		convertView.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				boolean checked=convertView.isItemChecked(pos);
				friendsListView.setItemChecked(pos,!checked);
			}
		})
		
		return convertView;
	}
}
