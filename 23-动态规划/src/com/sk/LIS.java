package com.sk;

public class LIS {
	public static void main(String[] args) {
		test01();
	}
	
	
	static void test01() {
		int[] array = new int[] {10, 2, 2, 5, 1, 7, 101, 18};
		System.out.println(lengthOfLIS1(array));
	}
	/**
	 * 动态规划
	 * */
	/**
	 * 
	 * 给定一个无序的整数序列，求出它最长上升序列的长度(要求严格上升)
	 * 比如[10,2,2,5,1,7,101,18]的最长上升序列是[2,5,7,101]、[2,5,7,18],长度是4
	 * */
	static int lengthOfLIS1(int[] nums) { 
		if (nums == null ||nums.length == 0) {
			return 0;
		}
		int[] dp = new int[nums.length];
		int max = dp[0] - 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] <= nums[j]) continue;
				dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			max = Math.max(dp[i], max);
		}
		return max;
	}
	
}


