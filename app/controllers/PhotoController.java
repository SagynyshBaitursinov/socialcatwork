package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dao.impl.CatDaoJpa;
import dao.interfaces.CatDao;
import models.Cat;
import models.CatEntity;
import models.PhotoEntity;
import play.Logger;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

public class PhotoController extends Controller {

	@Before
	private static void checkSecure() {
		if (!session.contains("id")) {
    		Application.loginPage();
    	}
	}
	
    public static void upload(File photo) {
    	if (photo == null) {
    		flash.put("error", "Photo cannot be empty");
    		PagesController.uploadsPage();
    	}
    	Cat cat = Application.catDao.getCatById(session.get("id"));
    	String title = request.params.get("title");
    	if (title == null || title.isEmpty()) {
    		title = "Empty";
    	}
    	PhotoEntity photoEntity = new PhotoEntity();
    	photoEntity.setTitle(title);
    	photoEntity.setDate(new Date());
    	photoEntity.setName(photo.getName());
    	photoEntity.setCat(new CatEntity(cat));
    	photoEntity.save();
        String destFolder = Play.configuration.getProperty("file.upload.path");
        String destFile = destFolder + File.separator + photoEntity.getId() + photo.getName();
        try {
			FileUtils.moveFile(photo, new File(destFile));
		} catch (IOException e) {
			Logger.info("Error with file uploading");
		}
        flash.put("success", "File was successfully uploaded");
		PagesController.uploadsPage();
    }
    
    public static void uploadsPage() {
    	Cat cat = Application.catDao.getCatById(session.get("id"));
    	render(cat);
    }
    
    public static void getPhoto(Long id) {
    	PhotoEntity photo = PhotoEntity.findById(id);
        String destFolder = Play.configuration.getProperty("file.upload.path");
        String destFile = destFolder + File.separator + photo.getId() + photo.getName();
    	renderBinary(new File(destFile));
    }
}
