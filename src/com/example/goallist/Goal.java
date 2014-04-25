package com.example.goallist;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Goal implements Parcelable {

	String goalName;
	int numComplete;
	int numTotal;
	ArrayList<Task> goalTasks;

	public Goal(String rawGoal) {
		goalTasks = new ArrayList<Task>();
		parseGoal(rawGoal);
	}

	public Goal(Parcel in) {
		// TODO Auto-generated constructor stub
		goalName = in.readString();
		numComplete = in.readInt();
		numTotal = in.readInt();
		goalTasks = new ArrayList<Task>();
		in.readList(goalTasks, null);
	}

	/**
	 * data for saved goals will be passed to the app via a text file. Each line
	 * is one goal, and the goal data is parsed by a ';' character An example
	 * Goal data line is:
	 * <goalName>;<numComplete>;<numTotal>;task1;task2;...;taskN each taskN
	 * is a TaskItem object
	 * 
	 * All Goals in progress should have at least three strings to parse:
	 * <goalName>, <numIncomplet>, <numTotal>
	 * 
	 * If there are less than three the goal is a new goal and the first string
	 * is taken to be the goal name. The numComplete and numTotal then get
	 * initialized to 0;
	 * 
	 * @param rawGoal
	 */
	private void parseGoal(String rawGoal) {
		// TODO Auto-generated method stub
		String[] parseData = rawGoal.split(";");
		int parseSize = parseData.length;
		if (parseSize >= 3) {
			for (int i = 0; i < parseSize; i++) {
				switch (i) {
				// Set GoalName
				case 0:
					this.goalName = parseData[i];
					break;
				// Set numComplete
				case 1:
					this.numComplete = Integer.parseInt(parseData[i]);
					break;
				// Set numTotal
				case 2:
					this.numTotal = Integer.parseInt(parseData[i]);
					break;
				default:
					if (i >= 3) {
						Task task = new Task(parseData[i]);
						this.goalTasks.add(task);
					} else {
						System.out
								.println("this Should never happen Goal parse i<0");
					}

				}
			}
		} else {
			goalName = parseData[0];
			this.numComplete = 0;
			this.numTotal = 0;
		}
	}

	public void addTask(Task t){
		this.goalTasks.add(0, t);
		this.numTotal++;
	}
	
	public String getGoalName() {
		return this.goalName;
	}

	public int getNumComplete() {
		return this.numComplete;
	}

	public int getNumTotal() {
		return this.numTotal;
	}

	public ArrayList<Task> getGoalTasks() {
		return this.goalTasks;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(goalName);
		out.writeInt(numComplete);
		out.writeInt(numTotal);
		out.writeList(goalTasks);

	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Goal createFromParcel(Parcel in) {
			return new Goal(in);
		}

		public Goal[] newArray(int size) {
			return new Goal[size];
		}
	};

	public void removeTask(int i) {
		// TODO Auto-generated method stub
		if(getGoalTasks().get(i).getCompleteness()){
			numComplete--;
		}
		numTotal--;
		getGoalTasks().remove(i);
		
	}

	public void completeTask(int i) {
		// TODO Auto-generated method stub
		if(!getGoalTasks().get(i).getCompleteness()){
			numComplete++;
		}
		getGoalTasks().get(i).setCompleteness(true);
	}

}
