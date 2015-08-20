package com.example.ViewPager;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter {

	private Context mContext;
	//数据源  
	private JSONArray mJsonArray;
	//HashMap保存相册的位置以及IteamView
	private HashMap<Integer, ViewPagerIteamView> mHashMap;
	
	public ViewPagerAdapter(Context context,JSONArray arrays) {
		super();
		this.mContext=context;
		this.mJsonArray=arrays;
		mHashMap=new HashMap<Integer, ViewPagerIteamView>();
	}
	/**
	 * 这里进行回收，左右滑动时，把早期的删除掉
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		ViewPagerIteamView iteamView=(ViewPagerIteamView) object;
		iteamView.recycle();
	}
	/**
	 * 返回相册数据数
	 */
	@Override
	public int getCount() {
		return mJsonArray.length();
	}

	/**
	 * 初始化ViewPagerIteamView,如果已经存在  就重新加载
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		ViewPagerIteamView iteamView;
		if (mHashMap.containsKey(position)) {
			iteamView=mHashMap.get(position);
			iteamView.reload();
		} else {
			iteamView=new ViewPagerIteamView(mContext);
			try {
				JSONObject dataObject=(JSONObject) mJsonArray.get(position);
				iteamView.setData(dataObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mHashMap.put(position, iteamView);
			((ViewPager)container).addView(iteamView);
		}
		return iteamView;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

}
