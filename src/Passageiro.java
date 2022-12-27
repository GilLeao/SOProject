public class Passageiro {
    /**
     * Estação de Destino
     */
    String estacaoDestino;
    /**
     * Boolean que indica se o bilhete é válido
     */
    boolean bilheteValido;

    public String getEstacaoDestino() {
        return estacaoDestino;
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
}
