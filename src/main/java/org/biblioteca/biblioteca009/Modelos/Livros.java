package org.biblioteca.biblioteca009.Modelos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Livros {
    private IntegerProperty idlivro = new SimpleIntegerProperty();
    private StringProperty exemplar = new SimpleStringProperty();
    private StringProperty autor = new SimpleStringProperty();
    private IntegerProperty edicao = new SimpleIntegerProperty();
    private IntegerProperty ano = new SimpleIntegerProperty();
    private StringProperty disponibilidade = new SimpleStringProperty();

    public Livros() {}

    public Livros(int id, String exemplar, String autor, String edicao, String ano, String disponibilidade) {
        this.idlivro.set(id);
        this.exemplar.set(exemplar);
        this.autor.set(autor);
        this.edicao.set(Integer.parseInt(edicao));
        this.ano.set(Integer.parseInt(ano));
        this.disponibilidade.set(disponibilidade);
    }

    public IntegerProperty idProperty() { return idlivro; }
    public StringProperty exemplarProperty() { return exemplar; }
    public StringProperty autorProperty() { return autor; }
    public IntegerProperty anoProperty() { return ano; }
    public StringProperty disponibilidadeProperty() { return disponibilidade; }

    // Getters e setters padrões
    public int getId() { return idlivro.get(); }
    public void setId(int id) { this.idlivro.set(id); }

    public String getExemplar() { return exemplar.get(); }
    public void setExemplar(String exemplar) { this.exemplar.set(exemplar); }

    public String getAutor() { return autor.get(); }
    public void setAutor(String autor) { this.autor.set(autor); }


    public String getDisponibilidade() { return disponibilidade.get(); }
    public void setDisponibilidade(String disponibilidade) { this.disponibilidade.set(disponibilidade); }


}
