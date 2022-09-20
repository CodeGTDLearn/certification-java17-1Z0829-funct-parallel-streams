package collect;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static datasource.LettersProvider.getStrings;

public class PS_Collect_1Arg {
  public static void main(String[] args) {

    Set<String> set = getStrings();

    String col1Arg =
         set
              .stream()
              // Collector argument
              .collect(Collectors.joining());
    show("Col 01 Arg: " + col1Arg);


  }

  private static void show(String text) {

    System.out.println(text);
  }
}