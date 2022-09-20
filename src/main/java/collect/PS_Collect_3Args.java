package collect;

import java.util.Set;
import java.util.TreeSet;

import static datasource.LettersProvider.getStrings;

public class PS_Collect_3Args {
  public static void main(String[] args) {

    Set<String> set = getStrings();

    String col3Arg =
         set
              .stream()
              // Supplier + BiConsumer + BiConsumer
              .collect(String::new, String::concat, String::concat);
    show("Col 03 Args: " + col3Arg);
  }

  private static void show(String text) {

    System.out.println(text);
  }
}