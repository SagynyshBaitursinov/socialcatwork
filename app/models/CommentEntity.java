package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity extends GenericModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_")
	private Long id;
	
	@Column(name="comment_", columnDefinition = "TEXT")
	private String comment;

	@Column(name="cat_id_")
	private Long cat;

	@Column(name="photo_id_")
	private Long photo;

	public CommentEntity() {
	}

	public CommentEntity(Long id, String comment, Long cat, Long photo) {
		this.id = id;
		this.comment = comment;
		this.cat = cat;
		this.photo = photo;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCat() {
		return cat;
	}

	public void setCat(Long cat) {
		this.cat = cat;
	}

	public Long getPhoto() {
		return photo;
	}

	public void setPhoto(Long photo) {
		this.photo = photo;
	}

	@Override
	public String toString(){
		return id + " " + comment + " " + cat + " " + photo + "\n";
	}
}
