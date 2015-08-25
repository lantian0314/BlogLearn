package com.example.YouDao;

import com.example.bloglearn.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class YouDao extends Activity {
	
	private TextView youdao_edit;
	private Button youdao_sure;
	private Button youdao_cancel;
	private WebView  youdao_web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youdao);
		//获得几个控件
		youdao_edit = (TextView) findViewById(R.id.youdao_edit);
		youdao_sure = (Button) findViewById(R.id.youdao_sure);
		youdao_cancel=(Button) findViewById(R.id.youdao_cancel);
		youdao_web=(WebView) findViewById(R.id.youdao_web);
		
		youdao_sure.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				String strURL=youdao_edit.getText().toString().trim();
				if (strURL.length()==0) {
					  Toast.makeText(YouDao.this, "查询内容不能为空!", Toast.LENGTH_LONG)
		                .show();
				}else {
					//String strURl="http://dict.so.163.com/search?q="+strURL+"&keyfrom=dict.index";
					String strURl="http://www.baidu.com";
					youdao_web.loadUrl(strURl);
				}
			}
		});
		
		youdao_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				youdao_edit.setText("");
			}
		});
	}
}
