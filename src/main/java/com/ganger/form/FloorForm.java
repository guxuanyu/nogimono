package com.ganger.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="评论楼层表单")
public class FloorForm {

	@ApiModelProperty(value = "评论id")
	private Integer cid;

	public FloorForm(Integer cid) {
		this.cid = cid;
	}

	public FloorForm() {
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
}
