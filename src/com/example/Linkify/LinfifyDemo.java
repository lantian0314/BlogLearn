package com.example.Linkify;

import com.example.bloglearn.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class LinfifyDemo extends Activity {

	private TextView linkify_text;
	private EditText linkify_edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linkify);
		linkify_edit = (EditText) findViewById(R.id.linkify_edit);
		linkify_text = (TextView) findViewById(R.id.linkify_text);
		linkify_edit.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				linkify_text.setText(linkify_edit.getText());
				Linkify.addLinks(linkify_text, Linkify.WEB_URLS
						| Linkify.EMAIL_ADDRESSES | Linkify.PHONE_NUMBERS);
				return false;
			}
		});
	}
}
