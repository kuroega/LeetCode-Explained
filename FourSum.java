public class FourSum {
    /*
        Four sum (iterative)

        Think about Three sum

        Recall: three sum ( two pointer solution)
        1. Sort numbers[]
        2. Iterate numbers[]
        3. Set left right pointers
        4. Set sum=target-numbers[i]
        5. Move two pointers to find the final result
        6. If match then add(i, left, right)

        How to apply that in 4Sum?

        1. Sort numbers[]
        2. Iterate numbers[] from i
        3. Set sum1=target-numbers[i]
        4. Iterate numbers[] from j=i+1
        5. Set newTarget=sum1
        6. Set left right pointers
        7. Set sum2=newTarget-numbers[j]
        8. Move two pointers to find the final result
        9. If match then add(i, j, left, right)
    */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); // sort
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;

        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) { // dedupe
                int sum1 = target - nums[i];
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == i + 1 || (j > i + 1 && nums[j] != nums[j - 1])) {
                        int newTarget = sum1;
                        int left = j + 1, right = nums.length - 1, sum2 = newTarget - nums[j];
                        while (left < right) {
                            if (nums[left] + nums[right] == sum2) {
                                res.add(new ArrayList<>(Arrays.asList(nums[i],
                                    nums[j], nums[left], nums[right])));

                                while (left < right && nums[left] == nums[left + 1])
                                    left++; // dedupe
                                while (left < right && nums[right] == nums[right - 1])
                                    right--; // dedupe

                                left++; right--;
                            }
                            else if (nums[left] + nums[right] < sum2)
                                left++;
                            else right--;
                        }
                    }
                }
            }
        }
        return res;
    }
}
