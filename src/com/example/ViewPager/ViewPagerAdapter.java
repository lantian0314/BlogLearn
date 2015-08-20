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
	//����Դ  
	private JSONArray mJsonArray;
	//HashMap��������λ���Լ�IteamView
	private HashMap<Integer, ViewPagerIteamView> mHashMap;
	
	public ViewPagerAdapter(Context context,JSONArray arrays) {
		super();
		this.mContext=context;
		this.mJsonArray=arrays;
		mHashMap=new HashMap<Integer, ViewPagerIteamView>();
	}
	/**
	 * ������л��գ����һ���ʱ�������ڵ�ɾ����
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		ViewPagerIteamView iteamView=(ViewPagerIteamView) object;
		iteamView.recycle();
	}
	/**
	 * �������������
	 */
	@Override
	public int getCount() {
		return mJsonArray.length();
	}

	/**
	 * ��ʼ��ViewPagerIteamView,����Ѿ�����  �����¼���
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
