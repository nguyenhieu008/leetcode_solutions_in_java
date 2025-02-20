// https://leetcode.com/problems/making-file-names-unique/

// Solution 2: Same idea, but shorter a bit. 
// If the name exist, save result and move to next round quickly => no need nested if
// We use only 1 for loop to detect the existence using hash map, until not found.
// We need to update both the basename with lowest number possible integer to use, and the final name with lowest possible is 1.
// Time complexity: amortized O(n)
// Space complexity: O(n)

class Solution {
    public String[] getFolderNames(String[] names) {
        int n = names.length;
        HashMap<String, Integer> hashName = new HashMap<>();
        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            String name = names[i];

            if (!hashName.containsKey(name)) {
                ans[i] = name;
                hashName.put(name, 1);
                continue;
            }
            
            int cur = hashName.get(name);
            String tempName = name + '(' + cur + ')';
            while (hashName.containsKey(tempName)) {
                cur++;
                tempName = name + '(' + cur + ')';
            }

            ans[i] = tempName;
            hashName.put(name, cur + 1);
            hashName.put(tempName, 1);
        }

        return ans;
    }
}

// Solution 1: too complicated, while in if in for

class Solution {
    public String[] getFolderNames(String[] names) {
        int n = names.length;
        HashMap<String, Integer> hashName = new HashMap<>();
        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            String name = names[i];
            String tempName = name;

            //      i       names[i]        tempName       cur     hashName                     ans[]
            //      0           gta             gta                  {(gta, 1)}                 [gta]
            //      1           gta(1)          gta(1)               {(gta, 1), (gta(1), 1)}    [gta, gta(1)]
            //      2           gta             gta         1
            //                                  gta(1)      1        {(gta, 2), (gta(1), 1)}
            //                                  gta
            
            if (hashName.containsKey(tempName)) {
                int cur = hashName.get(tempName);

                while (hashName.containsKey(tempName)) {
                    tempName = name + "(" + cur + ")";
                    cur++;
                    hashName.put(name, cur);
                }
            }

            ans[i] = tempName;
            hashName.put(tempName, 1);
        }

        return ans;
    }
}

