package problemsolving.leetcode;

public class Solution238 {
    /**
     * 2 <= nums.length <= 10^5
     * -30 <= nums[i] <= 30
     * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
     */
    public int[] productExceptSelf(int[] nums) {
        return productExceptSelf_3(nums);
    }

    /**
     * Time: O(N)
     * Space: O(1)
     */
    public int[] productExceptSelf_1(int[] nums) {
        int zeros = 0;
        int products = 1;
        for (int num : nums) {
            if (num == 0) {
                zeros++;
            } else {
                products *= num;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (zeros > 0) {
                if (nums[i] == 0 && zeros == 1) {
                    nums[i] = products;
                } else {
                    nums[i] = 0;
                }
            } else {
                nums[i] = products / nums[i];
            }
        }

        return nums;
    }

    /**
     * Time: O(N)
     * Space: O(N)
     */
    public int[] productExceptSelf_2(int[] nums) {
        int n = nums.length;
        // leftward product [0, i)
        int[] leftwardProducts = new int[n];
        leftwardProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftwardProducts[i] = leftwardProducts[i - 1] * nums[i - 1];
        }

        // rightward product from (i, n-1]
        int[] rightwardProducts = new int[n];
        rightwardProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightwardProducts[i] = rightwardProducts[i + 1] * nums[i + 1];
        }

        // leftward product * rightward product
        for (int i = 0; i < n; i++) {
            nums[i] = leftwardProducts[i] * rightwardProducts[i];
        }

        return nums;
    }

    /**
     * Time: O(N)
     * Space: O(1)
     * Follow up: Can you solve the problem in O(1) extra space complexity?
     * (The output array does not count as extra space for space complexity analysis.)
     */
    public int[] productExceptSelf_3(int[] nums) {
        int n = nums.length;
        // leftward product [0, i)
        int[] leftwardProducts = new int[n];
        leftwardProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftwardProducts[i] = leftwardProducts[i - 1] * nums[i - 1];
        }

        // rightward product from (i, n-1]
        int rightwardProduct = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightwardProduct = rightwardProduct * nums[i + 1];
            // leftward product * rightward product
            leftwardProducts[i] = leftwardProducts[i] * rightwardProduct;
        }

        return leftwardProducts;
    }

    public static void main(String[] args) {
        Solution238 solution238 = new Solution238();
        int[] answer;
        answer = solution238.productExceptSelf(new int[]{1, 2, 3, 4});
        answer = solution238.productExceptSelf(new int[]{-1, 1, 0, -3, 3});
    }
}
