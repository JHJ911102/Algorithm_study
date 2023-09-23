package com.jhj.top_100_liked;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-23 19:26
 * @description 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
 * 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 *
 * 提示：
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class NO_15三数之和 { //
    @Test
    public void test() {
        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        // {-4,-3,-2,-1,-1,0,0,1,2,3,4};
        for (List<Integer> threeSum:
        threeSum(nums)) {
            for (Integer  a:threeSum ){
                System.out.print(a+",");
            }
            System.out.println();
        }
    }
    // 方法一，爆破
    // 方法二，双指针
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        List<String> tmpResult = new ArrayList<>();//辅助去重
        for (int i = 0; i < n; i++) {
            if(nums[i]>=0){
                break;
            }
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; ++k) {
                    if(nums[i]+nums[j]+nums[k]==0){
                        String resultEnum = nums[i]+","+nums[j]+","+nums[k];
                        if(tmpResult.contains(resultEnum)){ // 已经重复不添加
                            // 这里移动的时候也要判断left和tmp的关系，确保累加结果没有漏的
                            continue;
                        }
                        tmpResult.add(resultEnum);
                        List<Integer> group = new ArrayList<>();
                        group.add(nums[i]);
                        group.add(nums[j]);
                        group.add(nums[k]);
                        result.add(group); // 要去重，再弄个list辅助去重
                        //break;
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int left;
        int right;
        for (int i = 0; i < n; i++) {
            if(nums[i]>0){
                return result;
            }
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            left = i+1;
            right = n-1;
            while(left<right){
                if (nums[i] + nums[left] + nums[right]==0){
                    List<Integer> group = new ArrayList<>();
                    group.add(nums[i]);
                    group.add(nums[left]);
                    group.add(nums[right]);
                    result.add(group);
                    while (left<right && nums[left]==nums[left+1]){
                        left++;
                    }
                    while (left<right && nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right]>0) {
                    right--;
                }else{
                    left++;
                }
            }
        }
        return result;
    }


    // 以下是错误解答，思路接近，遍历不完全，漏了一些场景

    /*if(leftIndex+1==tmp){
        tmp++;
    }else{
        leftIndex++;
    } 这种移动在不满足==0的时候有问题例如{-4,-3,-2,-1,-1,0,0,1,2,3,4}。-4,0 4满足条件
    判断
    */


    // 顺序不重要，先排个序，然后找到0位置往两边找？或者由两边往中间找
    public List<List<Integer>> threeSumErr1(int[] nums) { // 存在问题，见threeSum1
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int leftIndex = 0;
        int rightIndex = n-1;
        int tmp = 1;
        List<String> tmpResult = new ArrayList<>();//辅助去重

        // 从左边开始遍历
        while (leftIndex<tmp && rightIndex>tmp && leftIndex<rightIndex){
            // 满足条件添加到结果集，中间坐标往右移动1
            if(nums[leftIndex]+nums[rightIndex]+nums[tmp] == 0){
                String resultEnum = nums[leftIndex]+","+nums[tmp]+","+nums[rightIndex];
                if(tmpResult.contains(resultEnum)){ // 已经重复不添加
                    // 这里移动的时候也要判断left和tmp的关系，确保累加结果没有漏的
                    if(leftIndex+1==tmp){
                        tmp++;
                    }else{
                        leftIndex++;
                    }
                    continue;
                }
                tmpResult.add(resultEnum);
                List<Integer> group = new ArrayList<>();
                group.add(nums[leftIndex]);
                group.add(nums[tmp]);
                group.add(nums[rightIndex]);
                result.add(group); // 要去重，再弄个list辅助去重
                if(leftIndex+1==tmp){
                    tmp++;
                }else{
                    leftIndex++;
                    tmp=leftIndex+1;
                }
                continue;
            }

            // 和大于0，说明右边的大了，rightIndex
            if (nums[leftIndex]+nums[rightIndex]+nums[tmp] > 0) {
                rightIndex--;
                continue;
            }

            // 和小于0，说明左边过小，若left和tmp连在一起(leftIndex+1==tmp)，移动tmp。leftIndex
            if (nums[leftIndex]+nums[rightIndex]+nums[tmp] < 0) {
                if(tmp+1==rightIndex){
                    leftIndex++;
                }else{
                    tmp++;
                }
            }
        }

        // 从右边开始遍历
        leftIndex = 0;
        rightIndex = n-1;
        tmp = rightIndex-1;
        while (leftIndex<tmp && rightIndex>tmp && leftIndex<rightIndex){
            // 满足条件添加到结果集，中间坐标往右移动1
            if(nums[leftIndex]+nums[rightIndex]+nums[tmp] == 0){
                String resultEnum = nums[leftIndex]+","+nums[tmp]+","+nums[rightIndex];
                if(tmpResult.contains(resultEnum)){ // 已经重复不添加
                    // 这里移动的时候也要判断left和tmp的关系，确保累加结果没有漏的
                    if(rightIndex-1==tmp){
                        tmp--;
                    }else{
                        rightIndex--;
                        tmp=leftIndex-1;
                    }
                    continue;
                }
                tmpResult.add(resultEnum);
                List<Integer> group = new ArrayList<>();
                group.add(nums[leftIndex]);
                group.add(nums[tmp]);
                group.add(nums[rightIndex]);
                result.add(group); // 要去重，再弄个list辅助去重
                if(rightIndex-1==tmp){
                    tmp--;
                }else{
                    rightIndex--;
                    tmp=leftIndex-1;
                }
                continue;
            }

            // 和大于0，说明右边的大了，rightIndex
            if (nums[leftIndex]+nums[rightIndex]+nums[tmp] > 0) {
                if(rightIndex-1==tmp){
                    tmp--;
                }else{
                    rightIndex--;
                    tmp=leftIndex-1;
                }
                continue;
            }

            // 和小于0，说明左边过小，若left和tmp连在一起(leftIndex+1==tmp)，移动tmp。leftIndex
            if (nums[leftIndex]+nums[rightIndex]+nums[tmp] < 0) {
                leftIndex++;
            }
        }
        return result;
    }
}
