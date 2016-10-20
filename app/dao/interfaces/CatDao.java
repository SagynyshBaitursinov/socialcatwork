package dao.interfaces;

import java.util.List;

import models.Cat;

public interface CatDao {

	public boolean saveCat(Cat cat) throws Exception;
	
	public Cat getCatById(String id);
	
	public Cat getCatByEmail(String email);

	public List<Cat> getAllCats();
}
