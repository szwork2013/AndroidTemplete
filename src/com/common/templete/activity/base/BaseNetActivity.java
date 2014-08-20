package com.common.templete.activity.base;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.common.templete.app.TEMApp;

/**
 * 此类用于初始化网络请求队列和停止网络请求
 * @author liangbx
 *
 */
public class BaseNetActivity extends BaseExtraLayoutActivity{
	
	public RequestQueue mQueue;
	private String requestTag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		mQueue = TEMApp.getInstance().getRequestQueue();	
		requestTag = TEMApp.getInstance().getRequestTag();
	}
	
	@Override
	protected void onDestroy() {			
		if(mQueue != null)
			mQueue.cancelAll(requestTag);
		super.onDestroy();
	}
	
	/**
	 * 启动请求队列
	 * @param request
	 */
	@SuppressWarnings("rawtypes")
	public void startRequest(Request request) {
		request.setTag(requestTag);
		mQueue.add(request);
		mQueue.start();
	}
		
	/**
	 * 网络请求的错误处理
	 * @param respCode 响应码
	 * @param respMsg 错误信息 为null时不显示错误信息
	 */
	public void processError(int respCode, String respMsg) {
		
	}
}
