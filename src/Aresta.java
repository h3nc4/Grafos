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
 * Classe que representa uma aresta não ponderada.
 * 
 * @see IAresta
 * @author henrish0
 */
public class Aresta implements IAresta {
    /** Vértice de destino desta aresta. */
    private final Vertice DESTINO;

    /** Indica se esta aresta já foi visitada. */
    private Boolean visitada;

    /**
     * Construtor padrão.
     * 
     * @param destino Vértice de destino desta aresta.
     */
    public Aresta(Vertice destino) {
        this.DESTINO = destino;
        this.visitada = false;
    }

    // @formatter:off
    @Override public String toString() { return "\n      Aresta destino=" + this.DESTINO.getID() + ", visitada=" + this.visitada; }
    @Override public Vertice getDestino() { return this.DESTINO; }
    @Override public Boolean eVisitada() { return this.visitada; }
    @Override public void setVisitada(Boolean visitada) { this.visitada = visitada; }
    @Override public Integer getPeso() { return null; }

}
