
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;



public class Main {
    /**
     * Controla o embraque de cada passageiro
     */
    protected static Semaphore SemaphoreEmbarquePassaegiros = new Semaphore(1);
    /**
     * Controla a saida dos comboios
     */
    protected static Semaphore SemaphoreAndamentoComboios = new Semaphore(5, true);
    /**
     * Semaforo espera pela entrada de todos os passageiros e so depois manda o comboio voltar a andar
     */
    protected static Semaphore[] SemaphorePermitirComboioAndar = new Semaphore[5];
    /**
     * Semaforo que espera ate o comboio chegar a sua paragem e so depois permite o embarque dos passageiros.
     */
    protected static Semaphore[] SemaphorePermitirEmbarque = new Semaphore[5];



    protected static Estacao[]  Estacoes = new Estacao[10];

    protected static Troco[] Trocos = new Troco[10];

    protected static Comboio[] Comboios = new Comboio[5];

    private static embarquePassageiros[] embarque = new embarquePassageiros[5];

    private static baseFrame mainFrame;



    public static void main(String[] args){
       login login = new login();



    }

    /**
     * INTERFACE GRÁFICA PRINCIPAL, A PARTIR DAQUI O UTILIZADOR CONSEGUE ACEDER A TODOS OS MENUS
     */
    public static void mainFrames(){
        mainFrame = new baseFrame();
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
        simuladorTrafego.addActionListener(e ->{
                simuladorTrafego simuladorTrafego1 = new simuladorTrafego(embarque, Comboios, Estacoes);



            //mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
        } );
        simuladorTrafego.setFocusable(false);

        JButton gestaoConflitos = new JButton();
        gestaoConflitos.setBounds(25,130, 200, 25);
        gestaoConflitos.setVisible(false);
        gestaoConflitos.setText("GESTÃO DE CONFLITOS");
        gestaoConflitos.setFocusable(false);
        gestaoConflitos.addActionListener(e -> {
            gestaoConflitos gestaoConflitos1 = new gestaoConflitos();
            gestaoConflitos1.lerTxt();

            System.out.println("INTRODUZA O CONFLITO QUE QUER RESOLVER: ");
            Scanner scanner = new Scanner(System.in);

            if(scanner.hasNextInt()) //verificar se há um valor inteiro para ler
            {
                int option = scanner.nextInt();
                gestaoConflitos1.resolverConflito(option, Comboios, Estacoes);
            }
            scanner.close();
        });


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

        /*JButton naoClicar = new JButton();
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
        naoClicar.setFocusable(false);*/


        JButton gerarDados = new JButton();
        gerarDados.setBounds(25,50, 200, 25);
        gerarDados.addActionListener(e -> Main.gerarDados(Comboios,Estacoes, simuladorTrafego, gestaoConflitos, painelControlo));
        gerarDados.setText("GERAR DADOS");
        gerarDados.setFocusable(false);
        panel.add(gerarDados);
        panel.add(simuladorTrafego);
        panel.add(gestaoConflitos);
        panel.add(painelControlo);
        //panel.add(naoClicar);
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
     */
    public static void gerarDados(Comboio[] comboios, Estacao[] Estacoes, JButton button, JButton button2, JButton button3){

        String[] Paragens = {"São Bento", "Heroísmo" ,"Campanhã", "Estádio do Dragão", "Fanzeres", "Santo Ovídio","Camara de Gaia" ,"João de Deus", "General Torres", "Trindade"};
        //Inicia as Estações
        for(int i = 0;i < 10;i++){
            Estacoes[i] = new Estacao(Paragens[i],10,1);


        }
        //Atribui passageiros de forma aleatória a todas as Estações
        for(int i = 0;i < 10;i++){
            Estacoes[i].escolherPassageirosRandom(Estacoes);
            Estacoes[i].setNmrMaxComboios(ThreadLocalRandom.current().nextInt(1, 5));
        }


        for(int i = 0;i < 5;i++){
            comboios[i] = new Comboio();
            comboios[i].setNmrMaxPassageiros(ThreadLocalRandom.current().nextInt(1, 20));
            comboios[i].gerarAleatorio(Estacoes);
            comboios[i].setNmrComboio(i);
            comboios[i].setIndiceTroco(-i);
        }

        for(int i = 0; i < Trocos.length - 1;i++){
            Trocos[i] = new Troco(Estacoes[i].getNome(), Estacoes[i + 1].getNome(), 200, 500);
        }
        Trocos[Trocos.length - 1] = new Troco(Estacoes[Estacoes.length - 1].getNome(), Estacoes[0].getNome(), 300, 600);


        //Imprime na consola os dados gerados
        for(int i = 0;i < 10;i++){
            System.out.println(Estacoes[i].toString());
        }
        System.out.println("______________________________________________________________________");
        for(int i = 0;i < 5;i++){
            System.out.println(Comboios[i].toString());
        }
        for(int i = 0;i < 10;i++){
            System.out.println("------------------------------------------------------------------");
            System.out.println(Trocos[i].toString());
        }




        button.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        //button4.setVisible(true);

    }

    public static void naoClicar() throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://encurtador.com.br/iHQW8"));
    }

}