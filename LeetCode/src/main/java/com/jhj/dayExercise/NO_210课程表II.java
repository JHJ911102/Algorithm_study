package com.jhj.dayExercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-10 11:39
 * @description 现在你总共有numCourses门课需要选，记为0到numCourses-1。
 * 给你一个数组prerequisites，其中prerequisites[i]=[ai,bi]表示在选修课程ai前必须先选修bi。
 *
 * 例如，想要学习课程0，你需要先完成课程1 ，我们用一个匹配来表示：[0,1]。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
 * 如果不可能完成所有课程，返回一个空数组 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 *
 * 示例 2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 *
 * 示例 3：
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */
public class NO_210课程表II {
    @Test
    public void test() {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(findOrder(numCourses,prerequisites)));
    }

    // 存放每个可能学习后可以学习的课程，因此在递归搜索的时候，最终找到的就是最后学的，结果数组的值从最末尾开始填充
    List<List<Integer>> edges;
    // 是否存在环
    boolean isCircle = false;
    // 各课程状态 0:未搜索；1：搜索中；2：搜索完毕
    int[] status;
    // 排序结果
    int[] result;
    // 待填充结果下标
    int index;

    /**
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 思路一:排序，考虑list,前后关系用map。key找value，接着value做为key继续往下找
        /*HashMap<Integer,Integer> map = new HashMap();
        for (int[] course:prerequisites) {
            map.put(course[1],course[0]);// 这里意识到一个问题，假如出现{{1,0},{2,0}}就会被覆盖了，感觉行不通，换思路
        }
        return null;*/

        // 思路二:先创建一个0~numCourses-1的递增数组，按照prerequisites挨个替换位置，再用一个map记录位置
        /*int[] result = new int[numCourses];
        HashMap<Integer,Integer> map = new HashMap();
        for (int i = 0; i < numCourses; i++) {
            result[i] = i;
            map.put(i,i);//课程对应的下标号
        }

        for (int[] course:prerequisites) {
            int a = course[0];
            int b = course[1];
            // b课程要在a课程前面
            int aindex = map.get(a);
            int bindex = map.get(b);
            // 交换位置,要先判断当前是否满足要求
            if(bindex>aindex){
                map.put(a,bindex);
                map.put(b,aindex);
                result[aindex]=b;
                result[bindex]=a;
            }
        }
        // 存在环{{1,0},{2,1},{0,2}},不行，换思路
        return result;*/

        // 脑子笨，没有思路，看答案解析T_T，好像用的递归
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }
        // 初始化不存在环状态
        isCircle = false;
        status = new int[numCourses];
        result = new int[numCourses];
        index = numCourses-1;
        // 存储后学可能，从做末尾开始填充；思考可不可以存放前置课程，从头开始填充结果
        for (int[] course:prerequisites) {
            edges.get(course[1]).add(course[0]);
        }

        for (int i = 0; i < numCourses && !isCircle; i++) {
            if(status[i]!=0){
                dfs(i);
            }
        }

        if (isCircle){
            return new int[0];
        }
        return result;
    }

    public void dfs(int i){
        status[i]=1;
        for(int course:edges.get(i)){
            if(status[course]==1){
                isCircle = true;
                return;
            }

            if(status[course]==0){
                dfs(course);
                if(isCircle){
                    return;
                }
            }
        }
        status[i]=2;
        result[index--] = i;
    }
}

