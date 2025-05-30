package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;

/**** Classe de controle do sistema GuardFlama.
 * Gerencia as regiões monitoradas e os alertas de risco.
 * Responsável por cadastrar sensores, avaliar riscos e gerar relatórios.
 * @author Weslley*/
public class Controle {

    private List<Regiao> regioes;
    private List<Alerta> alertas;
    private int proximoIdAlerta = 1;

    public Controle() {
        this.regioes = new ArrayList<>();
        this.alertas = new ArrayList<>();
    }

    // Cadastra nova região
    public void cadastrarRegiao(String nome) {
        regioes.add(new Regiao(nome));
    }

    // Adiciona sensor a uma região específica
    public boolean adicionarSensor(String nomeRegiao, Sensor sensor) {
        for (Regiao regiao : regioes) {
            if (regiao.getNome().equalsIgnoreCase(nomeRegiao)) {
                regiao.adicionarSensor(sensor);
                return true;
            }
        }
        return false;
    }

    // Verifica todas as regiões e gera alertas se houver risco
    public void verificarRiscos() {
        for (Regiao regiao : regioes) {
            if (regiao.riscoIncendio()) {
                double temp = 0;
                double fumaca = 0;
                for (Sensor sensor : regiao.getSensores()) {
                    if (sensor instanceof SensorTemperatura) temp = sensor.lerValor();
                    if (sensor instanceof SensorFumaca) fumaca = sensor.lerValor();
                }
                String nivel = (temp > 42 || fumaca > 85) ? "Crítico" : "Moderado";
                Alerta alerta = new Alerta(proximoIdAlerta++, regiao.getNome(), nivel, temp, fumaca);
                alertas.add(alerta);
            }
        }
    }

    // Gera relatório de todas as regiões
    public String gerarRelatorioCompleto() {
        StringBuilder relatorio = new StringBuilder();
        for (Regiao regiao : regioes) {
            relatorio.append(regiao.gerarRelatorio()).append("\n");
        }
        return relatorio.toString();
    }

    // Lista os alertas registrados
    public String listarAlertas() {
        StringBuilder sb = new StringBuilder("Alertas gerados:\n");
        for (Alerta alerta : alertas) {
            sb.append("ID: ").append(alerta.getId())
                    .append(" | Região: ").append(alerta.getRegiao())
                    .append(" | Nível: ").append(alerta.getNivelRisco())
                    .append(" | Temp: ").append(String.format("%.2f", alerta.getTemperaturaDetectada()))
                    .append(" | Fumaça: ").append(String.format("%.2f", alerta.getFumacaDetectada()))
                    .append(" | Data: ").append(alerta.getDataHoraFormatada())
                    .append("\n");
        }
        return sb.toString();
    }
}
