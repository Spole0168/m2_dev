package com.shang.base.common;

import java.io.Serializable;

/**
 * Created by 13 on 17/6/26.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code; //0000-成功；“9999”-失败
    private String msg;		//说明
    private T data;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Result() {
		super();
		this.code = Constant.SUCCESS_RESULT_CODE;
		this.msg = Constant.SUCCESS_RESULT_MSG;
	}
	public Result(T data) {
		super();
		this.code = Constant.SUCCESS_RESULT_CODE;
		this.msg = Constant.SUCCESS_RESULT_MSG;
		this.data = data;
	}
	public Result(String msg) {
		super();
		this.code = Constant.FAILED_RESULT_CODE;
		this.msg = msg;
	}

}
