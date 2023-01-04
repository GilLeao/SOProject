import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class graficoEstacoesFavoritas  extends ApplicationFrame{

    public graficoEstacoesFavoritas( String nomeJanela , String tituloGrafico ) {
        super( nomeJanela);
        JFreeChart barChart = ChartFactory.createBarChart(
                tituloGrafico,
                "Estação",
                "Numero de Passageiros",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private CategoryDataset createDataset( ) {
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );

        int[] numeroPassageiros = new int[10];
        String[] Paragens = new String[Main.Estacoes.length];
        for(int i = 0; i < Paragens.length;i++){
            Paragens[i] = Main.Estacoes[i].getNome();
        }

        for(int i = 0; i < Paragens.length; i++){
            Passageiro[] PassageirosNaEstacao = Main.Estacoes[i].getPassageiros();
            numeroPassageiros[i] = 0;
            if(PassageirosNaEstacao!= null) {
                for (Passageiro passageiro : PassageirosNaEstacao) {
                    switch (passageiro.getEstacaoDestino()) {
                        case "São Bento" -> numeroPassageiros[0]++;
                        case "Heroísmo" -> numeroPassageiros[1]++;
                        case "Campanhã" -> numeroPassageiros[2]++;
                        case "Estádio do Dragão" -> numeroPassageiros[3]++;
                        case "Fanzeres" -> numeroPassageiros[4]++;
                        case "Santo Ovídio" -> numeroPassageiros[5]++;
                        case "Camara de Gaia" -> numeroPassageiros[6]++;
                        case "João de Deus" -> numeroPassageiros[7]++;
                        case "General Torres" -> numeroPassageiros[8]++;
                        case "Trindade" -> numeroPassageiros[9]++;
                    }
                }
            }
        }


        for(int i = 0; i < Main.Comboios.length; i++){
            Passageiro[] PassageirosNoComboio = Main.Comboios[i].getPassageiros();
            if(PassageirosNoComboio != null) {
                for (Passageiro passageiro : PassageirosNoComboio) {
                    switch (passageiro.getEstacaoDestino()) {
                        case "São Bento" -> numeroPassageiros[0]++;
                        case "Heroísmo" -> numeroPassageiros[1]++;
                        case "Campanhã" -> numeroPassageiros[2]++;
                        case "Estádio do Dragão" -> numeroPassageiros[3]++;
                        case "Fanzeres" -> numeroPassageiros[4]++;
                        case "Santo Ovídio" -> numeroPassageiros[5]++;
                        case "Camara de Gaia" -> numeroPassageiros[6]++;
                        case "João de Deus" -> numeroPassageiros[7]++;
                        case "General Torres" -> numeroPassageiros[8]++;
                        case "Trindade" -> numeroPassageiros[9]++;
                    }

                }
            }
        }

        final String passageiros = "Passageiros";



        for(int i = 0; i < Paragens.length;i++){
            dataset.addValue( numeroPassageiros[i] , Paragens[i] ,passageiros );
        }


        return dataset;
    }





}
