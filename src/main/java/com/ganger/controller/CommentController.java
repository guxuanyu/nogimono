package com.ganger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(description = "评论接口")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/new")
	@ApiOperation(value = "添加评论", notes = "")
	public ResponseVo addComment(AddCommentForm addCommentForm) {
		
		commentService.addOne(addCommentForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/get")
	@ApiOperation(value = "评论列表", notes = "通过文章id获得")
	public ResponseVo getCommentList(Integer fid) {
		return ResponseUtil.success(commentService.getByFid(fid));
	}
	
	@PostMapping("/unread")
	@ApiOperation(value = "查看某用户所有未读信息列表", notes = "用户id和token确认用户")
	public ResponseVo getUnread(IdAndTokenForm idAndTokenForm) {
		return ResponseUtil.success(commentService.findByToUserAndReaded(idAndTokenForm));
	}
	
	@PostMapping("/read")
	@ApiOperation(value = "将某条评论设为已读", notes = "")
	public ResponseVo setRead(ReadedForm readedForm) {
		commentService.setReaded(readedForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/delete")
	@ApiOperation(value = "删除自己的某条评论", notes = "")
	public ResponseVo delete(ReadedForm readedForm) {
		commentService.deleteComment(readedForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/floor")
	@ApiOperation(value = "获取某层评论", notes = "")
	public ResponseVo getFloor(FloorForm floorForm) {
		return ResponseUtil.success(commentService.getFloorAndChile(floorForm));
	}
	
	@GetMapping("/{fid}/{page}/{size}")
	@ApiOperation(value = "查看文章评论，分页", notes = "")
	public ResponseVo test(@PathVariable("fid")Integer fid,@PathVariable("page")Integer page,@PathVariable("size")Integer size) {
		return ResponseUtil.success(commentService.getComment(fid,page,size));
	}
}
