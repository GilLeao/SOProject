public class Logs
{
    String codigo;
    String indice1Comboio, indice2Comboio, indiceTroco, indiceParagem1Comboio, indiceParagem2Comboio;
    String indiceComboio, indiceParagemComboio;
    String indiceEstacao, indiceComboioConflito, nomeEstacao, hora;

    public Logs(String codigo, String indice1Comboio, String indice2Comboio, String indiceTroco, String indiceParagem1Comboio, String indiceParagem2Comboio, String indiceComboio, String indiceParagemComboio, String indiceEstacao, String indiceComboioConflito, String nomeEstacao, String hora) {
        this.codigo = codigo;
        this.indice1Comboio = indice1Comboio;
        this.indice2Comboio = indice2Comboio;
        this.indiceTroco = indiceTroco;
        this.indiceParagem1Comboio = indiceParagem1Comboio;
        this.indiceParagem2Comboio = indiceParagem2Comboio;
        this.indiceComboio = indiceComboio;
        this.indiceParagemComboio = indiceParagemComboio;
        this.indiceEstacao = indiceEstacao;
        this.indiceComboioConflito = indiceComboioConflito;
        this.nomeEstacao = nomeEstacao;
        this.hora = hora;
    }
}
