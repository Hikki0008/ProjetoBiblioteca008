package org.biblioteca.biblioteca009.Modelos;

import java.time.LocalDate;

public class Emprestimos {
    private int idEmprestimos;
    private int idCliente;
    private int idlivro;
    private LocalDate dataEmprestimos;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    public Emprestimos() {
        this.idlivro = idlivro;
        this.idCliente = idCliente;
        this.dataEmprestimos = dataEmprestimos;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }



    // Getters e Setters
    public int getIdEmprestimos() {
        return idEmprestimos;
    }

    public void setIdEmprestimos(int idEmprestimos) {
        this.idEmprestimos = idEmprestimos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdLivro() {
        return idlivro;
    }

    public void setIdLivro(int idLivro) {
        this.idlivro = idLivro;
    }

    public LocalDate getDataEmprestimos() {
        return dataEmprestimos;
    }

    public void setDataEmprestimos(LocalDate dataEmprestimos) {
        this.dataEmprestimos = dataEmprestimos;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
