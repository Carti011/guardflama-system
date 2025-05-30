package br.com.fiap.bean;

/**** Representa um sensor de temperatura utilizado no sistema GuardFlama.
 * Esta classe herda de Sensor e armazena um valor de temperatura informado,
 * permitindo simular leituras realistas determinadas manualmente.
 * @author Weslley*/
public class SensorTemperatura extends Sensor {

    private double temperaturaAtual;

    /**** Construtor da subclasse SensorTemperatura.
     * @param id Identificador do sensor
     * @param localizacao Local onde o sensor está instalado
     * @param temperaturaAtual Valor da temperatura atual medida*/
    public SensorTemperatura(String id, String localizacao, double temperaturaAtual) {
        super(id, localizacao);
        this.temperaturaAtual = temperaturaAtual;
    }

    public double getTemperaturaAtual() { return temperaturaAtual; }

    public void setTemperaturaAtual(double temperaturaAtual) { this.temperaturaAtual = temperaturaAtual; }

    /**** Retorna o valor atual da temperatura informada para o sensor.
     * @return valor da temperatura em graus Celsius*/
    @Override
    public double lerValor() {
        return temperaturaAtual;
    }
}
