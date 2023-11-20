package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaJuridicaRepo {
    private final List<PessoaJuridica> pessoaJuridicas = new ArrayList<>();

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoaJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        pessoaJuridicas.replaceAll(p -> p.getId() == pessoaJuridica.getId() ? pessoaJuridica : p);
    }

    public void excluir(int id) {
        pessoaJuridicas.removeIf(p -> p.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        return pessoaJuridicas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoaJuridicas);
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(pessoaJuridicas);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoaJuridicas.clear();
            List<PessoaJuridica> listaRecuperada = (List<PessoaJuridica>) inputStream.readObject();
            pessoaJuridicas.addAll(listaRecuperada);
        }
    }
}
