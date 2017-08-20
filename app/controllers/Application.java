package controllers;

import dao.impl.CommentDaoJpa;
import dao.interfaces.CommentDao;
import play.mvc.*;

import dao.impl.CatDaoJpa;
import dao.interfaces.CatDao;
import models.*;

public class Application extends Controller {

	public static CatDao catDao = new CatDaoJpa();
	public static CommentDao commentDao = new CommentDaoJpa();

    public static void loginPage() {
    	String error = flash.get("error");
        render(error);
    }
    
    public static void login(String catemail, String catpassword) {
    	if (catemail == null || catemail.isEmpty() || catpassword == null || catpassword.isEmpty()) {
    		flash.put("error", "Your password and email don't match");
    		loginPage();
    	}
    	Cat cat = catDao.getCatByEmail(catemail);
    	if (cat == null) {
    		flash.put("error", "Your cat is not registered");
    		loginPage();
    	}
    	if (cat.getPassword().equals(catpassword)) {
    		session.put("id", cat.getId());
    	} else {
    		flash.put("error", "Your password and email don't match");
    		loginPage();
    	}
    	PagesController.mainPage();
    }
    
    public static void registrationPage() {
    	String error = flash.get("error");
    	render(error);
    }
    
    public static void register(String catemail, String catname, String catpassword) {
    	if (catpassword == null || catpassword.length() < 8) {
    		flash.put("error", "Your password is too short");
    		registrationPage();
    	}
    	if (catemail == null || catemail.isEmpty() || catname == null || catname.isEmpty()) {
    		flash.put("error", "Your cat's mail and name cannot be empty");
    		registrationPage();
    	}
    	try {
			if (catDao.saveCat(new Cat(catemail, catname, catpassword))) {
				loginPage();
			} else {
	    		flash.put("error", "Your cat is already registered here with its email");
	    		registrationPage();
			}
		} catch (Exception e) {
    		flash.put("error", "Your cat is already registered here with its email");
    		registrationPage();
		}
    	loginPage();
    }
    
    public static void logout() {
    	session.clear();
    	loginPage();
    }
}