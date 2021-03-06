/*
 * project  Week1_Java
 * 
 * package  com.jasminesloan.week1_java
 * 
 * author  Jasmine Sloan
 * 
 * date  Sep 3, 2013
 */
package com.jasminesloan.week1_java;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	LinearLayout layout;
	LinearLayout.LayoutParams params;
	TextView totals;
	TextView airTotal;
	TextView proTotal;
	EditText airEditText;
	EditText proEditText;
	boolean totalButton = true;
	int airResult;
	int proResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Main Layout
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        TextView textView = new TextView(this);
        textView.setText("How much for a new Apple Laptop " + getString(R.string.MacBookAir) + " and " 
        																+ getString(R.string.MacBookPro) + ".");
        textView.setTextSize(20.0f);
        textView.setTextColor(Color.BLACK);
        layout.addView(textView);

        airEditText = new EditText(this);
        airEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        airEditText.setHint("Enter Amount of MacBook Air's");

        Button airButton = new Button(this);
        airButton.setText("Air Total");
        airButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {				
				
				if (totalButton){
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(airEditText.getWindowToken(), 0);
				}else{
					airTotal.setText("code error");
				}

				int air = getResources().getInteger(R.integer.MacBookAir);

				//For Loop for amounts
				try {
					for (int i = 0; i < air; i++){

						int entry = Integer.parseInt(airEditText.getText().toString());

						airResult = (1*air)*entry;
						
						airTotal.setText("Total for MacBook Air: " + "$" + airResult + ".00");
						airTotal.setTextSize(15.0f); 
						airTotal.setTextColor(Color.MAGENTA);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        //Air Layout
        LinearLayout airLayout = new LinearLayout(this);
        airLayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        airLayout.setLayoutParams(params);
        
        airLayout.addView(airEditText);
        airLayout.addView(airButton);
        
        layout.addView(airLayout);

        proEditText = new EditText(this);
        proEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        proEditText.setHint("Enter Amount of MacBook Pro's");
        
        Button proButton = new Button(this);
        proButton.setText("Pro Total");
        proButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {				
				
				if (totalButton){
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(airEditText.getWindowToken(), 0);
				}else{
					proTotal.setText("code error");
				}

				int pro = getResources().getInteger(R.integer.MacbookPro);

				//For Loop for amounts
				try {
					for (int i = 0; i < pro; i++){

						int entry = Integer.parseInt(proEditText.getText().toString());

						proResult = (1*pro)*entry;
						
						proTotal.setText("Total for MacBook Pro: " + "$" + proResult + ".00");
						proTotal.setTextSize(15.0f); 
						proTotal.setTextColor(Color.MAGENTA);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        //Pro Layouts
        LinearLayout proLayout = new LinearLayout(this);
        proLayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        proLayout.setLayoutParams(params);
        
        proLayout.addView(proEditText);
        proLayout.addView(proButton);
        
        layout.addView(proLayout); 

        Button buttonTotal = new Button(this);
        buttonTotal.setText("Combined Total");
        buttonTotal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				totalsMethod();

			}
		});
        
        LinearLayout totalLayout = new LinearLayout(this);
        totalLayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        totalLayout.setLayoutParams(params);
        
        totalLayout.addView(buttonTotal);
        
        layout.addView(totalLayout); 

        airTotal = new TextView(this);
        layout.addView(airTotal);

        proTotal = new TextView(this);
        layout.addView(proTotal);

        totals = new TextView(this);
        layout.addView(totals);

        setContentView(layout);
    }
    
    
    public void totalsMethod(){
    	boolean airproTotal = true;

    	if (airproTotal){
    		totals.setText("Total is: " + "$" + (airResult + proResult) + ".00");
    		totals.setTextSize(15.0f); 
    		totals.setTextColor(Color.MAGENTA);
    	}else{
    		System.out.println("code error");
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
