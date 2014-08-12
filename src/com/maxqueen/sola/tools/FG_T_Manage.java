/**
 * <p>Title: FG_T_Manage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.maxqueen.com</p>
 * @author Sola
 * @date 2013-8-6
 * @version 1.0
 */
package com.maxqueen.sola.tools;

import java.util.ArrayList;
import java.util.List;

import com.maxqueen.sola.interfaces.IS_DEBUG;

import android.util.Log;

/**
 * <p>
 * Title: FG_T_Manage
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.maxqueen.com
 * </p>
 * 
 * @author Sola
 * @date 2013-8-6
 */
public class FG_T_Manage implements IS_DEBUG {

	// private Runnable[] _Pollings = null;
	// private List<Runnable> _Singles = null;

	private List<Thread> threadList = null;

	public FG_T_Manage(Runnable... polling) {
		if (threadList == null)
			threadList = new ArrayList<Thread>();
	}

	public void stopTheWorld() {
		for (Thread param : threadList)
			interrupt(param);
	}

	public void stopAndCleanTheWorld() {
		stopTheWorld();
		synchronized (threadList) {
			threadList.removeAll(threadList);
		}
	}

	public void destoryTheWorld() {
		if (threadList != null && threadList.size() != 0) {
			stopTheWorld();
			cleanTheWorld();
		}
	}

	public void cleanTheWorld() {

		synchronized (threadList) {
			threadList.removeAll(threadList);
		}
		threadList = null;
	}

	/**
	 * 
	 */
	// private void startPolling() {
	// if (_Pollings != null && getPollingSize() != 0)
	// for (int i = 0, len = getPollingSize(); i < len; i++) {
	// Log.d("Sola", "-----Polling----" + i);
	// threadList.get(i).start();
	// }
	// }

	// private int getPollingSize() {
	// return _Pollings == null ? 0 : _Pollings.length;
	// }

	/**
	 * 处理可停止的循环线程
	 * 
	 * @param runnables
	 */
	public void startCycle(Runnable... runnables) {
		if (threadList == null)
			threadList = new ArrayList<Thread>();
		synchronized (threadList) {
			for (Runnable runnable : runnables) {
				String name = runnable.getClass().getSimpleName();
				// if(threadList.contains(object))
				if (checkCycle(name))
					continue;
				Thread thread = new Thread(runnable, name);
				threadList.add(thread);
				thread.start();
			}
		}
	}

	public void startCycle(Runnable runnable, String threadName) {
		if (threadList == null)
			threadList = new ArrayList<Thread>();
		synchronized (threadList) {
			if (checkCycle(threadName))
				return;
			Thread thread = new Thread(runnable, threadName);
			threadList.add(thread);
			thread.start();
		}
	}

	public void removeCycle(String... nameList) {
		if (threadList == null)
			return;
		else {
			synchronized (threadList) {
				for (String name : nameList) {
					int memoryCount = -1;
					for (int i = 0, len = threadList.size(); i < len; i++) {
						if (name.equalsIgnoreCase(threadList.get(i).getName())) {
							threadList.get(i).interrupt();
							memoryCount = i;
							break;
						}
					}
					if (memoryCount != -1) {
						threadList.remove(memoryCount);
					}
				}
			}
		}
	}

	private boolean checkCycle(String name) {
		threadList.contains(name);
		for (Thread thread : threadList) {
			Log.v("String", "Check Thread: " + thread.getName());
			if (name.equalsIgnoreCase(thread.getName()))
				return true;
		}
		return false;
	}

	private void interrupt(Thread thread) {
		if (thread.isAlive())
			thread.interrupt();
	}

	public void startSingle(Runnable... runnables) {
		for (Runnable runnable : runnables)
			new Thread(runnable).start();
	}

}
