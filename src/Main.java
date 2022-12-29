import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static Estacao[]  Estacoes = new Estacao[10];

    private static Comboio[] Comboios = new Comboio[5];

    private static baseFrame mainFrame;

    public static void main(String[] args) {
        mainFrame = new baseFrame();
        Main.mainFrames();


    }

    /**
     * INTERFACE GRÁFICA PRINCIPAL, A PARTIR DAQUI O UTILIZADOR CONSEGUE ACEDER A TODOS OS MENUS
     */
    public static void mainFrames(){
        mainFrame.setLayout(null);
        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(70,90,250,300);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);


        ImageIcon source = new ImageIcon("src\\img\\logoGUI.png");
        ImageIcon resultat = new ImageIcon(source.getImage().getScaledInstance(150, 70, Image.SCALE_DEFAULT));



        JLabel picLabel = new JLabel(resultat);
        picLabel.setBounds(50,-50,280,200);
        mainFrame.add(picLabel);

        mainFrame.add(panel);




        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        mainFrame.setIconImage(logo.getImage());//Define o logo como o icon da janela
        mainFrame.getContentPane().setBackground(new Color(166, 237, 128));//Define a Cor de fundo





        JButton simuladorTrafego = new JButton();
        simuladorTrafego.setBounds(25,90, 200, 25);
        simuladorTrafego.setVisible(false);
        simuladorTrafego.setText("SIMULADOR DE TRAFEGO");
        simuladorTrafego.setFocusable(false);

        JButton gestaoConflitos = new JButton();
        gestaoConflitos.setBounds(25,130, 200, 25);
        gestaoConflitos.setVisible(false);
        gestaoConflitos.setText("GESTÃO DE CONFLITOS");
        gestaoConflitos.setFocusable(false);

        JButton painelControlo = new JButton();
        painelControlo.setBounds(25,170, 200, 25);
        painelControlo.setVisible(false);
        painelControlo.setText("PAINEL DE CONTROLO");
        painelControlo.setFocusable(false);
        painelControlo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == painelControlo){
                    painelControlo painel = new painelControlo(mainFrame, Comboios, Estacoes);
                    panel.setVisible(false);
                }
            }
        });

        JButton naoClicar = new JButton();
        naoClicar.setBounds(25,210, 200, 25);
        naoClicar.setVisible(false);
        naoClicar.addActionListener(e -> {
            try {
                Main.naoClicar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        naoClicar.setText("NÃO CLICAR!");
        naoClicar.setFocusable(false);


        JButton gerarDados = new JButton();
        gerarDados.setBounds(25,50, 200, 25);
        gerarDados.addActionListener(e -> Main.gerarDados(Comboios,Estacoes, simuladorTrafego, gestaoConflitos, painelControlo, naoClicar));
        gerarDados.setText("GERAR DADOS");
        gerarDados.setFocusable(false);
        panel.add(gerarDados);
        panel.add(simuladorTrafego);
        panel.add(gestaoConflitos);
        panel.add(painelControlo);
        panel.add(naoClicar);
        panel.repaint();
    }


    /**
     * Esta Função irá gerar todos os dados necessários para a simulação de um sistema de tráfego de comboios,
     * vai ainda fazer com que os botões dos restantes menus fiquem visiveis após o botão a que a ele é definido seja primido
     *
     * @param comboios Array com todos os comboios
     * @param Estacoes Array com todas as estações
     * @param button botão do Simulador de Tráfego
     * @param button2 botão da Gestão de Conflitos
     * @param button3 botão do Painel de Controlo
     * @param button4 ????????????????????????????
     */
    public static void gerarDados(Comboio[] comboios, Estacao[] Estacoes, JButton button, JButton button2, JButton button3, JButton button4){

        String[] Paragens = {"São Bento", "Heroísmo" ,"Campanhã", "Estádio do Dragão", "Fanzeres", "Santo Ovídio","Camara de Gaia" ,"João de Deus", "General Torres", "Trindade"};
        //Inicia as Estações
        for(int i = 0;i < 10;i++){
            Estacoes[i] = new Estacao(Paragens[i],10,1);


        }
        //Atribui passageiros de forma aleatória a todas as Estações
        for(int i = 0;i < 10;i++){
            Estacoes[i].escolherPassageirosRandom(Estacoes);
        }


        for(int i = 0;i < 5;i++){
            comboios[i] = new Comboio();
            comboios[i].setNmrMaxPassageiros(ThreadLocalRandom.current().nextInt(1, 20));
            comboios[i].gerarAleatorio(Estacoes);
        }

        //Imprime na consola os dados gerados
        for(int i = 0;i < 10;i++){
            System.out.println(Estacoes[i].toString());
        }
        System.out.println("______________________________________________________________________");
        for(int i = 0;i < 5;i++){
            System.out.println(Comboios[i].toString());
        }



        button.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        button4.setVisible(true);

    }

    public static void naoClicar() throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://encurtador.com.br/iHQW8"));
    }

}