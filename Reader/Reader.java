package Reader;

import java.io.BufferedReader;
import java.io.FileReader;

public class Reader {
  public static void read() {
    String NOME_ARQUIVO = "pessoas.csv";
    String SEPARADOR = ",";

    try {
      FileReader arquivo = new FileReader(NOME_ARQUIVO);
      BufferedReader buffer = new BufferedReader(arquivo);
      String cabecalho = buffer.readLine();
      while (buffer.ready()) {
        String linha = buffer.readLine();
        String[] tokens = linha.split(SEPARADOR);
        System.out.println(tokens[0]);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
