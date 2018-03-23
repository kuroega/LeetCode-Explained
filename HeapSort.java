public class HeapSort {
	public void sort(int[] arr) {
		int n = arr.length;
		
		// build max heap
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, i, n);

		// extract heap 
		for (int i = n - 1; i >= 0; i--) {
			// swap the largest number(root) with the current last number
			int tmp = arr[i];
			arr[i] = arr[0];
			arr[0] = tmp;

			heapify(arr, 0, heapSize);
		}
	}

	private void heapify(int[] arr, int i, int heapSize) {
		int largest = i;
		int left = largest << 1 + 1;
		int right = left + 1;
		if (left < heapSize && arr[left] > arr[largest])
			largest = left;
		if (right < heapSize && arr[right] > arr[largest])
			largest = right;
		if (largest != i) {
			int tmp = arr[largest];
			int arr[largest] = arr[i];
			arr[i] = tmp;

			heapify(arr, largest, heapSize);
		}

	}
}