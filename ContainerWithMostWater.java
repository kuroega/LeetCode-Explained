public class ContainerWithMostWater {
    /*
        Two Pointer Solution
        Time: O(N)
        Space: O(1)
    */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1; // two pointer
        int max = 0;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            max = Math.max(max, minHeight * (right - left));
            // update minHeight and Max first (don't miss initial case)

            while (left < right && height[left] <= minHeight) {
                left++;
            } // reduce left
            while (left < right && height[right] <= minHeight) {
                right--;
            } // reduce right
        }
        return max;
    }
}
