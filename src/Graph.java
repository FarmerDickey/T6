import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Graph {

    private final int vertices;
    private int edges;
    private final List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException("O numero de vertices nao pode ser negativo.");
        }

        this.vertices = vertices;
        this.edges = 0;
        this.adj = (List<Integer>[]) new ArrayList[vertices];

        for (int v = 0; v < vertices; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public Graph(String caminhoArquivo) throws FileNotFoundException {
        this(new Scanner(new File(caminhoArquivo)));
    }

    public Graph(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("Arquivo invalido: quantidade de vertices ausente.");
        }

        int totalVertices = scanner.nextInt();

        if (totalVertices < 0) {
            throw new IllegalArgumentException("Arquivo invalido: quantidade de vertices negativa.");
        }

        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("Arquivo invalido: quantidade de arestas ausente.");
        }

        int totalArestas = scanner.nextInt();

        if (totalArestas < 0) {
            throw new IllegalArgumentException("Arquivo invalido: quantidade de arestas negativa.");
        }

        this.vertices = totalVertices;
        this.edges = 0;

        @SuppressWarnings("unchecked")
        List<Integer>[] temp = (List<Integer>[]) new ArrayList[vertices];
        this.adj = temp;

        for (int v = 0; v < vertices; v++) {
            adj[v] = new ArrayList<>();
        }

        for (int i = 0; i < totalArestas; i++) {
            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException("Arquivo invalido: aresta incompleta na linha " + (i + 3) + ".");
            }

            int v = scanner.nextInt();

            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException("Arquivo invalido: aresta incompleta na linha " + (i + 3) + ".");
            }

            int w = scanner.nextInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return vertices;
    }

    public int E() {
        return edges;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);
        adj[w].add(v);
        edges++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public void sortAdjacencyLists() {
        for (int v = 0; v < vertices; v++) {
            Collections.sort(adj[v]);
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                    "Vertice " + v + " fora do intervalo permitido: 0 ate " + (vertices - 1) + "."
            );
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(vertices).append(" vertices, ").append(edges).append(" arestas\n");

        for (int v = 0; v < vertices; v++) {
            List<Integer> vizinhos = new ArrayList<>(adj[v]);
            Collections.sort(vizinhos);

            sb.append(v).append(": ");
            for (int w : vizinhos) {
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
