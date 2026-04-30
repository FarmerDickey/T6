# Trabalho Prático 6 — Isomorfismo em Árvores

Projeto em Java para verificar se duas árvores não direcionadas são isomorfas usando **codificação canônica**.

## Dados do trabalho

- Disciplina: Resolução de Problemas com Grafos
- Trabalho: T6 — Isomorfismo em árvores
- Orientador: Prof. Me Ricardo Carubbi
- Linguagem: Java

## Ideia da solução

O programa não compara o desenho das árvores nem apenas a sequência de graus. A solução faz uma comparação estrutural por codificação canônica:

1. lê os dois arquivos no formato `algs4`;
2. armazena cada entrada usando `Graph.java`;
3. valida se cada grafo é uma árvore;
4. encontra o centro ou os dois centros da árvore;
5. enraíza a estrutura a partir do centro;
6. codifica recursivamente as subárvores;
7. ordena os códigos dos filhos para remover dependência da ordem das arestas;
8. compara as codificações finais.

Se as codificações canônicas forem iguais, as árvores são isomorfas.

## Estrutura do projeto

```text
T6/
├── README.md
├── T6.md
├── dados/
│   ├── tree3.txt
│   ├── tree4.txt
│   ├── caminho4_a.txt
│   ├── caminho4_b.txt
│   ├── nao_isomorfa.txt
│   ├── invalida_ciclo.txt
│   └── invalida_desconexa.txt
├── imgs/
│   └── exemplo.png
└── src/
    ├── Main.java
    ├── Graph.java
    └── TreeIsomorphism.java
```

## Formato dos arquivos de entrada

Cada arquivo deve seguir o formato:

```text
V
E
v1 w1
v2 w2
...
```

Onde:

- `V` é a quantidade de vértices;
- `E` é a quantidade de arestas;
- cada linha `v w` representa uma aresta não direcionada;
- os vértices devem ir de `0` até `V - 1`.

## Como compilar

Na pasta raiz do projeto:

```bash
javac src/*.java
```

## Como executar

Ainda na pasta raiz do projeto:

```bash
java -cp src Main dados/tree3.txt dados/tree4.txt
```

## Execuções de teste

Árvores isomorfas do enunciado:

```bash
java -cp src Main dados/tree3.txt dados/tree4.txt
```

Caminhos com dois centros:

```bash
java -cp src Main dados/caminho4_a.txt dados/caminho4_b.txt
```

Árvores não isomorfas:

```bash
java -cp src Main dados/tree3.txt dados/nao_isomorfa.txt
```

Entrada inválida com ciclo:

```bash
java -cp src Main dados/tree3.txt dados/invalida_ciclo.txt
```

Entrada inválida desconexa:

```bash
java -cp src Main dados/tree3.txt dados/invalida_desconexa.txt
```

## Saída do programa

O programa exibe:

- lista de adjacência da primeira entrada;
- lista de adjacência da segunda entrada;
- validação de cada entrada;
- centro(s) de cada árvore válida;
- codificação canônica de cada árvore válida;
- veredito final de isomorfismo.

## Observação sobre dois centros

Quando a árvore possui dois centros, o algoritmo codifica os dois lados separados pela aresta central, ordena essas duas partes e cria uma codificação única. Isso evita depender de qual centro foi escolhido primeiro.

## Link do vídeo explicativo

Coloque aqui o link do vídeo antes de enviar no AVA/Moodle:

```text
https://drive.google.com/file/d/1otjJwY5QLfuAz79R2aOfpV2ifkuyYdXu/view?usp=sharing
```
