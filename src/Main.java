import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso correto:");
            System.out.println("java Main <arquivo1.txt> <arquivo2.txt>");
            return;
        }

        try {
            Graph firstGraph = new Graph(args[0]);
            Graph secondGraph = new Graph(args[1]);

            firstGraph.sortAdjacencyLists();
            secondGraph.sortAdjacencyLists();

            System.out.println("========================================");
            System.out.println(" Trabalho Pratico 6 - Isomorfismo");
            System.out.println("========================================\n");

            showGraph("ARVORE 1", args[0], firstGraph);
            showGraph("ARVORE 2", args[1], secondGraph);

            TreeIsomorphism.ValidationResult firstValidation = TreeIsomorphism.validateTree(firstGraph);
            TreeIsomorphism.ValidationResult secondValidation = TreeIsomorphism.validateTree(secondGraph);

            System.out.println("VALIDACAO DAS ENTRADAS");
            System.out.println("Arvore 1: " + status(firstValidation));
            System.out.println("Arvore 2: " + status(secondValidation));
            System.out.println();

            if (!firstValidation.isValid() || !secondValidation.isValid()) {
                System.out.println("Comparacao interrompida: pelo menos uma entrada nao representa uma arvore valida.");
                return;
            }

            List<Integer> firstCenters = TreeIsomorphism.findCenters(firstGraph);
            List<Integer> secondCenters = TreeIsomorphism.findCenters(secondGraph);

            String firstCode = TreeIsomorphism.canonicalCode(firstGraph);
            String secondCode = TreeIsomorphism.canonicalCode(secondGraph);

            System.out.println("ANALISE ESTRUTURAL");
            System.out.println("Centro(s) da arvore 1: " + firstCenters);
            System.out.println("Centro(s) da arvore 2: " + secondCenters);
            System.out.println();

            System.out.println("Codificacao canonica da arvore 1:");
            System.out.println(firstCode);
            System.out.println();

            System.out.println("Codificacao canonica da arvore 2:");
            System.out.println(secondCode);
            System.out.println();

            System.out.println("VEREDITO FINAL");
            if (firstCode.equals(secondCode)) {
                System.out.println("As duas arvores SAO ISOMORFAS.");
            } else {
                System.out.println("As duas arvores NAO SAO ISOMORFAS.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: arquivo nao encontrado.");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na leitura/validacao do arquivo:");
            System.out.println(e.getMessage());
        }
    }

    private static void showGraph(String title, String path, Graph graph) {
        System.out.println(title);
        System.out.println("Arquivo: " + path);
        System.out.println(graph);
    }

    private static String status(TreeIsomorphism.ValidationResult result) {
        String prefix = result.isValid() ? "VALIDA" : "INVALIDA";
        return prefix + " - " + result.getMessage();
    }
}
