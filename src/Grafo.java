/*
 *  Copyright 2023 Henrique Almeida
 * 
 * This file is part of Projeto grafos.
 * 
 * Projeto grafos is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Projeto grafos is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU
 * General Public License along with Projeto grafos. If not, see
 * <https://www.gnu.org/licenses/>.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Classe que representa um grafo.
 * 
 * @autor henrish0
 */
public class Grafo {
    /** Nome do grafo. */
    private final String NOME;

    /** Vértices do grafo. */
    private TreeMap<Integer, Vertice> vertices;

    /** Estratégia de adição de arestas. */
    private IAddAresta addAresta;

    private Boolean ponderado;

    private Boolean direcionado;

    /**
     * Construtor padrão.
     * 
     * @param nome Nome do grafo.
     */
    public Grafo(String nome, Boolean ponderado, Boolean direcionado) {
        this.NOME = nome;
        this.vertices = new TreeMap<Integer, Vertice>();
        switch ((ponderado ? 1 : 0) + (direcionado ? 2 : 0)) {
            case 0 -> this.addAresta = new AddArestaNPND();
            case 1 -> this.addAresta = new AddArestaPND();
            case 2 -> this.addAresta = new AddArestaNPD();
            case 3 -> this.addAresta = new AddArestaPD();
        }
        this.ponderado = ponderado;
        this.direcionado = direcionado;
    }

    /**
     * Adiciona um vértice ao grafo.
     * 
     * @param id Identificador do vértice.
     * @return <code>true</code> se o vértice foi adicionado, <code>false</code> se
     *         o vértice já existia.
     */
    public Boolean addVertice(Integer id) {
        if (this.vertices.containsKey(id))
            return false;

        return this.vertices.put(id, new Vertice(id)) == null;
    }

    /**
     * Adiciona uma aresta ao grafo.
     * 
     * @param id1 Identificador do vértice de origem.
     * @param id2 Identificador do vértice de destino.
     * @return <code>true</code> se a aresta foi adicionada, <code>false</code> se a
     *         aresta já existia.
     */
    public Boolean addAresta(Integer id1, Integer id2) {
        Vertice vOrigem = vertices.get(id1),
                vDestino = vertices.get(id2);
        return vOrigem == null || vDestino == null || vOrigem.haAresta(vDestino.getID()) ? false
                : this.addAresta.addAresta(vOrigem, vDestino);
    }

    /**
     * Salva o grafo em um arquivo.
     * 
     * @return <code>true</code> se o grafo foi salvo, <code>false</code> se houve
     *         algum erro.
     */
    public Boolean salvar() {
        StringBuilder sb = new StringBuilder(
                (this.ponderado ? "1" : "0") +
                        (this.direcionado ? "1" : "0") + "\n" //
        );
        vertices.values().forEach(v -> sb.append(v.getID()).append(";")); // IDs dos vértices
        sb.append("\n");
        vertices.values().forEach(v -> sb.append(v.toFile())); // Arestas
        return Arquivo.salvarGrafo(new String(sb), this.NOME);
    }

    /**
     * Carrega um grafo de um arquivo.
     * 
     * @param nome Nome do arquivo.
     * @return Grafo carregado.
     */
    public static Grafo carregar(String nome) {
        return Arquivo.lerGrafo(nome);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        vertices.values().forEach(v -> sb.append(v.toString()));
        return "\n\nGrafo \"" + this.NOME + "\", vertices= {" + new String(sb) + "\n}";
    }// @formatter:off

    /** Define a forma de adicionar arestas ao grafo não ponderado não direcionado. */ 
    private class AddArestaNPND implements IAddAresta {
        @Override public Boolean addAresta(Vertice origem, Vertice destino) {
            return origem.addAresta(new Aresta(destino)) && destino.addAresta(new Aresta(origem));
        }
    }
    /** Define a forma de adicionar arestas ao grafo não ponderado direcionado. */
    private class AddArestaNPD implements IAddAresta {
        @Override public Boolean addAresta(Vertice origem, Vertice destino) {
            return origem.addAresta(new Aresta(vertices.get(destino.getID())));
        }
    }
    /** Define a forma de adicionar arestas ao grafo ponderado não direcionado. */
    private class AddArestaPND implements IAddAresta {
        @Override public Boolean addAresta(Vertice origem, Vertice destino) {
            int peso = App.lerInt("Peso da aresta: ");
            return origem.addAresta(new ArestaPonderada(destino, peso)) && destino.addAresta(new ArestaPonderada(origem, peso));
        }
    }
    /** Define a forma de adicionar arestas ao grafo ponderado direcionado. */
    private class AddArestaPD implements IAddAresta {
        @Override public Boolean addAresta(Vertice origem, Vertice destino) {
            return origem.addAresta(new ArestaPonderada(vertices.get(destino.getID()), App.lerInt("Peso da aresta: ")));
        }
    }

    /** Escreve e lê grafos. */
    private class Arquivo {
        /** Tipo de arquivo. */
        private static String TIPO = ".csv";
        /** Pasta onde os arquivos são salvos. */
        private static String PASTA = "data/";

        /**
         * Salva o grafo em um arquivo.
         * 
         * @param conteudo Conteúdo do arquivo.
         * @param nome     Nome do arquivo.
         * @return <code>true</code> se o arquivo foi salvo, <code>false</code> se
         *         ocorreu algum erro.
         */
        private static Boolean salvarGrafo(String conteudo, String nome) {
            try (FileWriter fw = new FileWriter(PASTA + nome + TIPO)) {
                fw.write(conteudo);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        /**
         * Lê um grafo de um arquivo.
         * 
         * @param nome Nome do arquivo.
         * @return Grafo lido.
         */
        private static Grafo lerGrafo(String nome) {
            Grafo out = null;
            try (BufferedReader br = new BufferedReader(new FileReader(PASTA + nome + TIPO))) {
                String linha = br.readLine();
                out = new Grafo(nome, linha.charAt(0) == '1', linha.charAt(1) == '1');
                for (String id : br.readLine().split(";"))
                    out.addVertice(Integer.parseInt(id));
                for (String item : br.readLine().split(";")) {
                    String[] ids = item.split("-");
                    // Em caso de out não direcionado, a aresta foi escrita nos dois sentidos.
                    // Elimina-se a verificação de direcionado
                    switch (out.ponderado ? 1 : 0) {
                        case 0 -> out.vertices.get(Integer.parseInt(ids[0])).addAresta(new Aresta(
                                out.vertices.get(Integer.parseInt(ids[1]))));

                        case 1 -> out.vertices.get(Integer.parseInt(ids[0])).addAresta(new ArestaPonderada(
                                out.vertices.get(Integer.parseInt(ids[1])), Integer.parseInt(ids[2])));
                    }
                }
                br.close();
            } catch (IOException e) {}
            return out;
        } // @formatter:on
    }
}
