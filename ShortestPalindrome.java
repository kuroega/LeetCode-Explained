class ShortestPalindrome {
	/*
		KMP algorithm
			iterative
	*/
    public String shortestPalindrome(String s) {
		String rev = new StringBuilder(s).reverse().toString();
        String l = s + "#" + rev;
		char[] ca = l.toCharArray();
		int[] p = new int[ca.length];
		for (int i = 1; i < ca.length; i++) {
			int j = p[i - 1];
			while (j > 0 && ca[i] != ca[j])
				j = p[j - 1];
			p[i] = j + (ca[i] == ca[j] ? 1 : 0); 
		}    	
		return rev.substring(0, s.length() - p[p.length - 1]) + s;
   	}

   	/*
		recursive 

			personally perfer!
			more intuitive
   	*/
	public String shortestPalindrome(String s) {
		if (s.isEmpty()) return "";
        if (s.length() == 1) return s;
		int i = 0;
		for (int j = s.length() - 1; j >= 0; j--) 
			if (s.charAt(i) == s.charAt(j))
				i++;
		if (i == s.length()) return s; // aba self-palindrome
		String suffix = s.substring(i);
		return new StringBuilder(suffix).reverse().toString() +
			shortestPalindrome(s.substring(0, i)) + suffix; 
	}
}
