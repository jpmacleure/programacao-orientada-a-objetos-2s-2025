package calculadora;

public class Calculadora {
    
    public Double numeroA;
    public Double numeroB;

    public void somar(){
        Double resultadoSoma = this.numeroA + this.numeroB;
        System.out.println(this.numeroA + " + " + this.numeroB + " = " + resultadoSoma);
    }

}
