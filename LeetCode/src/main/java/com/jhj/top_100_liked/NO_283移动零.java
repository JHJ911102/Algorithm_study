package com.jhj.top_100_liked;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-10 21:57
 * @description 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 提示:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 */
public class NO_283移动零 {
    @Test
    public void test() {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
    }

    // 双指针一个标记下一个非零一个标记下一个零，交换位置
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int left = 0,right = 0;
        while (right<length){
            // 右边的不为0就交换，假如位置一样，还是当前位置值，假如等于0,left停留在0的位置，right向下找不为0的位置
            if(nums[right]!=0){
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
            }
            right++;
        }

        // 还有个跑题的思路，将不为0的直接挨着左边放，剩下的长度直接全部置0;
        /*int index = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[index]=nums[i];
                index++;
            }
        }
        for(int i=index;i<nums.length;i++){
            nums[i]=0;
        }*/
    }
}
