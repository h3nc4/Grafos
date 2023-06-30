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
 * Classe que representa um vértice de um grafo.
 * 
 * @see IAresta
 * @autor henrish0
 */
public class Vertice implements Comparable<Vertice> {
    /** Identificador do vértice. */
    private final Integer ID;

    /** Arestas que saem do vértice atual. */
    private TreeMap<Integer, IAresta> arestas;

    /** Indica se o vértice já foi visitado por um algoritmo. */
    private Boolean visitado;

    /**
     * Construtor padrão.
     * 
     * @param id Identificador do vértice.
     */
    public Vertice(Integer id) {
        this.ID = id;
        this.arestas = new TreeMap<Integer, IAresta>();
        this.visitado = false;
    }

    /**
     * Adiciona uma aresta ao vértice atual.
     * 
     * @param aresta Aresta a ser adicionada.
     * @return <code>true</code> se a aresta foi adicionada, <code>false</code> se a
     *         aresta já existia.
     */
    public Boolean addAresta(IAresta aresta) {
        return this.arestas.put(aresta.getDestino().ID, aresta) == null;
    }

    @Override
    public String toString() {
        return "\n  Vertice [id=" + this.ID + ", visitado=" + this.visitado + ", arestas= {" + this.arestas + "}]";
    }

    @Override
    public int compareTo(Vertice o) {
        return this.ID - o.ID;
    }

    // @formatter:off
    /** getID @return Identificador do vértice. */
    public Integer getID() { return this.ID; }
    /** haAresta @return <code>true</code> se o vértice possui a aresta, <code>false</code> caso contrário. */
    public Boolean haAresta(Integer id) { return this.arestas.containsKey(id); }

}