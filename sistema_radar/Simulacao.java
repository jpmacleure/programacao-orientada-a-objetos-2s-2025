package sistema_radar;

public class Simulacao {
    public static void main(String[] args) {
        System.out.println("Simulação");

        Carro opala = new Carro();
        opala.ano = 76;
        opala.modelo = "Comodoro";
        opala.placa = "ACLR300";
        opala.velocidade = 0;

        Radar radar = new Radar();
        radar.limiteVelocidade = 60;
        radar.localizacao = "Pistão Sul";

        radar.avaliarVelocidade(opala);

        opala.acelerar(); // 10
        opala.acelerar();
        opala.acelerar();
        opala.acelerar();
        opala.acelerar();
        opala.acelerar();
        opala.acelerar(); // 70

        radar.avaliarVelocidade(opala);

        opala.frear(); // 60

        radar.avaliarVelocidade(opala);
    }
}
