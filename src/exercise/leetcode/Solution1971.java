package exercise.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution1971 {
    /**
     * 1 <= n <= 2 * 10^5
     * 0 <= edges.length <= 2 * 10^5
     * edges[i].length == 2
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * 0 <= source, destination <= n - 1
     * There are no duplicate edges.
     * There are no self edges.
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        return validPath_1(n, edges, source, destination);
    }

    /**
     * Time: O(n) - Each node is traversed at most once.
     * Space: O(edges.length) - maximum queue length
     */
    public boolean validPath_0(int n, int[][] edges, int source, int destination) {
        // BFS
        // paths is sparse so using linkedList path not matrix
        Map<Integer, List<Integer>> paths = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> value = paths.get(edge[0]);
            if (value != null) {
                value.add(edge[1]);
            } else {
                List<Integer> newValue = new ArrayList<>();
                newValue.add(edge[1]);
                paths.put(edge[0], newValue);
            }

            value = paths.get(edge[1]);
            if (value != null) {
                value.add(edge[0]);
            } else {
                List<Integer> newValue = new ArrayList<>();
                newValue.add(edge[0]);
                paths.put(edge[1], newValue);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        queue.add(source);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == destination) {
                return true;
            }

            List<Integer> nexts = paths.getOrDefault(now, new ArrayList<>());
            for (int next : nexts) {
                if (!visited[next]) {
                    queue.add(next);
                    // mark visited before polling helps deduping(removing duplicate)
                    visited[next] = true;
                }
            }
        }

        return false;
    }

    /**
     * Time: O(n) - Each node is traversed at most once.
     * Space: O(n) - Each node is traversed at most once.
     */
    public static boolean Answer;
    public static boolean[] Visited;
    public static Map<Integer, List<Integer>> Paths;

    public boolean validPath_1(int n, int[][] edges, int source, int destination) {
        // DFS
        Answer = false;
        Visited = new boolean[n];
        // paths is sparse so using linkedList path not matrix
        Paths = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> value = Paths.get(edge[0]);
            if (value != null) {
                value.add(edge[1]);
            } else {
                List<Integer> newValue = new ArrayList<>();
                newValue.add(edge[1]);
                Paths.put(edge[0], newValue);
            }

            value = Paths.get(edge[1]);
            if (value != null) {
                value.add(edge[0]);
            } else {
                List<Integer> newValue = new ArrayList<>();
                newValue.add(edge[0]);
                Paths.put(edge[1], newValue);
            }
        }

        dfs(source, destination);

        return Answer;
    }

    public void dfs(int now, int destination) {
        if (Answer) {
            return;
        }
        if (now == destination) {
            Answer = true;
            return;
        }

        List<Integer> nexts = Paths.getOrDefault(now, new ArrayList<>());
        for (int next : nexts) {
            if (!Visited[next]) {
                Visited[next] = true;
                dfs(next, destination);
                // Visited[next] = false; don't need to explore again
            }
        }
    }

    public static void main(String[] args) {
        Solution1971 solution1971 = new Solution1971();
        boolean answer;
        answer = solution1971.validPath(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 0, 2); // true
        answer = solution1971.validPath(6, new int[][]{{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}}, 0, 5); // false
        answer = solution1971.validPath(1, new int[][]{}, 0, 0); // true
        answer = solution1971.validPath(1, new int[][]{}, 0, 1); // false
    }
}
