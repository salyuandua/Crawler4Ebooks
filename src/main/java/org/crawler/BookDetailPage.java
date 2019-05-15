package org.crawler;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookDetailPage {

	private Document document;
	private Book book;

	public BookDetailPage(Document document, Book book) {
		this.document = document;
		this.book = book;
	}

	public Book parseBookInfo() {
		parseBookTitle().parseBookSubTitle().parseAuthors().parseDescription().parseImgUrl().parsePublisher()
				.parseCategories().parseISBN().parsePrice();
		return book;

	}

	/**
	 * Parse categories
	 * 
	 * @return
	 */
	public BookDetailPage parseCategories() {
		Element e = document.selectFirst("ul.subjectnav");
		Elements cataList = null;
		if (e != null && (cataList = e.select("li")) != null && cataList.size() != 0) {
			for (Element cata_li : cataList) {
				Elements cata_a_list = cata_li.select("a");
				if (cata_a_list != null) {
					for (Element cata_a : cata_a_list) {
						book.getCategories().add(cata_a.text());
					}

				}

			}

		}
		return this;
	}

	/**
	 * parse ISBN
	 * 
	 * @return
	 */
	public BookDetailPage parseISBN() {
		Element e = document.selectFirst("span[itemprop=isbn]");
		if (e != null) {
			book.setISBN(e.text());
		}
		return this;
	}

	/**
	 * Parse publisher
	 * 
	 * @return
	 */
	public BookDetailPage parsePublisher() {

		Element e = document.selectFirst("li[itemprop=publisher]");
		Element publisherEle = null;
		if (e != null && (publisherEle = e.selectFirst("a")) != null) {
			book.setPublisher(publisherEle.text());

		}

		return this;

	}

	/**
	 * parse price
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public BookDetailPage parsePrice() {
		try {
			Element e = document.selectFirst("div.price.center.h2 div.price");
			if (e != null) {
				String priceStr = e.text();
				priceStr = priceStr.substring(3);
				double price = Double.parseDouble(priceStr);
				book.setPrice(price);
			}
			// return this;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			return this;
		}

	}

	/**
	 * parse description html of book
	 * 
	 * @return
	 */
	public BookDetailPage parseDescription() {
		Element e = document.selectFirst("div[itemprop=description]");
		if (e != null) {
			book.setDescription(e.html());
			return this;
		}
		book.setDescription("<p></p>");
		return this;
	}

	/**
	 * Parse image url
	 * 
	 * @return
	 */

	public BookDetailPage parseImgUrl() {
		Element e = document.selectFirst("img[itemprop=image]");
		if (e != null) {
			book.setImageUrl(e.attr("src"));
			return this;
		}
		book.setImageUrl("");
		return this;

	}

	/**
	 * Parse authors of this book
	 * 
	 * @return
	 */
	public BookDetailPage parseAuthors() {
		Elements authors_a = document.select("h2.author a");
		book.setAuthors(new ArrayList<String>());
		if (authors_a != null && authors_a.size() != 0) {
			for (Element author : authors_a) {
				book.getAuthors().add(author.text());
			}

		}
		return this;
	}

	/**
	 * parse subtitle of this book
	 * 
	 * @return
	 */
	public BookDetailPage parseBookSubTitle() {
		Element e = document.selectFirst("h2.subtitle");
		if (e != null) {
			book.setSubTitle(e.text());
			return this;
		}
		book.setSubTitle("");
		return this;
	}

	/**
	 * parse title of this book
	 * 
	 * @return
	 */
	public BookDetailPage parseBookTitle() {
		Element e = document.selectFirst("h1[itemprop=name]");
		if (e != null) {
			book.setTitle(e.text());
			return this;
		}
		book.setTitle("");
		return this;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return book.toString();
	}
}
