import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static Estacao[]  Estacoes = new Estacao[10];
    public static void main(String[] args) {
        baseFrame main = new baseFrame();
        String[] Paragens = {"São Bento", "Heroísmo" ,"Campanhã", "Estádio do Dragão", "Fanzeres", "Santo Ovídio","Camara de Gaia" ,"João de Deus", "General Torres", "Trindade"};

        for(int i = 0;i < 10;i++){
            Estacoes[i] = new Estacao(Paragens[i],10,1);
            Estacoes[i].escolherPassageirosRandom(Estacoes);

        }

        for(int i = 0;i < 10;i++){
            Estacoes[i].escolherPassageirosRandom(Estacoes);
        }

        for(int i = 0;i < 10;i++){
            System.out.println(Estacoes[i].toString());
        }







    }






}