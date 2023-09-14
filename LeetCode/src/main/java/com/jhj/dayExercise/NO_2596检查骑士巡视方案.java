package com.jhj.dayExercise;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-13 1:34
 * @description 骑士在一张nxn的棋盘上巡视。在有效的巡视方案中，骑士会从棋盘的左上角出发，并且访问棋盘上的每个格子恰好一次。
 * 给你一个nxn的整数矩阵grid，由范围[0,n*n-1]内的不同整数组成，
 * 其中grid[row][col]表示单元格(row,col)是骑士访问的第grid[row][col]个单元格。骑士的行动是从下标0开始的。
 * 如果grid表示了骑士的有效巡视方案，返回true；否则返回false。
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 3 <= n <= 7
 * 0 <= grid[row][col] < n * n
 * grid 中的所有整数 互不相同
 */
public class NO_2596检查骑士巡视方案 {
    @Test
    public void test() {
        int[][] grid = {{0,11,16,5,20},{17,4,19,10,15},{12,1,8,21,6},{3,18,23,14,9},{24,13,2,7,22}};
        System.out.println(checkValidGrid(grid));
    }

    // 找到每一个数字的位置，看相连两个数之间的坐标差绝对值乘积是否为2
    public boolean checkValidGrid(int[][] grid) {
        // 获取骑士要走的步数
        int length = grid.length*grid.length;
        //创建一个map存放每一个数字的坐标
        HashMap<Integer,Integer[]> station = new HashMap();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid.length;j++){
                Integer[] sta = new Integer[]{i,j};
                station.put(grid[i][j],sta);
                //System.out.println(grid[i][j] +":"+sta[0]+","+sta[1]);
            }
        }

        //左上方起始位置必须是0;(可能不需要，题目保证了)
        if(station.get(0)==null){
            return false;
        }
        Integer[] pre = station.get(0);
        // (可能不需要，题目保证了)
        if(pre[0]!=0 || pre[1]!=0){
            return false;
        }
        Integer[] after;
        // 开始遍历看相邻巡视点坐标是否符合要求
        for(int index=0;index<length-1;index++){
            after = station.get(index+1);
            if((Math.abs(after[0]-pre[0])*Math.abs(after[1]-pre[1]))!=2){
                return false;
            }
            pre = after;
        }
        return true;
    }
}
