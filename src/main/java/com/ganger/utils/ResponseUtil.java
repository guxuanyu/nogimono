package com.ganger.utils;

import com.ganger.VO.ResponseVo;
import com.ganger.enums.CodeEnum;

public class ResponseUtil {

	public static ResponseVo success(Object data) {
		ResponseVo responseEntity=new ResponseVo();
		responseEntity.setCode(CodeEnum.SUCCESS.getCode());
		responseEntity.setMessage(CodeEnum.SUCCESS.getMsg());
		responseEntity.setData(data);
		return responseEntity;
	}
	
	public static ResponseVo success() {
		return success(null);
	}
	public static ResponseVo error(String msg) {
		ResponseVo responseEntity=new ResponseVo();
		responseEntity.setCode(CodeEnum.ERROR.getCode());
		responseEntity.setMessage(msg);
		return responseEntity;
	}
}
