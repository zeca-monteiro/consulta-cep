package service;

import exceptions.CepInexistenteException;
import exceptions.QuantidadeDeDigitosException;
import exceptions.ValorNumericoException;
import bancoDeDados.BancoDeDados;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Cep;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CepService {

    private final BancoDeDados bancoDeDados;
    private final Gson gson = new GsonBuilder().create();

    public CepService(BancoDeDados bancoDeDados) {
        this.bancoDeDados = bancoDeDados;
    }

    public void consultaCep(String numeroCep) throws CepInexistenteException, ValorNumericoException, QuantidadeDeDigitosException {
        validaFormato(numeroCep);

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/" + numeroCep + "/json/")).build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Cep cep = gson.fromJson(json, Cep.class);

            validaExistencia(cep);
            System.out.println(cep);
            bancoDeDados.adicionaNoBancoDeDados(cep);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void geraRelatorio(){
        try (BufferedWriter buffered = new BufferedWriter(new FileWriter("relatorio.txt"))) {
            if (bancoDeDados.getCepsConsultados().isEmpty()) {
                buffered.write("Nenhum CEP válido consultado durante a execução do programa.");
            } else {
                buffered.write("Lista de CEPs consultados durante a execução do programa:\n\n");
                for (Cep cep : bancoDeDados.getCepsConsultados()) {
                    buffered.write(String.valueOf(cep));
                    buffered.newLine();
                    buffered.newLine();
                }
            }
            System.out.println("Relatório com as consultas realizadas foi gerado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validaFormato(String numeroCep) throws ValorNumericoException, QuantidadeDeDigitosException {
        validaValorNumerico(numeroCep);
        validaComprimento(numeroCep);
    }

    public void validaValorNumerico(String numeroCep) throws ValorNumericoException {
        if (!numeroCep.matches("[0-9]+")) {
            throw new ValorNumericoException("\nERRO! CEP não pode conter espaços, letras ou caracteres especiais.");
        }
    }

    public void validaComprimento(String numeroCep) throws QuantidadeDeDigitosException {
        if (numeroCep.length() != 8) {
            throw new QuantidadeDeDigitosException("\nERRO: CEP deve ter 8 dígitos, sem espaços.");
        }
    }

    public void validaExistencia(Cep cep) throws CepInexistenteException {
        if (cep.getNumeroCep().isEmpty()) {
            throw new CepInexistenteException("\nNúmero digitado não é um CEP existente.");
        }
    }

}