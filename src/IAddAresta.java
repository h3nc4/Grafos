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

/**
 * Interface que define como adicionar arestas ao grafo.
 * <br><br>
 * Usa o padrão de projeto <code>Strategy</code>.
 * 
 * @see Grafo.AddArestaNPND
 * @see Grafo.AddArestaPND
 * @see Grafo.AddArestaNPD
 * @see Grafo.AddArestaPD
 * @author henrish0
 */
interface IAddAresta {
    /**
     * Adiciona uma aresta ao grafo.
     * 
     * @param origem Vértice de origem da aresta.
     * @param destino Vértice de destino da aresta.
     * @return <code>true</code> se a aresta foi adicionada, <code>false</code> se a
     *         aresta já existia.
     */
    Boolean addAresta(Vertice origem, Vertice destino);
}