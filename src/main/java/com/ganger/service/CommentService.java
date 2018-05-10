package com.ganger.service;

import java.util.List;

import com.ganger.VO.CommentVO;
import com.ganger.VO.FloorVO;
import com.ganger.VO.UnreadReplyVO;
import com.ganger.entity.Comment;
import com.ganger.form.AddCommentForm;
import com.ganger.form.FloorForm;
import com.ganger.form.IdAndTokenForm;
import com.ganger.form.ReadedForm;

public interface CommentService {

	Comment getOne(Integer cid);
	
	void addOne(AddCommentForm addCommentForm);
	
	List<CommentVO> getByFid(Integer fid);
	
	List<UnreadReplyVO> findByToUserAndReaded(IdAndTokenForm idAndTokenForm);
	
	void setReaded(ReadedForm readedForm);
	
	void deleteComment(ReadedForm readedForm);
	
	FloorVO getFloorAndChile(FloorForm floorForm);
}
