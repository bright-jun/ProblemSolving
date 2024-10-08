package problemsolving.leetcode;

import java.util.HashMap;

public class Solution169 {
    /**
     * n == nums.length
     * 1 <= n <= 5 * 10^4
     * -10^9 <= nums[i] <= 10^9
     */
    public int majorityElement(int[] nums) {
        return majorityElement_2(nums);
    }

    /**
     * Time: O(N)
     * Space: O(N)
     */
    public int majorityElement_1(int[] nums) {
        int half = nums.length / 2;
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
            if (hm.get(num) > half) {
                return num;
            }
        }

        return -1;
    }

    /**
     * Time: O(N)
     * Space: O(1)
     * Solution: https://sgc109.github.io/2020/11/30/boyer-moore-majority-vote-algorithm/
     * Boyer-Moore Majority Vote Algorithm
     */
    public int majorityElement_2(int[] nums) {
        int count = 0;
        int candidate = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                // next candidate
                candidate = nums[i];
            }

            if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }


    public static void main(String[] args) {
        Solution169 solution169 = new Solution169();
        int answer;
        answer = solution169.majorityElement(new int[]{3, 2, 3});
        answer = solution169.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2});
    }
}
