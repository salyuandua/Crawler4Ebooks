package org.crawler;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class App 
{
	public static final String  websiteRootURL="https://www.ebooks.com";
	public static final String rootURL="https://www.ebooks.com/en-us/";
	public static final String searchURL=rootURL+"searchapp/searchresults.net?"
			+ "searchResultOrder=Relevance&searchFilterFormatType=All&"
			+ "searchFilterDateMadeActive=All&pageNumber=%d";
	public static final String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
	//public static final String bookSearchURL=rootURL
	public static final int totalPage=20;//how many pages to extract
	
	private final BlockingQueue<Book> taskQ=new LinkedBlockingQueue<Book>();
	
	private static Logger loger=LogManager.getLogger(App.class);
	
	//private ExecutorService threadPool=Executors.newFixedThreadPool(4);
	
	private final AtomicBoolean completed=new AtomicBoolean(false);
	public void initSubTasks() {
		BookDetailExtracter bExtracter=new BookDetailExtracter(taskQ,completed);
		new Thread(bExtracter).start();
		new Thread(bExtracter).start();
	}
	
	public void excute() {
		initSubTasks();
		for(int pageNum=1;pageNum<=totalPage;pageNum++) {
			//parse one page
			String url=String.format(searchURL, pageNum);
			//loger.error("Page {} started... url is {}",pageNum,url);
			System.out.println("Page "+pageNum+" started...url is "+url);
			try {
				Document document= Jsoup.connect(url).
				userAgent(userAgent)
				.get();
				//System.out.println(document.toString());
				
				Elements booksEle= document.select("div .searchresult.tile.book-details");
				System.out.println("****size is "+booksEle.size());
				for(Element b:booksEle){
					
					String bookDetailURL=b.selectFirst("h2.title").selectFirst("a").attr("href");
					//System.out.println(bookDetailURL);
					Book book=new Book();
					book.setUrl(websiteRootURL+bookDetailURL);
					
					taskQ.add(book);
					
				}

				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//completed
		System.out.println("%%%%%%%%completed%%%%%%%%%%");
		completed.set(true);
		
		
		
	}
	
	
    public static void main( String[] args )
    {
    	System.out.println(Thread.currentThread().getName());
        App app=new App();
        app.excute();
        
    	
    	
    }
}
