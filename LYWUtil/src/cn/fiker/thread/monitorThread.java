package cn.fiker.thread;

/**
 * provider thread monitor
 * 
 * @author fiker
 * 
 */
public class monitorThread extends Thread {

	private boolean state = false;
	/**
	 * each 2 second to check threads
	 */
	private int timer = 2000;

	/**
	 * check thread's state pool each 2 second on default
	 */
	public void run() {
		while (!state) {
			state = true;
			for (int i = 0; i < Main.ThreadPoolState.length; i++) {
				if (Main.ThreadPoolState[i] == 0) {
					System.out.println("I'm monitor, project doesn't complete!");
					this.state = false;
					break;
				}
			}

			if (state) {
				System.out.println("complete all task, exit!");
			}

			try {
				Thread.sleep(timer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
