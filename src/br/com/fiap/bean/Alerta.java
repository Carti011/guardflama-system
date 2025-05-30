package br.com.fiap.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*** Representa um alerta de risco gerado pelo sistema GuardFlama.
 * Contém dados sobre o nível de risco, data/hora, região e valores detectados.
 * @author Weslley*/
public class Alerta {

    private int id;
    private String regiao;
    private String nivelRisco;
    private double temperaturaDetectada;
    private double fumacaDetectada;
    private LocalDateTime dataHora;

    public Alerta(int id, String regiao, String nivelRisco, double temperaturaDetectada, double fumacaDetectada) {
        this.id = id;
        this.regiao = regiao;
        this.nivelRisco = nivelRisco;
        this.temperaturaDetectada = temperaturaDetectada;
        this.fumacaDetectada = fumacaDetectada;
        this.dataHora = LocalDateTime.now();
    }

    public int getId() { return id; }

    public String getRegiao() { return regiao; }

    public String getNivelRisco() { return nivelRisco; }

    public double getTemperaturaDetectada() { return temperaturaDetectada; }

    public double getFumacaDetectada() { return fumacaDetectada; }

    public LocalDateTime getDataHora() { return dataHora; }

    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHora.format(formatter);
    }
}
