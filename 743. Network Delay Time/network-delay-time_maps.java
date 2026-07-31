// using nested maps

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : times) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.computeIfAbsent(u, key -> new HashMap<>()).put(v, w);
        }
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );
        pq.add(new int[]{0, k});
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int d = pair[0], curr = pair[1];
            if (d > distances[curr]) continue;
            for (Map.Entry<Integer, Integer> entry : graph.getOrDefault(curr, new HashMap<>()).entrySet()) {
                int neighbor = entry.getKey(), weight = entry.getValue();
                if (distances[neighbor] > d + weight) {
                    distances[neighbor] = d + weight;
                    pq.add(new int[]{distances[neighbor], neighbor});
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, distances[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}
