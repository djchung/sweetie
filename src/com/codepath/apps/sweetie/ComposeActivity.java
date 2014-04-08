package com.codepath.apps.sweetie;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		EditText tweetCompose = (EditText) findViewById(R.id.etStatus);
		if (tweetCompose.requestFocus()) {
			showKeyboardForView(tweetCompose);
		}
	}
	
	private void showKeyboardForView(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	public void onSave(MenuItem mi) {
		EditText etStatus = (EditText) findViewById(R.id.etStatus);
		String status = etStatus.getText().toString();
		TwitterApp.getRestClient().postTweet(status, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject arg0) {
				finish();
			}
			
		});
	}

}
