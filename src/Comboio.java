import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Comboio implements Runnable {

    private int indiceTroco;

    private int nmrComboio;
    /**
     * Numero de passageiros no comboio
     */
    private int contadorPassageiros;
    /**
     * Numero Máximo de Passageiros no Comboio
     */
    private int nmrMaxPassageiros;

    /**
     * Array com todas as paragens que o comboio faz
     */
    private String[] Paragens;
    /**
     * Ultima Paragem do Comboio
     */
    private String ultimaParagem;
    /**
     * Indice de paragem em que o comboio se encotnra
     */
    private int indiceParagem = 0;

    /**
     * Array com os horários de chegada
     */
    private LocalTime[] horariosChegada;
    /**
     * Array com os horários de saída
     */
    private LocalTime[] horariosSaida;
    /**
     * Boolean que indica se chegou á ultima paragem
     */
    private boolean estacaoFinal;
    /**
     * Array de passageiros no comboio
     */
    private Passageiro[] Passageiros = null;




    public Comboio() {
    }

    public int getIndiceTroco() {
        return indiceTroco;
    }

    public void setIndiceTroco(int indiceTroco) {
        this.indiceTroco = indiceTroco;
    }

    public void addPassageiro(Passageiro passageiro) {
        Passageiro[] PassageirosNovo;
        if (this.Passageiros != null) {
            PassageirosNovo = new Passageiro[this.Passageiros.length + 1];

            for (int i = 0; i < this.Passageiros.length; i++) {
                PassageirosNovo[i] = new Passageiro();
                PassageirosNovo[i] = this.Passageiros[i];
            }
            PassageirosNovo[this.Passageiros.length] = passageiro;


        } else {
            PassageirosNovo = new Passageiro[1];
            PassageirosNovo[0] = passageiro;
        }
        this.setPassageiros(PassageirosNovo);
        this.setContadorPassageiros(this.Passageiros.length);
    }

    private boolean findPassageiro(int nmrPassageiro){
        if (this.Passageiros != null) {

            for (int i = 0; i < this.Passageiros.length; i++) {
                if (this.Passageiros[i].getNmrPassageiro() == nmrPassageiro) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removePassageiro(int nmrPassageiro) {
        if(this.Passageiros != null) {
            if (this.findPassageiro(nmrPassageiro) == true) {
                Passageiro[] PassageirosNovos = new Passageiro[this.Passageiros.length - 1];
                int indiceFor = 0;
                for (int i = 0; i < this.Passageiros.length; i++) {
                    if (this.Passageiros[i].getNmrPassageiro() != nmrPassageiro) {
                        PassageirosNovos[indiceFor] = this.Passageiros[i];
                        indiceFor++;
                    }
                }
                this.setPassageiros(PassageirosNovos);
                this.setContadorPassageiros(this.Passageiros.length);
            }
        }
    }

    public int getNmrComboio() {
        return nmrComboio;
    }

    public void setNmrComboio(int nmrComboio) {
        this.nmrComboio = nmrComboio;
    }

    public Passageiro[] getPassageiros() {
        return Passageiros;
    }

    public void setPassageiros(Passageiro[] passageiros) {
        Passageiros = passageiros;
        this.setContadorPassageiros(this.Passageiros.length);
    }

    public int getContadorPassageiros() {
        return contadorPassageiros;
    }

    public void setContadorPassageiros(int contadorPassageiros) {
        this.contadorPassageiros = contadorPassageiros;
    }

    public int getNmrMaxPassageiros() {
        return nmrMaxPassageiros;
    }

    public void setNmrMaxPassageiros(int nmrMaxPassageiros) {
        this.nmrMaxPassageiros = nmrMaxPassageiros;
    }

    public String[] getParagens() {
        return Paragens;
    }

    public void setParagens(String[] paragens) {
        Paragens = paragens;
        this.setUltimaParagem(this.Paragens[this.Paragens.length - 1]);
    }

    public String getUltimaParagem() {
        return ultimaParagem;
    }

    public void setUltimaParagem(String ultimaParagem) {
        this.ultimaParagem = ultimaParagem;
    }

    public int getIndiceParagem() {
        return indiceParagem;
    }

    public void setIndiceParagem(int indiceParagem) {
        this.indiceParagem = indiceParagem;
    }

    public LocalTime[] getHorariosChegada() {
        return horariosChegada;
    }

    public void setHorariosChegada(LocalTime[] horariosChegada) {
        this.horariosChegada = horariosChegada;
    }

    public LocalTime[] getHorariosSaida() {
        return horariosSaida;
    }

    public void setHorariosSaida(LocalTime[] horariosSaida) {
        this.horariosSaida = horariosSaida;
    }

    public boolean isEstacaoFinal() {
        return estacaoFinal;
    }

    public void setEstacaoFinal(boolean estacaoFinal) {
        this.estacaoFinal = estacaoFinal;
    }

    /**
     * Gera de forma aleatória as paragens e os horários de chegada e partida das estações
     *
     * @param values Array de estações
     */
    public void gerarAleatorio(Estacao[] values) {
        int numEstacoes = ThreadLocalRandom.current().nextInt(2, 10);//Numero de Paragens que o comboio vai ter


        String[] percurso = new String[numEstacoes]; //Array que armazena o percurso do comboio

        String prevEstacao = null;
        for (int i = 0; i < numEstacoes; i++) {
            String estacao;
            do {
                int index = ThreadLocalRandom.current().nextInt(0, 10);

                estacao = values[index].getNome();
            } while (estacao.equals(prevEstacao));

            percurso[i] = estacao;


            prevEstacao = estacao;
        }

        this.setParagens(percurso);


        LocalTime Anterior = LocalTime.of(0, 0);
        LocalTime[] Partida = new LocalTime[10];

        for (int i = 0; i < 10; i++) {
            do {
                LocalTime time1 = LocalTime.of(0, 0);
                LocalTime time2 = LocalTime.of(23, 59);
                int secondOfDayTime1 = time1.toSecondOfDay();
                int secondOfDayTime2 = time2.toSecondOfDay();
                Random random = new Random();
                int randomSecondOfDay = secondOfDayTime1 + random.nextInt(secondOfDayTime2 - secondOfDayTime1);
                LocalTime randomLocalTime = LocalTime.ofSecondOfDay(randomSecondOfDay);
                randomLocalTime = randomLocalTime.truncatedTo(ChronoUnit.MINUTES);
                Partida[i] = randomLocalTime;
            } while (Partida[i].isBefore(Anterior));
            Anterior = Partida[i];
        }


        Anterior = LocalTime.of(0, 0);
        LocalTime[] Chegada = new LocalTime[10];

        do {
            LocalTime time1 = LocalTime.of(0, 0);
            LocalTime time2 = LocalTime.of(23, 59);
            int secondOfDayTime1 = time1.toSecondOfDay();
            int secondOfDayTime2 = time2.toSecondOfDay();
            Random random = new Random();
            int randomSecondOfDay = secondOfDayTime1 + random.nextInt(secondOfDayTime2 - secondOfDayTime1);
            LocalTime randomLocalTime = LocalTime.ofSecondOfDay(randomSecondOfDay);
            randomLocalTime = randomLocalTime.truncatedTo(ChronoUnit.MINUTES);
            Chegada[0] = randomLocalTime;


        } while (Chegada[0].isBefore(Anterior) || Chegada[0].isAfter(Partida[0]));


        int k = 1;
        for (int i = 1; i < 10; i++) {
            do {
                LocalTime time1 = LocalTime.of(0, 0);
                LocalTime time2 = LocalTime.of(23, 59);
                int secondOfDayTime1 = time1.toSecondOfDay();
                int secondOfDayTime2 = time2.toSecondOfDay();
                Random random = new Random();
                int randomSecondOfDay = secondOfDayTime1 + random.nextInt(secondOfDayTime2 - secondOfDayTime1);
                LocalTime randomLocalTime = LocalTime.ofSecondOfDay(randomSecondOfDay);
                randomLocalTime = randomLocalTime.truncatedTo(ChronoUnit.MINUTES);
                Chegada[i] = randomLocalTime;

            } while (Chegada[i].isBefore(Anterior) || Chegada[i].isAfter(Partida[i]) || Chegada[i].isBefore(Partida[i - 1]));

            Anterior = Chegada[i];
        }
        LocalTime[] PartidaFinal = new LocalTime[9];
        for (int i = 0; i < 9; i++) {
            PartidaFinal[i] = Partida[i];
        }
        this.setHorariosSaida(Partida);
        this.setHorariosChegada(Chegada);

        this.setParagens(percurso);
    }


    public int getIndiceTrocoAndar(){
        int indiceTroco = - this.getNmrComboio();
        for(int i = 0; i < Main.Trocos.length;i++){
            if(Main.Trocos[i].getLigacao1().equals(this.Paragens[this.getIndiceParagem()]) && Main.Trocos[i].getLigacao2().equals(this.Paragens[this.getIndiceParagem() + 1])){
                indiceTroco = i;
                Main.Trocos[i].setSentido(false);

            }else if(Main.Trocos[i].getLigacao2().equals(this.Paragens[this.getIndiceParagem()]) && Main.Trocos[i].getLigacao1().equals(this.Paragens[this.getIndiceParagem() + 1])){
                indiceTroco = i;
                Main.Trocos[i].setSentido(true);
            }
        }
        return indiceTroco;
    }



    @Override
    public String toString() {
        return "Comboio{" +
                "contadorPassageiros=" + contadorPassageiros +
                ", nmrMaxPassageiros=" + nmrMaxPassageiros +
                ", Paragens=" + Arrays.toString(Paragens) +
                ", ultimaParagem='" + ultimaParagem + '\'' +
                ", indiceParagem=" + indiceParagem +
                ", horariosChegada=" + Arrays.toString(horariosChegada) +
                ", horariosSaida=" + Arrays.toString(horariosSaida) +
                ", estacaoFinal=" + estacaoFinal +
                ", Passageiros=" + Arrays.toString(Passageiros) +
                '}';
    }


    /**
     * Thread primeiro verifica se ja se encontra na ultima estacao, caso nao esteja determina quanto tempo tem que esperar ate
     * puder partir da estacao, ficando adormecida ate chegar a estacao seguinte.
     */
    @Override
    public void run() {
        int Chegada;
        int Partida;



        while(this.indiceParagem < (this.Paragens.length - 1)) {
            embarquePassageiros embarque = new embarquePassageiros();
            embarque.arrayEntradaeSaidaEstacao(this.nmrComboio, Main.Comboios, Main.Estacoes);
            log log = new log(Main.Comboios, Main.Estacoes);
            try {
                embarque.embarquePassageiros();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
           try {
               Main.SemaphorePermitirComboioAndar[this.getNmrComboio()].acquire();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           try {
                Main.SemaphoreAndamentoComboios.acquire();
                if (this.indiceParagem < (this.Paragens.length - 1)) {
                    if (this.indiceParagem == 0) {
                        Chegada = 0;
                    } else {
                        int HoraC = this.horariosChegada[this.indiceParagem].getHour();
                        HoraC = HoraC * 10000;
                        int MinutosC = this.horariosChegada[this.indiceParagem].getMinute();
                        MinutosC = (int) (MinutosC * 27.78);
                        Chegada = HoraC + MinutosC;

                    }

                    int HoraP = this.horariosSaida[this.indiceParagem].getHour();
                    HoraP = HoraP * 10000;
                    int MinutosP = this.horariosSaida[this.indiceParagem].getMinute();
                    MinutosP = (int) (MinutosP * 27.78);
                    Partida = HoraP + MinutosP;

                    int TempoEspera = Partida - Chegada;
                    int tempoEsperaConvertido = TempoEspera;

                    if(TempoEspera >= 3600000) //converter em horas
                    {
                        tempoEsperaConvertido = tempoEsperaConvertido / 3600000;
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Espera: " + tempoEsperaConvertido + " horas\n---------------------------------------------------------------------");

                    } else if(TempoEspera >= 60000 && TempoEspera < 3599999) //converter em minutos
                    {
                        tempoEsperaConvertido = tempoEsperaConvertido / 60000;
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Espera: " + tempoEsperaConvertido + " minutos\n---------------------------------------------------------------------");

                    } else if(TempoEspera >= 1000 && TempoEspera < 59999) //converter em segundos
                    {
                        tempoEsperaConvertido = tempoEsperaConvertido / 1000;
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Espera: " + tempoEsperaConvertido + " segundos\n---------------------------------------------------------------------");

                    } else //converter em ms
                    {
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Espera: " + tempoEsperaConvertido + " milissegundos\n---------------------------------------------------------------------");
                    }

                    Thread.sleep(TempoEspera);


                    int indiceTroco = this.getIndiceTrocoAndar();
                    this.setIndiceTroco(indiceTroco);


                    int HorasPartida = this.horariosSaida[this.indiceParagem].getHour();
                    HorasPartida = HorasPartida * 10000;
                    int MinutosPartida = this.horariosSaida[this.indiceParagem].getMinute();
                    MinutosPartida = (int) (MinutosPartida * 27.78);
                    int TempoPartida = HorasPartida + MinutosPartida;


                    int HorasChegada = this.horariosChegada[this.indiceParagem + 1].getHour();
                    HorasChegada = HorasChegada * 10000;
                    int MinutosChegada = this.horariosChegada[this.indiceParagem + 1].getMinute();
                    MinutosChegada = (int) (MinutosChegada * 27.78);
                    int TempoChegada = HorasChegada + MinutosChegada;

                    int TempoViagem = TempoChegada - TempoPartida;
                    int tempoViagemConvertido = TempoViagem;

                    if(TempoViagem >= 3600000) //converter em horas
                    {
                        tempoViagemConvertido = tempoViagemConvertido / 3600000;
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Viagem: " + tempoViagemConvertido + " horas\n---------------------------------------------------------------------");

                    } else if(TempoViagem >= 60000 && TempoViagem < 3599999) //converter em minutos
                    {
                        tempoViagemConvertido = tempoViagemConvertido / 60000;
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Viagem: " + tempoViagemConvertido + " minutos\n---------------------------------------------------------------------");

                    } else if(TempoViagem >= 1000 && TempoViagem < 59999) //converter em segundos
                    {
                        tempoViagemConvertido = tempoViagemConvertido / 1000;
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Viagem: " + tempoViagemConvertido + " segundos\n---------------------------------------------------------------------");

                    } else //converter em ms
                    {
                        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " Tempo de Viagem: " + tempoViagemConvertido + " milissegundos \n---------------------------------------------------------------------");
                    }

                    int indiceTroco1 = this.getIndiceTrocoAndar();

                    this.indiceTroco = indiceTroco1;

                    Thread.sleep(TempoViagem);

                    this.indiceParagem++;
                    System.out.println("---------------------------------------------------------------------\nComboio " + this.nmrComboio + " chegou á estação: " + this.Paragens[this.indiceParagem] + " Estação Anterior: " + this.Paragens[this.indiceParagem - 1] + "\n---------------------------------------------------------------------");

                    for(int i = 0; i < Main.Estacoes.length;i++){
                        Main.Estacoes[i].NmrComboiosNaEstacao(Main.Comboios);
                    }
                    if(this.Passageiros != null) {
                        if (this.indiceParagem == (this.Passageiros.length - 1)) {
                            embarque = new embarquePassageiros();
                            embarque.arrayEntradaeSaidaEstacao(this.nmrComboio, Main.Comboios, Main.Estacoes);
                        }
                    }
                    Main.SemaphoreAndamentoComboios.release();
                    Main.SemaphorePermitirEmbarque[this.nmrComboio].release();


                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
       }
        System.out.println("---------------------------------------------------------------------\nComboio: " + this.nmrComboio + " chegou ao fim da viagem.\n---------------------------------------------------------------------");

    }
}
