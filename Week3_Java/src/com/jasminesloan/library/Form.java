package com.jasminesloan.library;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Form extends LinearLayout{
	EditText _firstName;
	EditText _lastName;
	Button _getButton;
	Button _randomButton;
	LinearLayout _layout;
	
	public Form(Context context, String hintString, String hintTwo, String buttonTextButton, String randomTextButton){
		super(context);
		
		LayoutParams params;
		@SuppressWarnings("unused")
		LayoutParams paramsTwo;
		
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		_firstName = new EditText(context);
		paramsTwo = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1.0f);
		_firstName.setHint(hintString);
		
		_lastName = new EditText(context);
		_lastName.setHint(hintTwo);
		
		_getButton = new Button(context);
		_getButton.setText(buttonTextButton);
		
		_randomButton = new Button(context);
		_randomButton.setText(randomTextButton);
		
		_layout = new LinearLayout(context);
		_layout.setLayoutParams(params);
		this.addView(_layout);
		this.addView(_getButton);
		this.addView(_randomButton);
		this.setOrientation(VERTICAL);
		this.setLayoutParams(params);
	}
	
	public EditText getFirstName(){
		return _firstName;
	}
	
	public EditText getLastName(){
		return _lastName;
	}
	
	public Button getButton(){
		return _getButton;
	}
	
	public Button getRandomButton(){
		return _randomButton;
	}
}
