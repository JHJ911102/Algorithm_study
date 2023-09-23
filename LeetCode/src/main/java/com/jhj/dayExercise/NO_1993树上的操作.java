package com.jhj.dayExercise;

import org.junit.Test;

import java.util.*;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-23 11:27
 * @description 给你一棵 n 个节点的树，编号从 0 到 n - 1 ，以父节点数组 parent 的形式给出，其中 parent[i] 是第 i 个节点的父节点。树的根节点为 0 号节点，所以 parent[0] = -1 ，因为它没有父节点。你想要设计一个数据结构实现树里面对节点的加锁，解锁和升级操作。
 *
 * 数据结构需要支持如下函数：
 *
 * Lock：指定用户给指定节点 上锁 ，上锁后其他用户将无法给同一节点上锁。只有当节点处于未上锁的状态下，才能进行上锁操作。
 * Unlock：指定用户给指定节点 解锁 ，只有当指定节点当前正被指定用户锁住时，才能执行该解锁操作。
 * Upgrade：指定用户给指定节点 上锁 ，并且将该节点的所有子孙节点 解锁 。只有如下 3 个条件 全部 满足时才能执行升级操作：
 * 指定节点当前状态为未上锁。
 * 指定节点至少有一个上锁状态的子孙节点（可以是 任意 用户上锁的）。
 * 指定节点没有任何上锁的祖先节点。
 * 请你实现 LockingTree 类：
 *
 * LockingTree(int[] parent) 用父节点数组初始化数据结构。
 * lock(int num, int user) 如果 id 为 user 的用户可以给节点 num 上锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 id 为 user 的用户 上锁 。
 * unlock(int num, int user) 如果 id 为 user 的用户可以给节点 num 解锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 变为 未上锁 状态。
 * upgrade(int num, int user) 如果 id 为 user 的用户可以给节点 num 升级，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 升级 。
 */
public class NO_1993树上的操作 {
    @Test
    public void test() {
        int[] parent = {-1,0,3,1,0};
        LockingTree lockingTree = new LockingTree(parent);
        /*System.out.println(lockingTree.lock(2,2));
        System.out.println(lockingTree.unlock(2,3));
        System.out.println(lockingTree.unlock(2,2));
        System.out.println(lockingTree.lock(4,5));
        System.out.println(lockingTree.upgrade(0,1));
        System.out.println(lockingTree.lock(0,1));*/

        System.out.println(lockingTree.upgrade(4,5));
        System.out.println(lockingTree.upgrade(3,8));
        System.out.println(lockingTree.unlock(0,7));
        System.out.println(lockingTree.lock(2,7));
        System.out.println(lockingTree.upgrade(4,6));
    }

    // 定义一个树对象？
    // 用node只能从头开始遍历，有点麻烦
    /*class Node{
        int index;//当前位置
        Node father;//父节点
        List<Node> childs;//子节点
    }*/

    // 用一个map存放上的锁及上锁用户？
    class LockingTree { // LockingTree1改进
        // [-1, 0, 0, 1, 1, 2, 2]
        HashMap<Integer,Integer> lock_user = new HashMap<>(); // 改成数组
        HashMap<Integer,List<Integer>> lock_childs = new HashMap<>();// 改成数组
        int[] locks;
        public LockingTree(int[] parent) {
            locks = parent;
            for (int i = 1; i < parent.length; i++) {
                int father = parent[i];
                List<Integer> chlids = lock_childs.getOrDefault(father,new ArrayList<>());
                chlids.add(i);
                lock_childs.put(father,chlids);
            }
        }

        public boolean lock(int num, int user) {
            if (lock_user.containsKey(num)) {
                return false;
            }
            lock_user.put(num, user);
            return true;
        }

        public boolean unlock(int num, int user) {
            if (!lock_user.containsKey(num)) {
                return false;
            }
            if (lock_user.get(num) == user) {
                lock_user.remove(num);
                return true;
            }
            return false;
        }

        // 用一个list来管理上下级关系 有点复杂
        // 用一个map管理上下级关系？ 只能单向找，不能同时查找上下级。但是集合数组可以找到父锁
        public boolean upgrade(int num, int user) {
            if (lock_user.containsKey(num)) {
                return false;
            }
            int fatherIndex = locks[num];
            // 最终都会找到祖先0位置
            while(fatherIndex>0) {
                if(lock_user.containsKey(fatherIndex)){
                    return false;
                }
                fatherIndex = locks[fatherIndex];
            }
            // 存放上锁的子孙节点,方便后面解锁
            List<Integer> lockedhilds = new ArrayList<>();
            // 递归查找
            lockForLockedChild(lockedhilds, num);
            if(lockedhilds.size()<=0){
                return false;
            }
            lock_user.put(num, user);
            for (int locked:lockedhilds) {
                lock_user.remove(locked);
            }
            return true;
        }
        private void lockForLockedChild(List<Integer> lockedhilds, int num){
            List<Integer> childs = lock_childs.get(num);
            if(Objects.isNull(childs)){
                return;
            }
            for (int child:childs) {
                if(lock_user.containsKey(child)){
                    lockedhilds.add(child);
                }
                lockForLockedChild(lockedhilds, child);
            }
        }
    }







    class LockingTree1 { // 超时了，LockingTree改进
        // [-1, 0, 0, 1, 1, 2, 2]
         // 改成数组
        int[] locks;
        int[] lock_user;
        List<Integer>[] lock_childs;
        public LockingTree1(int[] parent) {
            int n = parent.length;
            locks = parent;
            lock_user = new int[n];
            lock_childs = new List[n];
            Arrays.fill(lock_user,-1);
            for (int i = 1; i < n; i++) {
                // 找到父节点坐标
                int fatherIndex = parent[i];
                List<Integer> father = lock_childs[fatherIndex];
                if(Objects.isNull(father)){
                    father = new ArrayList<>();
                }
                father.add(i);
                lock_childs[fatherIndex] = father;
            }
        }

        public boolean lock(int num, int user) {
            if (lock_user[num]==-1) {
                lock_user[num] = user;
                return true;
            }
            return false;
        }

        public boolean unlock(int num, int user) {
            if (lock_user[num]==user) {
                lock_user[num] = -1;
                return true;
            }
            return false;
        }

        /*int[] locks;
        int[] lock_user;
        List<Integer>[] lock_childs;*/
        public boolean upgrade(int num, int user) {
            if (lock_user[num]!=-1) {
                return false;
            }
            int fatherIndex = locks[num];
            // 最终都会找到祖先0位置
            while(fatherIndex!=-1) {
                if(lock_user[fatherIndex]!=-1){
                    return false;
                }
                fatherIndex = locks[num];
            }

            // 存放上锁的子孙节点,方便后面解锁
            List<Integer> lockedhilds = new ArrayList<>();
            // 递归查找
            lockForLockedChild(lockedhilds, num);
            if(lockedhilds.size()<=0){
                return false;
            }
            lock_user[num]=user;
            for (int locked:lockedhilds) {
                lock_user[locked]=-1;
            }
            return true;
        }
        private void lockForLockedChild(List<Integer> lockedhilds, int num){
            List<Integer> childs = lock_childs[num];
            if(Objects.isNull(childs)){
                return;
            }
            for (int child:childs) {
                if(lock_user[child]!=-1){
                    lockedhilds.add(child);
                }
                lockForLockedChild(lockedhilds, child);
            }
        }
    }
}
