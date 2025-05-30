package br.com.fiap.bean;

/*** Representa um sensor de temperatura utilizado no sistema GuardFlama.
 * Esta classe herda de Sensor e implementa um comportamento específico
 * para a leitura de temperatura.
 * A leitura simula um valor entre 30°C e 45°C.
 * Possui um atributo adicional que define a temperatura máxima permitida.
 * @author Weslley*/
public class SensorTemperatura extends Sensor {

    private double temperaturaMaxPermitida;

    /*** Construtor da subclasse SensorTemperatura.
     * @param id Identificador do sensor
     * @param localizacao Local onde o sensor está instalado
     * @param temperaturaMaxPermitida Valor limite configurado para risco*/
    public SensorTemperatura(String id, String localizacao, double temperaturaMaxPermitida) {
        super(id, localizacao);
        this.temperaturaMaxPermitida = temperaturaMaxPermitida;
    }

    public double getTemperaturaMaxPermitida() { return temperaturaMaxPermitida; }

    public void setTemperaturaMaxPermitida(double temperaturaMaxPermitida) { this.temperaturaMaxPermitida = temperaturaMaxPermitida; }

    /*** Sobrescreve o método da superclasse para simular a leitura de temperatura.
     * Retorna um valor aleatório entre 30.0°C e 45.0°C para representar o valor lido pelo sensor.
     * @return valor simulado da temperatura em graus Celsius*/
    @Override
    public double lerValor() {
        return 30.0 + Math.random() * 15.0;
    }
}
