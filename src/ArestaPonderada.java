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
 * Classe que representa a aresta de um grafo ponderado.
 * 
 * @author henrish0
 */
public class ArestaPonderada implements Comparable<IAresta>, IAresta {
    /** Vértice de destino desta aresta. */
    private final Vertice DESTINO;

    /** Peso desta aresta. */
    private final Integer PESO;

    /** Indica se esta aresta já foi visitada. */
    private Boolean visitada;

    /**
     * Construtor padrão.
     * 
     * @param destino Vértice de destino desta aresta.
     * @param peso    Peso desta aresta.
     */
    public ArestaPonderada(Vertice destino, Integer peso) {
        this.DESTINO = destino;
        this.PESO = peso;
        this.visitada = false;
    }

    @Override
    public String toString() {
        return "\n      Aresta destino=" + this.DESTINO.getID() + ", peso=" + this.PESO +  ", visitada=" + this.visitada;
    }

    @Override
    public int compareTo(IAresta aresta) throws ClassCastException {
        return this.PESO - ((ArestaPonderada) aresta).PESO;
    }

    // @formatter:off
    @Override public Vertice getDestino() { return this.DESTINO; }

    @Override public Integer getPeso() { return this.PESO; }

    @Override public Boolean eVisitada() { return this.visitada; }

    @Override public void setVisitada(Boolean visitada) { this.visitada = visitada; }

}
