import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class graficoEstacoesFavoritas  extends ApplicationFrame{

    public graficoEstacoesFavoritas( String applicationTitle , String chartTitle ) {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
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
            Paragens[i] = new String();
            Paragens[i] = Main.Estacoes[i].getNome();
        }

        for(int i = 0; i < Paragens.length; i++){
            Passageiro[] PassageirosNaEstacao = Main.Estacoes[i].getPassageiros();
            numeroPassageiros[i] = 0;
            if(PassageirosNaEstacao!= null) {
                for (int k = 0; k < PassageirosNaEstacao.length; k++) {
                    if (PassageirosNaEstacao[k].getEstacaoDestino().equals("São Bento")) {
                        numeroPassageiros[0]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("Heroísmo")) {
                        numeroPassageiros[1]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("Campanhã")) {
                        numeroPassageiros[2]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("Estádio do Dragão")) {
                        numeroPassageiros[3]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("Fanzeres")) {
                        numeroPassageiros[4]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("Santo Ovídio")) {
                        numeroPassageiros[5]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("Camara de Gaia")) {
                        numeroPassageiros[6]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("João de Deus")) {
                        numeroPassageiros[7]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("General Torres")) {
                        numeroPassageiros[8]++;
                    } else if (PassageirosNaEstacao[k].getEstacaoDestino().equals("Trindade")) {
                        numeroPassageiros[9]++;
                    }
                }
            }
        }


        for(int i = 0; i < Main.Comboios.length; i++){
            Passageiro[] PassageirosNoComboio = Main.Comboios[i].getPassageiros();
            if(PassageirosNoComboio != null) {
                for (int k = 0; k < PassageirosNoComboio.length; k++) {
                    if (PassageirosNoComboio[k].getEstacaoDestino().equals("São Bento")) {
                        numeroPassageiros[0]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("Heroísmo")) {
                        numeroPassageiros[1]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("Campanhã")) {
                        numeroPassageiros[2]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("Estádio do Dragão")) {
                        numeroPassageiros[3]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("Fanzeres")) {
                        numeroPassageiros[4]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("Santo Ovídio")) {
                        numeroPassageiros[5]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("Camara de Gaia")) {
                        numeroPassageiros[6]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("João de Deus")) {
                        numeroPassageiros[7]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("General Torres")) {
                        numeroPassageiros[8]++;
                    } else if (PassageirosNoComboio[k].getEstacaoDestino().equals("Trindade")) {
                        numeroPassageiros[9]++;
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
