package dao.impl;

import dao.interfaces.CommentDao;
import models.CommentEntity;
import play.Logger;
import play.db.jpa.JPA;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoJpa implements CommentDao {

    @Override
    public boolean saveComment(CommentEntity comment) throws Exception {
        try {
            JPA.em().merge(comment);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<CommentEntity> getCommentByCat(String id) {
        if (id == null) {
            return null;
        }
        try {
            List<CommentEntity> commentEntities = JPA.em().createNativeQuery("Select * from comments c where c.cat_id_ = " + id, CommentEntity.class).getResultList();
            return commentEntities;
        } catch (Exception exception) {
            Logger.info("Nothing was found for id " + id);
        }
        return null;
    }

    @Override
    public List<CommentEntity> getCommentsByPhotoId(String id) {
        if (id == null) {
            return null;
        }
        try {
            List<CommentEntity> commentEntities = JPA.em().createNativeQuery("Select * from comments c where c.photo_id_ = " + id, CommentEntity.class).getResultList();
            return commentEntities;
        } catch (Exception exception) {
            Logger.info("Nothing was found for id " + id);
        }
        return null;
    }

    @Override
    public List<CommentEntity> getAllComments() {
        List<CommentEntity> commentEntities = JPA.em().createNativeQuery("Select * from comments", CommentEntity.class).getResultList();
        List<CommentEntity> result = new ArrayList<CommentEntity>();
        for (CommentEntity commentEntity: commentEntities) {
            result.add(commentEntity);
        }
        return result;
    }
}
