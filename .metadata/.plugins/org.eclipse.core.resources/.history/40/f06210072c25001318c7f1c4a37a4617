package com.jasminesloan.library;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

public class Display extends GridLayout {
	TextView _textView;
	Context _context;
	GridLayout _gridLayout;
	
	public Display(Context context){
		super(context);
		_context = context;
		_gridLayout = this;
		
		this.setColumnCount(2);
		
		TextView textLabel = new TextView(_context);
		_textView = new TextView(_context);
		textLabel.setText("");
		
		this.addView(textLabel);
		this.addView(_textView);
	}
	
	public void setGridLayout(String label){
		_textView.setText(label);
	}
	
	public GridLayout getGridLayout(){
		return _gridLayout;
	}
}
