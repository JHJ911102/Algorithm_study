package com.jhj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-23 10:05
 * @description 张兵和王武是五子棋迷，工作之余经常切磋棋艺。这不，这会儿又下起来了。
 * 走了一会儿，轮张兵了，对着一条线思考起来了，这条线上的棋子分布如下：
 * 用数组表示：-1 0 1 1 1 0 1 0 1 -1
 * 棋子分布说明
 * 1.-1代表白子，0代表空位，1代表黑子
 * 2.数组长度L.满足1<L<40,且L为奇数
 *
 * 你得帮他写一个程序，算出最有利的出子位置。最有利定义：
 * 1.找到一个空位(0),用棋子（1，-1）填充该位可以使得当前子的最大连续长度变大；
 * 2.如果存在多个位置，返回最靠近中间的较小的那个坐标
 * 3.如果不存在可行位置，直接返回-1；
 * 4.连续长度不能超过5个（五子棋约束）
 * 输入描述
 * 第一行，当前出子颜色
 * 第二行：当前的棋局状态
 * 输出描述
 * 1个整数，表示出子位置的数组下标
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 1
 * -1 0 1 1 1 0 1 0 1 -1 1
 * 输出
 * 5
 * 说明
 * 当前为黑子1，放置在下标为5的位置，黑子的最大连续长度，可以由3到5。
 */
public class 五子棋迷 {
    // 第一反应思路:挨个遍历记录0的位置和最大长度，不好
    // 找0位置往两边遍历
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nextColour = sc.nextInt();
        sc.nextLine();
        int[] colours = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        int maxLength = Integer.MIN_VALUE;
        int index = -1;
        int midDistance = 0;

        for (int i = 0; i < colours.length; i++) {
            if (colours[i]==0){
                int left = i-1;
                int right = i+1;
                int leftDistance = 0;
                int rightDistance = 0;

                for (int j = left; j >=0 ; j--) {
                    if(colours[j]==nextColour){
                        leftDistance++;
                    }else{
                        break;
                    }
                }

                for (int j = right; j < colours.length ; j++) {
                    if(colours[j]==nextColour){
                        rightDistance++;
                    }else{
                        break;
                    }
                }
                int allDistance = leftDistance+rightDistance;
                if(allDistance>5){
                    continue;
                }

                int indexDistance = Math.abs(i-colours.length/2);
                // 大于直接替换，等于要比较距离
                if(allDistance>maxLength){
                    maxLength = allDistance;
                    index=i;
                    midDistance = indexDistance;
                }else if(allDistance==maxLength && indexDistance<midDistance){
                    index=i;
                    midDistance = indexDistance;
                }
            }
        }
        System.out.println(index);
    }
}
