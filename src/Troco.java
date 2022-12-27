public class Troco {
    /**
     * Nome da Estacao a que ele liga á sua esquerda
     */
    String ligacao1;
    /**
     * Nome da Estação a que ele se liga á direita
     */
    String ligacao2;
    /**
     * Sentido do Troço
     * 0 - Esquerda
     * 1 - Direita
     */
    boolean sentido;
    /**
     * Tamanho do troço
     */
    float distancia;
    /**
     * Duração da viagem em Milisegundos
     */
    int tempoViagem;

    public String getLigacao1() {
        return ligacao1;
    }

    public void setLigacao1(String ligacao1) {
        this.ligacao1 = ligacao1;
    }

    public String getLigacao2() {
        return ligacao2;
    }

    public void setLigacao2(String ligacao2) {
        this.ligacao2 = ligacao2;
    }

    public boolean isSentido() {
        return sentido;
    }

    public void setSentido(boolean sentido) {
        this.sentido = sentido;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public int getTempoViagem() {
        return tempoViagem;
    }

    public void setTempoViagem(int tempoViagem) {
        this.tempoViagem = tempoViagem;
    }
}
