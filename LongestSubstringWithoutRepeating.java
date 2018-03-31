public class Solution {
     /*
      *  Sliding Window   Time: O(N) 
      *                   Space: O(min(m,n))=O(k) k=set.size [alphbetsize,s]
      */  
      public int lengthOfLongestSubstring(String s) {
          int max = 0, l = 0;
          Map<Character, Integer> map = new HashMap<>(); // <char, indx>
          for (int i = 0; i < s.length(); i++) {
              if (map.containsKey(s.charAt(i))) {
                  if (map.get(s.charAt(i)) >= l) {
                      l = map.get(s.charAt(i)) + 1;
                  }

              }
              map.put(s.charAt(i), i);
              max = Math.max(max, i - l + 1);
          }
          return max;
      }


}

/*

abba
0123

pwwkew
012345

abcabcbb
01234567

*/

