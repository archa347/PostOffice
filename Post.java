package me.post;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Post {

	public static void main(String[] args) {
		
		class TestCase {
			public int w,h;
			public boolean[][] intersections;
			public int[][] result;
		
		}
		
		TestCase[] cases=null;
		
		Pattern testDefinition = Pattern.compile("\\d+ \\d+ \\d+");
		Pattern intersection = Pattern.compile("\\d+ \\d+");
		
		
		try{
			BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
			//Console c = System.console();
			int testCases = Integer.parseInt(c.readLine());
			cases = new TestCase[testCases+1];
			
			for (int i=0; i < testCases; i++) {
				c.readLine();
				Matcher testMatcher = testDefinition.matcher(c.readLine());
				
				TestCase newCase = new TestCase();
				if (testMatcher.matches()) {
					newCase.w = Integer.parseInt(testMatcher.group(1));
					newCase.h = Integer.parseInt(testMatcher.group(2));
					int intersections = Integer.parseInt(testMatcher.group(3));
					newCase.intersections = new boolean[newCase.w][newCase.h];
					for (int j = 0; j < intersections; j++) {
						Matcher matcher = intersection.matcher(c.readLine());
						if (matcher.matches()) {
							newCase.intersections[Integer.parseInt(matcher.group(1))][Integer.parseInt(matcher.group(2))] = true;
						}
					}
					cases[i] = newCase;
				}
			}
		} catch (Exception e) {
			System.out.println("error reading parameters");
			e.printStackTrace();
			return;
		}
		
		for (int caze = 0; caze < cases.length; caze++) {
			TestCase cur = cases[caze];
			cur.result = new int[cur.w][cur.h];
			
			for (int x = 0; x < cur.w; x++) {
				
				for (int y=0; y < cur.h; y++) {
					if (cur.intersections[x][y]) {
						cur.result[x][y]= -1;
						break;
					}
					
					int dx,dy;
					if (x-1 < 0) dx = 0;
					else dx = 1 + cur.result[x-1][y];
					
					if (y-1 < 0) dy = 0;
					else dy = 1 + cur.result[x][y-1];
						
					cur.result[x][y] = dx + dy;
				}
			}
			
			System.out.println(cur.result[cur.w][cur.h]);
		}
	}
	
	
	
}
