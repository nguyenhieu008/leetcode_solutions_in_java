## Implementation tricks
#### String
* Convert and handle on char array for shorter code.
* When need to return substring, store result as indexes would be better in terms of shorter implementation and space complexity (no need to call String.substring each time update result)
#### Array (Applied to char array as well)
* When need to return a pair of ints, can use array of 2 ints
* For swapping 2 parts [0, i, i + 1, n], we can reverse the whole string, then reverse each part => we will got something like [i + 1, n, 0, i]
* Implement hash table as int[]
  * Pros:
    * fast, shorter code to get and set,
    * all items initialized to 0
  * Cons:
    * need to handle the first case carefully, because all items are here, initiazlied to 0. Where as in hash table, if not put into table => the item will be null or containsKey return false => easy check
* When need to compare a -> b and b -> a, we can map both of them to the same value:
  *  [isomorphic string](https://leetcode.com/problems/isomorphic-strings/)
  *  [Minimum Domino Rotations For Equal Row](https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/description/): Map to the possible values and calculate results, rather than compare the tops\[0\]
* When it's hard to sovle the problem as a range, try to calculate at each item and contribute it to the final result, e.g. [trapping rain water](https://leetcode.com/problems/trapping-rain-water/description/)
* Sometimes, process an item in the middle of something is difficult. We can restrict to only process at one end to the other end for easier (e.g. sort the array and go from smalest one => no need to handle items at both sides, ...)
* Whether we can preprocess to make the problem easier:
  * Sort
  * Add all items to a hash table
  * Calculate max/min item form both left and right direction
  * Calculate prefix sum/product from both left and right direction
  * ...
* When the solution is unclear and we have some directions must deal with, e.g. [Kadane in circular](https://leetcode.com/problems/maximum-sum-circular-subarray/description/), we can list out the possible cases for solutions, then handle those cases separately (in Kadane circular, we calculate minSum and maxSum separately to handle both cases)
* Sometimes, we apply some restrictions when processing then we no need to deal with complicated situation where the duplicates input make noise to the result, e.g.:
  *  [Ways to split](https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/array_string/sliding_window_binary_search_m1712.java) : (Keep condition "while (k < j ||" make it won't add negative value to the result
  *  [Longest consecutive subsequency](https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/hash_table/m128.java) : When adding item to set, avoid number already used => the new number will not be inside of a contiguous sequence => easier to handle.
  *  [Increasing Triplet Subsequence](https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/array_string/m334_IncreasingTripletSubsequence.java) : When initialize small/mid numbers, we only initialize the mid number when it's really greater than other number before => We no need to deal with the case where mid and small are the same number, or mid is greater than small, but it's before small number => In this case, we need to define some state to understand that a number is not initialized and no need to deal with the situation of duplicates
* When need to swap the values of integers in-places, we can utilize the large space of integer to store additional values without losing the original values, e.g.:
  * [Build Array from Permutation](https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/array_string/e1920_BuildArrayfromPermutation.java) : Store/extrace the new and original value by modulo operation (use when the range of values is small enough and can only be used to store 2 values), combined = new * 1000 + old => new = combined / 1000, old = combine % 1000.
  * [Game of Life](https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/array_string/matrix/m289_GameofLife.java) : States are store in bits, original bits in first bit, next states in second bit.
 
## Sliding window
#### Substring
* Reference: https://leetcode.com/problems/minimum-window-substring/solutions/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems/
* For most substring problem, we are given a string and need to find a substring of it which satisfy some restrictions. A general way is to use a hashmap assisted with two pointers. The template is given below.
* One thing needs to be mentioned is that when asked to find maximum substring, we should update maximum after the inner while loop to guarantee that the substring is valid. On the other hand, when asked to find minimum substring, we should update minimum inside the inner while loop.
```
    int findSubstring(string s){
        vector<int> map(128,0);
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d; //the length of substring

        for() { /* initialize the hash map here */ }

        while(end<s.size()){

            if(map[s[end++]]-- ?){  /* modify counter here */ }

            while(/* counter condition */){ 
                 
                 /* update d here if finding minimum*/

                //increase begin to make it invalid/valid again
                
                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
            }  

            /* update d here if finding maximum*/
        }
        return d;
    }
```
