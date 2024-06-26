package bancoDeDados;

import models.Cep;

import java.util.ArrayList;

public class BancoDeDados {

    ArrayList<Cep> cepsConsultados = new ArrayList<>();

    public void adicionaNoBancoDeDados(Cep cep){
        cepsConsultados.add(cep);
    }

    public ArrayList<Cep> getCepsConsultados() {
        return cepsConsultados;
    }

}
