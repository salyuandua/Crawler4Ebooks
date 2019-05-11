package org.crawler;

import java.util.List;

public class Book {

	private int id;
	
	private String url;
	
	private String title;
	
	private String subTitle;
	
	private double price;
	
	private List<String> authors;
	
	private String description;
	
	private String publisher;
	
	private String releaseDate;
	
	private List<String> ISBNs;
	
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<String> getISBNs() {
		return ISBNs;
	}

	public void setISBNs(List<String> iSBNs) {
		ISBNs = iSBNs;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", url=" + url + ", title=" + title + ", subTitle=" + subTitle + ", price=" + price
				+ ", authors=" + authors + ", description=" + description + ", publisher=" + publisher
				+ ", releaseDate=" + releaseDate + ", ISBNs=" + ISBNs + ", imageUrl=" + imageUrl + "]";
	}
	
	
	
	
}
