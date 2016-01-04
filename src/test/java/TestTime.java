import java.util.concurrent.TimeUnit;

public class TestTime {

	public static void main(String[] args) {
		Long startTime = System.currentTimeMillis();
		System.out.println("Time Started: " + startTime);
		int seconds = 2;
		System.out.println("Seconds: " + seconds);
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(String.format("%dm %ds",
				TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - startTime),
				TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(System.currentTimeMillis() - startTime))));
	}
	
}
