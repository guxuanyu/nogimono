package com.ganger.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ganger.entity.Comment;

public interface CommentSpecRepo extends JpaSpecificationExecutor<Comment>{

}
