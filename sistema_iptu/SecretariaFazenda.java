package sistema_iptu;

public class SecretariaFazenda {
    public static void main(String[] args) {
        Municipio municipio = new Municipio("Brasília", "DF", 31.5);
        Apartamento apt = new Apartamento(municipio, 63.0, 0, true);

        System.out.println("IPTU R$: " + apt.calcularIPTU());
    }
}
