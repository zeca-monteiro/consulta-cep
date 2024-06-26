package models;

import com.google.gson.annotations.SerializedName;

public class Cep {

    @SerializedName("cep")
    private String numeroCep = "";
    private String logradouro;
    private String bairro;
    private String localidade;
    @SerializedName("uf")
    private String unidadeFederativa;

    @Override
    public String toString() {
        return "CEP: " + numeroCep +
                "\nLogradouro: " + logradouro +
                "\nBairro: " + bairro +
                "\nLocalidade: " + localidade +
                "\nUnidade Federativa: " + unidadeFederativa;
    }

    public String getNumeroCep() {
        return numeroCep;
    }

}
