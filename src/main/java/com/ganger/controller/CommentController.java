package com.ganger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ganger.VO.ResponseVo;
import com.ganger.form.AddCommentForm;
import com.ganger.form.FloorForm;
import com.ganger.form.IdAndTokenForm;
import com.ganger.form.ReadedForm;
import com.ganger.service.CommentService;
import com.ganger.utils.ResponseUtil;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/new")
	public ResponseVo addComment(AddCommentForm addCommentForm) {
		
		commentService.addOne(addCommentForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/get")
	public ResponseVo getCommentList(Integer fid) {
		return ResponseUtil.success(commentService.getByFid(fid));
	}
	
	@PostMapping("/unread")
	public ResponseVo getUnread(IdAndTokenForm idAndTokenForm) {
		return ResponseUtil.success(commentService.findByToUserAndReaded(idAndTokenForm));
	}
	
	@PostMapping("/read")
	public ResponseVo setRead(ReadedForm readedForm) {
		commentService.setReaded(readedForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/delete")
	public ResponseVo delete(ReadedForm readedForm) {
		commentService.deleteComment(readedForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/floor")
	public ResponseVo getFloor(FloorForm floorForm) {
		return ResponseUtil.success(commentService.getFloorAndChile(floorForm));
	}
}
