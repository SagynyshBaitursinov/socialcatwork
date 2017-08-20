package dao.impl;

import dao.interfaces.CommentDao;
import models.Comment;
import models.CommentEntity;
import play.Logger;
import play.db.jpa.JPA;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoJpa implements CommentDao {

    @Override
    public boolean saveComment(Comment comment) throws Exception {
        try {
            JPA.em().merge(new CommentEntity(comment));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Comment getCommentByCat(String id) {
        if (id == null) {
            return null;
        }
        try {
            return new Comment(JPA.em().createQuery("Select c from CommentEntity c where c.cat_id_ = :id", CommentEntity.class).setParameter("id", Long.valueOf(id)).getSingleResult());
        } catch (Exception exception) {
            Logger.info("Nothing was found for id " + id);
        }
        return null;
    }

    @Override
    public Comment getCommentByPhoto(String id) {
        if (id == null) {
            return null;
        }
        try {
            return new Comment(JPA.em().createQuery("Select c from CommentEntity c where c.photo_id_ = :id", CommentEntity.class).setParameter("id", Long.valueOf(id)).getSingleResult());
        } catch (Exception exception) {
            Logger.info("Nothing was found for id " + id);
        }
        return null;
    }

    @Override
    public List<Comment> getAllComments() {
        List<CommentEntity> catEntities = JPA.em().createQuery("Select c from CatEntity c", CommentEntity.class).getResultList();
        List<Comment> result = new ArrayList<Comment>();
        for (CommentEntity catEntity: catEntities) {
            result.add(new Comment(catEntity));
        }
        return result;
    }
}
