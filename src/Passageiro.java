import java.util.Random;
import java.util.concurrent.Semaphore;

public class Passageiro implements Runnable {
    /**
     * Variavel do tipo Boolean que indica se o Passageiro vai entrar numa:
     * Estacao - Caso o valor seja <b>True</b>
     * Comboio - Caso o valor seja <b>False</b>
     */
    private boolean isEstacao;
    /**
     * Indice do Comboio em que o Passageiro vai entrar ou sair
     */
    private int indiceComboio;
    /**
     * Indice da Estacao em que o passageiro vai entrar ou sair;
     */
    private int indiceEstacao;

    /**
     * Numero de Passageiro, unico para cada passageiro
     */
    private int nmrPassageiro;

    /**
     * Estação de Destino
     */
    private String estacaoDestino;
    /**
     * Boolean que indica se o bilhete é válido
     */
    private boolean bilheteValido;

    Semaphore s;

    Passageiro(){
        Random rd = new Random(); // creating Random object
        this.bilheteValido = rd.nextBoolean();
    }

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

    public int getIndiceEstacao() {
        return indiceEstacao;
    }

    public void setIndiceEstacao(int indiceEstacao) {
        this.indiceEstacao = indiceEstacao;
    }

    public Semaphore getS() {
        return s;
    }

    public void setS(Semaphore s) {
        this.s = s;
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

                Main.SemaphoreEmbarquePassaegiros.acquire();
                try {
                    Thread.sleep((long) 2.78);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (this.isEstacao == true) {
                    Main.Comboios[indiceComboio].removePassageiro(this.nmrPassageiro);
                    System.out.println("--------------------------------------------------------------\nPASSAGEIRO NR " + this.getNmrPassageiro() + " SAIU DO COMBOIO NR " + this.indiceComboio + "\n--------------------------------------------------------------");
                } else {
                    Main.Estacoes[this.indiceEstacao].removePassageiro(this.nmrPassageiro);
                    Passageiro add = new Passageiro();
                    add.setEstacaoDestino(this.getEstacaoDestino());
                    add.setNmrPassageiro(this.getNmrPassageiro());
                    add.setBilheteValido(this.isBilheteValido());
                    if (add.bilheteValido == true) {
                        Main.Comboios[this.indiceComboio].addPassageiro(add);
                        System.out.println("--------------------------------------------------------------\nPASSAGEIRO NR " + this.getNmrPassageiro() + " ENTROU DO COMBOIO NR " + this.indiceComboio + "\n--------------------------------------------------------------");

                    } else {
                        System.out.println("--------------------------------------------------------------\nPASSAGEIRO NR " + this.getNmrPassageiro() + " NAO ENTROU DO COMBOIO NR " + this.indiceComboio + ".\nBILHETE INVALIDO!\n--------------------------------------------------------------");
                    }
                }
                Main.SemaphoreEmbarquePassaegiros.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
