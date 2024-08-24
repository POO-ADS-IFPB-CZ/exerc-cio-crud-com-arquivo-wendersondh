package view;

import dao.PessoasDao;
import model.Pessoa;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        PessoasDao pessoasDao = new PessoasDao();

        int numero = 0;
        do {
            menu();
            numero = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()

            switch (numero){
                case 1: {
                    System.out.println("Informe o nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Informe o email: ");
                    String email = scanner.nextLine();
                    Pessoa pessoa = new Pessoa(nome, email);
                    System.out.println(pessoasDao.salvar(pessoa));
                    break;
                }
                case 2:
                    System.out.println(pessoasDao.getPessoas());
                    break;

                case 3: {
                    System.out.println("Informe o email: ");
                    String email = scanner.nextLine();
                    System.out.println(pessoasDao.deletarPorEmail(email));
                    break;
                }
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Resposta inválida");

            }

        }while (numero != 0);
    }

    public static void menu(){
        System.out.println("----------MENU----------");
        System.out.println("(1)- salvar pessoa");
        System.out.println("(2)- lista de pessoas");
        System.out.println("(3)- deletar uma pessoa por email");
        System.out.println("(0)- sair");
    }

}
