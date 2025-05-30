package br.com.fiap.main;

import br.com.fiap.bean.*;

import javax.swing.JOptionPane;

/**
 * Classe principal para executar o sistema GuardFlama.
 * Realiza teste simulado da Amazônia e permite ao usuário cadastrar outras regiões manualmente.
 * @author Weslley
 */
public class MainControle {

    public static void main(String[] args) {
        try {
            Controle controle = new Controle();

            // Teste fixo da Amazônia (Teste Mocado)
            Regiao regiaoTeste = new Regiao("Amazônia");
            regiaoTeste.adicionarSensor(new SensorTemperatura("T1", "Amazônia", 44.55));
            regiaoTeste.adicionarSensor(new SensorFumaca("F1", "Amazônia", 44.66));
            controle.adicionarRegiao(regiaoTeste);
            controle.verificarRiscos();

            JOptionPane.showMessageDialog(null,
                    controle.gerarRelatorioCompleto() + "\n" + controle.listarAlertas(),
                    "Relatório Inicial", JOptionPane.INFORMATION_MESSAGE);

            // Contadores automáticos para nomeação dos sensores
            int contadorTemp = 2;
            int contadorFumaca = 2;

            int opcao;
            do {
                String nomeRegiaoUsuario = JOptionPane.showInputDialog("Informe o nome da nova região:");
                controle.cadastrarRegiao(nomeRegiaoUsuario);

                // Solicita valores manualmente ao usuário
                double temperatura = Double.parseDouble(JOptionPane.showInputDialog("Informe a temperatura da região " + nomeRegiaoUsuario + ":"));
                double fumaca = Double.parseDouble(JOptionPane.showInputDialog("Informe o nível de fumaça da região " + nomeRegiaoUsuario + ":"));

                // Cria sensores com nomes automáticos e valores informados
                String idTemp = "T" + contadorTemp++;
                String idFumaca = "F" + contadorFumaca++;

                controle.adicionarSensor(nomeRegiaoUsuario, new SensorTemperatura(idTemp, nomeRegiaoUsuario, temperatura));
                controle.adicionarSensor(nomeRegiaoUsuario, new SensorFumaca(idFumaca, nomeRegiaoUsuario, fumaca));

                controle.verificarRiscos();

                JOptionPane.showMessageDialog(null,
                        controle.gerarRelatorioCompleto() + "\n" + controle.listarAlertas(),
                        "Relatório Atualizado", JOptionPane.INFORMATION_MESSAGE);

                opcao = JOptionPane.showConfirmDialog(null,
                        "Deseja cadastrar outra região?", "Continuar", JOptionPane.YES_NO_OPTION);

            } while (opcao == JOptionPane.YES_OPTION);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao executar o sistema: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
