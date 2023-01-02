public class embarquePassageiros {
    /**
     * Passageiros que vao sair do comboio.
     */
    Passageiro[] PassageirosParaSair;
    /**
     * Passageiros que vao entrar no comboio.
     */
    Passageiro[] PassageirosEntrarComboio;

    public void embarquePassageiros(){

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
        String[] Paragens = Comboios[indiceComboio].getParagens();

        int indiceParagem = Comboios[indiceComboio].getIndiceParagem();

        String ParagemAtual = Paragens[indiceParagem];

        String[] ParagensDisponiveis = new String[Paragens.length - indiceParagem];

        int y = 0;

        for (int i = indiceParagem + 1; i < Paragens.length; i++) {
            ParagensDisponiveis[y] = Paragens[i];
            y++;
        }

        int indiceEstacaoAtual = -1;

        for (int i = 0; i < Estacoes.length; i++) {
            if (Estacoes[i].getNome().equals(ParagemAtual)) {
                indiceEstacaoAtual = i;
            }
        }


        Passageiro[] PassageirosNaEstacao = Estacoes[indiceEstacaoAtual].getPassageiros();


        int[] indiceNaEstacaoParaEntrar = new int[PassageirosNaEstacao.length];
        int[] numeroDeEstacoesAteDestino = new int[PassageirosNaEstacao.length];
        y = 0;
        for (int i = 0; i < indiceNaEstacaoParaEntrar.length; i++) {
            for (int f = 0; f < ParagensDisponiveis.length; f++) {
                if (PassageirosNaEstacao[i].getEstacaoDestino().equals(ParagensDisponiveis[f])) {
                    //ver se nao e preciso inicializar primeiro
                    indiceNaEstacaoParaEntrar[y] = i;
                    numeroDeEstacoesAteDestino[y] = f;
                    y++;
                }
            }
        }
        int[] guardarTempIndiceEntrar = new int[y];
        int[] guardarTempnumeroDeEstacoes = new int[y];

        for (int i = 0; i < y; i++) {
            guardarTempIndiceEntrar[i] = indiceNaEstacaoParaEntrar[i];
            guardarTempnumeroDeEstacoes[i] = numeroDeEstacoesAteDestino[i];
        }

        indiceNaEstacaoParaEntrar = guardarTempIndiceEntrar;
        numeroDeEstacoesAteDestino = guardarTempnumeroDeEstacoes;


        int guardarTemp = -1;
        for (int i = 0; i < y - 1; i++) {
            if (numeroDeEstacoesAteDestino[i] < numeroDeEstacoesAteDestino[i + 1]) {
                guardarTemp = indiceNaEstacaoParaEntrar[i];
                indiceNaEstacaoParaEntrar[i] = indiceNaEstacaoParaEntrar[i + 1];
                indiceNaEstacaoParaEntrar[i + 1] = guardarTemp;
            }
        }

        PassageirosEntrarComboio = new Passageiro[y];
        int indice = 0;
        for(int i = 0; i < y;i++){
            for(int k = 0; k < PassageirosNaEstacao.length;k++){
                if(k == indiceNaEstacaoParaEntrar[i]){
                    PassageirosEntrarComboio[indice] = new Passageiro();
                    PassageirosEntrarComboio[indice] = PassageirosNaEstacao[k];
                    PassageirosEntrarComboio[indice].setEstacao(false);
                    PassageirosEntrarComboio[indice].setIndiceComboio(indiceComboio);
                    indice++;
                }
            }
        }

        Passageiro[] PassageirosNoComboio = Comboios[indiceComboio].getPassageiros();

        PassageirosParaSair = new Passageiro[PassageirosNoComboio.length];

        indice = 0;

        for(int i = 0;i < PassageirosNoComboio.length;i++){
            if(PassageirosNoComboio[i].getEstacaoDestino().equals(ParagemAtual)){
                PassageirosParaSair[indice] = new Passageiro();
                PassageirosParaSair[indice] = PassageirosNoComboio[i];
                indice++;
            }
        }
        Passageiro[] PassageirosParaSairTemp = new Passageiro[indice];

        for(int i = 0; i < indice;i++){
            PassageirosParaSairTemp[i] = new Passageiro();
            PassageirosParaSairTemp[i] = PassageirosParaSair[i];
            PassageirosParaSairTemp[i].setEstacao(true);
            PassageirosParaSairTemp[i].setIndceEstacao(indiceEstacaoAtual);
        }

        PassageirosParaSair = PassageirosParaSairTemp;
    }
}
