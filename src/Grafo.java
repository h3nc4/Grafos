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
    TreeMap<Integer, Vertice> vertices;

    private IAddAresta addAresta;

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

    public Boolean addAresta(Integer origem, Integer destino) {
        Vertice vOrigem = vertices.get(origem),
                vDestino = vertices.get(destino);
        return vOrigem == null || vDestino == null || vOrigem.haAresta(vDestino.getID()) ? false
                : this.addAresta.addAresta(vOrigem, vDestino);
    }

    // @formatter:off
    @Override public String toString() { return "Grafo [nome=" + this.NOME + ", vertices= {" + this.vertices + "}]"; }

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
    } // @formatter:on

}
