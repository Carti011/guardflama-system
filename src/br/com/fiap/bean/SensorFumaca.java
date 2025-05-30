package br.com.fiap.bean;

/**** Representa um sensor de fumaça utilizado no sistema GuardFlama.
 * Esta classe herda de Sensor e armazena um valor de densidade de fumaça informado,
 * permitindo simular leituras manuais determinadas pelo usuário.
 * @author Weslley*/
public class SensorFumaca extends Sensor {

    private double nivelFumacaAtual;

    /**** Construtor da subclasse SensorFumaca.
     * @param id Identificador do sensor
     * @param localizacao Local onde o sensor está instalado
     * @param nivelFumacaAtual Valor atual da densidade de fumaça medida*/
    public SensorFumaca(String id, String localizacao, double nivelFumacaAtual) {
        super(id, localizacao);
        this.nivelFumacaAtual = nivelFumacaAtual;
    }

    public double getNivelFumacaAtual() { return nivelFumacaAtual; }

    public void setNivelFumacaAtual(double nivelFumacaAtual) { this.nivelFumacaAtual = nivelFumacaAtual; }

    /**** Retorna o valor atual da densidade de fumaça informada para o sensor.
     * @return valor da fumaça em escala de 0 a 100*/
    @Override
    public double lerValor() {
        return nivelFumacaAtual;
    }
}
