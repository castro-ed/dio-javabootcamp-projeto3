package com.banco;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BancoDigital banco = new BancoDigital("Banco Digital");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("1. Criar conta");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void criarConta() {
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.println("Tipo de conta (1- Corrente, 2- Poupança): ");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = new Cliente(nome, email, telefone);
        Conta conta = null;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente);
        }

        banco.adicionarConta(conta);
        System.out.println("Conta criada com sucesso!");
    }

    private static void login() {
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();

        Conta conta = banco.autenticar(email, telefone);
        if (conta != null) {
            acessarConta(conta);
        } else {
            System.out.println("Login inválido");
        }
    }

    private static void acessarConta(Conta conta) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Consultar Saldo");
            System.out.println("4. Consultar Extrato");
            System.out.println("5. Transferir");
            System.out.println("6. Sair");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Valor para depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    scanner.nextLine();
                    conta.depositar(valorDeposito);
                    break;
                case 2:
                    System.out.println("Valor para saque: ");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine();
                    conta.sacar(valorSaque);
                    break;
                case 3:
                    System.out.println(String.format("Saldo: %.2f", conta.getSaldo()));
                    break;
                case 4:
                    conta.imprimirExtrato();
                    break;
                case 5:
                    System.out.println("Email da conta destino: ");
                    String emailDestino = scanner.nextLine();
                    System.out.println("Valor para transferência: ");
                    double valorTransferencia = scanner.nextDouble();
                    scanner.nextLine();
                    Conta contaDestino = banco.getContas().get(emailDestino);
                    if (contaDestino != null) {
                        conta.transferir(valorTransferencia, contaDestino);
                    } else {
                        System.out.println("Conta destino não encontrada");
                    }
                    break;
                case 6:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
