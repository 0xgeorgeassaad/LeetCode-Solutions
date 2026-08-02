class Solution {
    public static int[] buildLPS(String s) {
        int[] lps = new int[s.length()];
        int i = 1;
        int len = 0;
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    } 
    public int strStr(String haystack, String needle) {
        int[] lps = buildLPS(needle);
        int j = 0;
        int i = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) return i - j;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        } 
        return -1;
    }
}
