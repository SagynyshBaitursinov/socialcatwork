package models;

public class Comment {

	private Long id;

	private String comment;

	private Long cat_id;
	private Long photo_id;

	public Comment() {
		//
	}

	public Comment(String comment, Long cat_id, Long photo_id) {
		this.comment = comment;
		this.cat_id = cat_id;
		this.photo_id = photo_id;
	}

	public Comment(CommentEntity commentEntity) {
		this.id = commentEntity.getId();
		this.comment = comment;
		this.cat_id = cat_id;
		this.photo_id = photo_id;
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

	public Long getCat_id() {
		return cat_id;
	}

	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}

	public Long getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(Long photo_id) {
		this.photo_id = photo_id;
	}
}
