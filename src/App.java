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
        grafo = new Grafo(
                App.lerStr("\n Digite o nome do grafo: "),
                App.lerStr(" O grafo sera ponderado? (S/N) ").equalsIgnoreCase("S"),
                App.lerStr(" O grafo sera direcionado? (S/N) ").equalsIgnoreCase("S") //
        );
        System.out.println(" Grafo criado com sucesso!");
        App.pause();
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
                + " 3 - Adicionar um vertice\n"
                + " 4 - Adicionar uma aresta\n"
                + " 5 - Imprimir grafo\n"
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
            case 3 -> {
                if (grafo.addVertice(App.lerInt(" Digite o id do vertice: ")))
                    System.out.println(" Vertice adicionado com sucesso");
                else
                    System.out.println(" Erro ao adicionar vertice, verifique se o vertice ja existe");
                App.pause();
            }
            case 4 -> {
                if (grafo.addAresta(App.lerInt(" Digite o id do vertice de origem: "),
                        App.lerInt(" Digite o id do vertice de destino: ")))
                    System.out.println(" Aresta adicionada com sucesso");
                else
                    System.out.println(
                            " Erro ao adicionar aresta, verifique se os vertices existem ou se a aresta ja existe");
                App.pause();
            }
            case 5 -> {
                System.out.println(grafo);
                App.pause();
            }
            case 0 -> {
                if (App.lerStr(" Deseja salvar o grafo? (S/N) ").equalsIgnoreCase("S"))
                    grafo.salvar();
                System.exit(0);
            }
            default -> App.menuMain();
        }
    }

    public static void main(String[] args) throws Throwable {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n Refatoracao do projeto de grafos");
        while (true)
            App.menuMain();
    }

}
