package system.design;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/*Implement a webpage CrawlLer to crawl webpages of http://www.wikipedia.org/. 
 * To simplify the question, let's use url instead of the the webpage content.

Your crawler should:

Call HtmlHelper.parseUrls(url) to get all urls from a webpage of given url.
Only crawl the webpage of wikipedia.
Do not crawl the same webpage twice.
Start from the homepage of wikipedia: http://www.wikipedia.org/
 
You need do it with multithreading.

Given
"http://www.wikipedia.org/": ["http://www.wikipedia.org/help/"]
"http://www.wikipedia.org/help/": []
Return ["http://www.wikipedia.org/", "http://www.wikipedia.org/help/"]

Given:
"http://www.wikipedia.org/": ["http://www.wikipedia.org/help/"]
"http://www.wikipedia.org/help/": ["http://www.wikipedia.org/", "http://www.wikipedia.org/about/"]
"http://www.wikipedia.org/about/": ["http://www.google.com/"]
Return ["http://www.wikipedia.org/", "http://www.wikipedia.org/help/", "http://www.wikipedia.org/about/"]
*/

// Version 1, use sleep()
class WebCrawler extends Thread {
	/**
	 * The optional capacity bound constructor argument serves as a way to
	 * prevent excessive queue expansion. The capacity, if unspecified, is equal
	 * to Integer.MAX_VALUE. Linked nodes are dynamically created upon each
	 * insertion unless this would bring the queue above capacity.
	 * 
	 * java.util.concurrent.BlockingQueue implementations are thread-safe. All
	 * queuing methods achieve their effects atomically using internal locks or
	 * other forms of concurrency control. However, the bulk Collection
	 * operations addAll, containsAll, retainAll and removeAll are not
	 * necessarily performed atomically unless specified otherwise in an
	 * implementation. So it is possible, for example, for addAll(c) to fail
	 * (throwing an exception) after adding only some of the elements in c
	 */
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private static HashSet<String> visited = new HashSet<String>();
	private static List<String> res = new ArrayList<String>();

	public static void setFirstUrl(String url) {
		try {
			// Inserts the specified element at the tail of this queue, waiting
			// if necessary for space to become available.
			queue.put(url);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	public static List<String> getResults() {
		return res;
	}

	@Override
	public void run() {
		while (true) {
			String url = "";
			try {
				// Retrieves and removes the head of this queue, waiting if
				// necessary until an element becomes available.
				url = queue.take(); // what if queue is empty? it will wait,
									// then when the thread ends?
									// Queue里暂时没有货了，但是一会儿别的线程可能会produce一些
			} catch (Exception e) {
				// e.printStackTrace();
				break; // If Interrupted, this thread is done
			}

			String domain = "";
			try {
				URL netUrl = new URL(url);
				domain = netUrl.getHost();
			} catch (MalformedURLException e) {
				// e.printStackTrace();
			}

			if (!visited.contains(url) && domain.endsWith("wikipedia.org")) { // not
																				// visited
																				// yet,
																				// and
																				// we
																				// care
																				// only
																				// this
																				// domain
																				// name
				visited.add(url);
				res.add(url);

				List<String> nextURLs = HtmlHelper.parseUrls(url);
				for (String u : nextURLs) {
					try {
						queue.put(u);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
				}
			}
		}
	}
}

class Solution {
	/**
	 * @param url
	 *            a url of root page
	 * @return all urls
	 */
	public List<String> crawler(String url) {
		WebCrawler.setFirstUrl(url);
		CrawlerThread[] thread_pools = new CrawlerThread[7];
		for (int i = 0; i < 7; ++i) {
			thread_pools[i] = new CrawlerThread();
			thread_pools[i].start();
		}

		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}

		for (int i = 0; i < 7; ++i) {
			// thread_pools[i].interrupt();
			thread_pools[i].stop(); // 我主动结束他们
		}

		List<String> results = CrawlerThread.getResults();
		return results;
	}
}

// Version 2, use AtomicLong
class CrawlerThread extends Thread {
	private static AtomicLong counter;
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private static HashMap<String, Boolean> map = new HashMap<String, Boolean>();
	private static List<String> res = new ArrayList<String>();

	public static void setFirstUrl(String url, int thread_num) {
		counter = new AtomicLong(thread_num);
		try {
			queue.put(url);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	public static Long getCounter() {
		return counter.get();
	}

	public static List<String> getResults() {
		return res;
	}

	@Override
	public void run() {
		while (true) {
			String url = "";
			try {
				counter.decrementAndGet();
				url = queue.take(); // 如果queue是空的，这边会一直等，所以下边的increment就一直执行不到
				counter.incrementAndGet();
			} catch (Exception e) {
				// e.printStackTrace();
				break;
			}

			String domain = "";
			try {
				URL netUrl = new URL(url);
				domain = netUrl.getHost();
			} catch (MalformedURLException e) {
				// e.printStackTrace();
			}

			if (!map.containsKey(url) && domain.endsWith("wikipedia.org")) {
				map.put(url, true);
				res.add(url);

				List<String> nextURLs = HtmlHelper.parseUrls(url);
				for (String u : nextURLs) {
					try {
						queue.put(u);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
				}
			}
		}
	}
}

class Solution2 {
	/**
	 * @param url
	 *            a url of root page
	 * @return all urls
	 */
	public List<String> crawler(String url) {
		int thread_num = 7;
		CrawlerThread.setFirstUrl(url, thread_num);

		CrawlerThread[] thread_pools = new CrawlerThread[thread_num];
		for (int i = 0; i < thread_num; ++i) {
			thread_pools[i] = new CrawlerThread();
			thread_pools[i].start();
		}

		while (CrawlerThread.getCounter() > 0)
			; // 只要有线程在跑 就一直循环

		for (int i = 0; i < thread_num; ++i) {
			thread_pools[i].stop();
		}

		List<String> results = CrawlerThread.getResults();
		return results;
	}
}

class HtmlHelper {
	static List<String> parseUrls(String url) {
		return null;
	}
	// Get all urls from a webpage of given url.
}
