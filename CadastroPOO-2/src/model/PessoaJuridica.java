package model;

import java.io.Serializable;

public class PessoaJuridica extends Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    private String cnpj;

    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getNome() + ", CNPJ: " + this.getCnpj();
    }
}
