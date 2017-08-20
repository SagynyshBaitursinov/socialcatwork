package dao.interfaces;

import models.Cat;
import models.Comment;

import java.util.List;

public interface CommentDao {

	public boolean saveComment(Comment comment) throws Exception;
	
	public Comment getCommentByCat(String id);
	
	public Comment getCommentByPhoto(String email);

	public List<Comment> getAllComments();
}
