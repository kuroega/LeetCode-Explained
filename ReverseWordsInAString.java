public class ReverseWordsInAString {
    /*
        Given s = "the sky is blue",
        return "blue is sky the".
    */
    public String reverseWords(String s) { // "     a      b" => "b a"
        String str = s.trim();
        if (str.length() <= 1) return str;

        StringBuilder sb = new StringBuilder();
        String[] sa = str.split(" "); // (a, "", "", "", "", "", "", b)
        int i = sa.length - 1;
        while (i >= 0) {
            while (sa[i].isEmpty()) i--;
            sb.append(sa[i]);
            sb.append(" ");
            i--;
        }
        return sb.toString().trim();
    }

    /*
        Follow-up: what if we are given a char array of
        a sentence:

         exp. char[]{'h','i',' ','l','o','v','e',' ', 'u'} as "i love u"

         actually the problem can also be solved by using the previous
         solution.

         but how about we do it in-place (O(1) space)
    */
    public void reverseWords(char[] str) {
        // reverse the whole sentence
        reverse(str, 0, str.length - 1); // str -> {'u', ' ', 'e', 'v', 'o', 'l', ' ', 'i', 'h'}

        // then let us reverse each word separated by space
        int i = 0;
        for (int j = 0; j < str.length; j++) {
            if (str[j] == ' ') {
                reverse(str, i, j - 1);
                i = j + 1;
            }
        }
        // because the last word has no space after it
        // so we have to take care of it
        reverse(str, i, str.length - 1);
    }
    private void reverse(char[] str, int start, int end) {
        while (start < end) {
            char tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;
            start++;
            end--;
        }
    }
}
