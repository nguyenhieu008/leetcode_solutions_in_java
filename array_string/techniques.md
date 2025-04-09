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
* When need to compare a -> b and b -> a, we can map both of them to the same value, e.g. [isomorphic string](https://leetcode.com/problems/isomorphic-strings/)
* When it's hard to sovle the problem as a range, try to calculate at each item and contribute it to the final result, e.g. [trapping rain water](https://leetcode.com/problems/trapping-rain-water/description/)
* Whether we can preprocess to make the problem easier:
  * Sort
  * Calculate max/min item form both left and right direction
  * Calculate prefix sum/product from both left and right direction
  * ...
 
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
