package company.linkedin;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ExecutorServiceImpl {

	public static void main(String[] args) {

	}

	class BeeperControl {
		private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		// A task
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};
		
		public void beepForAnHour() {
			
			final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
			
			// Also start a thread to cancel above non-stop task after certain time
			scheduler.schedule(new Runnable() {
				public void run() {
					beeperHandle.cancel(true);
				}
			}, 60 * 60, SECONDS);
		}
	}
}
