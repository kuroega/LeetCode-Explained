public class SingleNumber {
    /*
        Naive Solution
            use hashSet
    */
    public int singleNumber(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                seen.remove(num);
            } else {
                seen.add(num);
            }
        }
        int res = 0;
        for (int n : seen)
            res = n;
        return res;
    }

    /*
        Advanced Solution:
            use XOR '^':
                any number 'a' XOR a number 'b' for twice,
                it will recover to be 'a'
            which means if we XOR 'a' to all the numbers that appear
            twice, it is still 'a'
            one trick is to initialize 'a' to be 0, because 0 XOR any
            number, it becomes that number
    */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums)
            res ^= num;
        return res;
    }

    /*
        Follow-Up:
            all numbers appear 3 times except for one, a number appears once
    */

    /*
        Naive Solution:
            Use HashMap to count the times a number appeared
    */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                if (map.get(num) == 2) map.remove(num);
                else map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int res = 0;
        for (int key : map.keySet()) {
            res = key;
        }
        return res;
    }

    /*
        Advanced Solution:
            Use XOR '^' and Bitwise complement '~'

            idea is:
                    State | one | two
                      I      0     0
            =>        II     1     0
                      III    0     1
                      I      0     0
                     ...       ...
            Initially, one is 0, two is 0,
            State I:
                step (1) (one(0) ^ 1) is 1, and 1 & ~two(0) is 1 -> one
                step (2) (two(0) ^ 1) is 1, and 1 & ~one(1) is 0 -> two
            State II:
                one: 1, two: 0
            =>  one: 0, one: 1
            State III:
                one: 0, two: 1
            =>  one: 0, two: 0

            Therefore, if a number appears 3 times, it is state (I)->II->III->I.
            That is, a number only appears once: State (I)->II, where one = 1
                    or a number appears 3 times: State (I)->II->III->I, one = 0

            Or, more intuitively :-)

            First time number appear -> save it in “ones”
            Second time -> clear “ones” but save it in “twos” for later check
            Third time -> try to save in “ones” but value saved in “twos” clear it.

    */
    public int singleNumber(int[] nums) {
        int one = 0, two = 0;
        for (int num : nums) {
            one = (one ^ num) & ~two;
            two = (two ^ num) & ~one;
        }
        return one;
    }

}
