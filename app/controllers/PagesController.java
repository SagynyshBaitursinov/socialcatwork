package controllers;

import java.util.List;

import dao.impl.CatDaoJpa;
import dao.interfaces.CatDao;
import models.Cat;
import models.Comment;
import models.CommentEntity;
import models.PhotoEntity;
import play.mvc.Before;
import play.mvc.Controller;

public class PagesController extends Controller {

	@Before
	private static void checkSecure() {
		if (!session.contains("id")) {
    		Application.loginPage();
    	}
	}
	
    public static void mainPage() {
		Cat cat = Application.catDao.getCatById(session.get("id"));
		Comment comment = Application.commentDao.getCommentByCat(session.get("id"));
		if (cat == null) {
			session.clear();
			Application.loginPage();
		}
		List<PhotoEntity> allPhotos = PhotoEntity.find("order by date desc").fetch();
		List<CommentEntity> allComments = CommentEntity.find("order by id").fetch();
		render(cat, allPhotos);
		render(comment, allComments);
		render();
    }
    
    public static void all() {
    	List<Cat> cats = Application.catDao.getAllCats();
    	List<Comment> comments = Application.commentDao.getAllComments();
    	renderJSON(cats);
    	renderJSON(comments);
    }
    
    public static void uploadsPage() {
    	String error = flash.get("error");
    	String success = flash.get("success");
    	Cat cat = Application.catDao.getCatById(session.get("id"));
    	Comment comment = Application.commentDao.getCommentByCat(session.get("id"));
    	render(cat, error, success);
    	render(comment, error, success);
    }
}
