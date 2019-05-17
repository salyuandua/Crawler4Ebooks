package org.crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BookDetailExtracter implements Runnable {

	private BlockingQueue<Book> q;

	private AtomicBoolean completed;

	public BookDetailExtracter(BlockingQueue<Book> q, AtomicBoolean completed) {
		this.q = q;
		this.completed = completed;
	}

	public void run() {
		System.out.println("+++++Thread " + Thread.currentThread().getName() + " get started+++++");

		while (!completed.get() || q.size() != 0) {
			// get url from q
			try {
				Book book = q.take();
				System.out.println("+++++Thread " + Thread.currentThread().getName() + " starting extract info from "
						+ book.getUrl() + "+++++");
				Document bookDetailDoc = Jsoup.connect(book.getUrl()).userAgent(App.userAgent).get();
				BookDetailPage bookDetailPage = new BookDetailPage(bookDetailDoc, book);
				bookDetailPage.parseBookInfo();
				bookDetailPage.persist();
				System.out.println("++++++Book "+bookDetailPage.getBook().getTitle()+" persisted++++++");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("+++++Thread " + Thread.currentThread().getName() + " stopping+++++");

	}

}
