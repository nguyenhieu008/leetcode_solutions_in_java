// https://leetcode.com/problems/design-underground-system/

// This problem focus on design the data structure. We can spend time on discuss the structure/design and create new data structure/class for clean code.


import java.util.AbstractMap.SimpleEntry;

class UndergroundSystem {

    private HashMap<String, SimpleEntry<Integer, Integer>> totalTime;
    private HashMap<Integer, SimpleEntry<String, Integer>> startTime;

    public UndergroundSystem() {
        totalTime = new HashMap<>();
        startTime = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        startTime.put(id, new SimpleEntry<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        int departTime = startTime.get(id).getValue();
        String departStation = startTime.get(id).getKey();

        String route = departStation + "-" + stationName;
        SimpleEntry<Integer, Integer> currentAggregation = totalTime.getOrDefault(route, new SimpleEntry<>(0, 0));

        int aggregateTime = currentAggregation.getKey() + (t - departTime);
        int count = currentAggregation.getValue() + 1;

        totalTime.put(route, new SimpleEntry<>(aggregateTime, count));
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        int aggregate = totalTime.get(route).getKey();
        int count = totalTime.get(route).getValue();

        return (double) aggregate / count;
    }
}
