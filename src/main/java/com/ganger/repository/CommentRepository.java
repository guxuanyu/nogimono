package com.ganger.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ganger.entity.Comment;
import com.ganger.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	Page<Comment> findByFid(Integer fid,Pageable pageable);
	
	Page<Comment> findByFidAndFatherIsNull(Integer fid,Pageable pageable);
	
	Page<Comment> findByFather(Integer father,Pageable pageable);
	
	Page<Comment> findByFidAndFather(Integer fid,Integer father,Pageable pageable);
	
	
	Comment findByCid(Integer cid);
	
	List<Comment> findByToUserAndReaded(User toUser,Integer readed);
	//Page<Comment> findByFidOrderByPost(Integer fid,Pageable pageable);
	
	//Page<Comment> findByFatherOrderByPost(Integer fid,Pageable pageable);
	
	List<Comment> findByFatherIn(List<Integer> cid);
}
