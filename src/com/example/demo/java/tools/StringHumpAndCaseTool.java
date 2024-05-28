package com.example.demo.java.tools;

import java.util.LinkedList;

public class StringHumpAndCaseTool {
	public static void main(String[] args) {
		format("SchoolRemark");
		format("RealEndDate");
		format("project_value_funds");
		format("project_serialsNumber_funds");
		
	}
	
	private static void format(String data) {
		StringBuilder result=new StringBuilder();
		int i=0;
		for(Character c:data.toCharArray()) {
			if(i!=0&&Character.isUpperCase(c)) {
				result.append("_");
			}
			result.append(String.valueOf(Character.toUpperCase(c)));
			i++;
		}
		System.out.println(result.toString());
	}
}
