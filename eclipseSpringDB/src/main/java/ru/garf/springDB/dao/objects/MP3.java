package ru.garf.springDB.dao.objects;

public class MP3 {

	private int id;
	private String name;
	private String author;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("");
		str.append("id: ").append(id).append(";   Name: ").append(name).append(";   Author: ").append(author).append(";");
		return str.toString();
	}

}
