// https://leetcode.com/problems/text-justification/description/

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // input and output well-formed
        // may use greedy approach
        // use a lines: List<String> 
        // n = words.length
        // startLine = 0, i = 0, lineLength = 0
        // while i < n:
        //      if lineLength + 1 + words[i] <= maxWith
        //          => Update lineLength
        //      else 
        //          => Justtify the words from [startLine, i - 1]
        //              Update startLine = i, lineLength = 0;
        //      i++

        int n = words.length;
        List<String> res = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int startLine = i, lineLength = words[i].length();
            i++;
            while (i < n) {
                if (lineLength + 1 + words[i].length() <= maxWidth) {
                    // still fit, update line length and examine next word
                    lineLength += 1 + words[i].length();
                    i++;
                } else {
                    // get enough words for this line, justify and break to go next
                    res.add(justify(words, startLine, i - 1, maxWidth));
                    break;
                }
            }

            if (i == n) {
                // handle last line
                StringBuilder lastLine = new StringBuilder(words[startLine]);
                for (int j = startLine + 1; j < n; j++) {
                    lastLine.append(' ' + words[j]);
                }
                while (lastLine.length() < maxWidth) {
                    lastLine.append(' ');
                }
                res.add(lastLine.toString());
            }
        }
        return res;

        // Make Justify function
        //      w = Count number of words
        //      l = length of all words
        //      spaces = maxWidth - l
        //      eachSpace = spaces / (w - 1)
        //      moreSpaces = space % (w - 1)
        //      then append strings and space

        
    }

    private String justify(String[] words, int start, int end, int maxWidth) {
        int w = end - start + 1;
        if (w == 1) {
            return words[start] + new String(sameChars(' ', maxWidth - words[start].length()));
        }

        int l = 0;
        for (int i = start; i <= end; i++) {
            l += words[i].length();
        }
        int spaces = maxWidth - l;
        int eachSpaces = spaces / (w - 1);
        int moreSpaces = spaces % (w - 1);

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < w - 1; i++) {
            res.append(words[start + i]);
            int numSpaces = eachSpaces + (i < moreSpaces? 1 : 0);
            res.append(sameChars(' ', numSpaces)); 
        }
        res.append(words[end]);
        return res.toString();
    }

    private char[] sameChars(char c, int nums) {
        char[] res = new char[nums];
        Arrays.fill(res, c);
        return res;
    }
}
