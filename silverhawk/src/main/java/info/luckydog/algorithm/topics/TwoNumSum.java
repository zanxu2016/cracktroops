package info.luckydog.algorithm.topics;

import org.junit.Test;

import java.util.*;

/**
 * TwoNumSum
 * 数组中两数之和等于指定数
 *
 * @author eric
 * @since 2019/7/3
 */
public class TwoNumSum {

    @Test
    public void twoNumSumTest() {

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("twoNumSum index array: " + Arrays.toString(twoNumSum(nums, target)));
        System.out.println("twoNumSum2 index array: " + Arrays.toString(twoNumSum2(nums, target)));

    }

    /**
     * 暴力枚举法 时间复杂度O(n^2)，空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoNumSum(int[] nums, int target) {

        int[] numsTarget;
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (sub == nums[j]) {
                    numsTarget = new int[2];
                    numsTarget[0] = i;
                    numsTarget[1] = j;
                    return numsTarget;
                }
            }
        }

        return null;
    }

    /**
     * 一遍哈希表 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoNumSum2(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub)) {
                return new int[]{map.get(sub), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
