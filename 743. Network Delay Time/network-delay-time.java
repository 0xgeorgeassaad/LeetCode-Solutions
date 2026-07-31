// using classes, much cleaner

class Solution {
    class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    class Node implements Comparable<Node> {
        int id;
        int dist;
        Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : times) {
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(k, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.dist > dist[curr.id]) continue;
            for (Edge edge : graph[curr.id])  {
                int neighbor = edge.to, weight = edge.weight;
                if (dist[neighbor] > curr.dist + weight) {
                    dist[neighbor] = curr.dist + weight;
                    pq.offer(new Node(neighbor, dist[neighbor] ));
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }
}
