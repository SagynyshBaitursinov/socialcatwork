package dao.interfaces;

import java.util.List;

import models.CatEntity;

public interface CatDao {

	public boolean saveCat(CatEntity cat) throws Exception;
	
	public CatEntity getCatById(String id);
	
	public CatEntity getCatByEmail(String email);

	public List<CatEntity> getAllCats();
}
