package com.ganger.service;

import java.util.List;

import com.ganger.VO.CommentHolderVO;
import com.ganger.VO.CommentVO;
import com.ganger.VO.CommentVO2;
import com.ganger.VO.FloorVO;
import com.ganger.VO.UnreadReplyVO;
import com.ganger.entity.Comment;
import com.ganger.form.AddCommentForm;
import com.ganger.form.FloorForm;
import com.ganger.form.IdAndTokenForm;
import com.ganger.form.ReadedForm;

public interface CommentService {

	/**
	 * 获得单条评论
	 * @param cid
	 * @return
	 */
	Comment getOne(Integer cid);

	/**
	 * 添加一条评论
	 * @param addCommentForm
	 */
	void addOne(AddCommentForm addCommentForm);
	
	List<CommentVO> getByFid(Integer fid);
	
	List<UnreadReplyVO> findByToUserAndReaded(IdAndTokenForm idAndTokenForm);

	/**
	 * 设置评论已读
	 * @param readedForm
	 */
	void setReaded(ReadedForm readedForm);

	/**
	 * 删除评论
	 * @param readedForm
	 */
	void deleteComment(ReadedForm readedForm);

	/**
	 * 获得评论楼层和下属评论
	 * @param floorForm
	 * @return
	 */
	FloorVO getFloorAndChile(FloorForm floorForm);
	
	CommentHolderVO<List<CommentVO2>> getComment(Integer fid,Integer page,Integer size);
}
