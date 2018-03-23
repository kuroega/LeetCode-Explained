public class Solution {
    /*
     *  Brute Force Solution  Time  O(n^3)
     *  [Time Limit Exceeds]  Space O(min(n,m))=O(k) k=set.size [alphbetsize,s]
     */
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; i++) {
                if (containsRepeated(s, i, j))
                    ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }
    private boolean containsRepeated(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int k = start; k < end; k++) {
            Character ch = s.charAt(k);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    /*
     *  Sliding Window  Time  O(2n)=O(n)
     *  [Accepted]      Space O(min(n,m))=O(k) k=set.size [alphbetsize,s]
     */
     public int lengthOfLongestSubstring(String s) {
         int length = s.length();
         Set<Character> set = new HashSet<>();
         int ans = 0, i = 0, j = 0;
         while (i < length & j < length) {
             if (!set.contains(s.charAt(j)) {
                 set.add(s.charAt(j++);
                 ans = Math.max(ans, j - i);
             } else {
                 // if exists, then remove the element from the front
                 // until the existed one has got removed.
                 set.remove(s.charAt[i++]);
             }
         }
         return ans;
     }

     /*
      *  Sliding Window (Improved)  Time: O(N) 
      *  [Accepted]           Space: O(min(m,n))=O(k) k=set.size [alphbetsize,s]
      */
      public int lengthOfLongestSubstring(String s) {
          int length = s.length();
          int ans = 0, j = 0;
          Map<Character, Integer> map = new HashMap<>();
          for (int i = 0; i < length; i++) {
              if (map.containsKey(s.charAt(i))) {
                  int preIdx = map.get(s.charAt(i));
                  j = Math.max(j, preIdx);   // abba
              }
              // update index for every element
              map.put(s.charAt(i), i + 1);
              ans = Math.max(ans, i - j + 1);
          }
          return ans;
      }


}
