package model;
public class Revista extends Material{
    private Integer edicao;

    public Revista(String titulo, Integer anoPublicacao, Integer edicao){
        super(titulo, anoPublicacao);
        this.edicao = edicao;
    }

    public Integer getEdicao() {
        return this.edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    @Override
    public void descricao() {
        System.out.println("Título: " + this.getTitulo());
        System.out.println("Ano de publicação: " + this.getAnoPublicacao());
        System.out.println("Edição: " + this.edicao);
    }
}
