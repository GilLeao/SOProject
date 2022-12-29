import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static java.awt.Font.createFont;


public class painelControlo {
    private int indiceComboioEditar;

    public painelControlo(baseFrame frame, Comboio[] comboios, Estacao[] estacoes) {
        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(70,90,250,300);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);


        frame.add(panel);

        JButton alterarHorarios = new JButton();
        alterarHorarios.setBounds(25,50, 200, 25);
        alterarHorarios.addActionListener(e -> {panel.setVisible(false);this.selecionarComboioEditar(frame, comboios, estacoes);});
        alterarHorarios.setText("ALTERAR HORÁRIOS");
        alterarHorarios.setFocusable(false);
        panel.add(alterarHorarios);


        JButton planearViagem = new JButton();
        planearViagem.setBounds(25,90, 200, 25);
        planearViagem.addActionListener(e -> {panel.setVisible(false);this.planearViagem(frame, estacoes, comboios);});
        planearViagem.setText("INFOMAÇÃO VIAGENS");
        planearViagem.setFocusable(false);
        panel.add(planearViagem);
        panel.repaint();

    }

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

        final JComboBox escolherParagem =new JComboBox(Paragens);
        escolherParagem.setBounds(20, 10, 140, 30);

        final String[] EstacaoEscolhida = {null};

        JButton confirmar = new JButton("PROCURAR");
        confirmar.setBounds(170, 10, 120, 30);
        confirmar.addActionListener(e -> {

            EstacaoEscolhida[0] = (String) escolherParagem.getSelectedItem();
            Comboio[] paramNaEstacao = this.paragemComboio(EstacaoEscolhida[0], comboios);
            this.escolherComboioMostrarInformacao(paramNaEstacao, frame, panel, estacaos, EstacaoEscolhida[0]);


        });
        panel.add(confirmar);
        panel.add(escolherParagem);
        panel.repaint();


    }

    public void escolherComboioMostrarInformacao(Comboio[] param, baseFrame frame, JPanel panel, Estacao[] estacaos, String EstacaoEscolhida){
        String[] nomeComboios = new String[param.length];

        for(int i = 0; i < param.length; i++){
            nomeComboios[i] = new String();
            nomeComboios[i] = "COMBOIO " + (i + 1);
        }

        JComboBox escolherComboio =new JComboBox(nomeComboios);
        escolherComboio.setBounds(430, 10, 140, 30);

        JButton procurar = new JButton("CONFIRMAR");
        procurar.setBounds(600, 10, 120, 30);
        procurar.addActionListener(e -> {
            String ComboioEscolhido = (String) escolherComboio.getSelectedItem();
            String intValue = ComboioEscolhido.replaceAll("[^0-9]", "");
            int indice = Integer.parseInt(intValue);
            indice--;
            System.out.println(indice);
            this.mostrarInformacaoComboio(EstacaoEscolhida, param[indice], frame, panel);
            procurar.setVisible(false);
            escolherComboio.setVisible(false);
        });
        panel.add(escolherComboio);
        panel.add(procurar);
        panel.repaint();


    }

    public void mostrarInformacaoComboio(String Estacao, Comboio comboio, baseFrame frame, JPanel panel){
        String[] Paragens = comboio.getParagens();
        int indice = 0;
        for(int i = 0; i < comboio.getParagens().length; i++){
            if(Estacao.equals(Paragens[i])){
                if(indice == 0){
                    indice = i;
                }
            }
        }

        LocalTime[] HoraChegada = comboio.getHorariosChegada();
        LocalTime[] HoraPartida = comboio.getHorariosSaida();




        JLabel horadePartida = new JLabel("HORA DE PARTIDA DA ESTAÇÃO ANTERIOR: " + HoraPartida[indice - 1].toString());
        horadePartida.setForeground(Color.WHITE);
        horadePartida.setFont(new Font("Arial", Font.BOLD, 14));
        horadePartida.setLayout(null);
        horadePartida.setBounds(30, 150, 1200, 10);

        panel.add(horadePartida);
        panel.repaint();

    }

    public Comboio[] paragemComboio(String estacao, Comboio[] comboios){
        String[] Paragens;
        Comboio[] paramNaEstacao = new Comboio[comboios.length];
        int indiceComboios = 0;
        for(int i = 0; i < comboios.length;i++){
            paramNaEstacao[i] = new Comboio();
            Paragens = comboios[i].getParagens();
            for(int k = 1; k < comboios[i].getParagens().length;k++){
                if(Paragens[k].equals(estacao)){
                    paramNaEstacao[indiceComboios] = comboios[i];
                    indiceComboios++;
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
            ComboiosBotoes[i].setText("COMBOIO " + (i+1) );
            ComboiosBotoes[i].setFocusable(false);
            int finalI1 = i;
            ComboiosBotoes[i].addActionListener(e -> {panel.setVisible(false);this.selecionarEstacaoEditar(frame, comboios[finalI1]);});
            y = y + 40;
            panel.add(ComboiosBotoes[i]);
        }
        panel.repaint();
    }

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
