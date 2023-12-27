package util;

import java.util.Random;
import java.util.Scanner;

public class Util {
	private static final Scanner scan = new Scanner(System.in);
	private static final Random rd = new Random();
	
	public static int getValue(String msg,int start,int end) {
		int num = 0;
		while(true) {
			System.out.printf("[%d ~ %d] %s%n",start,end,msg);
			try {
				num = scan.nextInt();
				if(num < start || num > end) {
					System.out.println("입력 오류");
					continue;
				}
				return num;
			} catch(Exception e) {
				System.out.println("숫자만 입력...");
			} finally {
				scan.nextLine();
			}
		}
	}
	public static String getValueString(String msg) {
		System.out.println(msg);
		return scan.next();
	}
}
