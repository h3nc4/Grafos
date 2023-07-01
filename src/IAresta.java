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
 * Interface que define os métodos que devem ser implementados por uma aresta,
 * permite abstração entre arestas ponderadas e não ponderadas.
 * 
 * @see Vertice
 * @see Aresta
 * @see ArestaPonderada
 * @see IAddAresta
 * @autor henrish0
 */
public interface IAresta {
    /**
     * Retorna o vértice de destino desta aresta.
     * 
     * @return Vértice de destino desta aresta.
     */
    public Vertice getDestino();

    /**
     * Retorna o peso desta aresta.
     * 
     * @return Peso desta aresta.
     */
    public Integer getPeso();

    /**
     * Retorna se esta aresta já foi visitada.
     * 
     * @return <code>true</code> se esta aresta já foi visitada,
     *         <code>false</code> caso contrário.
     */
    public Boolean eVisitada();

    /**
     * Define se esta aresta já foi visitada.
     * 
     * @param visitada <code>true</code> se esta aresta já foi visitada,
     *                 <code>false</code> caso contrário.
     */
    public void setVisitada(Boolean visitada);

}
