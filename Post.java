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
		
		Pattern testDefinition = Pattern.compile("(\\d+) (\\d+) (\\d+)");
		Pattern intersection = Pattern.compile("(\\d+) (\\d+)");
		
		
		try{
			BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
			//Console c = System.console();
			int testCases = Integer.parseInt(c.readLine());
			cases = new TestCase[testCases];
			
			for (int i=0; i < testCases; i++) {
				c.readLine();
				Matcher testMatcher = testDefinition.matcher(c.readLine());
				
				TestCase newCase = new TestCase();
				if (testMatcher.matches()) {
					newCase.w = Integer.parseInt(testMatcher.group(1));
					newCase.h = Integer.parseInt(testMatcher.group(2));
					int intersections = Integer.parseInt(testMatcher.group(3));
					newCase.intersections = new boolean[newCase.w+1][newCase.h+1];
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
			cur.result = new int[cur.w+1][cur.h+1];
			
			for (int i = 0; i <= cur.w; i++) {
				if (cur.intersections[i][0]) {
					cur.result[i][0]= 0;
					break;
				}
				cur.result[i][0] = 1;
			}
			

			for (int i = 0; i <= cur.h; i++) {
				if (cur.intersections[0][i]) {
					cur.result[0][i]= 0;
					break;
				}
				cur.result[0][i] = 1;
			}
			
			for (int x = 1; x <= cur.w; x++) {
				
				for (int y=1; y <= cur.h; y++) {
					if (cur.intersections[x][y]) {
						cur.result[x][y]= 0;
						continue;
					}
					
					int dx,dy;
					if (x-1 < 0) dx = 0;
					else dx = cur.result[x-1][y];
					
					if (y-1 < 0) dy = 0;
					else dy = cur.result[x][y-1];
						
					cur.result[x][y] = dx + dy;
				}
			}
			
			System.out.println(cur.result[cur.w][cur.h]);
		}
	}
	
	
	
}
