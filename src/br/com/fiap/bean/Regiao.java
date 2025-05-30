package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;

/*** Representa uma região monitorada pelo sistema GuardFlama.
 * Cada região pode conter múltiplos sensores, como temperatura e fumaça.
 * Possui métodos para adicionar sensores, verificar o risco de incêndio
 * e gerar alertas com base nas leituras atuais.
 * @author Weslley*/
public class Regiao {

    private String nome;
    private List<Sensor> sensores;

    /*** Construtor que inicializa a região com um nome.
     * @param nome Nome da região monitorada*/
    public Regiao(String nome) {
        this.nome = nome;
        this.sensores = new ArrayList<>();
    }

    /*** Adiciona um sensor à lista de sensores da região.
     * @param sensor Objeto Sensor a ser adicionado*/
    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
    }

    /*** Verifica o risco da região com base nas leituras dos sensores.
     * @return true se houver risco alto, false caso contrário*/
    public boolean riscoIncendio() {
        for (Sensor sensor : sensores) {
            if (sensor instanceof SensorTemperatura && sensor.lerValor() > 40) {
                return true;
            }
            if (sensor instanceof SensorFumaca && sensor.lerValor() > 70) {
                return true;
            }
        }
        return false;
    }

    /*** Gera um relatório simples com as leituras atuais dos sensores da região.
     * @return Texto com o resumo dos valores lidos*/
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("Relatório da Região: " + nome + "\n");
        for (Sensor sensor : sensores) {
            relatorio.append("- ")
                    .append(sensor.getClass().getSimpleName())
                    .append(" (")
                    .append(sensor.getId())
                    .append("): ")
                    .append(String.format("%.2f", sensor.lerValor()))
                    .append("\n");
        }
        return relatorio.toString();
    }

    /*** Gera um objeto Alerta com base nas leituras atuais dos sensores da região.
     * @param id Identificador do alerta a ser criado
     * @return Objeto Alerta preenchido com os dados atuais*/
    public Alerta gerarAlerta(int id) {
        double temperatura = 0;
        double fumaca = 0;

        for (Sensor sensor : sensores) {
            if (sensor instanceof SensorTemperatura) {
                temperatura = sensor.lerValor();
            } else if (sensor instanceof SensorFumaca) {
                fumaca = sensor.lerValor();
            }
        }

        String nivelRisco;
        if (temperatura > 40 || fumaca > 70) {
            nivelRisco = "ALTO";
        } else if (temperatura > 37 || fumaca > 50) {
            nivelRisco = "MODERADO";
        } else {
            nivelRisco = "BAIXO";
        }

        return new Alerta(id, nome, nivelRisco, temperatura, fumaca);
    }

    public String getNome() { return nome; }

    public List<Sensor> getSensores() { return sensores; }
}
