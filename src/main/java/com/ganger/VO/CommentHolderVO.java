package com.ganger.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentHolderVO<T> {

	private int more;
	
	@JsonProperty("comment")
	private T data;

	public CommentHolderVO() {
	}

	public CommentHolderVO(int more, T data) {
		this.more = more;
		this.data = data;
	}

	public int getMore() {
		return more;
	}

	public void setMore(int more) {
		this.more = more;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	
}
