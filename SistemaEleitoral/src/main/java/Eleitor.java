import java.util.Objects;

public class Eleitor {

    private String nome;
    private String titulo;

    public Eleitor(String nome, String titulo) {
        this.nome = nome;
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Eleitor eleitor = (Eleitor) obj;
        return Objects.equals(nome, eleitor.nome) && Objects.equals(titulo, eleitor.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, titulo);
    }
}
