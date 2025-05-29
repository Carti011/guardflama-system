package br.com.fiap.bean;
/*** Representa um sensor de temperatura utilizado no sistema GuardFlama.
 * Esta classe herda de Sensor e implementa um comportamento específico
 * para a leitura de temperatura.
 * A leitura simula um valor entre 30°C e 45°C.
 * @author Weslley*/

public class SensorTemperatura extends Sensor {
    /*** Construtor da subclasse SensorTemperatura.
     * @param id Identificador do sensor
     * @param localizacao Local onde o sensor está instalado*/
    public SensorTemperatura(String id, String localizacao) {
        super(id, localizacao);
    }

    /*** Simula a leitura da temperatura ambiente em graus Celsius.
     * @return Valor aleatório entre 30.0 e 45.0*/
    @Override
    public double lerValor() {
        return 30.0 + Math.random() * 15.0;
    }
}
