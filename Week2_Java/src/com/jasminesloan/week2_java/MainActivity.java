/*
 * project  Week2_Java
 * 
 * package  com.jasminesloan.week2_java
 * 
 * author  Jasmine Sloan
 * 
 * date  Sep 3, 2013
 */
package com.jasminesloan.week2_java;

import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jasminesloan.library.Form;
import com.jasminesloan.packages.Info;
import com.jasminesloan.packages.JSON;

public class MainActivity extends Activity {
	
	TextView textView;
	TextView infoText;
	
	RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout layout = new LinearLayout(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		layout.setLayoutParams(params);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		textView = Form.displayTextView(this, "Please choose a product " 
					+ getString(R.string.MacBookAir) + " or "
					+ getString(R.string.MacBookPro) + " to get more information"
					+ "\r\n");
		
		String[] productDetails = {Info.MAC.name(),
									Info.MACBOOKAIR.name(),
									Info.MACBOOKPRO.name(),
									Info.IPAD.name(),
									Info.MINI.name(),
									Info.IPOD.name(),
									Info.IPHONE.name()
									
		};
		
		radioGroup = Form.displayRadioGroup(this, productDetails);
		
		Button infoButton = Form.displayButton(this, "Product Details");
		infoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Get Data
				try{
					int infoId = radioGroup.getCheckedRadioButtonId();
					RadioButton checkedInfo = (RadioButton) findViewById(infoId);
					String selected = checkedInfo.getText().toString();
					infoText.setText(JSON.readJSON(selected));
					
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		//Display Data
		infoText = Form.displayInfo(this);
		infoText.setText("Please pick a product for detail");
		
		layout.addView(textView);
		layout.addView(radioGroup);
		layout.addView(infoButton);
		layout.addView(infoText);
		
		setContentView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
