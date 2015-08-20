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
	// ��ǰ����ֵ ��һ��ͼƬѡ��
	private int mCurrentIndex = 0;
	// ������
	private Context context;
	// �����Ļ�����ֵ
	private float startX;
	private int mDisplayWidth;
	// �����ҽ���Ķ���
	private Animation mLeft2RightInAnimation;
	// �����ҳ�ȥ�Ķ���
	private Animation mLeft2RightOutAnimation;
	// ���ҵ������Ķ���
	private Animation mRight2LeftInAnimation;
	// ���ҵ����ȥ�Ķ���
	private Animation mRight2LeftOutAnimation;
	// ��������ʱ��
	private long duration = 1000;
	// ViewFlipper�ؼ��̳�FameLayout ͼƬ����
	private ViewFlipper mViewFlipper;
	/**
	 * ���涯̬���ɵ��tip������.
	 */
	private LinearLayout mTipLinearLayout;

	/**
	 * ��������Bitmap.
	 */
	private Bitmap mPointNorBitmap;

	/**
	 * ��ѡ�еĵ���Bitmap.
	 */
	private Bitmap mPointSelBitmap;
	/**
	 * ������ʾ���ͼƬ��ID
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

		// �����ͼƬ����ViewFlipper
		for (int i = 0; i < imgResId.length; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(imgResId[i]);
			mViewFlipper.addView(imageView);
		}

		// ����㶯̬����Linerlayout
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

		// ��ʼ������
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
			//�ж����һ����л�ͼƬ
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
