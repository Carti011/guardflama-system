package br.com.fiap.bean;

/*** Representa um sensor de fumaça utilizado no sistema GuardFlama.
 * Esta classe herda de Sensor e implementa um comportamento específico
 * para a leitura da densidade de fumaça em determinada região.
 * A leitura simula um valor entre 0 e 100.
 * Possui um atributo adicional que define o nível crítico de fumaça.
 * @author Weslley*/
public class SensorFumaca extends Sensor {

    private double nivelFumacaCritico;

    /*** Construtor da subclasse SensorFumaca.
     * @param id Identificador do sensor
     * @param localizacao Local onde o sensor está instalado
     * @param nivelFumacaCritico Nível de fumaça que representa risco*/
    public SensorFumaca(String id, String localizacao, double nivelFumacaCritico) {
        super(id, localizacao);
        this.nivelFumacaCritico = nivelFumacaCritico;
    }

    public double getNivelFumacaCritico() { return nivelFumacaCritico; }

    public void setNivelFumacaCritico(double nivelFumacaCritico) { this.nivelFumacaCritico = nivelFumacaCritico; }

    /*** Sobrescreve o método da superclasse para simular a leitura da densidade de fumaça.
     * Retorna um valor aleatório entre 0.0 e 100.0 para representar o nível de fumaça no ambiente.
     * @return valor simulado da densidade de fumaça*/
    @Override
    public double lerValor() {
        return Math.random() * 100.0;
    }
}
