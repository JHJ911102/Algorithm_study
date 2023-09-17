package com.jhj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-18 2:18
 * @description 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0~N的箱子，
 * 每个箱子上面贴有一个数字，箱子中可能有一个黄金宝箱。黄金宝箱满足排在它之前的所有箱子数字和等于排在它之后的所有箱子数字和；
 * 第一个箱子左边部分的数字和定义为0；最后一个宝箱右边部分的数字和定义为0。
 * 请帮阿里巴巴找到黄金宝箱，输出第一个满足条件的黄金宝箱编号，如果不存在黄金宝箱，请返回-1。
 *
 * 输入描述
 * 箱子上贴的数字列表，使用逗号分隔，例如1，-1，0。
 * 宝箱的数量不小于1个，不超过10000
 * 宝箱上贴的数值范围不低于-1000，不超过1000
 *
 * 输出描述
 * 第一个黄金宝箱的编号
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 2,5,-1,8,6
 * 输出
 * 3
 *
 * 样例说明
 * 下标3之前的数字和为：2 + 5 + -1 = 6
 * 下标3之后的数字和为：6 = 6
 */
public class 阿里巴巴找黄金宝箱 {
    // 我的第一思路，有一个箱子，他左边箱子标记数字之和和等于右边箱子标记数字之和，所有箱子数字相加记为sumR，sumL记为0
    //从左开始依次移动指针修正sumR，sumL，直到相等为止，为防止溢出用long
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] boxes = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int n = boxes.length;
        long suml = 0L;
        long sumr = 0L;
        for (int box:boxes) {
            sumr += box;
        }
        for (int i = 0; i < n; i++) {
            sumr -= boxes[i];
            if(sumr==suml){
                System.out.println(i);
                break;
            }
            suml += boxes[i];
            if(i==n-1){
                System.out.println(-1);
            }
        }

    }
}
