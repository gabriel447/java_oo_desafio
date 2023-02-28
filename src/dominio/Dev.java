package dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp) {
        // pegando o meu set de conteudos inscritos e adicionando dentro dele todos os conteudos do bootcamp
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        // adicionar o dev no bootcamp
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        // pegando o primeiro conteudo de conteudos inscritos e colocando dentro de uma variável
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        // tirando o conteudo de dentro de conteudos inscritos e colocar dentro de conteudos concluidos
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            //agora que colocamos dentro de conteudos concluidos vamos remover de conteudos inscritos
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.out.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        // pegamos cada conteudo do set de conteudosConcluidos, pegamos o xp de cada conteudo e somamos
        return this.conteudosConcluidos.stream().mapToDouble(conteudo -> conteudo.calcularXp()).sum();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos)
                && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }

}
