pubic class TrappingRainWater {
    /*
        Brute Force
        scan current to left and to right, water+= min(maxL, maxR) - a[i]
        Time: O(N^2)
        Space: O(1)
    */
    public int trap(int[] height) {
        int water = 0;
        for (int i = 1; i < height.length; i++) {
            int maxL = height[i], maxR = height[i];
            for (int j = i; j >= 0; j--) {
                maxL = Math.max(maxL, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                maxR = Math.max(maxR, height[j]);
            }
            water += Math.min(maxL, maxR) - height[i];
        }
        return water;
    }

    /*
        DP
        create a table to store the maxL and maxR for i
        from i = 1 to i = nums.length - 1
        Time: O(N)
        Space: O(N)
    */
    public int trap(int[] height) {
        int water = 0;
        int n = height.length;
        int[] a_maxL = new int[n];
        int[] a_maxR = new int[n];
        int maxL = 0, maxR = 0;

        for (int i = 0; i < n; i++) {
            maxL = Math.max(maxL, height[i]);
            a_maxL[i] = maxL;
        } // from i = 0 to right, record maxL to a_maxL

        for (int i = n - 1; i >= 0; i--) {
            maxR = Math.max(maxR, height[i]);
            a_maxR[i] = maxR;
        } // from i = height.length - 1 to left, record maxR to a_maxR;

        for (int i = 0; i < n; i++) {
            water += Math.min(a_maxL[i], a_maxR[i]) - height[i];
        } // accumulate water by 'min(a_maxL[i], a_maxR[i]) - height[i]'

        return water;
    }

    /*
        Two Pointers Solution
        Time: O(N)
        Space: O(1)
    */
    public int trap(int[] height) {
        int water = 0;
        int left = 0, right = height.length - 1;
        // two pointers

        int maxL = 0, maxR = 0;
        // initialize maxL, maxR

        while (left < right) {
            if (height[left] < height[right]) { // update the smaller side
                if (height[left] > maxL)
                    maxL = height[left]; // update maxL if h[i] > maxL
                else
                    water += maxL - height[left];// update water if h[i] <= maxL
                left++;
            } else { // update the smaller side
                if (height[right] > maxR)
                    maxR = height[right]; // update maxR if h[i] > maxR
                else
                    water += maxR - height[right]; // update water if h[i] <= maxR
                right--;
            }
        }
        return water;
    }

    /*
        another way to understand Two Pointers
    */
    public int trap(int[] height) {
        int water = 0;
        int left = 0, right = height.length - 1;

        while (left < right && height[left] < height[left + 1]) left++;
        while (left < right && height[right] < height[right - 1]) right--;

        while (left < right) {
            int l = height[left];
            int r = height[right];
            if (l < r) {
                while (left < right && l > height[++left])
                    water += l - height[left];
            } else {
                while (left < right && r > height[--right])
                    water += r - height[right];
            }
        }
        return water;
    }

}
