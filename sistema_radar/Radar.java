public class Radar {
    public Integer limiteVelocidade;
    public String modelo;
    public String localizacao;

    public void emitirNotificacao(String placa, Integer valocidadeAvaliada){
        System.out.println("Limite de velocidade: "+ this.limiteVelocidade);
        System.out.println("Velocidade avaliada: "+ valocidadeAvaliada);
        System.out.println("Placa: "+placa);
    }

    public void avaliarVelocidade(Carro c){
        if(c.velocidade > this.limiteVelocidade){
            emitirNotificacao(c.placa, c.velocidade);
        }
    }

}
