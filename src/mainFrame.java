import javax.swing.*;
import java.awt.*;

public class mainFrame extends JFrame {

    public mainFrame(Estacao[] Estacoes) throws HeadlessException {

        this.setTitle("Sistema de Gestão de Tráfego Ferroviário");//Define o Titulo da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Define que o programa acaba quando o user decide fechar a janela
        this.setResizable(false);//Não permite ao user mudar o tamanho da janela
        this.setSize(300,400);//Comprimento e Largura da Janela
        this.setVisible(true);//A janela é visivel para o utilizador
        this.setLayout(null);


        JPanel panelGerarDados = new JPanel();
        panelGerarDados.setBackground(new Color(64,64,64));
        panelGerarDados.setBounds(50,90,180,250);
        panelGerarDados.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelGerarDados.setAlignmentY(Component.CENTER_ALIGNMENT);


        ImageIcon source = new ImageIcon("src\\img\\logoGUI.png");
        ImageIcon resultat = new ImageIcon(source.getImage().getScaledInstance(100, 41, Image.SCALE_DEFAULT));



        JLabel picLabel = new JLabel(resultat);
        picLabel.setBounds(50,0,180,100);
        this.add(picLabel);

        this.add(panelGerarDados);




        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        this.setIconImage(logo.getImage());//Define o logo como o icon da janela
        this.getContentPane().setBackground(new Color(166, 237, 128));//Define a Cor de fundo



        JButton gerarDados = new JButton();
        gerarDados.setBounds(70,100, 140, 25);
        gerarDados.addActionListener(e -> this.gerarDadosRandom(Estacoes));
        this.add(gerarDados);
    }

    public void gerarDadosRandom(Estacao[] Estacoes){
        String[] Paragens = {"São Bento", "Heroísmo" ,"Campanhã", "Estádio do Dragão", "Fanzeres", "Santo Ovídio","Camara de Gaia" ,"João de Deus", "General Torres", "Trindade"};

        for(int i = 0;i < 10;i++){
            Estacoes[i] = new Estacao(Paragens[i],10,1);


        }

        for(int i = 0;i < 10;i++){
            Estacoes[i].escolherPassageirosRandom(Estacoes);
        }

        for(int i = 0;i < 10;i++){
            System.out.println(Estacoes[i].toString());
        }

    }
}
