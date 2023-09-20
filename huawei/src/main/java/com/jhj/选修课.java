package com.jhj;

import java.util.*;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-20 21:29
 * @description 现有两门选修课，每门选修课都有一部分学生选修，每个学生都有选修课的成绩，
 * 需要你找出同时选修了两门选修课的学生，先按照班级进行划分，班级编号小的先输出，
 * 每个班级按照两门选修课成绩和的降序排序，成绩相同时按照学生的学号升序排序。
 *
 * 输入描述
 * 第一行为第一门选修课学生的成绩，第二行为第二门选修课学生的成绩，每行数据中学生之间以英文分号分隔，
 * 每个学生的学号和成绩以英文逗号分隔，
 * 学生学号的格式为8位数字(2位院系编号+入学年份后2位+院系内部1位专业编号+所在班级3位学号)，
 * 学生成绩的取值范围为[0,100]之间的整数，两门选修课选修学生数的取值范围为[1-2000]之间的整数。
 *
 * 输出描述
 * 同时选修了两门选修课的学生的学号，如果没有同时选修两门选修课的学生输出NULL，
 * 否则，先按照班级划分，班级编号小的先输出，每个班级先输出班级编号(学号前五位)，
 * 然后另起一行输出这个班级同时选修两门选修课的学生学号，
 * 学号按照要求排序(按照两门选修课成绩和的降序，成绩和相同时按照学号升序)，学生之间以英文分号分隔。
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 01202021,75;01201033,95;01202008,80;01203006,90;01203088,100
 * 01202008,70;01203088,85;01202111,80;01202021,75;01201100,88
 *
 * 输出
 * 01202
 * 01202008;01202021
 * 01203
 * 01203088
 * 样例说明
 * 同时选修了两门选修课的学生01202021、01202008、01203088，
 * 这三个学生两门选修课的成绩和分别为150、150、185，01202021、01202008属于01202班的学生，
 * 按照成绩和降序，成绩相同时按学号升序输出的结果为01202008;01202021,01203088属于01203班的学生，
 * 按照成绩和降序，成绩相同时按学号升序输出的结果为01203088，01202的班级编号小于01203的班级编号，需要先输出。
 */
public class 选修课 {
    public static void main(String[] args) {
        // 先转成数组，再遍历放到map中，找到有没有选两门课的同学，排序后面再考虑
        Scanner sc = new Scanner(System.in);
        String[] sources1 = sc.nextLine().split(";");
        String[] sources2 = sc.nextLine().split(";");
        HashMap<String, List<Integer>> studentScore = new HashMap<>();
        for (String source1: sources1) {
            String student = source1.split(",")[0];
            Integer score = Integer.parseInt(source1.split(",")[1]);
            List<Integer> list =  studentScore.getOrDefault(student,new ArrayList<>());
            list.add(score);
            studentScore.put(student,list);
        }
        for (String source2: sources2) {
            String student = source2.split(",")[0];
            Integer score = Integer.parseInt(source2.split(",")[1]);
            List<Integer> list =  studentScore.getOrDefault(student,new ArrayList<>());
            list.add(score);
            studentScore.put(student,list);
        }
        // 接下来遍历看有没有list长度为5的，若没有返回null；若有创建一个三维数组存放，班级，学号，成绩和再遍历排序，最后输出
        // 该方法较麻烦，需要循环遍历5次
        List<String> resultTmp = new ArrayList<>();
        for (Map.Entry<String,List<Integer>> student:studentScore.entrySet()) {
            String classNo = student.getKey();
            List<Integer> scores = student.getValue();
            if (scores.size()==2){
                resultTmp.add(classNo);
            }
        }

        String[][] info = new String[resultTmp.size()][3];
        for (int i = 0; i < resultTmp.size(); i++) {
            info[i][0] = resultTmp.get(i).substring(0,5);
            info[i][1] = resultTmp.get(i);
            info[i][2] = studentScore.get(resultTmp.get(i)).get(0)+studentScore.get(resultTmp.get(i)).get(1)+"";
        }
        // 输出排序有点麻烦
        Arrays.sort(info);
    }
}
