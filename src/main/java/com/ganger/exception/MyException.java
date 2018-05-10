package com.ganger.exception;

public class MyException extends RuntimeException{

	private String showmsg;
	
	private String logmsg;
	
	
	public MyException(String showmsg,String logmsg) {
		super(showmsg);
		this.showmsg=showmsg;
		this.logmsg=logmsg;
	}


	public String getShowmsg() {
		return showmsg;
	}


	public void setShowmsg(String showmsg) {
		this.showmsg = showmsg;
	}


	public String getLogmsg() {
		return logmsg;
	}


	public void setLogmsg(String logmsg) {
		this.logmsg = logmsg;
	}
	
	
}
