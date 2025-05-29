package br.com.fiap.bean;

/*** Representa um sensor de fumaça utilizado no sistema GuardFlama.
 * Esta classe herda de Sensor e implementa um comportamento específico
 * para a leitura da densidade de fumaça em determinada região.
 * A leitura simula um valor entre 0 e 100.
 * @author Weslley*/
public class SensorFumaca extends Sensor {

    /*** Construtor da subclasse SensorFumaca.
     * @param id Identificador do sensor
     * @param localizacao Local onde o sensor está instalado*/
    public SensorFumaca(String id, String localizacao) {
        super(id, localizacao);
    }

    /*** Simula a leitura da densidade de fumaça no ambiente.
     * @return Valor aleatório entre 0.0 e 100.0*/
    @Override
    public double lerValor() {
        return Math.random() * 100.0;
    }
}
