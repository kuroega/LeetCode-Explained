public class CountAndSay {
    public String countAndSay(int n) {
        StringBuilder res = new StringBuilder();
        char[] ch = new char[]{'1'};
        while (n > 1) {
            StringBuilder current = new StringBuilder();
            int i = 0;
            while (i < ch.length) {
                int count = 1;
                while (i < ch.length - 1 && ch[i] == ch[i + 1]) {
                    count++;
                    i++;
                }
                current.append(String.valueOf(count));
                current.append(ch[i]);
                i++;
            }
            ch = current.toString().toCharArray();
            n--;
        }
        for (char c : ch)
            res.append(c);
        return res.toString();
    }
}
