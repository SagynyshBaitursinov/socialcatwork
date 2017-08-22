package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cats")
public class CatEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_")
	private Long id;
	
	@Column(unique=true, name="email_")
	private String email;
	
	@Column(name="password_")
	private String password;

	@Column(name="name_")
	private String name;
	
	@OneToMany(mappedBy="cat")
	private List<PhotoEntity> photos;
	
	public CatEntity(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public List<PhotoEntity> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoEntity> photos) {
		this.photos = photos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
