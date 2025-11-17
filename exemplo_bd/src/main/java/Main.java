import java.util.ArrayList;
import java.util.Scanner;

import dao.ProdutoDAO;
import entity.Produto;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ProdutoDAO dao = new ProdutoDAO();

        ArrayList<Produto> produtos = dao.getTodosProdutos();


        int opcao;

        do {
            System.out.println("===== SISTEMA DE PRODUTOS =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeP = sc.nextLine();
                    System.out.print("Quanitdade: ");
                    int qtdP = sc.nextInt();
                    sc.nextLine();
                    Produto p1 = new Produto(nomeP, qtdP);
                    produtos.add(p1);
                    dao.salvarProduto(p1);
                    System.out.println("Produto cadastrado com sucesso!\n");
                    break;

                case 2:
                    System.out.println("=== Lista de Produtos ===");
                    for (Produto p : produtos) {
                        System.out.println(p);
                        System.out.println("-------------------");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!\n");
            }

        } while (opcao != 0);

        sc.close();
    }
}
