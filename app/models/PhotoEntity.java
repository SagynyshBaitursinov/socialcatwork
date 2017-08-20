package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.GenericModel;

@Entity
@Table(name = "photos")
public class PhotoEntity extends GenericModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_")
	private Long id;
	
	@Column(name="title_")
	private String title;
	
	@Column(name="filename_")
	private String name;
	
	@Column(name="uploaded_date_")
	private Date date;

	@OneToMany(mappedBy="photo")
	private List<CommentEntity> comments;
	
	@ManyToOne
	@JoinColumn(name="cat_id_")
	private CatEntity cat;
	
	public CatEntity getCat() {
		return cat;
	}

	public void setCat(CatEntity cat) {
		this.cat = cat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStringDate() {
		return new SimpleDateFormat("dd-MM-yyyy hh:mm").format(this.date);
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}
}
