public class TextJustification {
    /*
        one pass iteration
            left justify
            middle justify
    */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int index = 0;
        // int count = 0;
        int last = 0;
        while (index < words.length) {
            int count = words[index].length();
            last = index + 1;
            while (last < words.length) {
                if (words[last] + count + 1 > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            } // find the last word for current line

            if (last == words.length || count == words[index].length()) {
                String line = leftJustify(words, count, index, last, maxWidth);
            } // left justify: last line OR only one word in the line
            else {
                String line = middleJustify(words, count, index, last, maxWidth);
            } // middle justify: else
            res.add(line);
            index = last;
        }
        return res;
    }

    private String middleJustify(String[] words, int sum, int start, int end, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int[] space = new int[end - start - 1]; // empty slots
        int numSpace = maxWidth - sum;
        int i = 0;
        while (numSpace > 0) {
            space[i % space.length] += 1;
            i++;
            numSpace--;
        } // allocate space from left to right

        for (int j = 0; j < end - start; j++) {
            sb.append(words[start + j]);
            if (j < space.length)
                while (space[j] > 0) {
                    sb.append(" ");
                    space[j]--;
                }
        } // construct string for this line using space and words arrays
        return sb.toString();
    }
    private String leftJustify(String[] words, int sum, int start, int end, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < end - start; i++) {
            sb.append(words[start + i] + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        for (int i = 0; i < maxWidth - sb.length(); i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
