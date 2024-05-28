package com.example.demo.java.tryCatch;

public class TryCatchDemo {

	public static void main(String[] args) {
		try{
			int h=1/0;
		}catch(NullPointerException e) {
//			e.printStackTrace();
		}finally {
			System.out.println("finally");
		}
		
		
		boolean status=false;
		for(int i=0;i<3;i++) {
			System.out.println(i);
			status=test();
			if(status) {
				break;
			}
		}
	}
	
	public static boolean test(){
		int a=(int)(1+Math.random()*(10-1+1))%2;
		try {
			int i=1/a;
		}catch (Exception e) {
			return false;
		}
		return true;
	}

}
