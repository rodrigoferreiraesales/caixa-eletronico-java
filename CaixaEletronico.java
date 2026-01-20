import java.util.Scanner;
public class CaixaEletronico {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double saldo = 1000.00;
        String senhaCorreta = "1234";
        int tentativas = 3;
        int opcao;

        while (tentativas > 0) {
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            if (senha.equals(senhaCorreta)) {
                System.out.println("\nLogin realizado com sucesso!\n");
                break;
            } else {
                tentativas--;
                System.out.println("Senha incorreta. Tentativas restantes: " + tentativas);
            }

            if (tentativas == 0) {
                System.out.println("Cartão bloqueado!");
                scanner.close();
                return;
            }
        }

        do {
            System.out.println("===== CAIXA ELETRÔNICO =====");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Extrato");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.printf("Saldo atual: R$ %.2f\n\n", saldo);
                    break;

                case 2:
                    System.out.print("Valor para depósito: R$ ");
                    double deposito = scanner.nextDouble();

                    if (deposito > 0) {
                        saldo += deposito;
                        System.out.println("Depósito realizado com sucesso!\n");
                    } else {
                        System.out.println("Valor inválido!\n");
                    }
                    break;

                case 3:
                    System.out.print("Valor para saque: R$ ");
                    double saque = scanner.nextDouble();

                    if (saque > 0 && saque <= saldo) {
                        saldo -= saque;
                        System.out.println("Saque realizado com sucesso!\n");
                    } else {
                        System.out.println("Saldo insuficiente ou valor inválido!\n");
                    }
                    break;

                case 4:
                    System.out.println("===== EXTRATO =====");
                    System.out.printf("Saldo disponível: R$ %.2f\n\n", saldo);
                    break;

                case 0:
                    System.out.println("Obrigado por usar o caixa eletrônico!");
                    break;

                default:
                    System.out.println("Opção inválida!\n");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
