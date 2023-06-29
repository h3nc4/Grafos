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
public abstract class Grafo {
    /** Nome do grafo. */
    private final String NOME;

    /** Vértices do grafo. */
    private TreeMap<Integer, Vertice> vertices;

    /**
     * Construtor padrão.
     * 
     * @param nome Nome do grafo.
     */
    public Grafo(String nome) {
        this.NOME = nome;
        this.vertices = new TreeMap<Integer, Vertice>();
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
     * @param id1  Identificador do primeiro vértice.
     * @param id2  Identificador do segundo vértice.
     * @param peso Peso da aresta.
     * @return <code>true</code> se a aresta foi adicionada, <code>false</code> se
     *         a aresta já existia.
     */
    abstract public Boolean addAresta(Integer id1, Integer id2);

    @Override
    public String toString() {
        return "Grafo [nome=" + this.NOME + ", vertices= {" + this.vertices + "}]";
    }

}
