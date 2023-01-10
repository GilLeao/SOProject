import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class gestaoConflitos
{
    String codigo="";
    String indice1Comboio="", indice2Comboio="", indiceTroco="", indiceParagem1Comboio="", indiceParagem2Comboio="";
    String indiceComboio="", indiceParagemComboio="";
    String indiceEstacao="", indiceComboioConflito="", nomeEstacao="", hora="";
    List<Logs> logs = new ArrayList<>();

    gestaoConflitos()
    {

    }

    public void conflitos(Comboio[] comboios){
        for(int i = 0; i < logs.size();i++){
            if(logs.get(i).codigo.equals("1")){

            }else if(logs.get(i).codigo.equals("2")){

            }else{

            }
        }
    }

    public void lerTxt()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("src/info/log.txt"));
            String line;

            while ((line = reader.readLine()) != null)
            {
                //obter em cada linha
                String[] parts = line.split(";");

                codigo = parts[0];

                if(codigo.equals("1")) //Conflito no troço
                {
                    indice1Comboio = parts[1];
                    indice2Comboio = parts[2];
                    indiceTroco = parts[3];
                    indiceParagem1Comboio = parts[4];
                    indiceParagem2Comboio = parts[5];

                } else if(codigo.equals("2")) //Comboio excede a capacidade máxima de passageiros
                {
                    indiceComboio = parts[1];
                    indiceParagemComboio = parts[2];

                } else //Estação excede a capacidade máxima de comboios
                {
                    indiceEstacao = parts[1];
                    nomeEstacao = parts[2];
                    hora = parts[3];
                    indiceComboioConflito = parts[4];
                }

                Logs log = new Logs(codigo, indice1Comboio, indice2Comboio, indiceTroco, indiceParagem1Comboio, indiceParagem2Comboio, indiceComboio, indiceParagemComboio, indiceEstacao, indiceComboioConflito, nomeEstacao, hora);
                logs.add(log);

                /*System.out.println("gestaoConflitos {" +
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
                        '}');*/
            }

            reader.close();

            //imprimir todas as logs
            for(Logs log : logs)
            {
                System.out.print("gestaoConflitos {");
                System.out.print("codigo='"+log.codigo + "'");

                if(!log.indice1Comboio.equals(""))
                {
                    System.out.print(", indice1Comboio='" + log.indice1Comboio + "'");
                }

                if(!log.indice2Comboio.equals(""))
                {
                    System.out.print(", indice2Comboio='" + log.indice2Comboio + "'");
                }

                if(!log.indiceTroco.equals(""))
                {
                    System.out.print(", indiceTroco='" + log.indiceTroco + "'");
                }

                if(!log.indiceParagem1Comboio.equals(""))
                {
                    System.out.print(", indiceParagem1Comboio='" + log.indiceParagem1Comboio + "'");
                }

                if(!log.indiceParagem2Comboio.equals(""))
                {
                    System.out.print(", indiceParagem2Comboio='" + log.indiceParagem2Comboio + "'");
                }

                if(!log.indiceComboio.equals(""))
                {
                    System.out.print(", indiceComboio='" + log.indiceComboio + "'");
                }

                if(!log.indiceParagemComboio.equals(""))
                {
                    System.out.print(", indiceParagemComboio='" + log.indiceParagemComboio + "'");
                }

                if(!log.indiceEstacao.equals(""))
                {
                    System.out.print(", indiceEstacao='" + log.indiceEstacao + "'");
                }

                if(!log.indiceComboioConflito.equals(""))
                {
                    System.out.print(", indiceComboioConflito='" + log.indiceComboioConflito + "'");
                }

                if(!log.nomeEstacao.equals(""))
                {
                    System.out.print(", nomeEstacao='" + log.nomeEstacao + "'");
                }

                if(!log.hora.equals(""))
                {
                    System.out.print(", hora='" + log.hora + "'}");
                }

                System.out.print("\n");
            }

        } catch (IOException ex)
        {
            System.out.println("ERRO: "+ex.getMessage());
        }
    }
    public void resolverConflito(Logs log, Comboio[] comboios, Estacao[] estacaoes)
    {
        if(log.codigo.equals("1")) //Conflito no troço
        {
            int indiceComboio1 = Integer.parseInt(log.indice1Comboio);
            int indiceComboio2 = Integer.parseInt(log.indice2Comboio);
            int indiceParagem1 = Integer.parseInt(log.indiceParagem1Comboio);
            int indiceParagem2 = Integer.parseInt(log.indiceParagem2Comboio);
            if(comboios[indiceComboio1].getPassageiros().length > comboios[indiceComboio2].getPassageiros().length){
                comboios[indiceComboio2].setIndiceParagem(indiceParagem1);
            }else{
                comboios[indiceComboio1].setIndiceParagem(indiceParagem2);
            }
        }

        if(log.codigo.equals("2")) //Comboio excede a capacidade máxima de passageiros
        {
            int indiceComboioInt = Integer.parseInt(log.indiceComboio);
            do {
                comboios[indiceComboioInt].removePassageiro(comboios[indiceComboioInt].getPassageiros()[comboios[indiceComboioInt].getPassageiros().length].getNmrPassageiro());
            }while (comboios[indiceComboioInt].getNmrMaxPassageiros() < comboios[indiceComboioInt].getContadorPassageiros());
        }

        if(log.codigo.equals("3")) //Estação excede a capacidade máxima de comboios
        {
            int indiceComboioConflitoInt = Integer.parseInt(log.indiceComboioConflito);
            int indiceEstacaoInt = Integer.parseInt(log.indiceEstacao);
            comboios[indiceComboioConflitoInt].setIndiceParagem(comboios[indiceComboioConflitoInt].getIndiceParagem() - 1);
        }
    }
}


