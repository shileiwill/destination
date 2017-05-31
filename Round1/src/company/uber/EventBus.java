package company.uber;

import java.util.HashMap;
import java.util.Map;

/**
 * 电面是写一个EventBus，完成以下三个函数，用来监听事件的，一个event可以有多个listener，多个线程可能会同时调用其中的函数，
 * 有些listener的回调函数可能会运行很久，导其它的listener要等待很长的一段时间才能被触发，要解决这些问题。当时面的是Uber pool组mobile职位。
 * 
 * The EventBus allows for objects to subscribe for or publish events, without having explicit knowledge of each other. 
 * The EventBus is not meant to be a general purpose publish/subscribe system or support interprocess communication.
	The EventBus is very flexible and can be used as a singleton, or an application can have several instances to 
	accomodate transferring events in different contexts. The EventBus will dispatch all events serially, 
	so it is important to keep the event handling methods lightweight. If you need to do heavier processing in the event handlers, 
	there is another flavor of the EventBus, AsyncEventBus. The AsyncEventBus is identical in functionality, 
	but takes an ExecutorService as a constructor argument to allow for asynchronous dispatching of events.
 */
public class EventBus {
		Map<String, Listener> map = new HashMap<>();
		// Add Lock to Listener level
		
		public void register(String eventName, Listener listener) { 
			map.put(eventName, listener);
		} 
		
		public void unregister(String eventName, Listener listener) { 
			map.remove(eventName);
		} 
		
		public void postEvent(String eventName, Object data) {
			map.get(eventName).process(data);
		}
}

class PurchaseListener implements Listener {
   public void process(Object object) {
	   System.out.println(object);
   }
}

class SellListener implements Listener {
	   public void process(Object object) {
		   System.out.println(object);
	   }
}

interface Listener {
	void process(Object object);
}

class TestMain {
	public static void main(String[] args) {
		EventBus bus = new EventBus();
		bus.register("PurchaseEvent", new PurchaseListener());
		bus.register("SellEvent", new SellListener());
		
		bus.postEvent("SellEvent", "SellEvent---Data");
	}
}