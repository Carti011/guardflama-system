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

    public Regiao(String nome) {
        this.nome = nome;
        this.sensores = new ArrayList<>();
    }

    /*** Adiciona um sensor à lista de sensores da região.
     * @param sensor Objeto do tipo Sensor (temperatura ou fumaça) a ser adicionado à região*/
    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
    }

    /*** Verifica se a região apresenta risco de incêndio com base nos valores
     * lidos pelos sensores de temperatura e fumaça.
     * @return true se algum valor ultrapassar os limites de segurança, false caso contrário*/
    public boolean riscoIncendio() {
        double temperatura = 0;
        double fumaca = 0;

        for (Sensor sensor : sensores) {
            if (sensor instanceof SensorTemperatura) {
                temperatura = sensor.lerValor();
            } else if (sensor instanceof SensorFumaca) {
                fumaca = sensor.lerValor();
            }
        }

        return temperatura > 40 || fumaca > 70;
    }

    /*** Gera um relatório textual com os valores atuais de todos os sensores da região.
     * @return String contendo a descrição e leituras dos sensores*/
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder("Relatório da Região: " + nome + "\n");

        for (Sensor sensor : sensores) {
            double valor = sensor.lerValor();
            relatorio.append("- ")
                    .append(sensor.getClass().getSimpleName())
                    .append(" (")
                    .append(sensor.getId())
                    .append("): ")
                    .append(String.format("%.2f", valor))
                    .append("\n");
        }

        return relatorio.toString();
    }

    /*** Cria um alerta de risco baseado nas leituras atuais dos sensores da região.
     * O nível de risco é classificado como Crítico, Moderado ou Baixo.
     * @param id Identificador único do alerta
     * @return Objeto Alerta contendo dados da região, valores e risco*/
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
            nivelRisco = "Crítico";
        } else if (temperatura > 35 || fumaca > 50) {
            nivelRisco = "Moderado";
        } else {
            nivelRisco = "Baixo";
        }

        return new Alerta(id, nome, nivelRisco, temperatura, fumaca);
    }

    public String getNome() { return nome; }

    public List<Sensor> getSensores() { return sensores; }
}
