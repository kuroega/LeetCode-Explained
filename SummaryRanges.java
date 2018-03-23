public class SummaryRanges {
	/*
		Similar problem to Missing Ranges
			...but here we do not consider duplicates
		exp:
			Input: [0, 2, 3, 4, 6, 8, 9]
			Output: ["0", "2->4", "6", "8->9"]
	*/
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		int n = nums.length;
		if (n == 0) return res;
		if (n == 1) {
			res.add(nums[0] + "");
			return res;
		}
		int i = 1;
		int start = 0, last = 0; // keep track the last number added for later check
		while (i < n) { 
			start = nums[i - 1];
			while (i < n && nums[i - 1] != nums[i] && nums[i - 1] + 1 == nums[i])
				i++;
			res.add(generateRange(start, nums[i - 1]));
			last = nums[i - 1];
            i++;
		}
		// deal with the last discontinuous number
		// exp: [0,1,2,4,5,7]
		//                 ^
		if (last != nums[n - 1]) 
			res.add(generateRange(nums[n - 1], nums[n - 1]));
		return res;
	}

	private String generateRange(int lo, int hi) {
		if (lo == hi)
			return lo + "";
		return lo + "->" + hi;
	}
}