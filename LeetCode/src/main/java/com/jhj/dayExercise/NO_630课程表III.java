package com.jhj.dayExercise;

import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-11 0:19
 * @description 这里有n门不同的在线课程，按从1到 n编号。给你一个数组courses，
 * 其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，
 * 并且必须在不晚于 lastDayi 的时候完成。
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * 返回你最多可以修读的课程数目。
 *
 * 示例 1：
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 *
 * 示例 2：
 * 输入：courses = [[1,2]]
 * 输出：1
 *
 * 示例 3：
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 *
 * 提示:
 * 1 <= courses.length <= 104
 * 1 <= durationi, lastDayi <= 104
 */
public class NO_630课程表III {
    @Test
    public void test() {
        int[][] nums = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        System.out.println(scheduleCourse(nums));
    }

    // 思路，根据数组计算出每门课程最晚的开始时间，将课程结束时间与其余课程最晚开始时间比较
    // 结束时间小于开始时间就可修
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a,b)->a[1]-b[1]);
        // 深入了解下PriorityQueue的用法
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        // 天数
        int sum = 0;
        for (int[] c:courses) {
            int d = c[0], e = c[1];
            sum += d;
            q.add(d);
            if (sum>e){
                sum -= q.poll();
            }
        }
        return q.size();
    }
}
