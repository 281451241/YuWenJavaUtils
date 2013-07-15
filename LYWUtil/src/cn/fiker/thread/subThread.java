package cn.fiker.thread;

import java.util.HashMap;

/**
 * like student
 * 
 * @author fiker
 * 
 */
public class subThread extends Thread {

	protected String TaskID;
	protected HashMap<String, Object> task;
	private boolean state = false;
	private int index;

	public void run() {
		while (!state) {
			System.out.println("I'm " + this.getName() + " , I'm starting.");

			task = Main.getTask();
			if (task != null) {
				this.TaskID = "" + task.get("taskID");

				System.out.println("I'm " + this.getName() + " , I have a task.");
				double rand = Math.random() * 20;
				if ((int) rand < 10) {
					failed();
				} else {
					complete();
				}
			} else {
				this.state = true;
				Main.ThreadPoolState[this.index] = 1;
				System.out.println("I'm " + this.getName() + " , null task , i'm dead.");
			}
			try {
				Thread.sleep((long) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setID(int id) {
		this.index = id;
	}

	public void complete() {
		System.out.println("I'm " + this.getName() + " , I complete task \"" + TaskID + "\".");
	}

	public void failed() {
		Main.returnTask(task);
		System.out.println("I'm " + this.getName() + " , I failed task \"" + TaskID + "\", return to tasklist.");
	}
}
