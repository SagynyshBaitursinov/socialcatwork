package controllers;

import java.util.List;

import dao.impl.CatDaoJpa;
import dao.interfaces.CatDao;
import models.Cat;
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
    	List<PhotoEntity> allPhotos = PhotoEntity.find("order by date desc").fetch(); 
		if (cat == null) {
			session.clear();
			Application.loginPage();
		}
		render(cat, allPhotos);
    }
    
    public static void all() {
    	List<Cat> cats = Application.catDao.getAllCats();
    	renderJSON(cats);
    }
    
    public static void uploadsPage() {
    	String error = flash.get("error");
    	String success = flash.get("success");
    	Cat cat = Application.catDao.getCatById(session.get("id"));
    	render(cat, error, success);
    }
}
