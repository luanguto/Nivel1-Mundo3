package main;

import java.io.IOException;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;


public class Main {
    public static void main(String[] args) {

        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();


        repo1.inserir(new PessoaFisica(1, "João", "123.456.789-01", 25));
        repo1.inserir(new PessoaFisica(2, "Maria", "987.654.321-09", 30));


        try {
            repo1.persistir("pessoasFisicas.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }


        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        try {
            repo2.recuperar("pessoasFisicas.dat");
            for (PessoaFisica pf : repo2.obterTodos()) {
                pf.exibir();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();


        repo3.inserir(new PessoaJuridica(3, "Empresa A", "12.345.678/0001-90"));
        repo3.inserir(new PessoaJuridica(4, "Empresa B", "98.765.432/0001-10"));


        try {
            repo3.persistir("pessoasJuridicas.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }


        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        try {
            repo4.recuperar("pessoasJuridicas.dat");
            for (PessoaJuridica pj : repo4.obterTodos()) {
                pj.exibir();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
