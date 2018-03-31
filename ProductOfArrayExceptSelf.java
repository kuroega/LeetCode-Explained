public class ProductOfArrayExceptSelf {
	public int[] productOfArrayExceptSelf(int[] nums) {
      int[] res = new int[nums.length];
      for (int i = 0, tmp = 1; i < nums.length; i++) {
          res[i] = tmp;
          tmp *= nums[i];
      }
      for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
          res[i] = tmp;
          tmp *= nums[i];
      }
      return res;
	}
}

/*

Bidirectional 


    a   b   c   d

->  1   a  ab  abc 

   bcd  cd  d   1  <-    


*/