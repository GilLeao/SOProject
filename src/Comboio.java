import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Comboio {
    int nmrComboio;
    /**
     * Numero de passageiros no comboio
     */
    int contadorPassageiros;
    /**
     * Numero Máximo de Passageiros no Comboio
     */
    int nmrMaxPassageiros;

    /**
     * Array com todas as paragens que o comboio faz
     */
    String[] Paragens;
    /**
     * Ultima Paragem do Comboio
     */
    String ultimaParagem;
    /**
     * Indice de paragem em que o comboio se encotnra
     */
    int indiceParagem = 0;

    /**
     * Array com os horários de chegada
     */
    LocalTime[] horariosChegada;
    /**
     * Array com os horários de saída
     */
    LocalTime[] horariosSaida;
    /**
     * Boolean que indica se chegou á ultima paragem
     */
    boolean estacaoFinal;
    /**
     * Array de passageiros no comboio
     */
    Passageiro[] Passageiros;



    public Comboio() {

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
