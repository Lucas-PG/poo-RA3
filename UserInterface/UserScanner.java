package UserInterface;

import java.util.Scanner;

@SuppressWarnings("resource")
public class UserScanner {
  public UserScanner() {
  }

  public int getInt(String message) {
    int userInt = -1;
    boolean confirmed = false;

    do {
      Scanner keyboard = new Scanner(System.in);

      try {
        System.out.print(message + " => ");
        userInt = keyboard.nextInt();

        if (userInt < 0) {
          throw new Exception("O número não pode ser negativo");
        }

        confirmed = true;
      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler o número! Tente novamente");
        System.out.println(e.getMessage());
      }
    } while (!confirmed);

    return userInt;
  }

  public String getCpf(String message) {
    String userCpf = "";
    boolean confirmed = false;

    do {
      Scanner keyboard = new Scanner(System.in);

      try {
        System.out.print(message + " => ");
        userCpf = keyboard.next();

        if (userCpf.length() < 11) {
          throw new Exception("O cpf está incompleto");
        }

        confirmed = true;
      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler o cpf! Tente novamente");
        System.out.println(e.getMessage());
      }
    } while (!confirmed);

    return userCpf;
  }

  public String getDate(String message) {
    String userDate = "";
    boolean confirmed = false;
    String datePattern = "\\d{4}-\\d{2}-\\d{2}";

    do {
      Scanner keyboard = new Scanner(System.in);

      try {
        System.out.print(message + " (YYYY-MM-DD) => ");
        userDate = keyboard.next();

        if (userDate.length() < 10) {
          throw new Exception("A data está incompleta");
        }

        if (!userDate.matches(datePattern)) {
          throw new Exception("A data deve estar no devido formato: YYYY-MM-DD");
        }

        confirmed = true;
      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler a data! Tente novamente");
        System.out.println(e.getMessage());
      }
    } while (!confirmed);

    return userDate;
  }
}
