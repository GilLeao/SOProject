import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class gestaoConflitos
{
    String codigo="", codigoTxt="";
    String indice1Comboio="", indice1ComboioTxt="", indice2Comboio="", indice2ComboioTxt="", indiceTroco="", indiceTrocoTxt="", indiceParagem1Comboio="", indiceParagem1ComboioTxt="", indiceParagem2Comboio="", indiceParagem2ComboioTxt="";
    String indiceComboio="", indiceComboioTxt="", indiceParagemComboio="", indiceParagemComboioTxt="";
    String indiceEstacao="", indiceComboioConflito="", nomeEstacao="", hora="", indiceEstacaoTxt="", indiceComboioConflitoTxt="", nomeEstacaoTxt="", horaTxt="";
    List<Logs> logs = new ArrayList<>();

    static boolean ultimaLinha = false;

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

    public void removerConflitoResolvido(String codigoConflitoRemover, String indice1ComboioConflitoRemover, String indice2ComboioConflitoRemover, String indiceTrocoConflitoRemover, String indiceParagem1ComboioConflitoRemover, String indiceParagem2ComboioConflitoRemover, String indiceComboioConflitoRemover, String indiceParagemComboioConflitoRemover, String indiceEstacaoConflitoRemover, String nomeEstacaoConflitoRemover, String horaConflitoRemover, String indiceComboioConflitoConflitoRemover)
    {
        try
        {
            logs = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("src/info/log.txt"));
            String line;
            StringBuilder novoConteudo = new StringBuilder();

            while ((line = reader.readLine()) != null)
            {
                codigoTxt = "";
                indice1ComboioTxt ="";
                indice2ComboioTxt = "";
                indiceTrocoTxt = "";
                indiceParagem1ComboioTxt ="";
                indiceParagem2ComboioTxt = "";
                indiceComboioTxt = "";
                indiceParagemComboioTxt = "";
                indiceEstacaoTxt = "";
                nomeEstacaoTxt = "";
                horaTxt = "";
                indiceComboioConflitoTxt = "";


                //obter em cada linha
                String[] parts = line.split(";");

                codigoTxt = parts[0];

                if(codigoTxt.equals("1")) //Conflito no troço
                {
                    indice1ComboioTxt = parts[1];
                    indice2ComboioTxt = parts[2];
                    indiceTrocoTxt = parts[3];
                    indiceParagem1ComboioTxt = parts[4];
                    indiceParagem2ComboioTxt = parts[5];

                    if(!codigoTxt.equals(codigoConflitoRemover) || !indice1ComboioTxt.equals(indice1ComboioConflitoRemover) || !indice2ComboioTxt.equals(indice2ComboioConflitoRemover) || !indiceTrocoTxt.equals(indiceTrocoConflitoRemover) || !indiceParagem1ComboioTxt.equals(indiceParagem1ComboioConflitoRemover) || !indiceParagem2ComboioTxt.equals(indiceParagem2ComboioConflitoRemover) || !indiceComboioTxt.equals(indiceComboioConflitoRemover) || !indiceParagemComboioTxt.equals(indiceParagemComboioConflitoRemover) || !indiceEstacaoTxt.equals(indiceEstacaoConflitoRemover) || !nomeEstacaoTxt.equals(nomeEstacaoConflitoRemover) || !horaTxt.equals(horaConflitoRemover) || !indiceComboioConflitoTxt.equals(indiceComboioConflitoConflitoRemover))
                    {
                        if(ultimaLinha)
                        {
                            String dados = codigoTxt+";"+indice1ComboioTxt+";"+indice2ComboioTxt+";"+indiceTrocoTxt+";"+indiceParagem1ComboioTxt+";"+indiceParagem2ComboioTxt;
                            novoConteudo.append(line + "\n");
                        }
                        else
                        {
                            String dados = codigoTxt+";"+indice1ComboioTxt+";"+indice2ComboioTxt+";"+indiceTrocoTxt+";"+indiceParagem1ComboioTxt+";"+indiceParagem2ComboioTxt;
                            novoConteudo.append(line);
                        }
                    }

                } else if(codigoTxt.equals("2")) //Comboio excede a capacidade máxima de passageiros
                {
                    indiceComboioTxt = parts[1];
                    indiceParagemComboioTxt = parts[2];

                    if(!codigoTxt.equals(codigoConflitoRemover) || !indice1ComboioTxt.equals(indice1ComboioConflitoRemover) || !indice2ComboioTxt.equals(indice2ComboioConflitoRemover) || !indiceTrocoTxt.equals(indiceTrocoConflitoRemover) || !indiceParagem1ComboioTxt.equals(indiceParagem1ComboioConflitoRemover) || !indiceParagem2ComboioTxt.equals(indiceParagem2ComboioConflitoRemover) || !indiceComboioTxt.equals(indiceComboioConflitoRemover) || !indiceParagemComboioTxt.equals(indiceParagemComboioConflitoRemover) || !indiceEstacaoTxt.equals(indiceEstacaoConflitoRemover) || !nomeEstacaoTxt.equals(nomeEstacaoConflitoRemover) || !horaTxt.equals(horaConflitoRemover) || !indiceComboioConflitoTxt.equals(indiceComboioConflitoConflitoRemover))
                    {
                        if(ultimaLinha)
                        {
                            String dados = codigoTxt+";"+indiceComboioTxt+";"+indiceParagemComboioTxt;
                            novoConteudo.append(line + "\n");

                        } else
                        {
                            String dados = codigoTxt+";"+indiceComboioTxt+";"+indiceParagemComboioTxt;
                            novoConteudo.append(line);
                        }
                    }

                } else //Estação excede a capacidade máxima de comboios
                {
                    indiceEstacaoTxt = parts[1];
                    nomeEstacaoTxt = parts[2];
                    horaTxt = parts[3];
                    indiceComboioConflitoTxt = parts[4];

                    if(!codigoTxt.equals(codigoConflitoRemover) || !indice1ComboioTxt.equals(indice1ComboioConflitoRemover) || !indice2ComboioTxt.equals(indice2ComboioConflitoRemover) || !indiceTrocoTxt.equals(indiceTrocoConflitoRemover) || !indiceParagem1ComboioTxt.equals(indiceParagem1ComboioConflitoRemover) || !indiceParagem2ComboioTxt.equals(indiceParagem2ComboioConflitoRemover) || !indiceComboioTxt.equals(indiceComboioConflitoRemover) || !indiceParagemComboioTxt.equals(indiceParagemComboioConflitoRemover) || !indiceEstacaoTxt.equals(indiceEstacaoConflitoRemover) || !nomeEstacaoTxt.equals(nomeEstacaoConflitoRemover) || !horaTxt.equals(horaConflitoRemover) || !indiceComboioConflitoTxt.equals(indiceComboioConflitoConflitoRemover))
                    {
                        if(ultimaLinha)
                        {
                            String dados = codigoTxt+";"+indiceEstacaoTxt+";"+nomeEstacaoTxt+";"+horaTxt+";"+indiceComboioConflitoTxt;
                            novoConteudo.append(line + "\n");
                        }
                        else
                        {
                            String dados = codigoTxt+";"+indiceEstacaoTxt+";"+nomeEstacaoTxt+";"+horaTxt+";"+indiceComboioConflitoTxt;
                            novoConteudo.append(line);
                        }


                    }
                }
            }

            reader.close();

            FileWriter fw = new FileWriter("src/info/log.txt");
            fw.write(novoConteudo.toString());
            fw.close();

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
                if(i == logs.size())
                {
                    ultimaLinha = true;
                }

                if(log.codigo.equals("1")) //Conflito no troço
                {
                    int indiceComboio1 = Integer.parseInt(log.indice1Comboio);
                    int indiceComboio2 = Integer.parseInt(log.indice2Comboio);
                    int indiceParagem1 = Integer.parseInt(log.indiceParagem1Comboio);
                    int indiceParagem2 = Integer.parseInt(log.indiceParagem2Comboio);

                    if(comboios[indiceComboio1].getPassageiros() != null && comboios[indiceComboio1].getPassageiros().length > comboios[indiceComboio2].getPassageiros().length)
                    {
                        LocalTime[] horariosChangeChegada = new LocalTime[comboios[indiceComboio1].getHorariosChegada().length];
                        LocalTime[] horariosChangeSaida = new LocalTime[comboios[indiceComboio1].getHorariosSaida().length];

                        horariosChangeChegada = comboios[indiceComboio1].getHorariosChegada();
                        horariosChangeSaida = comboios[indiceComboio1].getHorariosSaida();

                        horariosChangeChegada[indiceParagem1].plusSeconds(7);
                        horariosChangeSaida[indiceParagem1].plusSeconds(7);

                        comboios[indiceComboio1].setHorariosChegada(horariosChangeChegada);
                        comboios[indiceComboio1].setHorariosSaida(horariosChangeSaida);

                        //remover o conflito do txt
                        removerConflitoResolvido(log.codigo, log.indice1Comboio, log.indice2Comboio, log.indiceParagem1Comboio, log.indiceParagem2Comboio, "", "", "", "", "", "", "");
                        System.out.println("CONFLITO RESOLVIDO!");

                    }else{
                        LocalTime[] horariosChangeChegada = new LocalTime[comboios[indiceComboio2].getHorariosChegada().length];
                        LocalTime[] horariosChangeSaida = new LocalTime[comboios[indiceComboio2].getHorariosSaida().length];

                        horariosChangeChegada = comboios[indiceComboio2].getHorariosChegada();
                        horariosChangeSaida = comboios[indiceComboio2].getHorariosSaida();

                        horariosChangeChegada[indiceParagem2].plusSeconds(7);
                        horariosChangeSaida[indiceParagem2].plusSeconds(7);

                        comboios[indiceComboio2].setHorariosChegada(horariosChangeChegada);
                        comboios[indiceComboio2].setHorariosSaida(horariosChangeSaida);

                        //remover o conflito do txt
                        removerConflitoResolvido(log.codigo, log.indice1Comboio, log.indice2Comboio, log.indiceParagem1Comboio, log.indiceParagem2Comboio, "", "", "", "", "", "", "");
                        System.out.println("CONFLITO RESOLVIDO!");
                    }
                }

                if(log.codigo.equals("2")) //Comboio excede a capacidade máxima de passageiros
                {
                    int indiceComboioInt = Integer.parseInt(log.indiceComboio);

                    if(comboios[indiceComboioInt].getPassageiros() != null)
                    {
                        System.out.println("Nº MAX DE PASSAGEIROS NO COMBOIO: "+ comboios[indiceComboioInt].getNmrMaxPassageiros());
                        System.out.println("ANTES (remover passageiro do comboio): "+ Arrays.toString(comboios[indiceComboioInt].getPassageiros()));

                        do
                        {
                            //comboios[indiceComboioInt].removePassageiro(comboios[indiceComboioInt].getPassageiros()[comboios[indiceComboioInt].getPassageiros().length].getNmrPassageiro());
                            int nPassageiros = comboios[indiceComboioInt].getPassageiros().length - 1;
                            int idPassageiro = comboios[indiceComboioInt].getPassageiros()[nPassageiros].getNmrPassageiro();

                            comboios[indiceComboioInt].removePassageiro(idPassageiro);
                        } while (comboios[indiceComboioInt].getNmrMaxPassageiros() < comboios[indiceComboioInt].getContadorPassageiros());

                        System.out.println("DEPOIS (remover passageiro do comboio): "+ Arrays.toString(comboios[indiceComboioInt].getPassageiros()));

                        //remover o conflito do txt
                        removerConflitoResolvido(log.codigo, "", "", "", "", "", log.indiceComboio, log.indiceParagemComboio, "", "", "", "");
                        System.out.println("CONFLITO RESOLVIDO!");
                    }
                }

                if(log.codigo.equals("3")) //Estação excede a capacidade máxima de comboios
                {
                    int indiceComboioConflitoInt = Integer.parseInt(log.indiceComboioConflito);
                    int indiceEstacaoInt = Integer.parseInt(log.indiceEstacao);

                    if(comboios[indiceComboioConflitoInt].getHorariosChegada() != null && comboios[indiceComboioConflitoInt].getHorariosSaida() != null)
                    {
                        LocalTime[] horariosChangeChegada = new LocalTime[comboios[indiceComboioConflitoInt].getHorariosChegada().length];
                        LocalTime[] horariosChangeSaida = new LocalTime[comboios[indiceComboioConflitoInt].getHorariosSaida().length];

                        System.out.println("Nº MAX DE COMBOIOS NA ESTACAO: "+ estacaoes[indiceEstacaoInt].getNmrMaxComboios());
                        System.out.println("ANTES (remover comboio da estacao): "+ estacaoes[indiceEstacaoInt].getNmrComboios());

                        do
                        {
                            horariosChangeChegada = comboios[indiceComboioConflitoInt].getHorariosChegada();
                            horariosChangeSaida = comboios[indiceComboioConflitoInt].getHorariosSaida();

                            horariosChangeChegada[indiceEstacaoInt].plusSeconds(7);
                            horariosChangeSaida[indiceEstacaoInt].plusSeconds(7);

                            comboios[indiceComboioConflitoInt].setHorariosChegada(horariosChangeChegada);
                            comboios[indiceComboioConflitoInt].setHorariosSaida(horariosChangeSaida);

                        } while (estacaoes[indiceEstacaoInt].getNmrMaxComboios() < estacaoes[indiceEstacaoInt].getNmrComboios());

                        System.out.println("DEPOIS (remover comboio da estacao): "+ estacaoes[indiceEstacaoInt].getNmrComboios());

                        //remover conflito do txt
                        removerConflitoResolvido(codigo, "", "", "", "", "", "", "", log.indiceEstacao, log.nomeEstacao, log.hora, log.indiceComboioConflito);
                        System.out.println("CONFLITO RESOLVIDO!");
                    }
                }
            }

            i++;
        }
    }
}


