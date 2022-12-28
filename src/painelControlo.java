import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.awt.Font.createFont;


public class painelControlo {
    private static baseFrame painelControloFrame;

    private static baseFrame selecionarComboioAlterarHorario;

    private static baseFrame selecionarEstacaoAlterarHorario;

    private static baseFrame alterarHorarios;

    private int indiceComboioEditar;

    public painelControlo(Comboio[] comboios, Estacao[] estacoes) {
        painelControloFrame = new baseFrame();

        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        painelControloFrame.setIconImage(logo.getImage());//Define o logo como o icon da janela
        painelControloFrame.getContentPane().setBackground(new Color(166, 237, 128));//Define a Cor de fundo


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
        painelControloFrame.add(picLabel);
        painelControloFrame.add(panel);

        JButton alterarHorarios = new JButton();
        alterarHorarios.setBounds(25,50, 200, 25);
        alterarHorarios.addActionListener(e -> this.selecionarComboioEditar(comboios, estacoes));
        alterarHorarios.setText("ALTERAR HORÁRIOS");
        alterarHorarios.setFocusable(false);
        panel.add(alterarHorarios);

    }

    public void alterarHorários(Comboio comboio, int indice){
        selecionarEstacaoAlterarHorario.dispose();
        alterarHorarios = new baseFrame();
        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        alterarHorarios.setIconImage(logo.getImage());//Define o logo como o icon da janela
        alterarHorarios.getContentPane().setBackground(new Color(166, 237, 128));//Define a Cor de fundo
        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(15,90,360,350);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        ImageIcon source = new ImageIcon("src\\img\\logoGUI.png");
        ImageIcon resultat = new ImageIcon(source.getImage().getScaledInstance(150, 70, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel(resultat);
        picLabel.setBounds(50,-50,280,200);
        alterarHorarios.add(picLabel);
        alterarHorarios.add(panel);

        LocalTime[] HorariosChegada = comboio.getHorariosChegada();

        JSpinner spinner;

        LocalTime[] HorariosPartida = comboio.getHorariosSaida();

        int HoraPartida = HorariosPartida[indice].getHour();
        int MinutoPartida = HorariosPartida[indice].getMinute();

        int HoraChegada = HorariosChegada[indice].getHour();
        int MinutoChegada = HorariosChegada[indice].getMinute();


        if(indice > 0) {





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
        } else {
            spinner = null;
        }






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
        JSpinner spinnerSaida = new JSpinner(model);
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

        JButton salvarAlterações = new JButton();
        salvarAlterações.setBounds(30,260,300,30 );
        salvarAlterações.setText("SALVAR ALTERAÇÕES");
        salvarAlterações.setFocusable(false);

        salvarAlterações.addActionListener(e -> {
            if(indice > 0){

                Date date = (Date)spinner.getModel().getValue();

                Instant instant = date.toInstant();

                ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

                LocalTime localTimeChegada = zonedDateTime.toLocalTime();


                Date datePartida = (Date)spinnerSaida.getModel().getValue();

                Instant instantPartida = datePartida.toInstant();

                ZonedDateTime zonedDateTimePartida = instantPartida.atZone(ZoneId.systemDefault());

                LocalTime localTimePartida = zonedDateTimePartida.toLocalTime();

                HorariosChegada[indice] = localTimeChegada;
                HorariosPartida[indice] = localTimePartida;


                System.out.println("NOVO HORARIO DE CHEGADA: " + HorariosChegada[indice]);
                System.out.println("NOVO HORARIO DE PARTIDA: " + HorariosPartida[indice]);

                comboio.setHorariosSaida(HorariosPartida);
                comboio.setHorariosChegada(HorariosChegada);


            }else{
                Date datePartida = (Date)spinnerSaida.getModel().getValue();

                Instant instantPartida = datePartida.toInstant();

                ZonedDateTime zonedDateTimePartida = instantPartida.atZone(ZoneId.systemDefault());

                LocalTime localTimePartida = zonedDateTimePartida.toLocalTime();
                HorariosPartida[indice] = localTimePartida;

                comboio.setHorariosSaida(HorariosPartida);

                System.out.println("NOVO HORARIO DE PARTIDA: " + HorariosPartida[indice]);
            }
            this.selecionarEstacaoEditar(comboio);
        });

        panel.add(salvarAlterações);














    }


    public void selecionarComboioEditar(Comboio[] comboios, Estacao[] estacaos){
        painelControloFrame.dispose();
        selecionarComboioAlterarHorario = new baseFrame();

        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        selecionarComboioAlterarHorario.setIconImage(logo.getImage());//Define o logo como o icon da janela
        selecionarComboioAlterarHorario.getContentPane().setBackground(new Color(166, 237, 128));//Define a Cor de fundo


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
        selecionarComboioAlterarHorario.add(picLabel);
        selecionarComboioAlterarHorario.add(panel);

        JButton[] ComboiosBotoes = new JButton[comboios.length];
        int y = 50;
        for(int i = 0;i < ComboiosBotoes.length;i++){
            ComboiosBotoes[i] = new JButton();
            ComboiosBotoes[i].setBounds(25,y,200,25 );
            ComboiosBotoes[i].setText("COMBOIO " + (i+1) );
            ComboiosBotoes[i].setFocusable(false);
            int finalI1 = i;
            ComboiosBotoes[i].addActionListener(e -> this.selecionarEstacaoEditar(comboios[finalI1]));
            y = y +40;
            panel.add(ComboiosBotoes[i]);
        }
    }

    public void selecionarEstacaoEditar(Comboio comboio){
        selecionarComboioAlterarHorario.dispose();

        selecionarEstacaoAlterarHorario = new baseFrame();

        selecionarEstacaoAlterarHorario.setSize(400, (comboio.getParagens().length * 20) + 350);//Comprimento e Largura da Janela

        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        selecionarEstacaoAlterarHorario.setIconImage(logo.getImage());//Define o logo como o icon da janela
        selecionarEstacaoAlterarHorario.getContentPane().setBackground(new Color(166, 237, 128));//Define a Cor de fundo


        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(70,90,250,(comboio.getParagens().length * 80));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);


        ImageIcon source = new ImageIcon("src\\img\\logoGUI.png");
        ImageIcon resultat = new ImageIcon(source.getImage().getScaledInstance(150, 70, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel(resultat);
        picLabel.setBounds(50,-50,280,200);
        selecionarEstacaoAlterarHorario.add(picLabel);
        selecionarEstacaoAlterarHorario.add(panel);


        String[] Paragens = comboio.getParagens();


        JButton[] ParagensBotoes = new JButton[comboio.getParagens().length];

        int y = 50;

        for(int i = 0;i < comboio.getParagens().length;i++){
            ParagensBotoes[i] = new JButton();
            ParagensBotoes[i].setBounds(25,y,200,25 );
            ParagensBotoes[i].setText(Paragens[i]);
            ParagensBotoes[i].setFocusable(false);
            int finalI = i;
            ParagensBotoes[i].addActionListener(e -> this.alterarHorários(comboio, finalI));
            y = y +40;
            panel.add(ParagensBotoes[i]);

        }
    }

}
