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
     * 图片的ImageView. 
     */  
    private ImageView mImageView;  
      
    /** 
     * 图片名字的TextView. 
     */  
    private TextView mNameTextView;  
      
    /** 
     * 图片的Bitmap. 
     */  
    private Bitmap mBitmap;  
      
    /** 
     * 要显示图片的JSONOBject类. 
     */  
    private JSONObject mObject;  
    
	public ViewPagerIteamView(Context context) {
		super(context);
		initView();
	}

	public ViewPagerIteamView(Context context, AttributeSet attrs) {
		super(context,attrs);
	}
	//初始化View
	private void initView() {
		LayoutInflater inflater=LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.activity_main, null);
		mImageView=(ImageView) view.findViewById(R.id.album_imgview);
		mNameTextView=(TextView) view.findViewById(R.id.album_name);
		addView(view);
	}
	
	/**
	 * 填充数据，共外部调用
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
	 * 内存回收，外部调用
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
	 * 重新加载外部的调用
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
