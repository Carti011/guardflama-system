package br.com.fiap.bean;

/*** Classe base que representa um sensor genérico no sistema GuardFlama.
 * Essa classe poderá ser estendida por sensores específicos como SensorTemperatura e SensorFumaca.
 * Utiliza encapsulamento nos atributos e pode ser sobrescrita por subclasses.
 * @author Weslley*/
public class Sensor {

    private String id;
    private String localizacao;

    /*** Construtor da classe Sensor que inicializa os atributos principais.
     * @param id Identificador único do sensor
     * @param localizacao Local onde o sensor está instalado*/
    public Sensor(String id, String localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }

    public String getId() { return id; }

    public String getLocalizacao() { return localizacao; }

    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    /*** Método que simula a leitura do sensor.
     * Pode ser sobrescrito por subclasses com comportamentos diferentes.
     * @return Valor padrão lido pelo sensor*/
    public double lerValor() {
        return 0.0; // valor padrão, sobrescrito nas subclasses
    }
}
