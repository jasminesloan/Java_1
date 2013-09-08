package com.jasminesloan.week3_java;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.jasminesloan.library.Form;
import com.jasminesloan.library.Display;
import com.jasminesloan.library.JSON;
import com.jasminesloan.library.Network;



public class MainActivity extends Activity {

	Context _context;
	LinearLayout _mainLayout;
	Form _getForm;
	Display _display;
	Boolean _isConnected = false;
	HashMap<String, String> _history;
	LayoutParams _params;
	GridLayout _layoutGrid;
	TextView _viewText;
	String _stringFormat;
	String _StringId;
	String data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_context = this;
		_mainLayout = new LinearLayout(_context);
		_history = getHistory();
		_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		_getForm = new Form(_context, "", "", "", "");
		
		_viewText = new TextView(_context);
		_viewText.setText("Enter Name");
		_viewText.setLayoutParams(_params);
		
		EditText name = _getForm.getFirstName();
		name.setLayoutParams(_params);
		
		Button getButton = _getForm.getButton();
		getButton.setLayoutParams(_params);
		getButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getForm(_getForm.getFirstName().getText().toString(), _getForm.getLastName().getText().toString());
				
			}
		});
		
		Button random = _getForm.getRandomButton();
		random.setLayoutParams(_params);
		random.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getRandom();
				
			}

		});
		
		Button save = new Button(_context);
		save.setText("Save?");
		save.setLayoutParams(_params);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveForm();
			}
		});
		
		Button getData = new Button(_context);
		getData.setText("Get Saved Data?");
		getData.setLayoutParams(_params);
		
		getData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
				
			}
		});
		
		_isConnected = Network.getConnectionStatus(_context);
		if(_isConnected){
			Log.i("Network Connection", Network.getConnectionType(_context));
		}else{
			getButton.setClickable(false);
			random.setClickable(false);
			
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("");
			alert.setMessage("Try Again");
			alert.setCancelable(false);
			alert.setPositiveButton("", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					dialogInterface.cancel();
					}
			});
			
			alert.show();
		}
		
		_display = new Display(_context);
		_mainLayout.addView(_viewText);
		_mainLayout.addView(_getForm);
		_mainLayout.addView(_display);
		_mainLayout.addView(save);
		_mainLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(_mainLayout);
	}

	@SuppressWarnings("unchecked")
	private HashMap<String, String> getHistory() {
		Object stored = JSON.readStringObject(_context, "history", false);
		HashMap<String, String> history;
		if (stored == null){
			Log.i("History", "No History File Found");
			history = new HashMap<String, String>();
		}else{
			history = (HashMap<String, String>) stored;
		}
		return history;
	}
	
	private void getForm(String firstName, String lastName) {
		String baseURL = "http://api.icndb.com/jokes/random";
		String nameQuery = "?firstName="+firstName+"&amp;lastName="+lastName+"";
		
		URL finalURL;
		try {
			finalURL = new URL(baseURL+nameQuery);
			Log.i("Final URL", finalURL.toString());
			DataRequest dataRequest = new DataRequest();
			dataRequest.execute(finalURL);
		}catch (MalformedURLException e){
			Log.e("URL Error", "Error");
			finalURL = null;
		}
	}
	
	private void getRandom() {
		String baseURL = "http://api.icndb.com/jokes/random";
		URL finalURL;
		try {
			finalURL = new URL(baseURL);
			Log.i("Final URL", finalURL.toString());
			DataRequest dataRequest = new DataRequest();
			dataRequest.execute(finalURL);
		}catch (MalformedURLException e){
			Log.e("URL Error", "Error");
			finalURL = null;
		}
	}
	
	private class DataRequest extends AsyncTask<URL, Void, String>{
		@Override
		protected String doInBackground(URL... urls) {
			String response = "";
			for (URL url : urls) {
				response = Network.getURLStringResponse(url);
			}
			return response;
		}
		
		protected void onPostExecute(String result){
			Log.i("URL Response", result);
			try{
				JSONObject json = new JSONObject(result);
				JSONObject results = json.getJSONObject("value");
				String data = results.getString("joke");
				String _stringFormat = data.replaceAll("&quot;", "''");
				String _stringId = results.getString("id");
				_display.setGridLayout(_stringFormat);
				
			}catch (JSONException e){
				Log.e("JSON Error", e.toString());
			}
		}
	}

	private void saveForm() {
		_display.setGridLayout(_stringFormat);
		_history.put(_StringId, _stringFormat);
		JSON.storeObjectFile(_context, "history", _history, false);
		Log.i("Saved", "Data Saved");
	}
	
	private void getData() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
