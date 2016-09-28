package cc150.chapter15.multithread;
/**
 * https://www.javacodegeeks.com/2011/09/java-concurrency-tutorial-semaphores.html
 * So what is semaphore? The simplest way to think of a semaphore is to consider it an abstraction that allows n units to be acquired, and offers acquire and release mechanisms. It safely allows us to ensure that only n processes can access a certain resource at a given time.
That’s all well and good, but what purpose would this serve? Well, here is one example that will help explain its uses. It uses the nicely designed Semaphore class introduced in 1.5, located in the java.util.concurrent package.
Limiting connections
Perhaps we have a process that downloads resources for us periodically via HTTP. We don’t want to spam any of the hosts and at the same time, we want to limit how many connections we are making so we don’t exhaust the limited file handles or outbound connections we are permitted. A simple way to do this would be with a semaphore:
 */
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Semaphore;

public class ConnectionLimiter {
	   private final Semaphore semaphore;

	   private ConnectionLimiter(int maxConcurrentRequests) {
	       semaphore = new Semaphore(maxConcurrentRequests);
	   }

	   public URLConnection acquire(URL url) throws InterruptedException,
	                                                IOException {
	       semaphore.acquire();
	       return url.openConnection();
	   }

	   public void release(URLConnection conn) {
	       try {
	           /*
	           * ... clean up here
	           */
	       } finally {
	           semaphore.release();
	       }
	   }
	}