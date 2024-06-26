package controller;

import exceptions.CepInexistenteException;
import exceptions.QuantidadeDeDigitosException;
import exceptions.ValorNumericoException;
import service.CepService;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private CepService cepService;
    private String querContinuar;

    public Menu(CepService cepService){
        this.cepService = cepService;
    }

    public void executaMenu(){
        imprimeCabecalho();

        do {
            System.out.println("\nInforme o CEP que deseja consultar:");
            System.out.println("CEP deve ter 8 números, sem espaços ou caracteres, por exemplo: 00000000");
            String numeroCep = scanner.nextLine().trim();

            try {
                cepService.consultaCep(numeroCep);
            } catch (ValorNumericoException | QuantidadeDeDigitosException | CepInexistenteException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\nTecle 1 para consultar outro CEP ou qualquer outra tecla para sair.");
            querContinuar = scanner.nextLine().trim();
        } while (querContinuar.equals("1"));

        cepService.geraRelatorio();
        System.out.println("Fim da execução do programa.");
    }

    public void imprimeCabecalho(){
        System.out.println("============================");
        System.out.println("        CONSULTA CEP        ");
        System.out.println("============================");
    }

}
