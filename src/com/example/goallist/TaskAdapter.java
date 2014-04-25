package com.example.goallist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

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

	public int getNumSelected() {
		int count = 0;
		for (int i = 0; i < numTasks; i++) {
			if (this.tasks.get(i).isSelected()) {
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<Integer> getSelectedPositions(){
		ArrayList<Integer> positions=new ArrayList<Integer>();
		for (int i = 0; i < numTasks; i++){
			if (this.tasks.get(i).isSelected()){
				positions.add(i);
			}
		}
		return positions;
	}
	
	static class ViewHolder {
		CheckBox checkBox;
		TextView taskName;
		TextView taskDueDate;
		TextView completeness;
		Button removeButton;
		Button completeButton;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			view = LayoutInflater.from(whoCalledOnMe).inflate(
					R.layout.view_holder_task_item, null);
			final ViewHolder holder = new ViewHolder();
			holder.checkBox = (CheckBox) view.findViewById(R.id.task_check_box);
			holder.taskName = (TextView) view
					.findViewById(R.id.holder_task_name);
			holder.taskDueDate = (TextView) view
					.findViewById(R.id.holder_task_due_date);
			holder.completeness = (TextView) view
					.findViewById(R.id.holder_completeness_flag);
			holder.removeButton = (Button) view.findViewById(R.id.holder_remove_button);
			holder.completeButton = (Button) view.findViewById(R.id.holder_complete_button);
			holder.checkBox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Task element = (Task) holder.checkBox.getTag();
							element.setSelected(buttonView.isChecked());
						}
					});
			holder.removeButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((GoalActivity)whoCalledOnMe).onTaskButtonClicked(v,position);
				}
			});
			holder.completeButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((GoalActivity)whoCalledOnMe).onTaskButtonClicked(v,position);
				}
			});
			view.setTag(holder);
			holder.checkBox.setTag(tasks.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkBox.setTag(tasks.get(position));
		}
		Task nextItem = getItem(position);

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.checkBox.setChecked(tasks.get(position).isSelected());
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

		return view;
	}
	
}
