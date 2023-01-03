import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Comboio {
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

    public void addPassageiro(Passageiro passageiro){
        Passageiro[] PassageirosNovo;
        if (this.Passageiros != null) {
            PassageirosNovo = new Passageiro[this.Passageiros.length + 1];

            for (int i = 0; i < this.Passageiros.length; i++) {
                PassageirosNovo[i] = new Passageiro();
                PassageirosNovo[i] = this.Passageiros[i];
            }
            PassageirosNovo[this.Passageiros.length] = passageiro;


        }else{
            PassageirosNovo = new Passageiro[1];
            PassageirosNovo[0] = passageiro;
        }
        this.setPassageiros(PassageirosNovo);
        this.setContadorPassageiros(this.Passageiros.length);
    }

    public void removePassageiro(int nmrPassageiro){
        Passageiro[] PassageirosNovos = new Passageiro[this.Passageiros.length - 1];
        int indiceFor = 0;
        for(int i = 0; i < this.Passageiros.length; i++){
            if(this.Passageiros[i].getNmrPassageiro() != nmrPassageiro){
                PassageirosNovos[indiceFor] = this.Passageiros[i];
                indiceFor++;
            }
        }
        this.setPassageiros(PassageirosNovos);
        this.setContadorPassageiros(this.Passageiros.length);
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
     *
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
                }while(Partida[i].isBefore(Anterior));
                Anterior = Partida[i];
            }




            Anterior= LocalTime.of(0, 0);
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


            }while(Chegada[0].isBefore(Anterior) || Chegada[0].isAfter(Partida[0]));



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


                }while(Chegada[i].isBefore(Anterior) || Chegada[i].isAfter(Partida[i])|| Chegada[i].isBefore(Partida[i - 1]));

                Anterior = Chegada[i];
            }
            LocalTime[] PartidaFinal = new LocalTime[9];
            for(int i = 0;i < 9;i++){
                PartidaFinal[i] = Partida[i];
            }
            this.setHorariosSaida(Partida);
            this.setHorariosChegada(Chegada);

        this.setParagens(percurso);



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

}
