package br.com.fiap.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por controlar as regiões e os alertas no sistema GuardFlama.
 * Permite cadastrar regiões, sensores e verificar riscos com base nas leituras.
 * @author Weslley
 */
public class Controle {

    private List<Regiao> regioes;
    private List<Alerta> alertas;
    private int contadorAlertas;

    public Controle() {
        this.regioes = new ArrayList<>();
        this.alertas = new ArrayList<>();
        this.contadorAlertas = 1;
    }

    /*** Adiciona uma nova região instanciada externamente (usado em testes mocados) */
    public void adicionarRegiao(Regiao regiao) {
        regioes.add(regiao);
    }

    /*** Cadastra uma nova região com base no nome informado */
    public void cadastrarRegiao(String nome) {
        regioes.add(new Regiao(nome));
    }

    /*** Adiciona um sensor a uma região existente */
    public void adicionarSensor(String nomeRegiao, Sensor sensor) {
        for (Regiao r : regioes) {
            if (r.getNome().equalsIgnoreCase(nomeRegiao)) {
                r.adicionarSensor(sensor);
                break;
            }
        }
    }

    /*** Verifica o risco em todas as regiões e gera alertas se necessário */
    public void verificarRiscos() {
        for (Regiao r : regioes) {
            if (r.riscoIncendio()) {
                double temp = 0.0;
                double fumaca = 0.0;
                for (Sensor s : r.getSensores()) {
                    if (s instanceof SensorTemperatura) temp = s.lerValor();
                    if (s instanceof SensorFumaca) fumaca = s.lerValor();
                }

                String risco = (temp > 40 && fumaca > 70) ? "Crítico" :
                        (temp > 35 || fumaca > 50) ? "Moderado" : "Baixo";

                alertas.add(new Alerta(contadorAlertas++, r.getNome(), risco, temp, fumaca));
            }
        }
    }

    /*** Gera relatório de todas as regiões */
    public String gerarRelatorioCompleto() {
        StringBuilder sb = new StringBuilder();
        for (Regiao r : regioes) {
            sb.append(r.gerarRelatorio()).append("\n");
        }
        return sb.toString();
    }

    /*** Lista os alertas gerados */
    public String listarAlertas() {
        StringBuilder sb = new StringBuilder("🔔 Alertas Gerados:\n");
        for (Alerta a : alertas) {
            sb.append("ID ").append(a.getId()).append(" - Região: ")
                    .append(a.getRegiao()).append(" | Nível: ")
                    .append(a.getNivelRisco()).append(" | Temperatura: ")
                    .append(a.getTemperaturaDetectada()).append(" | Fumaça: ")
                    .append(a.getFumacaDetectada()).append(" | Data: ")
                    .append(a.getDataHoraFormatada()).append("\n");
        }
        return sb.toString();
    }
}
