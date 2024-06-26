package util;

import bancoDeDados.BancoDeDados;
import controller.Menu;
import service.CepService;

public class Main {
    public static void main(String[] args) {

        BancoDeDados bancoDeDados = new BancoDeDados();
        CepService cepService = new CepService(bancoDeDados);
        Menu menu = new Menu(cepService);

        menu.executaMenu();
    }
}
