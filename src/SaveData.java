public class SaveData {
    public Comboio[] comboiosSave;
    public Estacao[] estacoesSave;

    public SaveData(Comboio[] comboios, Estacao[] Estacoes) {
        this.comboiosSave = comboios;
        this.estacoesSave = Estacoes;
    }

    public Comboio[] getComboiosSave() {
        return comboiosSave;
    }

    public void setComboiosSave(Comboio[] comboiosSave) {
        this.comboiosSave = comboiosSave;
    }

    public Estacao[] getEstacoesSave() {
        return estacoesSave;
    }

    public void setEstacoesSave(Estacao[] estacoesSave) {
        this.estacoesSave = estacoesSave;
    }
}
