import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class TreeIsomorphism {

    public static class ValidationResult {
        private final boolean valid;
        private final String message;

        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public boolean isValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }
    }

    // =========================================================
    // Validacao da entrada
    // =========================================================

    public static ValidationResult validateTree(Graph graph) {
        if (graph.V() == 0) {
            return new ValidationResult(false, "o grafo nao possui vertices.");
        }

        if (graph.E() != graph.V() - 1) {
            return new ValidationResult(
                    false,
                    "uma arvore com " + graph.V() + " vertices deve ter exatamente " +
                            (graph.V() - 1) + " arestas, mas possui " + graph.E() + "."
            );
        }

        if (!isConnected(graph)) {
            return new ValidationResult(false, "o grafo nao e conexo.");
        }

        return new ValidationResult(true, "entrada valida: grafo conexo e com V - 1 arestas.");
    }

    private static boolean isConnected(Graph graph) {
        boolean[] visited = new boolean[graph.V()];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            int v = queue.remove();

            for (int w : graph.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }

        for (boolean value : visited) {
            if (!value) {
                return false;
            }
        }

        return true;
    }

    // =========================================================
    // Centro(s) da arvore
    // =========================================================

    public static List<Integer> findCenters(Graph graph) {
        int n = graph.V();
        int[] degree = new int[n];
        List<Integer> leaves = new ArrayList<>();

        for (int v = 0; v < n; v++) {
            degree[v] = graph.degree(v);
            if (degree[v] <= 1) {
                leaves.add(v);
            }
        }

        int processed = leaves.size();

        while (processed < n) {
            List<Integer> newLeaves = new ArrayList<>();

            for (int leaf : leaves) {
                for (int neighbor : graph.adj(leaf)) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        newLeaves.add(neighbor);
                    }
                }
                degree[leaf] = 0;
            }

            processed += newLeaves.size();
            leaves = newLeaves;
        }

        Collections.sort(leaves);
        return leaves;
    }

    // =========================================================
    // Codificacao canonica
    // =========================================================

    public static String canonicalCode(Graph graph) {
        List<Integer> centers = findCenters(graph);

        if (centers.size() == 1) {
            return encode(graph, centers.get(0), -1);
        }

        int firstCenter = centers.get(0);
        int secondCenter = centers.get(1);

        String firstSide = encode(graph, firstCenter, secondCenter);
        String secondSide = encode(graph, secondCenter, firstCenter);

        List<String> parts = new ArrayList<>();
        parts.add(firstSide);
        parts.add(secondSide);
        Collections.sort(parts);

        return "(" + parts.get(0) + parts.get(1) + ")";
    }

    private static String encode(Graph graph, int vertex, int parent) {
        List<String> childCodes = new ArrayList<>();

        for (int neighbor : graph.adj(vertex)) {
            if (neighbor != parent) {
                childCodes.add(encode(graph, neighbor, vertex));
            }
        }

        Collections.sort(childCodes);

        StringBuilder sb = new StringBuilder();
        sb.append("(");

        for (String code : childCodes) {
            sb.append(code);
        }

        sb.append(")");
        return sb.toString();
    }

    // =========================================================
    // Comparacao final
    // =========================================================

    public static boolean areIsomorphic(Graph first, Graph second) {
        if (first.V() != second.V()) {
            return false;
        }

        String firstCode = canonicalCode(first);
        String secondCode = canonicalCode(second);

        return firstCode.equals(secondCode);
    }
}
