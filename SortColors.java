public class SortColors {

    /*
        red, white, blue
        0      1      2
        General Solution:
            QuickSort
    */
    public void sortColors(int[] nums) {
        int low = 0, high = nums.length - 1;
        quickSort(nums, low, high);
    }
    private void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int pi = partition(nums, low, high);

            quickSort(nums, low, pi - 1);
            quickSort(nums, pi + 1, high);
        }
    }
    private int partition(int[] nums, int low, int high) {
        int i = low - 1;
        if (low < high) {
            int pivot = nums[high];

            for (int j = low; j < high; j++) {
                if (nums[j] <= pivot) {
                    i++;

                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
            int tmp = nums[i + 1];
            nums[i + 1] = nums[high];
            nums[high] = tmp;
        }
        return i + 1;
    }

    /*
        specialized at this problem

        only three different numbers
    */

    public void sortColors(int[] nums) {
        int r = 0;
        int i = 0;
        int b = nums.length -1;

        while (i <= b) {
            if (nums[i] == 0) { // if red (red is 0)
                swap(nums, i, r);
                r++;
                i++;
            } else if (nums[i] == 2) { // if blue (blue is 2)
                swap(nums, i, b);
                b--;
            } else { // if white (white is 1) skip
                i++;
            }
        }
    }
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
