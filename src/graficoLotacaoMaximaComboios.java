import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class graficoLotacaoMaximaComboios extends ApplicationFrame {
    public graficoLotacaoMaximaComboios( String titulo) {
        super( titulo );
        setContentPane(criarJanela( ));
    }

    private static PieDataset obterDados( ) {
        DefaultPieDataset dados = new DefaultPieDataset( );
        for(int i = 0; i < Main.Comboios.length;i++){
            dados.setValue("Comboio " + (i + 1), Main.Comboios[i].getNmrMaxPassageiros());
        }

        return dados;
    }

    private static JFreeChart criarGrafico( PieDataset dataset ) {
        JFreeChart grafico = ChartFactory.createPieChart(
                "Lotação Maxima de Passageiros",
                dataset,
                true,
                true,
                false);

        return grafico;
    }

    public static JPanel criarJanela( ) {
        JFreeChart grafico = criarGrafico(obterDados( ) );
        ChartPanel janela = new ChartPanel(grafico);
        return janela;
    }
}
