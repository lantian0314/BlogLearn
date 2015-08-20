package com.example.ViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.bloglearn.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPagerIteamView extends FrameLayout {

	/** 
     * ͼƬ��ImageView. 
     */  
    private ImageView mImageView;  
      
    /** 
     * ͼƬ���ֵ�TextView. 
     */  
    private TextView mNameTextView;  
      
    /** 
     * ͼƬ��Bitmap. 
     */  
    private Bitmap mBitmap;  
      
    /** 
     * Ҫ��ʾͼƬ��JSONOBject��. 
     */  
    private JSONObject mObject;  
    
	public ViewPagerIteamView(Context context) {
		super(context);
		initView();
	}

	public ViewPagerIteamView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}
	//��ʼ��View
	private void initView() {
		LayoutInflater inflater=LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.activity_main, null);
		mImageView=(ImageView) view.findViewById(R.id.album_imgview);
		mNameTextView=(TextView) view.findViewById(R.id.album_name);
		addView(view);
	}
	
	/**
	 * ������ݣ����ⲿ����
	 * @param object
	 */
	public void setData(JSONObject object){
		this.mObject=object;
		try {
			int resId=object.getInt("resId");
			String name=object.getString("name");
			
			mImageView.setImageResource(resId);
			mNameTextView.setText(name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ڴ���գ��ⲿ����
	 */
	public void recycle(){
		mImageView.setImageBitmap(null);
		if (this.mBitmap==null || this.mBitmap.isRecycled()) {
			return;
		}
		this.mBitmap.recycle();
		this.mBitmap=null;
	}
	/**
	 * ���¼����ⲿ�ĵ���
	 */
	public void reload(){
		try {
			int resId=mObject.getInt("resId");
			mImageView.setImageResource(resId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
