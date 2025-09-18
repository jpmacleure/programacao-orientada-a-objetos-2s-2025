package atividade1;

public class SistemaOperacional {
    private Computador computador;

    public SistemaOperacional(Computador computador) {
        this.computador = computador;
    }

    public Computador getComputador() {
        return this.computador;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    public Boolean executarPrograma(Programa p){
        if(p.getSSDOcupado() > this.computador.getSSD()){
            System.out.println("Erro na INSTALAÇÃO do programa.");
            return false;
        }
        if(p.getMemoriaRAMAlocada() > this.computador.getMemoriaRAM()){
            System.out.println("Erro na EXECUÇÃO do programa.");
            return false;
        }

        double tempoExecucao = p.getQuantidadeOperacoes() / (this.computador.getOperacoesPorSegundo()*this.computador.getNucleos());
        System.out.println("Programa executado com sucesso: " + tempoExecucao + "s");
        return true;
    }
}
