package sistema_iptu;

public class Apartamento extends Imovel{
    private Boolean possuiElevador;

    public Apartamento(Municipio municipio, Double areaM2, Integer vagas, Boolean possuiElevador){
        super(municipio, areaM2, vagas);
        this.possuiElevador = possuiElevador;
    }
}
