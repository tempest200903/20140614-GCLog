package com.example;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GC {

	static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SSS");

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList<String>();
		for (int i = 0;; i++) {
			list.add("a");
			if (i % Integer.MAX_VALUE / 10 == 0) {
				printHeap();
				Thread.sleep(1);
				System.gc();
				printDate();
			}
		}
	}

	private static void printDate() {
		System.out.println(dateFormat.format(new Date()));
	}

	private static void printHeap() {
		MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapUsage = mbean.getHeapMemoryUsage();
		long init = heapUsage.getInit();
		long used = heapUsage.getUsed();
		long committed = heapUsage.getCommitted();
		long max = heapUsage.getMax();
		StringBuffer sb = new StringBuffer();
		sb.append("init =: " + init);
		sb.append(", used =: " + used);
		sb.append(", committed =: " + committed);
		sb.append(", max =: " + max);
		System.out.println(sb.toString());
	}

}
