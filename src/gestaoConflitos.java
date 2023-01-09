import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class gestaoConflitos
{
    String codigo;
    String indice1Comboio, indice2Comboio, indiceTroco, indiceParagem1Comboio, indiceParagem2Comboio;
    String indiceComboio, indiceParagemComboio;
    String indiceEstacao, indiceComboioConflito, nomeEstacao, hora;

    gestaoConflitos()
    {
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

    public void lerTxt()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("src/info/log.txt"));
            String line;
            gestaoConflitos log = new gestaoConflitos();

            while ((line = reader.readLine()) != null)
            {
                //obter em cada linha
                String[] parts = line.split(";");
                log.setCodigo(parts[0]);

                log.setIndice1Comboio("");
                log.setIndice2Comboio("");
                log.setIndiceTroco("");
                log.setIndiceParagem1Comboio("");
                log.setIndiceParagem2Comboio("");
                log.setIndiceComboio("");
                log.setIndiceParagemComboio("");
                log.setIndiceEstacao("");
                log.setNomeEstacao("");
                log.setHora("");
                log.setIndiceComboioConflito("");


                if((log.getCodigo()).equals("1")) //Conflito no troço
                {
                    log.setIndice1Comboio(parts[1]);
                    log.setIndice2Comboio(parts[2]);
                    log.setIndiceTroco(parts[3]);
                    log.setIndiceParagem1Comboio(parts[4]);
                    log.setIndiceParagem2Comboio(parts[5]);

                } else if((log.getCodigo()).equals("2")) //Comboio excede a capacidade máxima de passageiros
                {
                    log.setIndiceComboio(parts[1]);
                    log.setIndiceParagemComboio(parts[2]);

                } else //Estação excede a capacidade máxima de comboios
                {
                    log.setIndiceEstacao(parts[1]);
                    log.setNomeEstacao(parts[2]);
                    log.setHora(parts[3]);
                    log.setIndiceComboioConflito(parts[4]);
                }

                System.out.println("gestaoConflitos {" +
                "codigo='" + log.getCodigo() + '\'' +
                        ", indice1Comboio='" + log.getIndice1Comboio() + '\'' +
                        ", indice2Comboio='" + log.getIndiceComboio() + '\'' +
                        ", indiceTroco='" + log.getIndiceTroco() + '\'' +
                        ", indiceParagem1Comboio='" + log.getIndiceParagem1Comboio() + '\'' +
                        ", indiceParagem2Comboio='" + log.getIndiceParagem2Comboio() + '\'' +
                        ", indiceComboio='" + log.getIndiceComboio() + '\'' +
                        ", indiceParagemComboio='" + log.getIndiceParagemComboio() + '\'' +
                        ", indiceEstacao='" + log.getIndiceEstacao() + '\'' +
                        ", indiceComboioConflito='" + log.getIndiceComboioConflito() + '\'' +
                        ", nomeEstacao='" + log.getNomeEstacao() + '\'' +
                        ", hora='" + log.getHora() + '\'' +
                        '}');
            }

            reader.close();

        } catch (IOException ex)
        {
            System.out.println("ERRO: "+ex.getMessage());
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIndice1Comboio() {
        return indice1Comboio;
    }

    public void setIndice1Comboio(String indice1Comboio) {
        this.indice1Comboio = indice1Comboio;
    }

    public String getIndice2Comboio() {
        return indice2Comboio;
    }

    public void setIndice2Comboio(String indice2Comboio) {
        this.indice2Comboio = indice2Comboio;
    }

    public String getIndiceTroco() {
        return indiceTroco;
    }

    public void setIndiceTroco(String indiceTroco) {
        this.indiceTroco = indiceTroco;
    }

    public String getIndiceParagem1Comboio() {
        return indiceParagem1Comboio;
    }

    public void setIndiceParagem1Comboio(String indiceParagem1Comboio) {
        this.indiceParagem1Comboio = indiceParagem1Comboio;
    }

    public String getIndiceParagem2Comboio() {
        return indiceParagem2Comboio;
    }

    public void setIndiceParagem2Comboio(String indiceParagem2Comboio) {
        this.indiceParagem2Comboio = indiceParagem2Comboio;
    }

    public String getIndiceComboio() {
        return indiceComboio;
    }

    public void setIndiceComboio(String indiceComboio) {
        this.indiceComboio = indiceComboio;
    }

    public String getIndiceParagemComboio() {
        return indiceParagemComboio;
    }

    public void setIndiceParagemComboio(String indiceParagemComboio) {
        this.indiceParagemComboio = indiceParagemComboio;
    }

    public String getIndiceEstacao() {
        return indiceEstacao;
    }

    public void setIndiceEstacao(String indiceEstacao) {
        this.indiceEstacao = indiceEstacao;
    }

    public String getIndiceComboioConflito() {
        return indiceComboioConflito;
    }

    public void setIndiceComboioConflito(String indiceComboioConflito) {
        this.indiceComboioConflito = indiceComboioConflito;
    }

    public String getNomeEstacao() {
        return nomeEstacao;
    }

    public void setNomeEstacao(String nomeEstacao) {
        this.nomeEstacao = nomeEstacao;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "gestaoConflitos {" +
                "codigo='" + codigo + '\'' +
                ", indice1Comboio='" + indice1Comboio + '\'' +
                ", indice2Comboio='" + indice2Comboio + '\'' +
                ", indiceTroco='" + indiceTroco + '\'' +
                ", indiceParagem1Comboio='" + indiceParagem1Comboio + '\'' +
                ", indiceParagem2Comboio='" + indiceParagem2Comboio + '\'' +
                ", indiceComboio='" + indiceComboio + '\'' +
                ", indiceParagemComboio='" + indiceParagemComboio + '\'' +
                ", indiceEstacao='" + indiceEstacao + '\'' +
                ", indiceComboioConflito='" + indiceComboioConflito + '\'' +
                ", nomeEstacao='" + nomeEstacao + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}


