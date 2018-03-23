class RepeatedDNASequences {
    /*
        HashMap Solution
            Time: O(N)
            Space: O(N)
    */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> dnaMap = new HashMap<>();
        for (int i = 0; i < s.length() - 10 + 1; i++) {
            String dna = s.substring(i, i + 10);
            if (dnaMap.containsKey(dna)) {
                if (dnaMap.get(dna) == 1) {
                    res.add(dna);
                    dnaMap.put(dna, -1);
                }
            } else dnaMap.put(dna, 1);
        }
        return res;
    }

    /*
        Avoid doing hashing
    */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        int[] map = new int[26];
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        Set<Integer> seen = new HashSet<>();
        Set<Integer> added = new HashSet<>();

        for (int i = 0; i < s.length() - 10 + 1; i++) {
            int dna = 0;
            for (int j = i; j < i + 10; j++) {
                dna <<= 2; // perserve the previous number

                dna |= map[s.charAt(j) - 'A']; // perserve number within the first two digits
            }
            if (!seen.add(dna) && added.add(dna)) {
                res.add(s.substring(i, i + 10));
            }
        }
        return res;
    }
}
