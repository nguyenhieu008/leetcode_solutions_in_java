// https://leetcode.com/problems/find-median-from-data-stream/description/

// Solution 2: Same idea as solution 1, but better implementation. Check size to determine, which heap we want to put this new num .
// Then add the new num to the other heap first (to guarantee the order), then put the polled number to the target heap.

class MedianFinder {

    PriorityQueue<Integer> maxHeap; // of smaller values
    PriorityQueue<Integer> minHeap; // of larger values

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (maxHeap.size() > minHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}

// Solution 1: self-done. We store 2 heaps of smaller/larger items => the median will be
/    - n / 2 == 0: average of 2 tops of heaps
/    - n / 2 == 1: top of max heap of smaller elements (notice that we always keep the maxHeap larger in size();
// Notice that, when adding a number to maxHeap, we also need to check if that number is greater than minHeap top => swap with it before adding to maxHeap
// to guarantee the numbers in maxHeap always smaller than minHeap.
// Time complexity: O(logn) for add, O(1) for findMedian
// Space complexity: O(n)

class MedianFinder {

    PriorityQueue<Integer> maxHeap; // of smaller values
    PriorityQueue<Integer> minHeap; // of larger values

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (!minHeap.isEmpty() && num > minHeap.peek()) {
            // Because the num should land in minHeap, we swap it with the smallest one in min heap
            // So we can add it to max heap.
            int num2 = minHeap.poll();
            minHeap.offer(num);
            num = num2;
        }

        maxHeap.offer(num); // add to max heap, maxHeap size is always >= minHeap size

        if (maxHeap.size() - minHeap.size() >= 2) {
            // largest of small set
            int largest = maxHeap.poll();
            minHeap.offer(largest);
        } 
    }
    
    public double findMedian() {
        int num1 = maxHeap.peek();
        if (minHeap.size() == maxHeap.size()) {
            int num2 = minHeap.peek();
            return (double) (num1 + num2) / 2;
        }
        return num1; // auto-casted
    }
}
