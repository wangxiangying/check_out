package com.chinagpay.zhpaysdk.api;

/**
 * 当api异步调用返回结果时调用此监听
 * 
 */
public interface ApiReturnResultListener {

	/**
	 * 请求成功时调用此方法
	 * 
	 * @param <T>
	 * @param apiResult
	 */
	<T> void onReturnSucceedResult(int requestCode, ApiResult<T> apiResult);

	/**
	 * 请求失败或发生错误调用此方法
	 * 
	 * @param <T>
	 * @param apiResult
	 */
	<T> void onReturnFailResult(int requestCode, ApiResult<T> apiResult);
	

	/**
	 * 加载进度
	 * @param requestCode
	 */
	<T> void onReturnLoading(int requestCode);

}
