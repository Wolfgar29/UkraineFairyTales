package com.fromwolfgar.entity;

public class ModelObject {
	
	private int ID;
	private String imageId;
	private String title;
	private String desc;
	private String about;
	
	 public ModelObject() { }

	public ModelObject(int ID, String imageId, String title, String desc) {
		this.setID(ID);
		this.imageId = imageId;
		this.title = title;
		this.desc = desc;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title + "\n" + desc;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	public static ModelObject selectedItem;
}
