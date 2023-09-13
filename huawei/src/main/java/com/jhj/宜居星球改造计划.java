package com.jhj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-13 23:00
 * @description 2XXX年，人类通过对火星的大气进行宜居改造分析，使得火星已在理论上具备人类宜居的条件，
 * 由于技术原因，无法一次性将火星大气全部改造，只能通过局部处理形式，
 * 假设将火星待改造的区域为row * column的网格每个网格有3个值，宜居区、可改造区、死亡区，使用YES、NO、NA代替，
 * YES表示该网格已经完成大气改造，NO表示该网格未进行改造，后期可进行改造，NA表示死亡区，不作为判断是否改造完成的宜居，无法穿过
 * 初始化下，该区域可能存在多个宜居区，并且每个宜居区能同时在每个太阳日单位向上下左右四个方向的相邻格子进行扩散，
 * 自动将4个方向相邻的真空区改造成宜居区;请计算这个待改造区域的网格中，可改造区是否能全部变成宜居区，
 * 如果可以，则返回改造的太阳日天数，不可以则返回-1。
 *
 * 输入描述:
 * 输入row*column个网格数据，每个网格值枚举值如下: YES，NO，NA，样例:
 * YES YES NO
 * NO NO NO
 * NA NO YES
 *
 * 输出描述:
 * 可改造区是否能全部变成宜居区，如果可以，则返回改造的太阳日天数，不可以则返回-1.
 *
 * 补充说明:
 * grid[i]只有3种情况，YES、NO、NA
 * row == grid.length, column == grid[i].length, 1 <= row, column <= 8
 *
 * 示例1
 * 输入:
 * YES YES NO
 * NO NO NO
 * YES NO NO
 * 输出:
 * 2
 *
 * 说明:
 * 经过2个太阳日，完成宜居改造.
 *
 * 示例2
 * 输入:
 * YES NO NO NO
 * NO NO NO NO
 * NO NO NO NO
 * NO NO NO NO
 *
 * 输出:
 * 6
 * 说明:
 * 经过6个太阳日，可完成改造
 *
 * 示例3
 * 输入:
 * NO NA
 * 输出:
 * -1
 * 说明:
 * 无改造初始条件，无法进行改造
 *
 * 示例4
 * 输入:
 * YES NO NO YES
 * NO NO YES NO
 * NO YES NA NA
 * YES NO NA NO
 * 输出:
 * -1
 * 说明:
 * 右下角的区域，被周边三个死亡区挡住，无法实现改造
 */
public class 宜居星球改造计划 {
    // 思路:遍历多轮，遇到YES即将四个方向的NO设置为YES，为避免造成过多扩散(YES修改的NO变成YES，遍历过程中没有标记导致继续扩散出YES)
    // 失败场景，全区域没有YES，存在NO被包围。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String[]> areas = new ArrayList<String[]>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if("over".equals(line)){
                break;
            }
            areas.add(line.split(" "));
        }
        int rowNum = areas.size();
        int columnNum = areas.get(0).length;

        // 记录宜居位置坐标
        ArrayList<int[]> yArea = new ArrayList<int[]>();
        // 记录可改造区数量
        int need = 0;
        for(int x = 0;x<rowNum;x++){
            String[] rows = areas.get(x);
            for (int y = 0; y < columnNum; y++) {
                if(rows[y].equals("YES")){
                    yArea.add(new int[]{x,y});
                    continue;
                }
                if(rows[y].equals("NO")){
                    need++;
                }
            }
        }
        int daySum=0;

        if(yArea.size()==0){
            System.out.println(-1);
            return;
        }
        if(need==0){
            System.out.println(0);
        }
        // 开始遍历，当yArea不为空开始扩张，遍历过的坐标进行删除，新增的坐标记录，同时need--，没有新的的坐标后查看need是否为0；
        while(yArea.size()>0 && need>0){
            ArrayList<int[]> newYArea = new ArrayList<int[]>();
            for(int[] pos:yArea){
                int x = pos[0];
                int y = pos[1];

                // 向下
                if(x+1>=0 && x+1<rowNum){
                    if(areas.get(x+1)[y].equals("NO")){
                        need--;
                        newYArea.add(new int[]{(x+1),y});
                        areas.get(x+1)[y]="YES";
                    }
                }
                // 向上
                if(x-1>=0 && x-1<rowNum){
                    if(areas.get(x-1)[y].equals("NO")){
                        need--;
                        newYArea.add(new int[]{(x-1),y});
                        areas.get(x-1)[y]="YES";
                    }
                }
                // 向左
                if(y-1>=0 && y-1<columnNum){
                    if(areas.get(x)[y-1].equals("NO")){
                        need--;
                        newYArea.add(new int[]{x,(y-1)});
                        areas.get(x)[y-1]="YES";
                    }
                }
                // 向右
                if(y+1>=0 && y+1<columnNum){
                    if(areas.get(x)[y+1].equals("NO")){
                        need--;
                        newYArea.add(new int[]{x,(y+1)});
                        areas.get(x)[y+1]="YES";
                    }
                }

            }
            daySum++;
            yArea = newYArea;
        }
        System.out.println(need==0 ? daySum : -1);
    }
}
