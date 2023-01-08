import java.util.concurrent.Semaphore;

import java.io.FileWriter;
import java.io.IOException;

public class simuladorTrafego {
    public simuladorTrafego(embarquePassageiros[] embarque, Comboio[] Comboios, Estacao[] Estacoes){
        Thread[] ths = new Thread[Comboios.length];

        for (int i = 0; i < Comboios.length; i++) {
            ths[i] = new Thread();
            Main.SemaphorePermitirComboioAndar[i] = new Semaphore(0);
            Main.SemaphorePermitirEmbarque[i] = new Semaphore(1);
        }

        for(int i = 0; i < Comboios.length; i++){
            ths[i] = new Thread(Comboios[i]);
            ths[i].start();
        }


    }



}
