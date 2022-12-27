import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Estacao {
    /**
     * Nome da Estação
     */
    String nome;
    /**
     * Array de Passageiros na Estação
     */
    Passageiro[] Passageiros;
    /**
     * Nmr máximo de Comboios na estação
     */
    int nmrMaxComboios;
    /**
     * Numero de Comboios na estação
     */
    int nmrComboios;

    public Estacao(String nome,int nmrMaxComboios, int nmrComboios) {
        this.nome = nome;
        this.nmrMaxComboios = nmrMaxComboios;
        this.nmrComboios = nmrComboios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Passageiro[] getPassageiros() {
        return Passageiros;
    }

    public void setPassageiros(Passageiro[] passageiros) {
        Passageiros = passageiros;
    }

    public int getNmrMaxComboios() {
        return nmrMaxComboios;
    }

    public void setNmrMaxComboios(int nmrMaxComboios) {
        this.nmrMaxComboios = nmrMaxComboios;
    }

    public int getNmrComboios() {
        return nmrComboios;
    }

    public void setNmrComboios(int nmrComboios) {
        this.nmrComboios = nmrComboios;
    }

    public void escolherPassageirosRandom(Estacao[] Estacoes){
        for(int k = 0;k < 10;k++) {
            int numPassageiros = ThreadLocalRandom.current().nextInt(1, 10);
            Passageiro[] ListaPassageiros = new Passageiro[numPassageiros];
            for (int i = 0; i < numPassageiros; i++) {
                ListaPassageiros[i] = new Passageiro();
                do {
                    int indiceEstacao = ThreadLocalRandom.current().nextInt(0, 10);
                    ListaPassageiros[i].setEstacaoDestino(Estacoes[indiceEstacao].getNome());
                } while (this.getNome().equals(ListaPassageiros[i].getEstacaoDestino()));

            }
            Estacoes[k].setPassageiros(ListaPassageiros);
        }

    }

    @Override
    public String toString() {
        return "Estacao{" +
                "nome='" + nome + '\'' +
                ", Passageiros=" + Arrays.toString(Passageiros) +
                ", nmrMaxComboios=" + nmrMaxComboios +
                ", nmrComboios=" + nmrComboios +
                '}';
    }
}
