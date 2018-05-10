package com.ganger.enums;

public enum CodeEnum {
	
	SUCCESS(200,"ok"),
	ERROR(1,"error")
	;
	
	int code;
	String msg;
	private CodeEnum(int code,String m) {
		// TODO Auto-generated constructor stub
		this.code=code;
		msg=m;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
