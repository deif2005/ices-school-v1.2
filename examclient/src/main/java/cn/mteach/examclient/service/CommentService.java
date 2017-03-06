package cn.mteach.examclient.service;

import cn.mteach.common.domain.question.Comment;
import cn.mteach.common.util.Page;

import java.util.List;


public interface CommentService {

	public List<Comment> getCommentByTypeAndReferId(int referType,int referId,int indexId,Page<Comment> page);
	
	public void addComment(Comment comment);
}
