import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class painelControlo {
    private int indiceComboioEditar;

    /**
     * Menu principal do Módulo Painel de Controlo (e tambem metodo construtor).
     *
     * @param frame JFrame do software
     * @param comboios array com todos os comboios existentes
     * @param estacoes array com todas as estacoes existentes
     */
    public painelControlo(baseFrame frame, Comboio[] comboios, Estacao[] estacoes) {
        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(70,90,250,300);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);


        frame.add(panel);

        JButton alterarHorarios = new JButton();
        alterarHorarios.setBounds(25,100, 200, 25);
        alterarHorarios.addActionListener(e -> {panel.setVisible(false);this.selecionarComboioEditar(frame, comboios, estacoes);});
        alterarHorarios.setText("ALTERAR HORÁRIOS");
        alterarHorarios.setFocusable(false);
        panel.add(alterarHorarios);


        JButton planearViagem = new JButton();
        planearViagem.setBounds(25,140, 200, 25);
        planearViagem.addActionListener(e -> {panel.setVisible(false);this.planearViagem(frame, estacoes, comboios);});
        planearViagem.setText("INFOMAÇÃO VIAGENS");
        planearViagem.setFocusable(false);
        panel.add(planearViagem);
        panel.repaint();

    }

    /**
     * Esta funcao vai apresentar ao usuario a opcao de escolher um destino para onde quer ir
     *
     * @param frame JFrame do software
     * @param estacaos Array com todas as estacoes existentes
     * @param comboios Array com todos os comboios existentes
     */
    public void planearViagem(baseFrame frame, Estacao[] estacaos, Comboio[] comboios){

        frame.setSize(800, 700);//Comprimento e Largura da Janela

        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(20,90,750,550);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        frame.add(panel);

        String[] Paragens = new String[estacaos.length];
        for(int i = 0; i < Paragens.length; i++){
            Paragens[i] = estacaos[i].getNome();
        }

        JLabel textoEstPartida = new JLabel("ESTAÇÃO DE PARTIDA:");
        textoEstPartida.setForeground(Color.WHITE);
        textoEstPartida.setFont(new Font("Arial", Font.BOLD, 15));
        textoEstPartida.setBounds(20,10,300,30);

        JComboBox paragemPartida =new JComboBox(Paragens);
        paragemPartida.setBounds(200, 10, 140, 30);

        String Paragemanterior = Paragens[0];
        Paragens[0] = Paragens[1];
        Paragens[1] = Paragemanterior;

        JComboBox paragemDestino = new JComboBox(Paragens);
        paragemDestino.setBounds(200, 60, 140, 30);

        JLabel textoEstDestino = new JLabel("ESTAÇÃO DE DESTINO:");
        textoEstDestino.setForeground(Color.WHITE);
        textoEstDestino.setFont(new Font("Arial", Font.BOLD, 15));
        textoEstDestino.setBounds(20,60,300,30);


        /**
         * ESTA FUNÇÃO VAI OBRIGAR A QUE AS DUAS COMBOBOX NÃO POSSAM TER VALORES IGUAIS
         */
        paragemPartida.addActionListener(e -> {
            if (paragemPartida.getSelectedItem().equals(paragemDestino.getSelectedItem()) &&  paragemPartida != paragemPartida) {
                paragemPartida.setSelectedIndex((paragemPartida.getSelectedIndex() + 1) % paragemPartida.getItemCount());
            } else if (paragemPartida.getSelectedItem().equals(paragemDestino.getSelectedItem()) && paragemPartida != paragemDestino) {
                paragemDestino.setSelectedIndex((paragemDestino.getSelectedIndex() + 1) % paragemDestino.getItemCount());
            }
        });
        paragemDestino.addActionListener(e -> {
            if (paragemDestino.getSelectedItem().equals(paragemPartida.getSelectedItem()) &&  paragemPartida != paragemPartida) {
                paragemDestino.setSelectedIndex((paragemDestino.getSelectedIndex() + 1) % paragemDestino.getItemCount());
            } else if (paragemDestino.getSelectedItem().equals(paragemPartida.getSelectedItem()) && paragemPartida != paragemDestino) {
                paragemPartida.setSelectedIndex((paragemPartida.getSelectedIndex() + 1) % paragemPartida.getItemCount());
            }
        });

        Paragemanterior = Paragens[1];
        Paragens[1] = Paragens[0];
        Paragens[0] = Paragemanterior;


        JButton confirmar = new JButton("PROCURAR");
        confirmar.addActionListener(e -> {
            String estacaoDestino = (String) paragemDestino.getSelectedItem();
            String estacaoPartida = (String) paragemPartida.getSelectedItem();
            Comboio[] comboiosDisponivel = this.comboiosDisponiveis(estacaoPartida, estacaoDestino, comboios);
            paragemPartida.setVisible(false);
            paragemDestino.setVisible(false);
            textoEstDestino.setVisible(false);
            textoEstPartida.setVisible(false);
            confirmar.setVisible(false);
            this.selecionarComboioParaViagem(comboiosDisponivel, panel, frame, comboios, estacaos, estacaoPartida, estacaoDestino);

        });
        confirmar.setBounds(370, 35, 120, 30);



        panel.add(confirmar);
        panel.add(paragemPartida);
        panel.add(paragemDestino);
        panel.add(textoEstPartida);
        panel.add(textoEstDestino);
        panel.repaint();


    }

    /**
     *
     * @param comboiosParam array de comboios que passam pela estacao de partida e estacao de destino referidas pelo utilizador
     * @param panel painel no qual as informacoes aqui dadas vao aparecer
     * @param frame JFrame do software
     * @param comboios array com todos os comboios do sistema
     * @param estacaos array com todas as estacoes do sistema
     * @param estacaoPartida estacao de partida do usuario
     * @param estacaoDestino estacao de destino do usuario
     */
    public void selecionarComboioParaViagem(Comboio[] comboiosParam, JPanel panel, JFrame frame, Comboio[] comboios,Estacao[] estacaos,String estacaoPartida,String estacaoDestino){

        String[] nomeComboios = new String[comboiosParam.length];

        for(int i = 0; i < nomeComboios.length; i++){
            nomeComboios[i] = new String();
            nomeComboios[i] = "Comboio " + (comboiosParam[i].getNmrComboio() + 1);
        }

        JComboBox escolherComboio = new JComboBox(nomeComboios);
        escolherComboio.setBounds(30, 60, 140, 30);

        JButton confirmar = new JButton("CONFIRMAR");
        confirmar.setBounds(200, 60, 120, 30);
        confirmar.addActionListener(e -> {
            escolherComboio.setVisible(false);
            confirmar.setVisible(false);
            String ComboioEscolhido = (String)escolherComboio.getSelectedItem();
            String intValue = ComboioEscolhido.replaceAll("[^0-9]", "");
            int indiceComboioEscol= Integer.parseInt(intValue);
            indiceComboioEscol--;
            this.apresentarInformacao(comboiosParam, panel, frame, comboios, estacaos, estacaoPartida, estacaoDestino, indiceComboioEscol);
        });




        panel.add(confirmar);
        panel.add(escolherComboio);


        panel.repaint();
    }

    /**
     * Aqui sera apresentada a informacao relativa a viagem e comboio selecionados pelo usuario
     *
     * @param comboiosParam array de comboios que passam pela estacao de partida e depois pela estacao de destino definidas pelo utilizador
     * @param panel painel onde a informacao aparecera para o user
     * @param frame JFrame do software
     * @param comboios array de comboios existentes no sistema
     * @param estacaos array de estacoes existentes no sistema
     * @param estacaoPartida estacao de partida do user
     * @param estacaoDestino estacao de destino do user
     * @param indiceComboioEscolhido indice na array "comboiosParam" do comboio que o user escolheu
     */
    public void apresentarInformacao(Comboio[] comboiosParam, JPanel panel, JFrame frame, Comboio[] comboios, Estacao[] estacaos,String estacaoPartida,String estacaoDestino, int indiceComboioEscolhido){
        for(int i = 0;i < comboiosParam.length;i++){
            if(comboiosParam[i].getNmrComboio() == indiceComboioEscolhido){
                indiceComboioEscolhido = i;
            }
        }
        LocalTime[] HorariosChegada = comboiosParam[indiceComboioEscolhido].getHorariosChegada();
        LocalTime[] HorariosPartida = comboiosParam[indiceComboioEscolhido].getHorariosSaida();

        int indicePartida = -1;
        int indiceChegada = -1;

        String[] Paragens = comboiosParam[indiceComboioEscolhido].getParagens();

        for(int i = 0;i < Paragens.length;i++){
            if(Paragens[i].equals(estacaoPartida)){
                if(indicePartida == -1){
                    indicePartida= i;
                    for(int k = i;k < Paragens.length;k++){
                        if(Paragens[k].equals(estacaoDestino)){
                            if(indiceChegada == -1){
                                indiceChegada = k;
                            }
                        }
                    }
                }
            }
        }

        int HorasPartida = HorariosPartida[indicePartida].getHour();
        int MinutosPartida = HorariosPartida[indicePartida].getMinute();

        MinutosPartida = MinutosPartida + (HorasPartida * 60);

        int HorasChegada = HorariosChegada[indiceChegada].getHour();
        int MinutosChegada = HorariosChegada[indiceChegada].getMinute();

        MinutosChegada = MinutosChegada + (HorasChegada * 60);

        int duracaoViagem = MinutosChegada - MinutosPartida;


        JLabel textoDuracaoViagem = new JLabel("DURAÇÃO DA VIAGEM: " + duracaoViagem + " MINUTOS");
        textoDuracaoViagem.setForeground(Color.WHITE);
        textoDuracaoViagem.setFont(new Font("Verdana", Font.BOLD, 15));
        textoDuracaoViagem.setBounds(10,20,500,20);

        String[] coluna = {"PARAGEM", "HORARIO CHEGADA", "HORARIO PARTIDA"};

        String[][] informacao = new String[Paragens.length][3];
        for (int i = 0; i < Paragens.length; i++) {
            informacao[i][0] = Paragens[i];
            if(i == 0){
                informacao[i][1] = "-";
            }else {
                informacao[i][1] = HorariosChegada[i].toString();
            }
            if(i == (Paragens.length - 1)){
                informacao[i][2] = "-";
            }else {
                informacao[i][2] = HorariosPartida[i].toString();
            }
        }

        JLabel textoHorarios = new JLabel("HORÁRIOS");
        textoHorarios.setBounds(320,80,520,(Paragens.length * 20));
        textoHorarios.setForeground(Color.WHITE);
        textoHorarios.setFont(new Font("Verdana", Font.BOLD, 15));

        final JTable horarios=new JTable(informacao,coluna);
        horarios.setBounds(100,200,520,(Paragens.length * 20) + 5);
        horarios.setEnabled(false);
        horarios.setColumnSelectionAllowed(false);
        horarios.setDragEnabled(false);
        horarios.setCellSelectionEnabled(false);
        horarios.getTableHeader().setReorderingAllowed(false);
        JScrollPane sp=new JScrollPane(horarios);
        sp.setBounds(110,200,520,(Paragens.length * 20));

        JLabel textoNmrMaxPassageiros = new JLabel("NUMERO MÁXIMO DE PASSAGEIROS: " + comboiosParam[indiceComboioEscolhido].getNmrMaxPassageiros() + " PESSOAS.");
        textoNmrMaxPassageiros.setBounds(10,440,500,20);
        textoNmrMaxPassageiros.setForeground(Color.WHITE);
        textoNmrMaxPassageiros.setFont(new Font("Verdana", Font.BOLD, 15));

        JLabel textoNmrPassageirosDentro = new JLabel("PASSAGEIROS DENTRO DO COMBOIO: " + comboiosParam[indiceComboioEscolhido].getContadorPassageiros() + " PESSOAS.");
        textoNmrPassageirosDentro.setBounds(10,480,500,20);
        textoNmrPassageirosDentro.setForeground(Color.WHITE);
        textoNmrPassageirosDentro.setFont(new Font("Verdana", Font.BOLD, 15));

        panel.add(textoNmrPassageirosDentro);
        panel.add(textoNmrMaxPassageiros);
        panel.add(textoHorarios);
        panel.add(sp);
        panel.add(textoDuracaoViagem);
        panel.repaint();
    }


    /**
     *
     * Verifica quais os comboios que passam pela estacao de Partida e depois pela Estacao de destino do usuario e
     * guarda-os numa array.
     *
     * @param estacaoPartida estacao de partida do user
     * @param estacaoDestino estacao de destino do user
     * @param comboios array de comboios existentes no sistema
     * @return uma array de comboios a passar pela estacaoPartida e <b>so depois</b> pela estacaoDestino.
     */
    public Comboio[] comboiosDisponiveis(String estacaoPartida, String estacaoDestino, Comboio[] comboios){
        String[] Paragens;
        Comboio[] paramNaEstacao = new Comboio[comboios.length];
        int indiceComboios = 0;
        for(int i = 0; i < comboios.length;i++){
            paramNaEstacao[i] = new Comboio();
            Paragens = comboios[i].getParagens();
            for(int k = 0; k < comboios[i].getParagens().length;k++){
                if(Paragens[k].equals(estacaoPartida)){
                    for(int j = k;j < comboios[i].getParagens().length;j++) {
                        if(Paragens[j].equals(estacaoDestino)) {
                            paramNaEstacao[indiceComboios] = comboios[i];
                            indiceComboios++;
                        }
                    }
                }
            }
        }
        Comboio[] paramNaEstacaoFinal = new Comboio[indiceComboios];
        for(int i = 0;i < indiceComboios;i++){
            paramNaEstacaoFinal[i] = new Comboio();
            paramNaEstacaoFinal[i] = paramNaEstacao[i];
        }

        return paramNaEstacaoFinal;

    }

    /**
     * Esta funcao permite ao user alterar os horarios de chegada e partida dos comboios
     *
     * @param frame JFrame da nossa app
     * @param comboio array de comboios existentes no sistema
     * @param indice indice do comboio cujo os horarios estamos a alterar
     */
    public void alterarHorarios(baseFrame frame, Comboio comboio, int indice){
        final int indices = indice;
        frame.setSize(400, 500);//Comprimento e Largura da Janela

        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(15,90,360,350);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);


        frame.add(panel);

        LocalTime[] HorariosChegada = comboio.getHorariosChegada();

        JSpinner spinner;
        JSpinner spinnerSaida;

        LocalTime[] HorariosPartida = comboio.getHorariosSaida();

        int HoraPartida = HorariosPartida[indice].getHour();
        int MinutoPartida = HorariosPartida[indice].getMinute();

        int HoraChegada = HorariosChegada[indice].getHour();
        int MinutoChegada = HorariosChegada[indice].getMinute();


        if(indice > 0) {
            spinnerSaida = null;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, HoraChegada);
            cal.set(Calendar.MINUTE, MinutoChegada);

            Date startTime = cal.getTime();
            cal.set(Calendar.HOUR, 23);
            cal.set(Calendar.MINUTE, 59);
            Date endTime = cal.getTime();

            System.out.println(startTime);
            System.out.println(endTime);


            SpinnerDateModel model = new SpinnerDateModel(startTime, null, endTime, Calendar.MINUTE);
            spinner = new JSpinner(model);
            spinner.setEditor(new JSpinner.DateEditor(spinner, "HH:mm"));

            spinner.setValue(startTime);

            JLabel textoHorarioChegada = new JLabel("HORÁRIO DE CHEGADA");
            textoHorarioChegada.setBounds(100, 50, 220, 25);
            textoHorarioChegada.setForeground(Color.WHITE);
            textoHorarioChegada.setFont(new Font("Verdana", Font.BOLD, 12));
            spinner.setBounds(110, 90, 120, 25);
            panel.add(textoHorarioChegada);
            textoHorarioChegada.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(spinner);
            spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        }else {
            spinner = null;
        }


        if(indice < comboio.getParagens().length - 1){
            Calendar cale = Calendar.getInstance();
            cale.set(Calendar.HOUR_OF_DAY, HoraPartida);
            cale.set(Calendar.MINUTE, MinutoPartida);

            Date startTimes = cale.getTime();
            cale.set(Calendar.HOUR, 23);
            cale.set(Calendar.MINUTE, 59);
            Date endTimes = cale.getTime();

            System.out.println(startTimes);
            System.out.println(endTimes);


            SpinnerDateModel model = new SpinnerDateModel(startTimes, null, endTimes, Calendar.MINUTE);
            spinnerSaida = new JSpinner(model);
            spinnerSaida.setEditor(new JSpinner.DateEditor(spinnerSaida, "HH:mm"));

            spinnerSaida.setValue(startTimes);

            JLabel textoHorarioPartida = new JLabel("HORÁRIO DE PARTIDA");
            textoHorarioPartida.setBounds(100, 130, 220, 25);
            textoHorarioPartida.setLayout(null);
            textoHorarioPartida.setForeground(Color.WHITE);
            textoHorarioPartida.setFont(new Font("Verdana", Font.BOLD, 12));
            spinnerSaida.setBounds(110, 170, 120, 25);
            panel.add(textoHorarioPartida);
            textoHorarioPartida.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(spinnerSaida);
            spinnerSaida.setAlignmentX(Component.CENTER_ALIGNMENT);

        } else {
            spinnerSaida = null;
        }


        JButton salvarAlterações = new JButton();
        salvarAlterações.setBounds(120,260,100,30 );
        salvarAlterações.setText("SALVAR");
        salvarAlterações.setFocusable(false);

        JSpinner finalSpinnerSaida = spinnerSaida;
        salvarAlterações.addActionListener(e -> {
            if(indices > 0 && indices < comboio.getParagens().length - 1){

                Date date = (Date)spinner.getModel().getValue();

                Instant instant = date.toInstant();

                ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

                LocalTime localTimeChegada = zonedDateTime.toLocalTime();


                Date datePartida = (Date) finalSpinnerSaida.getModel().getValue();

                Instant instantPartida = datePartida.toInstant();

                ZonedDateTime zonedDateTimePartida = instantPartida.atZone(ZoneId.systemDefault());

                LocalTime localTimePartida = zonedDateTimePartida.toLocalTime();

                if(localTimePartida.isAfter(localTimeChegada)) {
                    if(localTimePartida != null) {

                        HorariosPartida[indices] = localTimePartida;
                    }
                    HorariosChegada[indices] = localTimeChegada;

                    System.out.println("NOVO HORARIO DE CHEGADA: " + HorariosChegada[indices]);
                    System.out.println("NOVO HORARIO DE PARTIDA: " + HorariosPartida[indices]);

                    comboio.setHorariosSaida(HorariosPartida);
                    comboio.setHorariosChegada(HorariosChegada);
                    panel.setVisible(false);
                    this.selecionarEstacaoEditar(frame, comboio);
                }else{
                    janelaErro();
                }

            }else if (indices == 0){
                Date datePartida = (Date) finalSpinnerSaida.getModel().getValue();

                Instant instantPartida = datePartida.toInstant();

                ZonedDateTime zonedDateTimePartida = instantPartida.atZone(ZoneId.systemDefault());

                LocalTime localTimePartida = zonedDateTimePartida.toLocalTime();


                    HorariosPartida[indice] = localTimePartida;

                    comboio.setHorariosSaida(HorariosPartida);

                    System.out.println("NOVO HORARIO DE PARTIDA: " + HorariosPartida[indice]);

                panel.setVisible(false);
                this.selecionarEstacaoEditar(frame, comboio);
            }else{
                Date date = (Date)spinner.getModel().getValue();

                Instant instant = date.toInstant();

                ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

                LocalTime localTimeChegada = zonedDateTime.toLocalTime();
                comboio.setHorariosChegada(HorariosChegada);
                panel.setVisible(false);
                this.selecionarEstacaoEditar(frame, comboio);

            }

        });

        panel.add(salvarAlterações);
        panel.repaint();
    }

    /**
     * Permite ao User escolher o Comboio para o qual o horario quer alterar
     *
     * @param frame JFrame da nossa app
     * @param comboios array de comboios existentes no sistema
     * @param estacaos array de estacaos existentes no sistema
     */
    public void selecionarComboioEditar(baseFrame frame, Comboio[] comboios, Estacao[] estacaos){


        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(70,90,250,300);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);



        frame.add(panel);

        JButton[] ComboiosBotoes = new JButton[comboios.length];
        int y = 50;
        for(int i = 0;i < ComboiosBotoes.length;i++){
            ComboiosBotoes[i] = new JButton();
            ComboiosBotoes[i].setBounds(25,y,200,25 );
            ComboiosBotoes[i].setText("COMBOIO " + (comboios[i].getNmrComboio() +1));
            ComboiosBotoes[i].setFocusable(false);
            int finalI1 = i;
            ComboiosBotoes[i].addActionListener(e -> {panel.setVisible(false);this.selecionarEstacaoEditar(frame, comboios[finalI1]);});
            y = y + 40;
            panel.add(ComboiosBotoes[i]);
        }
        panel.repaint();
    }

    /**
     *
     * Permite ao user escolher a paragem do comboio cujo horario quer alterar
     *
     * @param frame JFrame da nossa app
     * @param comboio comboio que o user quer alterar
     */
    public void selecionarEstacaoEditar(baseFrame frame, Comboio comboio){
        frame.setSize(400, (comboio.getParagens().length * 20) + 320);//Comprimento e Largura da Janela
        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(70,90,250,(comboio.getParagens().length * 50) - 10);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        frame.add(panel);


        String[] Paragens = comboio.getParagens();


        JButton[] ParagensBotoes = new JButton[comboio.getParagens().length];

        int y = 20;

        for(int i = 0;i < comboio.getParagens().length;i++){
            ParagensBotoes[i] = new JButton();
            ParagensBotoes[i].setBounds(25,y,200,25 );
            ParagensBotoes[i].setText(Paragens[i]);
            ParagensBotoes[i].setFocusable(false);
            int finalI = i;
            ParagensBotoes[i].addActionListener(e -> {panel.setVisible(false);this.alterarHorarios(frame, comboio, finalI);});
            y = y +30;
            panel.add(ParagensBotoes[i]);


        }
        panel.repaint();
    }

    public void janelaErro(){
        baseFrame janelaErro = new baseFrame();
        janelaErro.setSize(400, 190);
        janelaErro.setBackground(Color.WHITE);
        janelaErro.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel textoErro = new JLabel("HORÁRIO DE SAÍDA ANTES DO HORARIO DE CHEGADA!");
        textoErro.setLayout(null);
        textoErro.setBounds(70, 10, 400,60 );
        textoErro.setFont(new Font("Calibri", Font.BOLD, 12));
        textoErro.setAlignmentY(Component.CENTER_ALIGNMENT);
        textoErro.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoOK = new JButton();
        botaoOK.setText("OK");
        botaoOK.setLayout(null);
        botaoOK.setBounds(150,100,70,30);
        botaoOK.addActionListener(e -> janelaErro.dispose());

        janelaErro.add(textoErro);
        janelaErro.add(botaoOK);
    }
}
