package com.jhj;

import org.apache.commons.lang3.StringUtils;

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
public class 选修课_优化方案 {
    public static void main(String[] args) {
        // 先转成数组，再遍历放到map中，找到有没有选两门课的同学，排序后面再考虑
        Scanner sc = new Scanner(System.in);
        String[] one = sc.nextLine().split(";");
        String[] two = sc.nextLine().split(";");
        HashMap<String, List<Student>> studentScore = new HashMap<>();
        for (int i = 0; i < one.length; i++) {
            String sourceNo1 = one[i].split(",")[0];
            for (int j = 0; j < two.length; j++) {
                String sourceNo2 = two[j].split(",")[0];
                if(StringUtils.equals(sourceNo1,sourceNo2)){
                    String classNo = sourceNo1.substring(0,5);
                    Integer score1 = Integer.parseInt(one[i].split(",")[1]);
                    Integer score2 = Integer.parseInt(two[j].split(",")[1]);
                    int allScore = score2+score1;
                    Student student = new Student(sourceNo1,allScore);
                    List<Student> list = studentScore.getOrDefault(classNo,new ArrayList<>());
                    list.add(student);
                    studentScore.put(classNo,list);
                }
            }
        }
        if(studentScore.size()<=0){
            System.out.println("null");
            return;
        }
        List<Map.Entry<String, List<Student>>> mapList = new ArrayList<>(studentScore.entrySet());
        mapList.sort((a, b) -> {
            if (a.getKey().compareTo(b.getKey()) < 0) {
                return -1;
            }
            return 1;
        });
        for (Map.Entry<String, List<Student>> m : mapList) {
            //输出班级编号
            System.out.println(m.getKey());
            String res = "";
            //对学生按照要求排序
            Collections.sort(m.getValue());
            for (Student s : m.getValue()) {
                res += s.stuId + ";";
            }
            System.out.println(res.substring(0, res.length() - 1));
        }
    }

    static class Student implements Comparable<Student>{
        String stuId;
        int score;

        public Student(String stuId, int score) {
            this.stuId = stuId;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            if (this.score > o.score) {
                return -1;
            } else if (this.score == o.score) {
                if (this.stuId.compareTo(o.stuId) > 0) {
                    return 1;
                }
                return -1;
            }
            return 1;
        }
    }
}
