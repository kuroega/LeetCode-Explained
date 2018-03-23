public class TheSkylineProblem {
	/*
		best intuitive solution
			for better understand the problem
		LC: 352ms
	*/
	public List<int[]> getSkyline(int[][] buildings) {
    	List<int[]> res = new ArrayList<>();
    	List<int[]> heights = new ArrayList<>();
    	for (int[] bd : buildings) {
    		// reverse the sign to make sure left higher bar is always before 
    		// left lower bar; also negative sign can indicate a start point
    		heights.add(new int[]{bd[0], -bd[2]}); 
    		heights.add(new int[]{bd[1], bd[2]});
    	}  
    	Collections.sort(heights, (a, b) -> {
    		if (a[0] == b[0]) // pre-validate
    			return a[1] - b[1]; // higher bar is before lower bar
    		return a[0] - b[0]; // in x axis ascending order 
    	});
    	// rewrite the comparator using lambda, create max heap
    	Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a)); 
    	pq.offer(0); // baseline
    	int preHeight = 0; // initial height is zero
    	for (int[] arr : heights) {
    		if (arr[1] < 0) 
    			pq.offer(-arr[1]); // negative height -> left side -> start point
    		else 
    			pq.remove(arr[1]); // positive -> right side -> end point

    		// assign a max height so far in pq
    		int curHeight = pq.peek();

    		// avoid add 2 points for a same building
    		if (preHeight != curHeight) {
    			res.add(new int[]{arr[0], curHeight});
    			preHeight = curHeight;
    		}
    	}
    	return res;
    }

    /*
		a tricky solution: Linked List (KeyPoint)
			much faster in running time
    	LC: 5ms
    */
   	class KeyPoint {
   		public int key;
   		public int height;
   		public KeyPoint next = null;
   		public KeyPoint(int key, int height) {
   			this.key = key;
   			this.height = height;
   		}
   	}
    public List<int[]> getSkyline(int[][] buildings) {
    	List<int[]> res = new ArrayList<>();
    	KeyPoint dummy = new KeyPoint(-1, 0); // dummy head
    	KeyPoint pre = dummy;

  		for (int[] bd : buildings) {
  			int L = bd[0];
  			int R = bd[1];
  			int H = bd[2];

  			while (pre.next != null && pre.next.key <= L)
  				pre = pre.next;

  			int preH = pre.height;

  			if (pre.key == L) 
  				pre.height = Math.max(pre.height, H);
  			else if (pre.height < H) {
  				KeyPoint next = pre.next;
  				pre.next = new KeyPoint(L, H);
  				pre = pre.next;
  				pre.next = next;
  			}

  			KeyPoint preIter = pre;
  			KeyPoint curIter = pre.next;
  			while (curIter != null && curIter.key < R) {
  				preH = curIter.height;
  				curIter.height = Math.max(curIter.height, H);

  				if (curIter.height == preIter.height)
  					preIter.next = curIter.next;
  				else 
  					preIter = curIter;

  				curIter = curIter.next;
  			}

  			if (preIter.height != preH && preIter.key != right && (curIter == null || curIter.key != right)) {
  				KeyPoint next = preIter.next;
  				preIter.next = new KeyPoint(R, preH);
  				preIter.next.next = next;
  			}
  		}

  		KeyPoint first = dummy;
  		KeyPoint second = dummy.next;
  		while (second != null) {
  			if (second.height != first.height) 
  				res.add(new int[]{second.key, second.height});
  			first = first.next;
  			second = second.next;
  		}
  		return res;
    }
}