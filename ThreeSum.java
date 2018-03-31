public class ThreeSum {
    /*
        3-Sum by 2-Sum (bi-directional)
    */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // sort
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) { // iteration
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) { // dedupe for sum
                int left = i + 1, right = nums.length - 1, sum = 0 - nums[i];
                while (left < right) { // bi-directional 2 Sum
                    // binary search
                    if (nums[left] + nums[right] == sum) {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left],
                            nums[right])));

                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--; // dedupe for 2 sum

                        left++; right--; // move to next different number 
                    } else if (nums[left] + nums[right] < sum) left++;
                    else right--;
                }
            }
        }
        return res;
    }


}

public static void main(String[] args) {
    
}