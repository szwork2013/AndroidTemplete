package com.common.templete.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import com.common.androidtemplete.R;
import com.common.templete.activity.base.BaseActivity;
import com.common.templete.adapter.DslvAdapter;
import com.common.templete.bean.DslvItem;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.DragSortListView.DropListener;
import com.mobeta.android.dslv.DragSortListView.RemoveListener;

/**
 * 
 * @描述：
 * @作者：liang bao xian
 * @时间：2014年8月13日 上午9:29:03
 */
public class DragSortListViewActivity extends BaseActivity implements DropListener, 
	RemoveListener{
	
	private DragSortListView mDslv;
	private DslvAdapter mDslvAdapter;
	private List<DslvItem> mDslvList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dslv);
		
		getIntentData();
		initView();
		test();
	}
	
	@Override
	public void getIntentData() {
		
	}

	@Override
	public void initView() {
		mDslv = (DragSortListView) findViewById(R.id.list);
		mDslv.setDropListener(this);
		mDslv.setRemoveListener(this);
		mDslv.setFloatViewManager(new MyDSController(mDslv));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		setDisplayHomeAsUpEnabled(true);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * 拖动位置后的回调
	 */
	@Override
	public void drop(int from, int to) {
		DslvItem dItem = mDslvList.get(from);
		mDslvList.remove(from);
		mDslvList.add(to, dItem);
		mDslvAdapter.notifyDataSetChanged();
	}
	
	/**
	 * 滑动删除后的回调
	 */
	@Override
	public void remove(int which) {
		mDslvList.remove(which);
		mDslvAdapter.notifyDataSetChanged();
	}
	
	private void test() {
		mDslvList = new ArrayList<DslvItem>();
		for(int i=1; i<=50; i++) {
			DslvItem dItem = new DslvItem();
			dItem.name = i + "";
			mDslvList.add(dItem);
		}
		
		mDslvAdapter = new DslvAdapter(this, mDslvList);
		mDslv.setAdapter(mDslvAdapter);
	}
	
	/**
	 * 
	 * @描述：自定义Controll
	 * @作者：liang bao xian
	 * @时间：2014年8月11日 上午9:13:52
	 */
	private class MyDSController extends DragSortController {
		
		private DragSortListView mDslv;
		
		public MyDSController(DragSortListView dslv) {
            super(dslv);
            setDragHandleId(R.id.drag_handle);
            mDslv = dslv;
		}
		
		@Override
        public View onCreateFloatView(int position) {
			View v = mDslvAdapter.getView(position, null, mDslv);
			v.setBackgroundColor(Color.DKGRAY);
			return v;
		}
		
		@Override
        public void onDestroyFloatView(View floatView) {
			
        }
		
		@Override
        public int startDragPosition(MotionEvent ev) {
            int res = super.dragHandleHitPosition(ev);
            int width = mDslv.getWidth();

            if ((int) ev.getX() < width / 3) {
                return res;
            } else {
                return DragSortController.MISS;
            }
        }
		
	}
}
