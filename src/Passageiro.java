public class Passageiro implements Runnable {
    /**
     * Variavel do tipo Boolean que indica se o Passageiro vai entrar numa:
     * Estacao - Caso o valor seja <b>True</b>
     * Comboio - Caso o valor seja <b>False</b>
     */
    boolean isEstacao;
    /**
     * Indice do Comboio em que o Passageiro vai entrar ou sair
     */
    int indiceComboio;
    /**
     * Indice da Estacao em que o passageiro vai entrar ou sair;
     */
    int indiceEstacao;

    /**
     * Numero de Passageiro, unico para cada passageiro
     */
    int nmrPassageiro;

    /**
     * Estação de Destino
     */
    String estacaoDestino;
    /**
     * Boolean que indica se o bilhete é válido
     */
    boolean bilheteValido;

    public boolean isEstacao() {
        return isEstacao;
    }

    public void setEstacao(boolean estacao) {
        isEstacao = estacao;
    }

    public int getIndiceComboio() {
        return indiceComboio;
    }

    public void setIndiceComboio(int indiceComboio) {
        this.indiceComboio = indiceComboio;
    }

    public int getIndceEstacao() {
        return indiceEstacao;
    }

    public void setIndceEstacao(int indceEstacao) {
        this.indiceEstacao = indceEstacao;
    }

    public String getEstacaoDestino() {
        return estacaoDestino;
    }

    public int getNmrPassageiro() {
        return nmrPassageiro;
    }

    public void setNmrPassageiro(int nmrPassageiro) {
        this.nmrPassageiro = nmrPassageiro;
    }

    public void setEstacaoDestino(String estacaoDestino) {
        this.estacaoDestino = estacaoDestino;
    }

    public boolean isBilheteValido() {
        return bilheteValido;
    }

    public void setBilheteValido(boolean bilheteValido) {
        this.bilheteValido = bilheteValido;
    }

    @Override
    public String toString() {
        return "Passageiro{" +
                "nmrPassageiro=" + nmrPassageiro +
                ", estacaoDestino='" + estacaoDestino + '\'' +
                ", bilheteValido=" + bilheteValido +
                '}';
    }

    /**
     * Primeiro dorme durante 100ms, tempo que simula a duracao entre entrar ou sair de uma estacao/comboio.
     * De seguida verifica se ele esta entrar num comboio ou estacao, caso esteja a entrar numa estacao
     * apenas e removido o passageiro da lista de passageiros no comboio, nao havendo necessidade de adicionar o passageiro a estacao,
     * se estiver a entrar num comboio, primeiro e removido da estacao e de seguida e adicionado ao comboio.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(this.isEstacao = true){
           Main.Comboios[indiceComboio].removePassageiro(this.nmrPassageiro);
        }else{
            Main.Estacoes[this.indiceEstacao].removePassageiro(this.nmrPassageiro);
            Passageiro add = new Passageiro();
            add.setEstacaoDestino(this.getEstacaoDestino());
            add.setNmrPassageiro(this.getNmrPassageiro());
            add.setBilheteValido(this.isBilheteValido());
            Main.Comboios[this.indiceComboio].addPassageiro(add);
        }
    }
}
