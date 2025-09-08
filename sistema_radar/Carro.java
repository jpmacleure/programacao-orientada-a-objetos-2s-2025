package sistema_radar;

public class Carro {
   public String placa;
   public Integer velocidade;
   public Integer ano;
   public String modelo;
   
   public void acelerar(){
    this.velocidade += 10;
   }

   public void frear(){
    if(this.velocidade > 0){
        this.velocidade -= 10;
    }
   }
   
}
