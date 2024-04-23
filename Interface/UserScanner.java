import java.util.Scanner;

public class UserScanner {
  Scanner keyboard;

  public UserScanner() {
    this.keyboard = new Scanner(System.in);
  }

  public int getInt(String message) {
    int userInt = -1;
    boolean confirmed = false;

    do {
      try {
        System.out.print(message + " => ");
        userInt = this.keyboard.nextInt();
        confirmed = true;
      } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler o número! Tente novamente");
      }
    } while (!confirmed);

    return userInt;
  }

  public String getCpf(String message) {
    String userCpf = "";
    boolean confirmed = false;

    do {
      try {
        System.out.print(message + " => ");
        userCpf = this.keyboard.next();

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
      try {
        System.out.print(message + " => ");
        userDate = this.keyboard.next();

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
