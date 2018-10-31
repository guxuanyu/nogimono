package com.ganger.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ganger.VO.CommentChildVO;
import com.ganger.VO.CommentHolderVO;
import com.ganger.VO.CommentUserVO;
import com.ganger.VO.CommentVO;
import com.ganger.VO.CommentVO2;
import com.ganger.VO.FloorVO;
import com.ganger.VO.UnreadReplyVO;
import com.ganger.entity.Comment;
import com.ganger.entity.User;
import com.ganger.exception.MyException;
import com.ganger.form.AddCommentForm;
import com.ganger.form.FloorForm;
import com.ganger.form.IdAndTokenForm;
import com.ganger.form.ReadedForm;
import com.ganger.repository.CommentRepository;
import com.ganger.repository.CommentSpecRepo;
import com.ganger.service.CommentService;
import com.ganger.utils.CheckUserUtil;
import com.ganger.utils.TimeUtil;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentRepository commentRepository;
	
//	@Autowired
//	CommentSpecRepo commentSpecRepo;
	
	@Autowired
	CheckUserUtil checkUserUtil;
	
	@Override
	public Comment getOne(Integer cid) {
		return null;
	}
	
	@Override
	@Transactional
	public void addOne(AddCommentForm addCommentForm) {
		checkUserUtil.checkUser(addCommentForm.getUid(), addCommentForm.getToken());
		Comment comment=new Comment();
		BeanUtils.copyProperties(addCommentForm, comment);
		comment.setIsreply(0);
		
		Integer floor=0;
		// 非嵌套评论
		if(addCommentForm.getFather()==null) {
			Page<Comment> last=commentRepository.findByFidAndFather(addCommentForm.getFid(),null,
					PageRequest.of(0, 1,Sort.by(Direction.DESC,"cid")));
			// 有评论的情况下获得评论楼层id，没有取0
			floor=CollectionUtils.isEmpty(last.getContent())?0:last.getContent().get(0).getFloor();
			
		}
		else {
			// 嵌套评论
			Page<Comment> last=commentRepository.findByFather(addCommentForm.getFather(), 
					PageRequest.of(0, 1,Sort.by(Direction.DESC,"cid")));
			Optional<Comment> fatherOptional=commentRepository.findById(addCommentForm.getFather());
			String noFloorErr="此楼已删除，无法评论";
			Comment father=fatherOptional.orElseThrow(()->new MyException(noFloorErr,"father_cid:["+addCommentForm.getFather()+"] "+noFloorErr));

			/**
			 * 判断是否都是当前文章下的评论
			 */
			if(father.getFid()-addCommentForm.getFid()!=0) {
				String err="此评论不属于此文章";
				throw new MyException(err, 
						"input_fid:["+addCommentForm.getFid()+"] father_fid:["+father.getFid()+"] "+err);
			}
			
			if(father.getFather()!=null) {
				String err="father参数不正确";
				throw new MyException(err, "father_cid:["+addCommentForm.getFather()+"] "+err);
			}
			
			User toUser=new User();
			if(addCommentForm.getTouid()==null) {
				toUser.setId(father.getUser().getId());
			}
			else {
				toUser.setId(addCommentForm.getTouid());
				comment.setIsreply(1);
			}
			comment.setToUser(toUser);
			
			floor=CollectionUtils.isEmpty(last.getContent())?0:last.getContent().get(0).getFloor();
			
		}
		
		User user=new User();
		user.setId(addCommentForm.getUid());
		comment.setUser(user);
		
		comment.setFather(addCommentForm.getFather());
		
		comment.setReaded(0);
		comment.setCid(0);
		comment.setFloor(floor+1);
		comment.setPost(new Timestamp(System.currentTimeMillis()));
		comment.setStatus(0);
		commentRepository.save(comment);
	}
	
	
	@Override
	@Transactional
	public CommentHolderVO<List<CommentVO2>> getComment(Integer fid,Integer page,Integer size) {
	
		Page<Comment> fathers=commentRepository.findByFidAndFatherIsNull(fid,PageRequest.of(page, size,Sort.by(Direction.DESC,"cid")));
		
		List<Integer> cids=new ArrayList<Integer>(fathers.getSize());
		List<CommentVO2> res=new ArrayList<>();
		for (Comment comment : fathers) {
			res.add(new CommentVO2(comment));
			cids.add(comment.getCid());
		}
		//System.out.println(cids);
		List<Comment> childs=commentRepository.findByFatherIn(cids);
		
		Map<Integer, List<CommentChildVO>> sonMap=new HashMap<>();
		
		for(Comment child:childs) {
			CommentChildVO childVO=new CommentChildVO(child);
			if(!sonMap.containsKey(child.getFather())) {
				List<CommentChildVO> childList=new ArrayList<CommentChildVO>();
				childList.add(childVO);
				sonMap.put(child.getFather(), childList);
			}
			else {
				sonMap.get(child.getFather()).add(childVO);
			}
		}
		for (CommentVO2 cVo : res) {
			if(sonMap.containsKey(cVo.getCid())) {
				List<CommentChildVO> childVOs=sonMap.get(cVo.getCid());
				List<CommentChildVO> sub;
				if(childVOs.size()>5) {
					cVo.setMore(1);
					sub=childVOs.subList(0, 5);
				}
				else {
					sub=childVOs;
					cVo.setMore(0);
				}
				cVo.setChildVOs(sub);
			}
		}
		return new CommentHolderVO<List<CommentVO2>>(fathers.hasNext()?1:0,res);
	}
	
	@Override
	@Transactional
	public List<CommentVO> getByFid(Integer fid) {
		PageRequest pageRequest=//new PageRequest(0, 10,new Sort(Sort.Direction.DESC,"post"));
				PageRequest.of(0, Integer.MAX_VALUE,Sort.by(Direction.DESC,"cid"));
		Page<Comment> cList=commentRepository.findByFid(fid,pageRequest);
		
		List<CommentVO> res=new LinkedList<>();
		Map<Integer, LinkedList<CommentChildVO>> sonMap=new HashMap<>();
		
//		Map<Integer, CommentVO> fatherMap=new HashMap<>();
//		int maxFather=0;
		
		for (Comment comment : cList.getContent()) {
			if(comment.getFather()!=null) {
				//是儿子
				CommentUserVO toUser=null;
				if(!comment.getIsreply().equals(0)) {
					//是楼中回复
					toUser=new CommentUserVO(comment.getToUser().getId(), comment.getToUser().getNickname(),comment.getToUser().getAvatar());
				}
				
				//maxFather=Math.max(maxFather, comment.getFather());
				
				if(!sonMap.containsKey(comment.getFather())) {
					LinkedList<CommentChildVO> linked=new LinkedList<>();
					linked.addFirst(new CommentChildVO(
							new CommentUserVO(comment.getUser().getId(),comment.getUser().getNickname(),comment.getUser().getAvatar()),
							toUser,
							comment.getCid(),
							TimeUtil.showTime(comment.getPost()),
							comment.getFloor(),
							comment.getMessage()
							));
					sonMap.put(comment.getFather(), linked);
				}
				else {
					sonMap.get(comment.getFather()).addFirst(new CommentChildVO(
							new CommentUserVO(comment.getUser().getId(),comment.getUser().getNickname(),comment.getUser().getAvatar()),
							toUser,
							comment.getCid(),
							TimeUtil.showTime(comment.getPost()),
							comment.getFloor(),
							comment.getMessage()
							));
				}
			}
			else {
				//父亲
				CommentVO cVo=new CommentVO(comment.getCid(), 
						new CommentUserVO(comment.getUser().getId(),comment.getUser().getNickname(),comment.getUser().getAvatar()),
						TimeUtil.showTime(comment.getPost()),
						comment.getFloor(), 
						comment.getMessage(),
						new ArrayList<>());
				
				res.add(cVo);
				//
				//fatherMap.put(comment.getFloor(),cVo);
			}
		}
		
		for (CommentVO item : res) {
			if(sonMap.containsKey(item.getCid())) {
				item.setChildVOs(sonMap.get(item.getCid()));
			}
		}
//		for(int i=1;i<=maxFather;i++) {
//			if(fatherMap.containsKey(i)) {
//				if(sonMap.containsKey(fatherMap.get(i).getCid())) {
//					fatherMap.get(i).setChildVOs(sonMap.get(fatherMap.get(i)));
//				}
//				res.add(fatherMap.get(i));
//			}
//			else {
//				
//			}
//		}
		return res;
	}
	
	
	@Override
	@Transactional
	public List<UnreadReplyVO> findByToUserAndReaded(IdAndTokenForm idAndTokenForm) {
		checkUserUtil.checkUser(idAndTokenForm.getUid(), idAndTokenForm.getToken());
		User user=new User();
		user.setId(idAndTokenForm.getUid());
		List<Comment> cList=commentRepository.findByToUserAndReaded(user, 0);
		//List<Comment> rList=cList.stream().map(c-> c.readed() ).collect(Collectors.toList());
		//commentRepository.saveAll(rList);
		List<UnreadReplyVO> res=cList
				.stream()
				.filter(c->!c.getToUser().getId().equals(c.getUser().getId()))
				.map(c -> 
				new UnreadReplyVO(
						new CommentUserVO(c.getUser().getId(),c.getUser().getNickname(),c.getUser().getAvatar()),
						c.getCid(),
						c.getFid(), TimeUtil.showTime(c.getPost()), c.getMessage(),c.getFather()))
				.collect(Collectors.toList());
				
		return res;
	}
	
	@Override
	@Transactional
	public void setReaded(ReadedForm readedForm) {
		checkUserUtil.checkUser(readedForm.getUid(), readedForm.getToken());
		
		
		//全部已读
		if(readedForm.getCid().equals("all")) {
			User user=new User();
			user.setId(readedForm.getUid());
			List<Comment> cList=commentRepository.findByToUserAndReaded(user, 0);
			List<Comment> rList=cList.stream().map(c-> c.readed() ).collect(Collectors.toList());
			commentRepository.saveAll(rList);
			return;
		}
		
		//单条已读
		Integer cid=Integer.valueOf(readedForm.getCid());
		
		Optional<Comment> commentOptional=commentRepository.findById(cid);
		String noCommentErr="此评论已经删除";
		Comment comment=commentOptional.orElseThrow(()->new MyException(noCommentErr, "input_cid:["+readedForm.getCid()+"] "+noCommentErr));
		if(!comment.getToUser().getId().equals(readedForm.getUid())) {
			String err="用户不匹配";
			throw new MyException(err, "input_uid:["+readedForm.getUid()+"] cid_uid:["+comment.getToUser().getId()+"] "+err);
		}
		commentRepository.save(comment.readed());
	}
	
	@Override
	@Transactional
	public void deleteComment(ReadedForm readedForm) {
		checkUserUtil.checkUser(readedForm.getUid(), readedForm.getToken());
		
		Integer cid=Integer.valueOf(readedForm.getCid());
		Optional<Comment> commentOptional=commentRepository.findById(cid);
		String noCommentErr="没有这条评论了";
		Comment comment=commentOptional.orElseThrow(()->new MyException(noCommentErr, "input_cid:["+readedForm.getCid()+"] "+noCommentErr));
		if(!comment.getUser().getId().equals(readedForm.getUid())) {
			String err="您只能删除自己的评论";
			throw new MyException(err, "input_uid:["+readedForm.getUid()+"] cid_uid:["+comment.getUser().getId()+"] "+err);
		}
		commentRepository.deleteById(comment.getCid());
	}
	
	@Override
	public FloorVO getFloorAndChile(FloorForm floorForm) {
		
		Optional<Comment> commentOptional=commentRepository.findById(floorForm.getCid());
		String noCommentErr="没有这条评论了";
		Comment father=commentOptional.orElseThrow(()->new MyException(noCommentErr, "input_cid:["+floorForm.getCid()+"] "+noCommentErr));
		
		PageRequest pageRequest=
				PageRequest.of(0, Integer.MAX_VALUE,Sort.by(Direction.ASC,"cid"));
		Page<Comment> childs=commentRepository.findByFather(floorForm.getCid(), pageRequest);
		
		List<CommentChildVO> commentChildVOs=
				childs.getContent().stream()
				.map(c->new CommentChildVO(c))
				.collect(Collectors.toList());
		
		CommentVO fatherVO=new CommentVO(father);
		fatherVO.setChildVOs(commentChildVOs);
		
		return new FloorVO(father.getFid(),fatherVO);
	}
}
