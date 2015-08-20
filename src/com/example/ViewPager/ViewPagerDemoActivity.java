package com.example.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bloglearn.R;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class ViewPagerDemoActivity extends Activity {

	// 定义相册的数量
	private static final int count = 100;

	/**
	 * 相册的资源
	 */
	private static final int Photo_Res[] = { R.drawable.test1,
			R.drawable.test2, R.drawable.test3 };

	private ViewPager mViewPager;
	/**
	 * 适配器
	 */
	private ViewPagerAdapter mviewPagerAdapter;
	/**
	 * 数据源
	 */
	private JSONArray mJsonArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		mJsonArray=new JSONArray();
		for (int i = 0; i < count; i++) {
			JSONObject object=new JSONObject();
			try {
				object.put("resId", Photo_Res[i%Photo_Res.length]);
				object.put("name", "Photo"+i);
				mJsonArray.put(object);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mViewPager=(ViewPager) findViewById(R.id.viewpager);
		mviewPagerAdapter=new ViewPagerAdapter(this, mJsonArray);
		mViewPager.setAdapter(mviewPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
