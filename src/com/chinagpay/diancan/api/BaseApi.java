package com.chinagpay.diancan.api;

import android.content.Context;

public abstract class BaseApi {
	protected Context context;
	protected ApiReturnResultListener returnResultListener;

	public BaseApi(Context context) {
		super();
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public ApiReturnResultListener getReturnResultListener() {
		return returnResultListener;
	}

	public void setReturnResultListener(
			ApiReturnResultListener returnResultListener) {
		this.returnResultListener = returnResultListener;
	}
}
