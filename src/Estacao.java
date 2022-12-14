import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Estacao {
    /**
     * Nome da Estação
     */
    private String nome;
    /**
     * Array de Passageiros na Estação
     */
    private Passageiro[] Passageiros;
    /**
     * Nmr máximo de Comboios na estação
     */
    private int nmrMaxComboios;
    /**
     * Numero de Comboios na estação
     */
    private int nmrComboios;

    embarquePassageiros embarque;

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

    public void NmrComboiosNaEstacao(Comboio[] comboios ){
        int nmrComboios = 0;
        for(int i = 0; i < comboios.length; i++){
            String[] paragens = comboios[i].getParagens();
            if(paragens[comboios[i].getIndiceParagem()].equals(this.nome)){
                nmrComboios++;
            }
        }
        this.nmrComboios = nmrComboios;
    }

    public int getNmrComboios() {
        return nmrComboios;
    }

    public void setNmrComboios(int nmrComboios) {
        this.nmrComboios = nmrComboios;
    }

    public void addPassageiro(Passageiro passageiro){
        Passageiro[] PassageirosNovo = new Passageiro[this.Passageiros.length];
        for(int i = 0 ; i < this.Passageiros.length;i++){
            PassageirosNovo[i] = new Passageiro();
            PassageirosNovo[i] = this.Passageiros[i];
        }
        PassageirosNovo[this.Passageiros.length] = passageiro;

        this.setPassageiros(PassageirosNovo);

    }



    public void escolherPassageirosRandom(Estacao[] Estacoes){
        int nmrPassageiro = 0;
        for(int k = 0;k < 10;k++) {
            int numPassageiros = ThreadLocalRandom.current().nextInt(1, 10);
            Passageiro[] ListaPassageiros = new Passageiro[numPassageiros];
            for (int i = 0; i < numPassageiros; i++) {
                ListaPassageiros[i] = new Passageiro();
                do {
                    int indiceEstacao = ThreadLocalRandom.current().nextInt(0, 10);
                    ListaPassageiros[i].setEstacaoDestino(Estacoes[indiceEstacao].getNome());
                } while (this.getNome().equals(ListaPassageiros[i].getEstacaoDestino()));
                ListaPassageiros[i].setNmrPassageiro(nmrPassageiro);
                nmrPassageiro++;
            }
            Estacoes[k].setPassageiros(ListaPassageiros);
        }

    }



    private boolean findPassageiro(int nmrPassageiro){
        for(int i = 0; i < this.Passageiros.length; i++){
            if (this.Passageiros[i].getNmrPassageiro() == nmrPassageiro) {
                return true;
            }
        }
        return false;
    }

    public void removePassageiro(int nmrPassageiro){
        Passageiro[] PassageirosNovos;
        int indiceFor = 0;
        if(findPassageiro(nmrPassageiro)) {
            if (this.Passageiros != null) {
                PassageirosNovos = new Passageiro[this.Passageiros.length - 1];
                for (int i = 0; i < this.Passageiros.length; i++) {
                    if (this.Passageiros[i].getNmrPassageiro() != nmrPassageiro) {
                        PassageirosNovos[indiceFor] = new Passageiro();
                        PassageirosNovos[indiceFor] = this.Passageiros[i];
                        indiceFor++;
                    }
                }
                this.setPassageiros(PassageirosNovos);
            }
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
