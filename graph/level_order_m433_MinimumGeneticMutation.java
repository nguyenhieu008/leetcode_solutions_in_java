// https://leetcode.com/problems/minimum-genetic-mutation/

// Solution 1: Because bank length is small (<= 10), so we can check the next node by checking all possible nodes left in the bank
// Time complexity: O(m * m + m * 8), where m is bank size => O(m^2)
// Space complexity: O(m);

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<String> q = new LinkedList<>();
        boolean[] visited = new boolean[bank.length];
        int steps = 0;

        q.offer(startGene);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();

                if (cur.equals(endGene)) {
                    return steps;
                }
                
                for (int j = 0; j < bank.length; j++) {
                    if (visited[j]) continue;
                    String s = bank[j];
                    
                    if (isValidMutation(cur, s)) {
                        q.offer(s);
                        visited[j] = true;;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    
    private boolean isValidMutation(String cur, String s) {
        int diff = 0;
        for (int sIdx = 0; sIdx < 8; sIdx++) {
            if (s.charAt(sIdx) != cur.charAt(sIdx)) {
                diff++;
            }
        }
        return diff == 1;
    }
}

// Solution 2: from a string, check all possible mutations and check if it exists in the bank and not visited.
// There are 32 options for next mutations for each string
// Time complexity: O(32 * m). 
//     But beware of line (String newGene = new String(curArray);), it can takes O(8) ops as well => O(32 * 8 * m)
// Space complexity: O(m)

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Queue<String> q = new LinkedList<>();
        int steps = 0;
        Set<String> visited = new HashSet<>();
        char[] mutations = {'A', 'C', 'G', 'T'};

        q.offer(startGene);
        visited.add(startGene);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(endGene)) {
                    return steps;
                }

                char[] curArray = cur.toCharArray();
                for (int j = 0; j < 8; j++) {
                    char originalChar = curArray[j];

                    for (char c : mutations) {
                        curArray[j] = c;
                        String newGene = new String(curArray);

                        if (bankSet.contains(newGene) && !visited.contains(newGene)) {
                            visited.add(newGene);
                            q.offer(newGene);
                        }
                    }

                    curArray[j] = originalChar;
                }
            }
            steps++;
        }
        return -1;
    }
}
