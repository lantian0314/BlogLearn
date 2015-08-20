package com.example.ViewFlipper;

import com.example.bloglearn.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class AdvserView extends FrameLayout {
	// 当前索引值 第一张图片选中
	private int mCurrentIndex = 0;
	// 上下文
	private Context context;
	// 点击屏幕的左边值
	private float startX;
	private int mDisplayWidth;
	// 从左到右进入的动画
	private Animation mLeft2RightInAnimation;
	// 从左到右出去的动画
	private Animation mLeft2RightOutAnimation;
	// 从右到左进入的动画
	private Animation mRight2LeftInAnimation;
	// 从右到左出去的动画
	private Animation mRight2LeftOutAnimation;
	// 动画播放时间
	private long duration = 1000;
	// ViewFlipper控件继承FameLayout 图片容器
	private ViewFlipper mViewFlipper;
	/**
	 * 下面动态生成点点tip的容器.
	 */
	private LinearLayout mTipLinearLayout;

	/**
	 * 正常点点的Bitmap.
	 */
	private Bitmap mPointNorBitmap;

	/**
	 * 被选中的点点的Bitmap.
	 */
	private Bitmap mPointSelBitmap;
	/**
	 * 这里显示广告图片的ID
	 * 
	 * @param context
	 */
	private int[] imgResId = { R.drawable.test1, R.drawable.test2,
			R.drawable.test3 };

	public AdvserView(Context context) {
		super(context);
		initView();
	}

	public AdvserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		context = getContext();
		mDisplayWidth = getResources().getDisplayMetrics().widthPixels;

		mViewFlipper = new ViewFlipper(context);
		mTipLinearLayout = new LinearLayout(context);

		mPointNorBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.nor);
		mPointSelBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.sel);

		// 将广告图片加入ViewFlipper
		for (int i = 0; i < imgResId.length; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(imgResId[i]);
			mViewFlipper.addView(imageView);
		}

		// 将点点动态加入Linerlayout
		for (int j = 0; j < imgResId.length; j++) {
			ImageView imageview = new ImageView(context);
			if (j == 0) {
				imageview.setImageBitmap(mPointSelBitmap);
			} else {
				imageview.setImageBitmap(mPointNorBitmap);
			}

			mTipLinearLayout.addView(imageview);
		}
		addView(mViewFlipper);
		addView(mTipLinearLayout);
		mTipLinearLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

		// 初始化动画
		mLeft2RightInAnimation = new TranslateAnimation(-mDisplayWidth, 0, 0, 0);
		mLeft2RightInAnimation.setDuration(duration);
		mLeft2RightOutAnimation = new TranslateAnimation(0, mDisplayWidth, 0, 0);
		mLeft2RightOutAnimation.setDuration(duration);

		mRight2LeftInAnimation = new TranslateAnimation(mDisplayWidth, 0, 0, 0);
		mRight2LeftInAnimation.setDuration(duration);
		mRight2LeftOutAnimation = new TranslateAnimation(0, -mDisplayWidth, 0,
				0);
		mRight2LeftOutAnimation.setDuration(duration);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX=event.getX();
			break;
		case MotionEvent.ACTION_UP:
			ImageView lastImageView=(ImageView) mTipLinearLayout.getChildAt(mCurrentIndex);
			//判断左右滑动切换图片
			if (event.getX()>startX) {
				mViewFlipper.setInAnimation(mLeft2RightInAnimation);
				mViewFlipper.setOutAnimation(mLeft2RightOutAnimation);
				mViewFlipper.showNext();
				mCurrentIndex++;
				if (mCurrentIndex>imgResId.length-1) {
					mCurrentIndex=0;
				}
			} else if (event.getX()<startX) {
				mViewFlipper.setInAnimation(mRight2LeftInAnimation);
				mViewFlipper.setOutAnimation(mRight2LeftOutAnimation);
				mViewFlipper.showPrevious();
				mCurrentIndex--;
				if (mCurrentIndex<0) {
					mCurrentIndex=imgResId.length-1;
				}
			}
			ImageView imageView=(ImageView) mTipLinearLayout.getChildAt(mCurrentIndex);
			imageView.setImageBitmap(mPointSelBitmap);
			lastImageView.setImageBitmap(mPointNorBitmap);
			break;
		default:
			break;
		}
		return true;
	}

}
