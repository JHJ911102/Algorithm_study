package com.jhj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-15 0:25
 * @description 小明在直线的公路上种树，现在给定可以种树的坑位的数量和位置，以及需要种多少棵树苗，
 * 问树苗之间的最小间距是多少时，可以保证种的最均匀（两棵树苗之间的最小间距最大）？
 *
 * 输入描述
 * 输入三行：
 * 第一行：坑位的数量
 * 第二行：坑位的位置
 * 第三行：需要种植树苗的数量
 *
 * 输出描述
 * 树苗之间的最小间距
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 7
 * 1 3 6 7 8 11 13
 * 3
 * 输出
 * 6
 * 说明
 * 3棵树苗分别种植在1，7，13位置的坑位时，树苗种植的最均匀，最小间距为6。
 */

// 第一反应思路:求出坑的最大差，计算平均坑进去，然后进行调整
// 答案思路可能类似于二分法:设定最小值和最大值以及中间值，种树为了使距离最大肯定从两边开始种
// 设定从左边开始种，遍历坑位，计算每个位置距离前一个树的距离是否大于等于中间值(因为距离最大肯定是中间的树尽量靠中间才最大)
// 若满足种下一棵树，树的数量+1,更改当前标志树的坐标，查找下一个树的位置。遍历完成后当满足条件时，将当前距离做为临时结果，继续增大中间值
// 为中间值+1/2中间值(因为树的数量要相等，上一轮遍历的结果可能数的数目大于目标值)
public class 最佳植树距离 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int kengNums = sc.nextInt();
        int[] solutions = new int[kengNums];
        for (int i = 0; i < kengNums; i++) {
            solutions[i] = sc.nextInt();
        }
        int treeNums = sc.nextInt();

        Arrays.sort(solutions);
        int min = 1, max = solutions[kengNums - 1] - solutions[0];
        int ans = 0;
        while (min <= max) {
            int mid = (min + max) >> 1;
            if (check(solutions, treeNums, mid)) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(ans);
    }
    public static boolean check(int[] positions, int m, int minDis) {
        int count = 1;
        int curPos = positions[0];
        for (int i = 1; i < positions.length; i++) {
            if (positions[i] - curPos >= minDis) {
                count++;
                curPos = positions[i];
            }
        }
        return count >= m;
    }

}
