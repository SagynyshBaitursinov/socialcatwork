package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.interfaces.CatDao;
import models.Cat;
import models.CatEntity;
import play.Logger;
import play.db.jpa.JPA;

public class CatDaoJpa implements CatDao {

	@Override
	public boolean saveCat(Cat cat) {
		try {
			JPA.em().merge(new CatEntity(cat));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Cat getCatById(String id) {
		if (id == null) {
			return null;
		}
		try {
			return new Cat(JPA.em().createQuery("Select c from CatEntity c where c.id = :id", CatEntity.class).setParameter("id", Long.valueOf(id)).getSingleResult());
		} catch (Exception exception) {
			Logger.info("Nothing was found for id " + id);
		}
		return null;
	}

	@Override
	public Cat getCatByEmail(String email) {
		if (email == null) {
			return null;
		}
		try {
			return new Cat(JPA.em().createQuery("Select c from CatEntity c where c.email = :email", CatEntity.class).setParameter("email", email).getSingleResult());
		} catch (Exception exception) {
			Logger.info("Nothing was found for id " + email);
		}
		return null;
	}

	@Override
	public List<Cat> getAllCats() {
		List<CatEntity> catEntities = JPA.em().createQuery("Select c from CatEntity c", CatEntity.class).getResultList();
		List<Cat> result = new ArrayList<Cat>();
		for (CatEntity catEntity: catEntities) {
			result.add(new Cat(catEntity));
		}
		return result;
	}
}
