// https://leetcode.com/problems/time-needed-to-inform-all-employees/description/

// Solution 1a: Shorter and more concise,
// In DFS, We can calculate the maxtime it needs form current to all of its subodinates.
// But NOTICE TO CLARIFY THAT: In case an employee has no subordinates, it has the informTime == 0, so we can apply this. Otherwise, it will produce wrong answer.
// Time complexity: O(n)
// Space complexity: O(n);

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        /*
            - Using a global variable timeTaken to store the result
            - Need to build a graph to know which employee a manager manage
            - Using DFS, along with the time it needs to reach that employee id: dfs(graph, int employee, int curTime)
                - Because this is a kind of directed graph/tree, only path from root -> subordinates, so no path to ancestor => no need to check the parent node when go down the children.
                - After reach each employee, update the mintime = Math.max(minTime, curTime)
        */
        List<Integer>[] tree = buildTree(manager);
        return dfs(tree, headID, informTime);
    }

    // Traverse the tree to know how many time it takes for the information to reach an employee 
    // Return the time it takes to spread the infor to all of its subodinates
    private int dfs(List<Integer>[] tree, int curEmployee, int[] informTime) {
        // no need base case, because traverse the tree and we only go deeper if there is another employee, no null case
        int res = 0;

        List<Integer> employees = tree[curEmployee];
        for (int e : employees) {
            res = Math.max(res, dfs(tree, e, informTime));
        }
        return res + informTime[curEmployee];
    }

    private List<Integer>[] buildTree(int[] manager) {
        int n = manager.length;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int employee = 0; employee < n; employee++) {
            int curManager = manager[employee];
            if (curManager == -1) {
                continue;
            }
            tree[curManager].add(employee);
        }
        return tree;
    }
}

// Solution 1: a bit redundant compared to solution 1a
class Solution {
    private int timeTaken = 0;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        /*
            - Using a global variable timeTaken to store the result
            - Need to build a graph to know which employee a manager manage
            - Using DFS, along with the time it needs to reach that employee id: dfs(graph, int employee, int manager, int curTime)
                - After reach each employee, update the mintime = Math.max(minTime, curTime)
        */
        List<Integer>[] tree = buildTree(manager);
        dfs(tree, headID, -1, informTime, 0);
        return timeTaken;
    }

    // Traverse the tree to know how many time it takes for the information to reach an employee 
    private void dfs(List<Integer>[] tree, int curEmployee, int manager, int[] informTime, int curTime) {
        // no need base case, because traverse the tree and we only go deeper if there is another employee, no null case

        timeTaken = Math.max(timeTaken, curTime);

        List<Integer> employees = tree[curEmployee];
        for (int e : employees) {
            if (e == manager) {
                continue;
            }
            dfs(tree, e, curEmployee, informTime, curTime + informTime[curEmployee]);
        }
    }

    private List<Integer>[] buildTree(int[] manager) {
        int n = manager.length;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int employee = 0; employee < n; employee++) {
            int curManager = manager[employee];
            if (curManager == -1) {
                continue;
            }
            tree[curManager].add(employee);
        }
        return tree;
    }
}
