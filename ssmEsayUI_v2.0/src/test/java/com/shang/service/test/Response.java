package com.shang.service.test;

public class Response {
	private Header header;
	private ResParentBody body;
	
	private String srcJson;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public ResParentBody getBody() {
		return body;
	}
	public void setBody(ResParentBody body) {
		this.body = body;
	}
	public String getSrcJson() {
		return srcJson;
	}
	public void setSrcJson(String srcJson) {
		this.srcJson = srcJson;
	}
	
	
}
