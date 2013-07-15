package cn.fiker.thread;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * like teacher
 * 
 * @author fiker
 * 
 */
public class Main {

	private static ArrayList<HashMap<String, Object>> TaskList;
	public static int[] ThreadPoolState;
	public static Thread[] ThreadPool;

	/**
	 * how many thread to complete this project
	 */
	public static final int maxThread = 7;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		createTask(100);

		monitorThread mt = new monitorThread();
		mt.start();

		ThreadPoolState = new int[maxThread];
		ThreadPool = new Thread[maxThread];
		subThread temp;

		for (int i = 0; i < maxThread; i++) {
			temp = new subThread();
			temp.setName("Thread - " + (i + 1));
			temp.setID(i);
			temp.start();

			ThreadPoolState[i] = 0;
			ThreadPool[i] = temp;
		}
	}

	/**
	 * create Task list
	 * 
	 * @param maxTasks
	 *            task count
	 */
	public static void createTask(int maxTasks) {
		TaskList = new ArrayList<HashMap<String, Object>>();
		System.out.println("make 100 tasks.");
		HashMap<String, Object> temp;
		for (int i = 1; i <= maxTasks; i++) {
			temp = new HashMap<String, Object>();
			temp.put("taskID", "task_" + i);
			temp.put("taskContent", Math.random() * 20);
			TaskList.add(temp);
		}
	}

	/**
	 * return a task item, will be delete on successful
	 * 
	 * @return
	 */
	public static synchronized HashMap<String, Object> getTask() {
		if (Main.TaskList.size() > 0) {
			HashMap<String, Object> temp = Main.TaskList.get(0);
			Main.TaskList.remove(0);
			return temp;
		} else {
			return null;
		}
	}

	/**
	 * when task execute failed, return the task back to task list
	 * 
	 * @param input
	 */
	public static void returnTask(HashMap<String, Object> input) {
		Main.TaskList.add(input);
	}

}
