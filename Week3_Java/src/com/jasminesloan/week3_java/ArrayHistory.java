package com.jasminesloan.week3_java;

import java.util.ArrayList;
import java.util.Collections;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.jasminesloan.library.JSON;

public class ArrayHistory extends Activity{
	
	ListView _listView;
	Context _context;
	ArrayList<String> _history;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.listview);
		super.onCreate(savedInstanceState);
		_context = this;
		_history = getHistory();
		Collections.reverse(_history);
		_listView = (ListView) findViewById(R.id.listview);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, _history);
		_listView.setAdapter(arrayAdapter); 


}



@SuppressWarnings("unchecked")
private ArrayList<String> getHistory (){
	Object stored = JSON.readStringObject(_context, "historyArray", false);

	ArrayList<String> history;
	if (stored == null) {
		Log.i("HISTORY", "NO HISTORY FILE FOUND");
		history = new ArrayList<String>();
	} else {
		history = (ArrayList<String>) stored;
	}
	return history;
}

}
