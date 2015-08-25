package com.example.YouDao;

import com.example.bloglearn.R;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextDemo extends Activity{
	
	private AutoCompleteTextView actv;
	private static final String[] autoStrs=new String[]{"a","ab","abc","abcd","ba"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autocompletetext);
		
		actv=(AutoCompleteTextView) findViewById(R.id.autocompletetext);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,autoStrs);
		actv.setAdapter(adapter);
	}
}
