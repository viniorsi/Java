import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String arquivoCSV = "3-9-3.csv";
        String arquivoTXT = "saida15.txt";

        String meta = "3.9";
        String indicador = "3.9.3";
        String insertBase = "INSERT INTO dado_indicador (cod_meta, cod_indicador, num_categoria, num_dado, vl_x, vl_y) VALUES ('%s', '%s', %s, %s, '%s', %s);";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "Cp1252"));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoTXT), "Cp1252"))) {
            String linha;
            int lineCount = 0;
            int insertCount = 0;

            List<String> valoresX = new ArrayList<>();

            while ((linha = br.readLine()) != null) {
                lineCount++;
                if (lineCount <= 2) continue;

                String[] colunas = linha.split(";");

                if (insertCount == 0) {
                    for (int i = 1; i < colunas.length; i++) {
                        valoresX.add(colunas[i]);
                    }
                    insertCount++;
                } else {
                    String categoriaId = null;
                    for (int i = 0; i < colunas.length; i++) {
                        if (i == 0) {
                            categoriaId = getCategoriaId(colunas[i]);
                        } else {
                            bw.write(String.format(insertBase, meta, indicador, categoriaId, insertCount, valoresX.get(i - 1), colunas[i].replaceAll("\"", "").replaceAll(",", ".")));
                            bw.newLine();
                            insertCount++;
                        }
                    }
                }

                /*
                bw.write(linha);
                bw.newLine();*/
            }

            for (String x : valoresX) {
                System.out.println(x);
            }

            System.out.println("Arquivo CSV foi lido e arquivo TXT em ANSI foi criado.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCategoriaId(String categoriaTxt) {
        switch (categoriaTxt) {
            case "Brasil":
                return "1";
            case "Norte":
                return "2";
            case "Sul":
                return "3";
            case "Nordeste":
                return "4";
            case "Sudeste":
                return "5";
            case "Centro-Oeste":
                return "6";
            case "Rond�nia":
                return "7";
            case "Acre":
                return "8";
            case "Amazonas":
                return "9";
            case "Roraima":
                return "10";
            case "Par�":
                return "11";
            case "Amap�":
                return "12";
            case "Tocantins":
                return "13";
            case "Maranh�o":
                return "14";
            case "Piau�":
                return "15";
            case "Cear�":
                return "16";
            case "Rio Grande do Norte":
                return "17";
            case "Para�ba":
                return "18";
            case "Pernambuco":
                return "19";
            case "Alagoas":
                return "20";
            case "Sergipe":
                return "21";
            case "Bahia":
                return "22";
            case "Minas Gerais":
                return "23";
            case "Esp�rito Santo":
                return "24";
            case "Rio de Janeiro":
                return "25";
            case "S�o Paulo":
                return "26";
            case "Paran�":
                return "27";
            case "Santa Catarina":
                return "28";
            case "Rio Grande do Sul":
                return "29";
            case "Mato Grosso do Sul":
                return "30";
            case "Mato Grosso":
                return "31";
            case "Goi�s":
                return "32";
            case "Distrito Federal":
                return "33";
            case "Regi�o Amaz�nica":
                return "34";
            default:
                return "";
        }
    }

}