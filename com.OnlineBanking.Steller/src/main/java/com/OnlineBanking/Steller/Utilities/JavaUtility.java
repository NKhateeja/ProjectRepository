package com.OnlineBanking.Steller.Utilities;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author HP
 *
 */
public class JavaUtility {
	/** 
	 * this method is used to get a random number
	 * @return
	 */
	public int retRandumNumber() {
		Random random=new Random();
		int randNum=random.nextInt(1000);
		return randNum;
		
	}
	
	/**
	 * This method is used to get the current system date based on yyyy-mm-dd formate
	 * @return
	 */
	public String currentSystemDate() {
		Date date=new Date();
		String currentDate=date.toString();
		System.out.println(currentDate);
		String ar[]=currentDate.split(" ");
		
		String yyyy=ar[5];
		String dd=ar[2];
		int mm=date.getMonth()+1;
		
		String formate=yyyy+"-"+mm+"-"+dd;
		return formate;
	}
	
	/**
	 * This method is used to get the current system date based on dd-mm-yyyy formate
	 * @return
	 */
	
	public String getSystemDateInIST()
	{
		Date date=new Date();
		String currentDate=date.toString();
		System.out.println(currentDate);
		String ar[]=currentDate.split(" ");
		
		String yyyy=ar[5];
		String dd=ar[2];
		int mm=date.getMonth()+1;
		
		String formate=dd+"-"+mm+"-"+yyyy;
		return formate;
	}
}
