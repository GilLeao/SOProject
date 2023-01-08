public class Troco {
    /**
     * Nome da Estacao a que ele liga á sua esquerda
     */
    private String ligacao1;
    /**
     * Nome da Estação a que ele se liga á direita
     */
    private String ligacao2;
    /**
     * Sentido do Troço
     * False - L1 -> L2
     * True - L2 -> L1
     */
    private boolean sentido;



    public Troco(String ligacao1, String ligacao2, float distancia, int tempoViagem) {
        this.ligacao1 = ligacao1;
        this.ligacao2 = ligacao2;
    }

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



    @Override
    public String toString() {
        return "Troco{" +
                "ligacao1='" + ligacao1 + '\'' +
                ", ligacao2='" + ligacao2 + '\'' +
                ", sentido=" + sentido +
                '}';
    }
}
