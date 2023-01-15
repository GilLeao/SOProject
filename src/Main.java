
import org.json.JSONObject;
import org.json.JSONArray;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
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

    static boolean primeiraVez = true;

    private static baseFrame mainFrame;
    static JSONObject json = new JSONObject();
    static JSONObject estacao0 = new JSONObject();
    static JSONObject estacao1 = new JSONObject();
    static JSONObject estacao2 = new JSONObject();
    static JSONObject estacao3 = new JSONObject();
    static JSONObject estacao4 = new JSONObject();
    static JSONObject estacao5 = new JSONObject();
    static JSONObject estacao6 = new JSONObject();
    static JSONObject estacao7 = new JSONObject();
    static JSONObject estacao8 = new JSONObject();
    static JSONObject estacao9 = new JSONObject();
    static JSONObject comboio0 = new JSONObject();
    static JSONObject comboio1 = new JSONObject();
    static JSONObject comboio2 = new JSONObject();
    static JSONObject comboio3 = new JSONObject();
    static JSONObject comboio4 = new JSONObject();
    static JSONArray comboiosJSON = new JSONArray();
    static JSONArray estacoesJSON = new JSONArray();

    public static void main(String[] args){
       login login = new login();



    }

    private static void btnGestorConflitosClicked()
    {
        gestaoConflitos gestaoConflitos1 = new gestaoConflitos();
        gestaoConflitos1.lerTxt();

        System.out.println("INTRODUZA O CONFLITO QUE QUER RESOLVER: ");

        Scanner scanner =  new Scanner(System.in);
        int option = scanner.nextInt();

        gestaoConflitos1.resolverConflito(option, Comboios, Estacoes);
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
            btnGestorConflitosClicked();
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
                    painelControlo painel = new painelControlo(mainFrame, Comboios, Estacoes, panel);
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

        if(primeiraVez == true)
        {
            //Inicia as Estações
            for(int i = 0;i < 10;i++){
                Estacoes[i] = new Estacao(Paragens[i],10,1);
            }
            //Atribui passageiros de forma aleatória a todas as Estações
            for(int i = 0;i < 10;i++){
                Estacoes[i].escolherPassageirosRandom(Estacoes);
                Estacoes[i].setNmrMaxComboios(3);
            }

            //definir os valores de estacao JSON
            estacao0.put("indiceEstacao", 0);
            estacao0.put("nmrMaxComboios", 3);

            estacao1.put("indiceEstacao", 1);
            estacao1.put("nmrMaxComboios", 3);

            estacao2.put("indiceEstacao", 2);
            estacao2.put("nmrMaxComboios", 3);

            estacao3.put("indiceEstacao", 3);
            estacao3.put("nmrMaxComboios", 3);

            estacao4.put("indiceEstacao", 4);
            estacao4.put("nmrMaxComboios", 3);

            estacao5.put("indiceEstacao", 5);
            estacao5.put("nmrMaxComboios", 3);

            estacao6.put("indiceEstacao", 6);
            estacao6.put("nmrMaxComboios", 3);

            estacao7.put("indiceEstacao", 7);
            estacao7.put("nmrMaxComboios", 3);

            estacao8.put("indiceEstacao", 8);
            estacao8.put("nmrMaxComboios", 3);

            estacao9.put("indiceEstacao", 9);
            estacao9.put("nmrMaxComboios", 3);

            for(int i = 0;i < 5;i++){
                comboios[i] = new Comboio();
                comboios[i].setNmrMaxPassageiros(10);
                comboios[i].gerarAleatorio(Estacoes);
                comboios[i].setNmrComboio(i);
                comboios[i].setIndiceTroco(-i);
            }

            //definir os valores de comboio JSON
            comboio0.put("indiceComboio", 0);
            comboio0.put("nmrMaxPassageiros", 10);

            comboio1.put("indiceComboio", 1);
            comboio1.put("nmrMaxPassageiros", 10);

            comboio2.put("indiceComboio", 2);
            comboio2.put("nmrMaxPassageiros", 10);

            comboio3.put("indiceComboio", 3);
            comboio3.put("nmrMaxPassageiros", 10);

            comboio4.put("indiceComboio", 4);
            comboio4.put("nmrMaxPassageiros", 10);

            for(int i = 0; i < Trocos.length - 1;i++){
                Trocos[i] = new Troco(Estacoes[i].getNome(), Estacoes[i + 1].getNome(), 200, 500);
            }
            Trocos[Trocos.length - 1] = new Troco(Estacoes[Estacoes.length - 1].getNome(), Estacoes[0].getNome(), 300, 600);

            // Criar um array JSON para armazenar os objetos de comboio
            comboiosJSON.put(comboio0);
            comboiosJSON.put(comboio1);
            comboiosJSON.put(comboio2);
            comboiosJSON.put(comboio3);
            comboiosJSON.put(comboio4);

            json.put("comboios", comboiosJSON);

            // Criar um array JSON para armazenar os objetos de estacao
            estacoesJSON.put(estacao0);
            estacoesJSON.put(estacao1);
            estacoesJSON.put(estacao2);
            estacoesJSON.put(estacao3);
            estacoesJSON.put(estacao4);
            estacoesJSON.put(estacao5);
            estacoesJSON.put(estacao6);
            estacoesJSON.put(estacao7);
            estacoesJSON.put(estacao8);
            estacoesJSON.put(estacao9);

            json.put("estacoes", estacoesJSON);

            //colocar o json num ficheiro JSON
            try {
                FileWriter fileWriter = new FileWriter("src/info/data.json");
                fileWriter.write(json.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

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

            primeiraVez = false;

        }
        else if(primeiraVez == false)
        {
            //Inicia as Estações
            for(int i = 0;i < 10;i++){
                Estacoes[i] = new Estacao(Paragens[i],10,1);
            }

            //aceder ao JSON
            try
            {
                FileReader fr = new FileReader("src/info/data.json");
                JSONObject json2 = new JSONObject(fr);
                JSONArray comboios2 = json2.getJSONArray("comboios");

                for(int i=0; i < 5; i++)
                {
                    JSONObject comboio2 = comboios2.getJSONObject(i);
                    int indiceComboio = comboio2.getInt("indiceComboio");
                    int nmrMaxPassageiros = comboio2.getInt("nmrMaxPassageiros");

                    comboios[i] = new Comboio();
                    comboios[i].setNmrMaxPassageiros(nmrMaxPassageiros);
                    comboios[i].gerarAleatorio(Estacoes);
                    comboios[i].setNmrComboio(i);
                    comboios[i].setIndiceTroco(-i);
                }

                JSONArray estacoes2 = json2.getJSONArray("estacoes");

                for(int i=0; i < 10; i++)
                {
                    JSONObject estacao2 = estacoes2.getJSONObject(i);
                    int indiceEstacao = comboio2.getInt("indiceEstacao");
                    int nmrMaxComboios = comboio2.getInt("nmrMaxComboios");

                    Estacoes[i].escolherPassageirosRandom(Estacoes);
                    Estacoes[i].setNmrMaxComboios(nmrMaxComboios); //DEFINIR AQUI
                }

            } catch (IOException ex)
            {
                System.out.println("ERRO: "+ex.getMessage());
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
    }

    public static void naoClicar() throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://encurtador.com.br/iHQW8"));
    }

}