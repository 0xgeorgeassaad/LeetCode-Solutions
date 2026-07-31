class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        graph = defaultdict(dict)
        for u, v, w in times:
            graph[u][v] = w
        q = [(0, k)]
        dist = [float('inf')] * (n + 1)
        dist[k] = 0
        while q:
            d, curr = heapq.heappop(q)
            # stale node
            if d > dist[curr]: continue
            for neighbor, weight in graph[curr].items():
                if dist[neighbor] > d + weight:
                    dist[neighbor] = d + weight
                    heapq.heappush(q, (dist[neighbor], neighbor))
        result = max(dist[1:])
        return result if result != float('inf') else -1
