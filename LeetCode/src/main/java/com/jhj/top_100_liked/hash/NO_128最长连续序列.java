package com.jhj.top_100_liked.hash;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-10 20:48
 * @description 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class NO_128最长连续序列 {
    @Test
    public void test() {
        int[] nums = {9,1,-3,2,4,8,3,-1,6,-2,-4,7};
        System.out.println(longestConsecutive(nums));
    }

    // 暴力破解,挨个查看nums[i]+1==nums[i+1]，满足临时长度+1，不满足从nums[i+1]继续开始新的一轮
    public int longestConsecutive(int[] nums) {
        int length = nums.length;
        if (length<=1){
            return length;
        }
        int result = 1;
        int tempLength = 1;
        Arrays.sort(nums);
        for (int i = 0; i < length-1; i++) {
            if(nums[i]+1==nums[i+1]){
                tempLength++;
            } else if(nums[i]!=nums[i+1]) {
                if(tempLength>result){
                    result=tempLength;
                }
                tempLength=1;
            }
        }
        if(tempLength>result){
            result=tempLength;
        }
        return result;
    }
}
