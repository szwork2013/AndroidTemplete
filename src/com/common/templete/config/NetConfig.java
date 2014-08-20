﻿package com.common.templete.config;

/**
 * 
 * @描述：配置Url地址、应用下载地址（可选，根据需求）、图片上传的超时时间
 * @作者：liang bao xian
 * @时间：2014年8月6日 下午5:38:25
 */
public class NetConfig {
			
	/*************************************测试环境配置************************************/
	//接口地址
	public static final String DEBUG_BASE_URL = "http://218.106.153.197:8080/";
	public static final String DEBUG_INTRANET_BASE_URL = "http://192.168.1.107:8080/"; 
		
	/*************************************正式环境配置************************************/
	//接口地址
	public static final String RELEASE_BASE_URL = "";
	public static final String RELEASE_INTRANET_BASE_URL = "";
	
	/*************************************项目扩展地址************************************/
	public static final String EXTEND_URL = "client/";
	
	/*************************************应用下载地址************************************/
	public static final String APK_DOWNLOAD_URL = "http://www.189data.com/tss/d/city/0.do";
	
	/*************************************图片上传的超时时间************************************/
	public static final int UPLOAD_IMG_TIMEOUT = 20 * 1000;
	
	/*************************************HTTPS开关************************************/
	public static final boolean HTTPS_ENABLE = true;
	
	/**
	 * 获取服务器前缀地址+项目扩展地址
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getServerBaseUrl() {
		if(DebugConfig.VERSION_CONFIG == 0) {
			if(DebugConfig.NET_CONFIG == 0) {
				return DEBUG_INTRANET_BASE_URL;
			} else {
				return DEBUG_BASE_URL;
			}
		} else {
			if(DebugConfig.NET_CONFIG == 0) {
				return RELEASE_INTRANET_BASE_URL;
			} else {
				return RELEASE_BASE_URL;
			}
		}
	}
	
	/**
	 * 获取图片的URL地址
	 * @param url
	 * @return
	 */
	public static String getPictureUrl(String url){
		if(url == null)
		{
			return "";
		}
		if(url.indexOf("http://")!=-1 || url.indexOf("https://")!=-1){
			return url;
		}else{
			return getServerBaseUrl() + EXTEND_URL + url;
		}
	}
}
