package com.example.Linkify;

import com.example.bloglearn.R;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewDemo extends Activity implements OnItemClickListener,
		OnItemSelectedListener {

	private TextView grid_text;
	private static final String[] grid_Res = { "lorem", "ipsum", "dolor",
			"sit", "amet", "hello", "me", "elit", "morbi", "vel", "ligula",
			"vitae", "arcu", "aliquet", "mollis", "etiam", "vel", "erat",
			"placerat", "ante", "hi", "sodales", "test", "augue", "purus" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		grid_text = (TextView) findViewById(R.id.grid_text);
		GridView gridView = (GridView) findViewById(R.id.grid_grid);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				grid_Res);
		// gridView.setAdapter(adapter);
		/*
		 * gridView.setAdapter(new FunnyLookingAdapter(getApplicationContext(),
		 * android.R.layout.simple_list_item_1, grid_Res));
		 */
		gridView.setAdapter(new Imageadapter(getApplicationContext()));
		gridView.setOnItemClickListener(this);
		gridView.setOnItemSelectedListener(this);
	}

	/**
	 * ��Ŀ����ļ����¼�
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		grid_text.setText("Clicked:" + grid_Res[arg2]);
	}

	/**
	 * ��Ŀѡ����¼�
	 */
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		grid_text.setText("Selected:" + grid_Res[arg2]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		grid_text.setText("");
	}

	/**
	 * �Զ���Arrayadapter
	 * 
	 * @author fengtao
	 * 
	 */
	public class FunnyLookingAdapter extends ArrayAdapter<String> {
		private Context mContext;
		private String[] theItems;

		public FunnyLookingAdapter(Context context, int resource, String[] items) {
			super(context, resource, items);
			this.mContext = context;
			this.theItems = items;
		};

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView lable = (TextView) convertView;
			if (null == convertView) {
				convertView = new TextView(mContext);
				lable = (TextView) convertView;
			}
			lable.setText(position + ":" + theItems[position]);
			return convertView;
		}
	}

	/************************* ImageAdapter **********************************************/

	public class Imageadapter extends BaseAdapter {
		private Context mContext;
		private int[] Imags = { R.drawable.ic_launcher, R.drawable.nor,
				R.drawable.sel, R.drawable.test1, R.drawable.test2,
				R.drawable.test3 };

		public Imageadapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {
			return Imags.length;
		}

		@SuppressWarnings("unused")
		@Override
		public View getView(int position, View conventView, ViewGroup arg2) {
			ImageView imageView = null;
			if (null == imageView) {
				imageView = new ImageView(mContext);
				// ����View��height��width��������֤����imageԭ���ĳߴ磬ÿ��ͼ�������ʺ����ָ���ĳߴ�
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				/*
				 * ImageView.ScaleType.CENTER ����ִ�����ű���
				 * ImageView.ScaleType.CENTER_CROP
				 * ������ͳһ����ͼƬ������ͼƬ�ĳߴ����������ͼƬ����ά����Ⱥ͸߶ȣ����ڻ������Ӧ����ͼά��
				 * ImageView.ScaleType.CENTER_INSIDE
				 * ������ͳһ����ͼƬ������ͼƬ�ĳߴ����������ͼƬ����ά����Ⱥ͸߶ȣ����ڻ�С����Ӧ����ͼά��
				 */
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) conventView;
			}
			imageView.setImageResource(Imags[position]);
			return imageView;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

	}
}
