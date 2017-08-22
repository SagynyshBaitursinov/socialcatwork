package controllers;

import java.util.List;

import models.*;
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
		CatEntity cat = Application.catDao.getCatById(session.get("id"));
		if (cat == null) {
			session.clear();
			Application.loginPage();
		}
		List<PhotoEntity> allPhotoEntities = PhotoEntity.find("cat_id_ = " + cat.getId() + " order by date desc").fetch();

		for (PhotoEntity photoEntity : allPhotoEntities) {
            List<CommentEntity> comments = Application.commentDao.getCommentsByPhotoId(photoEntity.getId().toString());
            photoEntity.setComments(comments);
        }

		render(cat, allPhotoEntities);
    }

    public static void all() {
    	List<CatEntity> cats = Application.catDao.getAllCats();
    	List<CommentEntity> comments = Application.commentDao.getAllComments();
    	renderJSON(cats);
    	renderJSON(comments);
    }
    
    public static void uploadsPage() {
    	String error = flash.get("error");
    	String success = flash.get("success");
    	CatEntity cat = Application.catDao.getCatById(session.get("id"));
		render(cat, error, success);
    }
}
