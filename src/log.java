import java.io.FileWriter;
import java.io.IOException;

public class log {

    /**
     * Apresenta um LOG dos problemas encontrados durante a simulacao
     *
     * Codigos de Log:
     * 1 - Conflito no troço
     * 2 - Comboio excede capacidade maxima
     * 3 - Estacao armazena demasiados comboios
     *
     * @param Comboios
     * @param Estacoes
     */
    public log(Comboio[] Comboios, Estacao[] Estacoes){
        try (FileWriter Ficheiro = new FileWriter("src/info/log.txt", true)) {
            int[] indiceTrocos = new int[Comboios.length];
            for(int i = 0;i < Comboios.length;i++){
                indiceTrocos[i] = Comboios[i].getIndiceTroco();
            }

            for(int i = 0; i < Comboios.length - 1;i++) {
                for (int k = i + 1; k < Comboios.length; k++) {
                    if (indiceTrocos[i] == indiceTrocos[k]) {
                        //CODIGO;INDICE PRIMEIRO COMBOIO;INDICE SEGUNDO COMBOIO; INDICE DO TROCO;INDICE DA PARAGEM DO COMBOIO 1; INDICE DA PARAGEM DO COMBOIO 2;
                        Ficheiro.write("\n1;" + i + ";" + k + ";" + Comboios[i].getIndiceTroco() + ";" + Comboios[i].getIndiceParagem() + ";" + Comboios[k].getIndiceParagem());
                    }
                }
            }

            for(int i = 0; i < Comboios.length;i++){
                if(Comboios[i].getNmrMaxPassageiros() < Comboios[i].getContadorPassageiros()){
                    String NomeEstacao = Estacoes[i].getNome();
                    for(int k = 0; k < Comboios.length;k++){
                        String[] Paragens = Comboios[k].getParagens();
                        String ParagemAtual = Paragens[Comboios[i].getIndiceParagem()];
                    }
                    //CODIGO;INDICE COMBOIO;INDICE PARAGEM DO COMBOIO;
                    Ficheiro.write("\n2;" + i + ";" + Comboios[i].getIndiceParagem());
                }
            }

            for(int i = 0; i < Estacoes.length;i++){
                if(Estacoes[i].getNmrComboios() > Estacoes[i].getNmrMaxComboios()){
                    int indiceParagens = -1;
                    int indice = 0;
                    for(int k = 0; k < Comboios.length; k++){
                        String[] Paragens = Comboios[k].getParagens();
                        if(Paragens[Comboios[k].getIndiceParagem()].equals(Estacoes[i].getNome())){
                            indiceParagens = k;

                        }
                    }
                    //CODIGO;INDICE ESTACAO;NOME DA ESTACAO;INDICE EM QUE ACONTECE O CONFLITO
                    Ficheiro.write("\n3;" + i + ";" + Estacoes[i].getNome() + ";" + indiceParagens);
                }
                Ficheiro.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
