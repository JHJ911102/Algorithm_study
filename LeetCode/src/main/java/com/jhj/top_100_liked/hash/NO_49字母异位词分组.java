package com.jhj.top_100_liked.hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author JHJ911102
 * @version 1.0
 * @date 2023-09-10 20:27
 * @description 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class NO_49字母异位词分组 {
    @Test
    public void test() {
        String[] nums = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> resultList = groupAnagrams(nums);
        for (List<String> results:resultList) {
            for (String result:results) {
                System.out.print(result+" ");
            }
            System.out.println();
        }

    }

    // 将素组中所有单词中的元素排序，将排序后的字符串做为key，排序前的做为值放到map<String，list>中
    // 最后将map中的值遍历放到list中
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> sortResults = new HashMap<>();
        for (String str:strs) {
            char[] strChar = str.toCharArray();
            Arrays.sort(strChar);
            String sortStr = new String(strChar);
            List<String> list = sortResults.getOrDefault(sortStr,new ArrayList<String>());
            list.add(str);
            sortResults.put(sortStr,list);
        }
        // 这里用到结构间转换的api。要时常回顾
        return new ArrayList<List<String>>(sortResults.values());
    }
}
