import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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
            }

            reader.close();

            //imprimir todas as logs
            int i=1;
            for(Logs log : logs)
            {
                System.out.print(i+". ");
                System.out.print("gestaoConflitos {");
                System.out.print("codigo='"+log.codigo + "'");

                switch (log.codigo)
                {
                    case "1":
                        System.out.print(", indice1Comboio='" + log.indice1Comboio + "'");
                        System.out.print(", indice2Comboio='" + log.indice2Comboio + "'");
                        System.out.print(", indiceTroco='" + log.indiceTroco + "'");
                        System.out.print(", indiceParagem1Comboio='" + log.indiceParagem1Comboio + "'");
                        System.out.print(", indiceParagem2Comboio='" + log.indiceParagem2Comboio + "'}");

                        i++;
                        break;

                    case "2":
                        System.out.print(", indiceComboio='" + log.indiceComboio + "'");
                        System.out.print(", indiceParagemComboio='" + log.indiceParagemComboio + "'}");

                        i++;
                        break;

                    case "3":
                        System.out.print(", indiceEstacao='" + log.indiceEstacao + "'");
                        System.out.print(", indiceComboioConflito='" + log.indiceComboioConflito + "'");
                        System.out.print(", nomeEstacao='" + log.nomeEstacao + "'");
                        System.out.print(", hora='" + log.hora + "'}");

                        i++;
                        break;
                }
                System.out.print("\n");
            }

        } catch (IOException ex)
        {
            System.out.println("ERRO: "+ex.getMessage());
        }
    }
    public void resolverConflito(int option, Comboio[] comboios, Estacao[] estacaoes)
    {
        //imprimir todas as logs
        int i=1;
        for(Logs log : logs)
        {
            if(i == option) //entra na log que quer resolver
            {
                if(log.codigo.equals("1")) //Conflito no troço
                {
                    int indiceComboio1 = Integer.parseInt(log.indice1Comboio);
                    int indiceComboio2 = Integer.parseInt(log.indice2Comboio);
                    int indiceParagem1 = Integer.parseInt(log.indiceParagem1Comboio);
                    int indiceParagem2 = Integer.parseInt(log.indiceParagem2Comboio);

                    if(comboios[indiceComboio1].getPassageiros().length > comboios[indiceComboio2].getPassageiros().length)
                    {
                        LocalTime[] horariosChangeChegada = new LocalTime[comboios[indiceComboio1].getHorariosChegada().length];
                        LocalTime[] horariosChangeSaida = new LocalTime[comboios[indiceComboio1].getHorariosSaida().length];

                        horariosChangeChegada = comboios[indiceComboio1].getHorariosChegada();
                        horariosChangeSaida = comboios[indiceComboio1].getHorariosSaida();

                        horariosChangeChegada[indiceParagem1].plusSeconds(7);
                        horariosChangeSaida[indiceParagem1].plusSeconds(7);

                        comboios[indiceComboio1].setHorariosChegada(horariosChangeChegada);
                        comboios[indiceComboio1].setHorariosSaida(horariosChangeSaida);

                    }else{
                        LocalTime[] horariosChangeChegada = new LocalTime[comboios[indiceComboio2].getHorariosChegada().length];
                        LocalTime[] horariosChangeSaida = new LocalTime[comboios[indiceComboio2].getHorariosSaida().length];

                        horariosChangeChegada = comboios[indiceComboio2].getHorariosChegada();
                        horariosChangeSaida = comboios[indiceComboio2].getHorariosSaida();

                        horariosChangeChegada[indiceParagem2].plusSeconds(7);
                        horariosChangeSaida[indiceParagem2].plusSeconds(7);

                        comboios[indiceComboio2].setHorariosChegada(horariosChangeChegada);
                        comboios[indiceComboio2].setHorariosSaida(horariosChangeSaida);
                    }
                }

                if(log.codigo.equals("2")) //Comboio excede a capacidade máxima de passageiros
                {
                    int indiceComboioInt = Integer.parseInt(log.indiceComboio);

                    do
                    {
                        System.out.println("ANTES (remover passageiro do comboio): "+ Arrays.toString(comboios[indiceComboioInt].getPassageiros()));
                        //comboios[indiceComboioInt].removePassageiro(comboios[indiceComboioInt].getPassageiros()[comboios[indiceComboioInt].getPassageiros().length].getNmrPassageiro());
                        int nPassageiros = comboios[indiceComboioInt].getPassageiros().length - 1;
                        int idPassageiro = comboios[indiceComboioInt].getPassageiros()[nPassageiros].getNmrPassageiro();

                        comboios[indiceComboioInt].removePassageiro(idPassageiro);


                        System.out.println("DEPOIS (remover passageiro do comboio): "+ Arrays.toString(comboios[indiceComboioInt].getPassageiros()));
                    } while (comboios[indiceComboioInt].getNmrMaxPassageiros() < comboios[indiceComboioInt].getContadorPassageiros());
                }

                if(log.codigo.equals("3")) //Estação excede a capacidade máxima de comboios
                {
                    int indiceComboioConflitoInt = Integer.parseInt(log.indiceComboioConflito);
                    int indiceEstacaoInt = Integer.parseInt(log.indiceEstacao);
                    LocalTime[] horariosChangeChegada = new LocalTime[comboios[indiceComboioConflitoInt].getHorariosChegada().length];
                    LocalTime[] horariosChangeSaida = new LocalTime[comboios[indiceComboioConflitoInt].getHorariosSaida().length];

                    horariosChangeChegada = comboios[indiceComboioConflitoInt].getHorariosChegada();
                    horariosChangeSaida = comboios[indiceComboioConflitoInt].getHorariosSaida();

                    horariosChangeChegada[indiceEstacaoInt].plusSeconds(7);
                    horariosChangeSaida[indiceEstacaoInt].plusSeconds(7);

                    comboios[indiceComboioConflitoInt].setHorariosChegada(horariosChangeChegada);
                    comboios[indiceComboioConflitoInt].setHorariosSaida(horariosChangeSaida);
                }
            }

            i++;
        }
    }
}


