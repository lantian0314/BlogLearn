package com.example.ViewFlipper;

import android.app.Activity;
import android.os.Bundle;

public class ViewFilpperDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new AdvserView(this));
	}
}
