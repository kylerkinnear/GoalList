package com.example.goallist;

import java.io.Serializable;

public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String taskName;
	Boolean complete;
	String taskDueDate;
	Boolean selected;

	public Task(String rawTask) {
		taskDueDate = null;
		parseTask(rawTask);
		selected = false;
	}

	public Task(String name, String date) {
		// TODO Auto-generated constructor stub
		taskName=name;
		taskDueDate=date;
		complete=false;
		selected=false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean TF) {
		this.selected = TF;
	}
	
	public void setCompleteness(boolean TF) {
		this.complete=TF;
	}
	
	public String getTaskName() {
		return this.taskName;
	}

	public Boolean getCompleteness() {
		return this.complete;
	}

	public String getTaskDueDate() {
		return this.taskDueDate;
	}

	private void parseTask(String rawTask) {
		String[] parseData = rawTask.split(",");
		int parseSize = parseData.length;

		// Assume parseSize is at least 2
		this.taskName = parseData[0];
		
		if (parseData[1].equals("c")) {
			this.complete = true;
		} else if (parseData[1].equals("i")) {
			this.complete = false;
		}
		if (parseSize == 3) {
			this.taskDueDate = parseData[2];
		}

	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getTaskName());
		sb.append(",");
		if(getCompleteness()){
			sb.append("c");
		}else{
			sb.append("i");
		}
		sb.append(",");
		sb.append(getTaskDueDate());
		return sb.toString();
	}

}
