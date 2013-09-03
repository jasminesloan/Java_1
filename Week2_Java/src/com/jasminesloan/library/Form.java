package com.jasminesloan.library;

import android.content.Context;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Form {
	
	//TextView
	public static TextView displayTextView(Context context, String textViewText){
		TextView textView = new TextView(context);
		textView.setText(textViewText);
		
		return textView;
	}
	
	//Button
	public static Button displayButton(Context context, String buttonText){
		Button button = new Button(context);
		button.setText(buttonText);
		
		return button;
	}
	
	// RadioGroup
	public static RadioGroup displayRadioGroup(Context context,
			String[] options) {
		RadioGroup radioGroup = new RadioGroup(context);

		try {
			for (int i = 0; i < options.length; i++) {
				RadioButton radioButton = new RadioButton(context);
				radioButton.setText(options[i]);
				radioButton.setId(i + 1);
				radioGroup.addView(radioButton);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return radioGroup;
	}

	// TextView
	public static TextView displayInfo(Context context){
		TextView displayInfoText = new TextView(context);

		return displayInfoText;
	}

}
