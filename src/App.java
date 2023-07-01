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
 * Classe principal do programa.
 * 
 * @autor henrish0
 */
public class App {
    /** Grafo que será utilizado no programa */
    private static Grafo grafo;

    /** Construtor para garantir classe não instanciável */
    private App() {
        throw new InstantiationError("Classe nao instanciavel");
    }

    /**
     * Método que lê uma string do console através do {@link System#console()}.
     * 
     * @param mensagem a ser exibida ao usuário.
     * @return string lida do console.
     */
    public static String lerStr(String mensagem) {
        String out = System.console().readLine(mensagem);
        return out == null || out.isBlank() ? App.lerStr(" ERRO: Valor invalido. Digite novamente: ") : out.trim();
    }

    /**
     * Método que lê um inteiro do console.
     * 
     * @param mensagem a ser exibida ao usuário.
     * @return inteiro lido do console.
     */
    public static int lerInt(String mensagem) {
        try {
            return Integer.parseInt(App.lerStr(mensagem));
        } catch (NumberFormatException e) {
            return App.lerInt(" ERRO: Valor invalido. Digite um numero inteiro: ");
        }
    }

    /**
     * Método que pause a execução do programa até que o usuário pressione ENTER.
     */
    public static void pause() {
        System.console().readLine("\n Pressione ENTER para continuar...");
    }

    /**
     * Menu para criação de um grafo.
     */
    private static void menuCriacao() {
        if (grafo != null && !App.lerStr(" Deseja sobrescrever o grafo atual? (S/N) ").equalsIgnoreCase("S"))
            return;
        if (App.lerStr(" Deseja criar um grafo completo? (S/N) ").equalsIgnoreCase("S")) {
            grafo = new Grafo(App.lerStr("\n Digite o nome do grafo: "), false, true);
            System.out.println(grafo.gerarCompleto(App.lerInt(" Digite o numero de vertices: "))
                    ? " Grafo criado com sucesso!"
                    : " Erro, digite um numero valido");
            return;
        }
        grafo = new Grafo(
                App.lerStr("\n Digite o nome do grafo: "),
                App.lerStr(" O grafo sera ponderado? (S/N) ").equalsIgnoreCase("S"),
                App.lerStr(" O grafo sera direcionado? (S/N) ").equalsIgnoreCase("S") //
        );
        System.out.println(" Grafo criado com sucesso!");
    }

    /**
     * Menu principal do programa.
     * 
     * @return opção escolhida pelo usuário.
     */
    private static Integer menuMainStr() {
        return App.lerInt("\n\n Digite o numero da opcao desejada e pressione ENTER\n"
                + " 1 - Criar um grafo\n"
                + " 2 - Carregar um grafo\n"
                + " 3 - Salvar o grafo\n"
                + " 4 - Adicionar um vertice\n"
                + " 5 - Adicionar uma aresta\n"
                + " 6 - Remover um vertice\n"
                + " 7 - Remover uma aresta\n"
                + " 8 - Verificar se o grafo é completo\n"
                + " 9 - Imprimir grafo\n"
                + " 0 - Sair\n " //
        );
    }

    /**
     * Método que executa a opção escolhida pelo usuário.
     */
    private static void menuMain() {
        switch (App.menuMainStr()) {
            case 1 -> App.menuCriacao();
            case 2 -> grafo = Grafo.carregar(App.lerStr(" Digite o nome do arquivo: "));
            case 3 -> System.out.println(
                    grafo.salvar() ? " Grafo salvo com sucesso" : " Erro ao escrever arquivo");
            case 4 -> System.out.println(
                    grafo.addVertice(App.lerInt(" Digite o id do vertice: "))
                            ? " Vertice adicionado com sucesso"
                            : " Erro ao adicionar vertice, verifique se este ja existe" //
                );
            case 5 -> System.out.println(
                    grafo.addAresta(App.lerInt(" Digite o id do vertice de origem: "),
                            App.lerInt(" Digite o id do vertice de destino: "))
                                    ? " Aresta adicionada com sucesso"
                                    : " Erro ao adicionar aresta, verifique se os vertices existem ou se a aresta ja existe" //
                );
            case 6 -> System.out.println(
                    grafo.removerVertice(App.lerInt(" Digite o id do vertice: "))
                            ? " Vertice removido com sucesso"
                            : " Erro ao remover vertice, verifique se este existe" //
                );
            case 7 -> System.out.println(
                    grafo.removerAresta(App.lerInt(" Digite o id do vertice de origem: "),
                            App.lerInt(" Digite o id do vertice de destino: "))
                                    ? " Aresta removida com sucesso"
                                    : " Erro ao remover aresta, verifique se esta existe" //
                );
            case 8 -> System.out.println(
                    grafo.completo()
                            ? " O grafo é completo"
                            : " O grafo não é completo" //
                );
            case 9 -> System.out.println(
                    grafo == null ? " Grafo nao criado" : grafo);
            case 0 -> {
                if (grafo != null && App.lerStr(" Deseja salvar o grafo? (S/N) ").equalsIgnoreCase("S"))
                    grafo.salvar();
                System.exit(0);
            }
            default -> App.menuMain();
        }
        App.pause();

    }

    public static void main(String[] args) throws Throwable {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n Refatoracao do projeto de grafos");
        while (true)
            try {
                App.menuMain();
            } catch (NullPointerException e) {
                System.out.println(" ERRO: Grafo nao criado");
                App.pause();
            } catch (Exception e) {
                System.out.println(" ERRO: " + e.getMessage());
                App.pause();
            }
    }

}
