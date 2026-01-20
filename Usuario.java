import java.util.ArrayList;

public class Usuario {
    String nome;
    String senha;
    double saldo;
    ArrayList<String> historico;

    public Usuario(String nome, String senha, double saldoInicial) {
        this.nome = nome;
        this.senha = senha;
        this.saldo = saldoInicial;
        this.historico = new ArrayList<>();
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            historico.add("Depósito: +R$ " + valor);
            System.out.println("Depósito realizado com sucesso!\n");
        } else {
            System.out.println("Valor inválido!\n");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            historico.add("Saque: -R$ " + valor);
            System.out.println("Saque realizado com sucesso!\n");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido!\n");
        }
    }

    public void mostrarExtrato() {
        System.out.println("===== EXTRATO =====");
        for (String t : historico) {
            System.out.println(t);
        }
        System.out.printf("Saldo atual: R$ %.2f\n\n", saldo);
    }
}

            
    