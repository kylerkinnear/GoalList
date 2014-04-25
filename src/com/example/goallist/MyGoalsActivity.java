package com.example.goallist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyGoalsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_goals);
		TextView txt = (TextView) findViewById(R.id.textview_My_Goals_Header_Top_Mid);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Super Retro M54.ttf");
		txt.setTypeface(font);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// Set Images
		setImages();
		
	}

	public void onButtonClick(View v) {

		switch (v.getId()) {
		case R.id.button_In_Progress:
		case R.id.button_In_Progress_Padding:
			
			Button inProgress = (Button) findViewById(R.id.button_In_Progress);
			Button inProgressPadding = (Button) findViewById(R.id.button_In_Progress_Padding);
			inProgress.setBackgroundResource(R.drawable.imgbutton_in_progress_clicked);
			inProgressPadding.setBackgroundResource(R.drawable.imgbutton_general_padding_clicked);
			Intent intentInProgress = new Intent(MyGoalsActivity.this,MainActivity.class);
			startActivity(intentInProgress);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	
			break;

		case R.id.button_Create_New:
		case R.id.button_Create_New_Padding:

			Button createNew = (Button) findViewById(R.id.button_Create_New);
			Button createNewPadding = (Button) findViewById(R.id.button_Create_New_Padding);
			createNew.setBackgroundResource(R.drawable.imgbutton_create_new_clicked);
			createNewPadding.setBackgroundResource(R.drawable.imgbutton_create_new_padding_clicked);
			Intent intentCreateNew = new Intent(MyGoalsActivity.this,MainActivity.class);
			startActivity(intentCreateNew);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	
			break;
			
		case R.id.button_Completed:
		case R.id.button_Completed_Padding:

			Button completed = (Button) findViewById(R.id.button_Completed);
			Button completedPadding = (Button) findViewById(R.id.button_Completed_Padding);
			completed.setBackgroundResource(R.drawable.imgbutton_completed_clicked);
			completedPadding.setBackgroundResource(R.drawable.imgbutton_general_padding_clicked);
			Intent intentCompleted = new Intent(MyGoalsActivity.this,MainActivity.class);
			startActivity(intentCompleted);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	
			break;
			
		case R.id.button_Remove:
		case R.id.button_Remove_Padding:

			Button remove = (Button) findViewById(R.id.button_Remove);
			Button removePadding = (Button) findViewById(R.id.button_Remove_Padding);
			remove.setBackgroundResource(R.drawable.imgbutton_remove_clicked);
			removePadding.setBackgroundResource(R.drawable.imgbutton_general_padding_clicked);
			Intent intentRemove = new Intent(MyGoalsActivity.this,MainActivity.class);
			startActivity(intentRemove);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	
			break;

		case R.id.button_Share:

			Toast.makeText(this, "SHARE", Toast.LENGTH_SHORT).show();

			break;

		default:

			Toast.makeText(this, "goal clicked", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void setImages() {
		Button inProgress = (Button) findViewById(R.id.button_In_Progress);
		Button inProgressPadding = (Button) findViewById(R.id.button_In_Progress_Padding);
		inProgress.setBackgroundResource(R.drawable.imgbutton_in_progress);
		inProgressPadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
		Button createNew = (Button) findViewById(R.id.button_Create_New);
		Button createNewPadding = (Button) findViewById(R.id.button_Create_New_Padding);
		createNew.setBackgroundResource(R.drawable.imgbutton_create_new);
		createNewPadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
		Button completed = (Button) findViewById(R.id.button_Completed);
		Button completedPadding = (Button) findViewById(R.id.button_Completed_Padding);
		completed.setBackgroundResource(R.drawable.imgbutton_completed);
		completedPadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
		Button remove = (Button) findViewById(R.id.button_Remove);
		Button removePadding = (Button) findViewById(R.id.button_Remove_Padding);
		remove.setBackgroundResource(R.drawable.imgbutton_remove);
		removePadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.slideright, R.anim.slideleft);
	}
	
}
