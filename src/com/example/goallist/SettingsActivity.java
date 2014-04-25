package com.example.goallist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
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
		case R.id.button_Goal_Settings:
		case R.id.button_Goal_Settings_Padding:
			
			Button goalSettings = (Button) findViewById(R.id.button_Goal_Settings);
			Button goalSettingsPadding = (Button) findViewById(R.id.button_Goal_Settings_Padding);
			goalSettings.setBackgroundResource(R.drawable.imgbutton_goal_settings_clicked);
			goalSettingsPadding.setBackgroundResource(R.drawable.imgbutton_screen_settings_clicked);
			Intent intentGoalSettings = new Intent(SettingsActivity.this, MainActivity.class);
			startActivity(intentGoalSettings);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	
			break;

		case R.id.button_Notifications:
		case R.id.button_Notifications_Padding:

			Button notifications = (Button) findViewById(R.id.button_Notifications);
			Button notificationsPadding = (Button) findViewById(R.id.button_Notifications_Padding);
			notifications.setBackgroundResource(R.drawable.imgbutton_notifications_clicked);
			notificationsPadding.setBackgroundResource(R.drawable.imgbutton_screen_settings_clicked);
			Intent intentNotifications = new Intent(SettingsActivity.this, MainActivity.class);
			startActivity(intentNotifications);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	
			break;
			
		case R.id.button_Social_Media:
		case R.id.button_Social_Media_Padding:

			Button socialMedia = (Button) findViewById(R.id.button_Social_Media);
			Button socialMediaPadding = (Button) findViewById(R.id.button_Social_Media_Padding);
			socialMedia.setBackgroundResource(R.drawable.imgbutton_social_media_clicked);
			socialMediaPadding.setBackgroundResource(R.drawable.imgbutton_general_padding_clicked);
			Intent intentSocialMedia = new Intent(SettingsActivity.this, MainActivity.class);
			startActivity(intentSocialMedia);
			overridePendingTransition(R.anim.slideright, R.anim.slideleft);	
			break;
			
		case R.id.button_About:
		case R.id.button_About_Padding:

			Button about = (Button) findViewById(R.id.button_About);
			Button aboutPadding = (Button) findViewById(R.id.button_About_Padding);
			about.setBackgroundResource(R.drawable.imgbutton_about_clicked);
			aboutPadding.setBackgroundResource(R.drawable.imgbutton_screen_settings_clicked);
			Intent intentAbout = new Intent(SettingsActivity.this, MainActivity.class);
			startActivity(intentAbout);
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
		Button goalSettings = (Button) findViewById(R.id.button_Goal_Settings);
		Button goalSettingsPadding = (Button) findViewById(R.id.button_Goal_Settings_Padding);
		goalSettings.setBackgroundResource(R.drawable.imgbutton_goal_settings);
		goalSettingsPadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
		Button notifications = (Button) findViewById(R.id.button_Notifications);
		Button notificationsPadding = (Button) findViewById(R.id.button_Notifications_Padding);
		notifications.setBackgroundResource(R.drawable.imgbutton_notifications);
		notificationsPadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
		Button socialMedia = (Button) findViewById(R.id.button_Social_Media);
		Button socialMediaPadding = (Button) findViewById(R.id.button_Social_Media_Padding);
		socialMedia.setBackgroundResource(R.drawable.imgbutton_social_media);
		socialMediaPadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
		Button about = (Button) findViewById(R.id.button_About);
		Button aboutPadding = (Button) findViewById(R.id.button_About_Padding);
		about.setBackgroundResource(R.drawable.imgbutton_about);
		aboutPadding.setBackgroundResource(R.drawable.imgbutton_general_padding);
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    overridePendingTransition(R.anim.slideright, R.anim.slideleft);
	}
}
