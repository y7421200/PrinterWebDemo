package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.document.DocumentInfo;


public class Response {
	private String success;
	private String msg;
	private List<DocumentInfo> data = new ArrayList<DocumentInfo>();
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<DocumentInfo> getData() {
		return data;
	}
	public void setData(List<DocumentInfo> data) {
		this.data = data;
	}
}
