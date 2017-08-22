package dao.impl;

import java.util.List;

import dao.interfaces.CatDao;
import models.CatEntity;
import play.Logger;
import play.db.jpa.JPA;

public class CatDaoJpa implements CatDao {

	@Override
	public boolean saveCat(CatEntity cat) {
		try {
			JPA.em().merge(cat);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public CatEntity getCatById(String id) {
		if (id == null) {
			return null;
		}
		try {
			return JPA.em().createQuery("Select c from CatEntity c where c.id = :id", CatEntity.class).setParameter("id", Long.valueOf(id)).getSingleResult();
		} catch (Exception exception) {
			Logger.info("Nothing was found for id " + id);
		}
		return null;
	}

	@Override
	public CatEntity getCatByEmail(String email) {
		if (email == null) {
			return null;
		}
		try {
			return JPA.em().createQuery("Select c from CatEntity c where c.email = :email", CatEntity.class).setParameter("email", email).getSingleResult();
		} catch (Exception exception) {
			Logger.info("Nothing was found for id " + email);
		}
		return null;
	}

	@Override
	public List<CatEntity> getAllCats() {
		List<CatEntity> catEntities = JPA.em().createQuery("Select c from CatEntity c", CatEntity.class).getResultList();
		return catEntities;
	}
}
