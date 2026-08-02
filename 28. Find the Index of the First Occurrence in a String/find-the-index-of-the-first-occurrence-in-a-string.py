class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        def build_lps(s):
            lps = [0] * len(s)
            i = 1
            l = 0
            while i < len(s):
                # I already know the current substring ends with a prefix of length l. 
                # Can I make that prefix one character longer?
                if s[i] == s[l]:
                    l += 1
                    lps[i] = l
                    i += 1
                elif l > 0:
                    l = lps[l - 1]
                else:
                    lps[i] = 0
                    i += 1
            return lps
        # Knuth–Morris–Pratt (KMP)
        # Time: O(n + m)
        # After a mismatch, move j to lps[j - 1]
        # ABABA|C|   ->    ABA|B|AC
        # ABABA|B|C  ->  ABABA|B|C
        lps = build_lps(needle)
        i, j = 0, 0
        while i < len(haystack):
            if haystack[i] == needle[j]:
                i += 1
                j += 1
                if j == len(needle): return i - j
            elif j != 0:
                j = lps[j - 1]
            else:
                i += 1
        return -1
            

        
    
