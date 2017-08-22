package dao.interfaces;

import models.CommentEntity;

import java.util.List;

public interface CommentDao {

	public boolean saveComment(CommentEntity comment) throws Exception;
	
	public List<CommentEntity> getCommentByCat(String id);

	public List<CommentEntity> getCommentsByPhotoId(String id);

	public List<CommentEntity> getAllComments();
}
