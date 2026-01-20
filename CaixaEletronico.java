import java.util.ArrayList;
import java.util.Scanner;

public class CaixaEletronico {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Rodrigo", "1234", 1000));

        Usuario usuarioLogado = null;
        int tentativas = 3;

        while (tentativas > 0) {
            System.out.print("Digite o nome do usuário: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();

            for (Usuario u : usuarios) {
                if (u.nome.equals(nome) && u.senha.equals(senha)) {
                    usuarioLogado = u;
                    break;
                }
            }

            if (usuarioLogado != null) break;
            else tentativas--;
            if (tentativas == 0) {
                System.out.println("Cartão bloqueado!");
                scanner.close();
                return;
            }
        }

        int opcao;
        do {
            System.out.println("===== CAIXA ELETRÔNICO =====");
            System.out.println("1 - Ver saldo / Extrato");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Ver histórico de transações");
            System.out.println("5 - Criar novo usuário");
            System.out.println("6 - Transferir para outro usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch(opcao) {
                case 1:
                    usuarioLogado.mostrarExtrato();
                    break;
                case 2:
                    System.out.print("Valor para depósito: R$ ");
                    double deposito = scanner.nextDouble();
                    usuarioLogado.depositar(deposito);
                    break;
                case 3:
                    System.out.print("Valor para saque: R$ ");
                    double saque = scanner.nextDouble();
                    usuarioLogado.sacar(saque);
                    break;
                case 4:
                    usuarioLogado.mostrarExtrato();
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.print("Digite o nome do novo usuário: ");
                    String novoNome = scanner.nextLine();
                    boolean nomeExiste = false;
                    for (Usuario u : usuarios) if(u.nome.equals(novoNome)) nomeExiste = true;
                    if(nomeExiste) break;
                    System.out.print("Digite a senha do novo usuário: ");
                    String novaSenha = scanner.nextLine();
                    if(novaSenha.length() < 4) break;
                    System.out.print("Digite o saldo inicial: R$ ");
                    double saldoInicial = scanner.nextDouble();
                    usuarios.add(new Usuario(novoNome, novaSenha, saldoInicial));
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.print("Nome do usuário destinatário: ");
                    String nomeDestino = scanner.nextLine();
                    Usuario destinatario = null;
                    for (Usuario u : usuarios) if(u.nome.equals(nomeDestino)) destinatario = u;
                    if(destinatario == null) break;
                    System.out.print("Valor da transferência: R$ ");
                    double valorTransferencia = scanner.nextDouble();
                    if(valorTransferencia > 0 && valorTransferencia <= usuarioLogado.saldo){
                        usuarioLogado.saldo -= valorTransferencia;
                        destinatario.saldo += valorTransferencia;
                        usuarioLogado.historico.add("Transferência enviada: -R$ " + valorTransferencia + " para " + destinatario.nome);
                        destinatario.historico.add("Transferência recebida: +R$ " + valorTransferencia + " de " + usuarioLogado.nome);
                    }
                    break;
                case 0:
                    break;
            }

        } while(opcao != 0);

        scanner.close();
    }
}
