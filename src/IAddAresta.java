interface IAddAresta {
    /**
     * Adiciona uma aresta ao grafo.
     * 
     * @param origem
     * @param destino
     * @return <code>true</code> se a aresta foi adicionada, <code>false</code> se a
     *         aresta já existia.
     */
    Boolean addAresta(Vertice origem, Vertice destino);
}