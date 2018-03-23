public KthLargestElementInAnArray {
	/*
		use builtin array sort:

			Time: O(NlogN)
			Space: O(1)
	*/
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}
	/*
		based on Heap Sort solution:

			Time: O(NlogK)
			Space: O(K) -> K is the largest value

    */
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int i = 0;
		for (int n : nums) {
			pq.add(n);
			i++;
			if (i > k) 
				pq.poll();
		}
		return pq.peek();
	}

	// !!!
	// implement a real min heap
	// !!!
	public int findKthLargest(int[] nums, int k) {
		int[] heap = new int[k];
		for (int i = 0; i != k; i++)
			insert(heap, nums[i], i);

		for (int i = k; i < nums.length; i++) {
			if (nums[i] > heap[0]) {
				heap[0] = nums[i];
				heapify(heap, 0, k);
			}
		}
		return heap[0];
	}
	private void insert(int[] heap, int n, int i) {
		heap[i] = n;
		while (i != 0) {
			int parent = i >>> 1; // parent = i >>> 1 in jdk
			if (heap[parent] > heap[i]) {
				swap(heap, parent, i);
				i = parent;
			} else {
				break;
			}
		}
	}
	private void heapify(int[] heap, int i, int heapSize) {
		int left = (i << 1) + 1;
		int right = left + 1;
		int largest = i;
        if (left < heapSize && heap[left] < heap[i])
            largest = left;
        if (right < heapSize && heap[right] < heap[largest])
            largest = right;
        if (largest != i) {
            swap(heap, largest, i);

            heapify(heap, largest, heapSize);
        }
	}
    private void swap(int[] heap, int p, int i) {
		int tmp = heap[i];
		heap[i] = heap[p];
		heap[p] = tmp;
	}

	/*
		based on Quick Sort:

			Time: O(N) best case, O(N^2) worst case
			Space: O(1)
	*/
	public int findKthLargest(int[] nums, int k) {
		
    }	

    /*
		randomized quick sort:

			Time: O(N) guaranteed
			space: O(1)
    */ 
	public int findKthLargest(int[] nums, int k) {

	}

}