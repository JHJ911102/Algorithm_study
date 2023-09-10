package com.jhj.top_100_liked.hash;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-10 20:05
 * @description 给定一个整数数组nums和一个整数目标值target，
 * 请你在该数组中找出和为目标值 target的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
public class NO_1两数之和 {
    @Test
    public void test() {
        int target = 4;
        int[] nums = {2,7,11,15};
        System.out.println(Arrays.toString(twoSum(nums,target)));
    }

    // 将数组中的元素依次添加到map中，添加的过程中查找(target-当前值)是否存在map中，找到即结果对象
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> numCache = new HashMap();
        int[] result = new int[2];
        int a;
        for(int i=0;i<nums.length;i++){
            a = nums[i];
            if(numCache.containsKey(target-a)){
                result[0]=numCache.get(target-a);
                result[1]=i;
                return result;
            }
            numCache.put(a,i);
        }
        return result;
    }
}
