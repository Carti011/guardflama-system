package br.com.fiap.bean;

import java.util.*;

/*** Classe responsável por controlar as regiões e os alertas no sistema GuardFlama.
 * Permite cadastrar regiões, sensores e verificar riscos com base nas leituras.
 * Evita alertas duplicados com base em registros únicos por região, temperatura e fumaça.
 * @author Weslley */
public class Controle {

    private List<Regiao> regioes;
    private List<Alerta> alertas;
    private int contadorAlertas;
    private Set<String> alertasRegistrados;

    public Controle() {
        this.regioes = new ArrayList<>();
        this.alertas = new ArrayList<>();
        this.contadorAlertas = 1;
        this.alertasRegistrados = new HashSet<>();
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

    /*** Verifica o risco em todas as regiões e gera alertas se necessário, evitando duplicatas */
    public void verificarRiscos() {
        for (Regiao r : regioes) {
            double temp = 0.0;
            double fumaca = 0.0;

            for (Sensor s : r.getSensores()) {
                if (s instanceof SensorTemperatura) {
                    temp = s.lerValor();
                } else if (s instanceof SensorFumaca) {
                    fumaca = s.lerValor();
                }
            }

            if (temp > 40 || fumaca > 70) {
                String chave = r.getNome() + "-" + temp + "-" + fumaca;
                if (!alertasRegistrados.contains(chave)) {
                    String risco = (temp > 40 && fumaca > 70) ? "Crítico"
                            : (temp > 35 || fumaca > 50) ? "Moderado"
                            : "Baixo";

                    alertas.add(new Alerta(contadorAlertas++, r.getNome(), risco, temp, fumaca));
                    alertasRegistrados.add(chave);
                }
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
        if (alertas.isEmpty()) return "Nenhum alerta gerado.";

        StringBuilder sb = new StringBuilder("Alertas Gerados:\n");
        for (Alerta alerta : alertas) {
            sb.append("ID ").append(alerta.getId())
                    .append(" - Região: ").append(alerta.getRegiao())
                    .append(" | Nível: ").append(alerta.getNivelRisco())
                    .append(" | Temperatura: ").append(String.format("%.2f", alerta.getTemperaturaDetectada()))
                    .append(" | Fumaça: ").append(String.format("%.2f", alerta.getFumacaDetectada()))
                    .append(" | Data: ").append(alerta.getDataHoraFormatada())
                    .append("\n");
        }
        return sb.toString();
    }
}
