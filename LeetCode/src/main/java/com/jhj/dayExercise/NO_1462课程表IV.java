package com.jhj.dayExercise;

import org.junit.Test;

import java.util.*;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-13 0:34
 * @description 你总共需要上 numCourses 门课，课程编号依次为 0 到 numCourses-1 。
 * 你会得到一个数组prerequisite，其中prerequisites[i]=[ai, bi]表示如果你想选bi课程你必须先选 ai课程。
 * 有的课会有直接的先修课程，比如如果想上课程 1 ，你必须先上课程 0 ，那么会以 [0,1] 数对的形式给出先修课程数对。
 * 先决条件也可以是间接的。如果课程a是课程b的先决条件，课程b是课程c的先决条件，那么课程 a 就是课程 c 的先决条件。
 *
 * 你也得到一个数组queries ，其中queries[j] = [uj, vj]。对于第j个查询，您应该回答课程uj是否是课程vj的先决条件。
 * 返回一个布尔数组 answer ，其中 answer[j] 是第 j 个查询的答案。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * 输出：[false,true]
 * 解释：课程 0 不是课程 1 的先修课程，但课程 1 是课程 0 的先修课程。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * 输出：[false,false]
 * 解释：没有先修课程对，所以每门课程之间是独立的。
 *
 * 示例 3：
 * 输入：numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * 输出：[true,true]
 *
 * 提示：
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 每一对 [ai, bi] 都 不同
 * 先修课程图中没有环。
 * 1 <= queries.length <= 104
 * 0 <= ui, vi <= n - 1
 * ui != vi
 */
public class NO_1462课程表IV {
    @Test
    public void test() {
        int numCourses = 5;
        int[][] prerequisites = {{4,3},{4,1},{4,0},{3,2},{3,1},{3,0},{2,1},{2,0},{1,0}};
        int[][] queries = {{1,4},{4,2},{0,1},{4,0},{0,2},{1,3},{0,1}};
        // List<Boolean> results = checkIfPrerequisite(numCourses,prerequisites,queries);
        List<Boolean> results = checkIfPrerequisite1(numCourses,prerequisites,queries);
        for (Boolean result:results) {
            System.out.println(result);
        }
    }
    HashMap<Integer, HashSet<Integer>> preRequisit;

    // 按照prerequisites遍历，存储每门课程的直接先决课程，用set存储
    // 深度搜索查找queries结果
    // 运行结果:超出时间限制
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int preLength = prerequisites.length;
        preRequisit = new HashMap();
        for (int i = 0; i < numCourses; i++) {
            preRequisit.put(i,new HashSet<>());
        }
        for (int i = 0; i < preLength; i++) {
            int[] prerequist = prerequisites[i];
            int firstCou = prerequist[0], cou = prerequist[1];
            HashSet<Integer> set = preRequisit.get(cou);
            set.add(firstCou);
        }

        // 开始判断是否是先修课程
        List<Boolean> result = new ArrayList();
        int queryLength = queries.length;
        for(int j = 0;j < queryLength;j++){
            int[] query =  queries[j];
            int first = query[0];
            int course = query[1];
            result.add(dfsCheck(first,preRequisit.get(course)));
        }
        return result;
    }

    private Boolean dfsCheck(int first, HashSet<Integer> sets) {
        if (sets.size()<=0){
            return false;
        }
        if(sets.contains(first)){
            return true;
        }
        boolean result = false;
        for (int set:sets) {
            result = result|dfsCheck(first,preRequisit.get(set));
            if(result){
                break;
            }
        }
        return result;
    }

    public List<Boolean> checkIfPrerequisite1(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            g[i] = new ArrayList<Integer>();
        }
        int[] indgree = new int[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            ++indgree[p[1]];
            g[p[0]].add(p[1]);
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indgree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int ne : g[cur]) {
                isPre[cur][ne] = true;
                for (int i = 0; i < numCourses; ++i) {
                    isPre[i][ne] = isPre[i][ne] | isPre[i][cur];
                }
                --indgree[ne];
                if (indgree[ne] == 0) {
                    queue.offer(ne);
                }
            }
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            res.add(isPre[query[0]][query[1]]);
        }
        return res;
    }
}
