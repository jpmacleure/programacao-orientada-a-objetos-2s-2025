package sistema_radar;

public class Carro {
   private String placa;
   private Integer velocidade;
   private Integer ano;
   private String modelo;

   public Carro(String placa, Integer velocidade, Integer ano, String modelo){
    this.placa = placa;
    this.velocidade = velocidade;
    this.ano = ano;
    this.modelo = modelo;
   }
   
   public Integer getVelocidade(){
        return this.velocidade;
   }

   public void setVelocidade(Integer velocidade){
    if(velocidade != null && velocidade > 0){
        this.velocidade = velocidade;
    }
   }

   public String getPlaca(){
    return this.placa;
   }

   public void acelerar(){
    this.velocidade += 10;
   }

   public void frear(){
    if(this.velocidade > 0){
        this.velocidade -= 10;
    }
   }
   
}
