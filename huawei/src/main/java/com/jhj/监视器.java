package com.jhj;

import java.util.Scanner;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-14 0:27
 * @description 某长方形停车场，每个车位上方都有对应监控器，
 * 当且仅当在当前车位或者前后左右四个方向任意一个车位范围停车时，监控器才需要打开。
 * 给出某一时刻停车场的停车分布，请统计最少需要打开多少个监控器。
 *
 * 输入描述
 * 第一行输入m，n表示长宽，满足1 < m,n <= 20;
 * 后面输入m行，每行有n个0或1的整数，整数间使用一个空格隔开，表示该行已停车情况，其中0表示空位，1表示已停。
 * 输出描述
 * 最少需要打开监控器的数量
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 3 3
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出
 * 5
 */
public class 监视器 {
    // 遍历，找到需要开摄像头的位置(摄像头计数+1时要判断当前位置摄像头是否已经开启，因为临位有车也会开启摄像头)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] mn = sc.nextLine().split(" ");
        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);
        String[][] carGround = new String[m][n];
        for(int i = 0;i<m;i++){
            carGround[i] = sc.nextLine().split(" ");
        }

        // 记录开了摄像头的位置
        int[][] openLook = new int[m][n];
        int openNum = 0;
        for(int x = 0;x<m;x++){
            for (int y = 0; y < n; y++) {
                if(carGround[x][y].equals("1")){
                    if(openLook[x][y]!=1){
                        openLook[x][y]=1;
                        openNum++;
                    }
                    if(x-1>=0 && x-1<m){
                        if(openLook[x-1][y]!=1){
                            openLook[x-1][y]=1;
                            openNum++;
                        }
                    }
                    if(x+1>=0 && x+1<m){
                        if(openLook[x+1][y]!=1){
                            openLook[x+1][y]=1;
                            openNum++;
                        }
                    }
                    if(y-1>=0 && y-1<n){
                        if(openLook[x][y-1]!=1){
                            openLook[x][y-1]=1;
                            openNum++;
                        }
                    }
                    if(y+1>=0 && y+1<n){
                        if(openLook[x][y+1]!=1){
                            openLook[x][y+1]=1;
                            openNum++;
                        }
                    }
                }
            }
        }
        System.out.println(openNum);
    }
}
