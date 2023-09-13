package com.jhj;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-13 21:20
 * @description A、B两个人把苹果分为两堆，A希望按照他的计算规则等分苹果，他的计算规则是按照二进制加法计算，
 * 并且不计算进位 12+5=9（1100 + 0101 = 9），B的计算规则是十进制加法，包括正常进位，B希望在满足A的情况下获取苹果重量最多。
 * 输入苹果的数量和每个苹果重量，输出满足A的情况下B获取的苹果总重量。
 *
 * 如果无法满足A的要求，输出-1。
 * 数据范围
 * 1 <= 总苹果数量 <= 20000
 * 1 <= 每个苹果重量 <= 10000
 * 输入描述：
 * 输入第一行是苹果数量：3
 * 输入第二行是每个苹果重量：3 5 6
 * 输出描述：
 * 输出第一行是B获取的苹果总重量：11
 *
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 3
 * 3 5 6
 * 输出
 * 11
 *
 * 示例2 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 8
 * 7258 6579 2602 6716 3050 3564 5396 1773
 * 输出
 * 35165
 */
public class 分苹果 {
    // 题目分析，按照A的方法，二进制不进位，两堆苹果相等，两堆苹果的二进制表示进行异或，结果是0。
    // 满足这个条件后，随便拿出一个苹果与剩下的苹果进行异或都是0，所以取一个最轻的苹果给A即可

    public static void main(String[] str){
        // 输入参数
        Scanner in = new Scanner(System.in);
        int index = in.nextInt();
        int sum = 0;
        int orSum = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<index;i++){
            int weight = in.nextInt();
            sum += weight;
            orSum ^= weight;
            min = Math.min(min,weight);
        }
        if (orSum==0) {
            System.out.println(sum - min);
        }else {
            System.out.println(-1);
        }
    }

}
