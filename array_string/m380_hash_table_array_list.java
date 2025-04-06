// https://leetcode.com/problems/insert-delete-getrandom-o1/description/

// Solution: Use hash map for O(1) insert and remove. But it does not support random operation => need an array list for that purpose.
// => the hash map need to point to the index of value in the list, in order to insert/remove items within the list.
//
// Time complexity: O(1) for all operations
// Space complexity: O(n) where n is the number of insert operations

class RandomizedSet {
    
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
        return false;
    }
    
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int idx = map.get(val);
            int retainValue = list.getLast();
            // Retain the last item, so we will remove the last
            list.set(idx, retainValue);
            // Need to update the map, the idx now contains retainValue
            map.put(retainValue, idx);

            // Remove last item, and remove the val from map
            list.removeLast();
            map.remove(val);
            return true;
        }
        return false;
    }
    
    public int getRandom() {
        int randomIdx = rand.nextInt(list.size());
        return list.get(randomIdx);
    }
}
