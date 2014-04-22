package com.example.goallist;

import java.io.Serializable;

public class Task implements Serializable{

	String taskName;
	Boolean complete;
	String taskDueDate;
	
	public Task(String rawTask) {
		taskDueDate=null;
		parseTask(rawTask);
	}
	
	public String getTaskName(){
		return this.taskName;
	}
	
	public Boolean getCompleteness(){
		return this.complete;
	}
	
	public String getTaskDueDate(){
		return this.taskDueDate;
	}

	private void parseTask(String rawTask) {
		String[] parseData = rawTask.split(",");
		int parseSize = parseData.length;
		
		//Assume parseSize is at least 2
		this.taskName=parseData[0];
		
		if(parseData[1].equals("c")){
			this.complete=true;
		}else if(parseData[1].equals("i")){
			this.complete=false;
		}
		
		if(parseSize==3){
			this.taskDueDate=parseData[2];
		}
		
	}


}
