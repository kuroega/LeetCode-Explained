public class MergeKSortedLists {
    /*
        Brute Force Solution    Time: O(NlogN)
                                Space: O(N)
    */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode current = lists[i];
            while (current != null) {
                list.add(current.val);
                current = current.next;
            }
        } // iterate all nodes and add their values in a new list

        if (list.size() == 0) return null; // avoid "[[],[],[]]"

        Collections.sort(list); // sort list: O(NlogN)

        ListNode res = new ListNode(list.get(0));
        ListNode dummy = new ListNode(0);
        dummy.next = res;

        for (int i = 1; i < list.size(); i++) {
            ListNode curNode = new ListNode(list.get(i));
            res.next = curNode;
            res = res.next;
        } // create a new node with the values
        return dummy.next;
    }

    /*
        PriotiryQueue   Time: O(NlogK)        K: number of the lists
                        Space: O(N) or O(K)   N: number of all nodes
    */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode iter = new ListNode(0);
        dummy.next = iter;

        PriorityQueue<ListNode> pq =
            new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    if (o1.val < o2.val)
                        return -1;
                    else if (o1.val == o2.val)
                        return 0;
                    else return 1;
                }
            }); // create PriorityQueue and re-implement compare

        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        } // add current head of each ListNode
        while (pq.size() != 0) {
            ListNode first = pq.poll();
            iter.next = first;
            iter = iter.next;
            first = first.next;
            if (first != null)
                pq.add(first);
        } // poll and construct a new ListNode
        return dummy.next.next;
    }

    /*
        Divide and Conqure
        Time: O(NlogK)
        Space: O(1)
    */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode res = partition(lists, 0, lists.length - 1);
        return res;
    }
    private ListNode partition(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int middle = (start + end) / 2;

        ListNode left = partition(lists, start, middle);
        ListNode right = partition(lists, middle + 1, end);

        return merge(left, right);
    }
    private ListNode merge(ListNode left, ListNode right) { // merge 2 sorted linkedlist
        if (left == null) return right;
        if (right == null) return left;

        if (left.val > right.val) {
            right.next = merge(left, right.next);
            return right;
        } else {
            left.next = merge(left.next, right);
            return left;
        }
    }


}
