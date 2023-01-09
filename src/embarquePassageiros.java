import java.util.concurrent.Semaphore;

public class embarquePassageiros {
    /**
     * Passageiros que vao sair do comboio.
     */
    private Passageiro[] PassageirosParaSair;
    /**
     * Passageiros que vao entrar no comboio.
     */
    private Passageiro[] PassageirosEntrarComboio;

    private int indiceComboio;

    public void embarquePassageiros() throws InterruptedException {


            Main.SemaphorePermitirEmbarque[indiceComboio].acquire();
            if (this.PassageirosParaSair != null) {
                Thread[] ths = new Thread[this.PassageirosParaSair.length];


                for (int i = 0; i < PassageirosParaSair.length; i++) {

                    ths[i] = new Thread(PassageirosParaSair[i]);
                }

                for (int i = 0; i < this.PassageirosParaSair.length; i++) {
                    if (i % 5 == 0) {
                        ths[i].start();
                        ths[i].join();
                        Thread.sleep((long) 5.56);
                    }else {
                        ths[i].start();
                        ths[i].join();
                    }
                }
            }

            Thread.sleep((long) 5.56);

            if (this.PassageirosEntrarComboio != null) {
                Thread[] ths = new Thread[this.PassageirosEntrarComboio.length];


                for (int i = 0; i < PassageirosEntrarComboio.length; i++) {
                    ths[i] = new Thread(PassageirosEntrarComboio[i]);
                }

                for (int i = 0; i < this.PassageirosEntrarComboio.length; i++) {
                    if (i % 5 == 0) {
                        ths[i].start();
                        ths[i].join();
                        Thread.sleep((long) 5.56);
                    } else {
                        ths[i].start();
                        ths[i].join();
                    }

                }
            }
            Main.SemaphorePermitirComboioAndar[indiceComboio].release();

    }


    /**
     *
     * Esta funcao vai guardar em duas arrays os Passageiros que vao entrar no comboio (ja ordenados de viagem mais curta para a mais longa)
     * e os passageiros que vao sair do comboio para a estacao.
     *
     * @param indiceComboio indice do Comboio que parou na estacao
     * @param Comboios array com todos os comboios no sistema
     * @param Estacoes array com todas as estacoes no sistema
     */
    public void arrayEntradaeSaidaEstacao(int indiceComboio, Comboio[] Comboios, Estacao[] Estacoes) {
        this.indiceComboio = indiceComboio;
        //Lista de Paragens no Comboio
        String[] Paragens = Comboios[indiceComboio].getParagens();
        //Lista de paragens em que o comboio ainda vai parar
        String[] ParagensDisponiveis = new String[Paragens.length - Comboios[indiceComboio].getIndiceParagem()];
        int indice3 = 0;
        /**
         *
         */
        for(int i = Comboios[indiceComboio].getIndiceParagem();i < Paragens.length;i++){
            ParagensDisponiveis[indice3] = new String();
            ParagensDisponiveis[indice3] = Paragens[i];
            indice3++;
        }
        //Indice da Estacao atual
        int indiceEstacao = -1;
        for(int i = 0;i < Estacoes.length;i++){
            if(Estacoes[i].getNome().equals(Paragens[Comboios[indiceComboio].getIndiceParagem()])){
                indiceEstacao = i;
            }
        }

        //Lista de Passageiros na Estacao
        Passageiro[] PassageirosNaEstacao = Estacoes[indiceEstacao].getPassageiros();
        //Lista de Passageiros para entrar no comboio
        Passageiro[] PassageirosParaEntrarComboio = new Passageiro[PassageirosNaEstacao.length];
        int[] numerDeParagensAteDestino = new int[PassageirosNaEstacao.length];

        int indice = 0;

        /**
         * Passa por todos os passageiros nas estacao e compara as suas estacoes de destino
         * com as paragens que o comboio ainda tem que fazer, guardando os passageiros cujo destino passa pelas paragens do comboio.
         * Guarda ainda o numero de paragens ate ao destino de cada passageiro.
         */
        for(int i = 0;i < PassageirosParaEntrarComboio.length;i++){
            for(int k = 0;k < ParagensDisponiveis.length;k++){
                if(PassageirosNaEstacao[i].getEstacaoDestino().equals(ParagensDisponiveis[k])){
                    PassageirosParaEntrarComboio[indice] = new Passageiro();
                    PassageirosParaEntrarComboio[indice] = PassageirosNaEstacao[i];
                    numerDeParagensAteDestino[indice] = k;
                    indice++;
                    break;
                }
            }
        }

        Passageiro temp = new Passageiro();
        /**
         * Ordena os passageiros que vao entrar no comboio, da viagem mais curta para a mais longa.
         */
        for(int i = 0;i < indice;i++){
            for(int k = i;k < indice;k++){
                if(numerDeParagensAteDestino[k]< numerDeParagensAteDestino[i]){
                    temp = PassageirosParaEntrarComboio[i];
                    PassageirosParaEntrarComboio[i] = PassageirosParaEntrarComboio[k];
                    PassageirosParaEntrarComboio[k] = temp;
                }
            }
        }

        this.PassageirosEntrarComboio = new Passageiro[indice];
        /**
         * Guarda os valores na variavel da classe
         */
        for(int i = 0; i < indice;i++){
            PassageirosEntrarComboio[i] = new Passageiro();
            PassageirosEntrarComboio[i] = PassageirosParaEntrarComboio[i];
            PassageirosEntrarComboio[i].setEstacao(false);
            PassageirosEntrarComboio[i].setIndiceEstacao(indiceEstacao);
            PassageirosEntrarComboio[i].setIndiceComboio(indiceComboio);

        }




        /**
         * Passageiros dentro do comboio
         */
        Passageiro[] PassageirosNoComboio = Comboios[indiceComboio].getPassageiros();

        if(PassageirosNoComboio != null) {
            /**
             * Passageiros que vao sair do comboio
             */
            Passageiro[] PassageirosSairComboio = new Passageiro[PassageirosNoComboio.length];

            indice = 0;
            /**
             * Passa por todos os passageiros no comboio e compara as suas estacoes de destino
             * com a estacao atual, guardando os passageiros cujo destino e a estacao atual.
             */
            for (int i = 0; i < PassageirosNoComboio.length; i++) {
                if (PassageirosNoComboio[i].getEstacaoDestino().equals(Paragens[Comboios[indiceComboio].getIndiceParagem()])) {
                    PassageirosSairComboio[indice] = new Passageiro();
                    PassageirosSairComboio[indice] = PassageirosNoComboio[i];
                    indice++;
                }
            }


            this.PassageirosParaSair = new Passageiro[indice];
            /**
             * Guarda os valores na variavel da classe
             */
            for (int i = 0; i < indice; i++) {
                PassageirosParaSair[i] = new Passageiro();
                PassageirosParaSair[i] = PassageirosSairComboio[i];
                PassageirosParaSair[i].setEstacao(true);
                PassageirosParaSair[i].setIndiceEstacao(indiceEstacao);
                PassageirosParaSair[i].setIndiceComboio(indiceComboio);
            }

        }



    }
}
