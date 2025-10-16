public class Instituicao implements TomadorEmprestimo{
    private String cpnj;
    private String nome;
    private String cidade;

    public Instituicao(String cpnj, String nome, String cidade) {
        this.cpnj = cpnj;
        this.nome = nome;
        this.cidade = cidade;
    }

    public String getCpnj() {
        return this.cpnj;
    }

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void exibirInfo(){
        System.out.println("CNPJ: " + this.cpnj);
        System.out.println("Nome: " + this.nome);
        System.out.println("Cidade: " + this.cidade);
    }

}
