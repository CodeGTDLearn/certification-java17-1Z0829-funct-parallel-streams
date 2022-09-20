package reduce;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static datasource.LettersProvider.getStrings;

public class PS_Reduce_3Args {
  public static void main(String[] args) {

    Set<String> set = getStrings();

    String red3Args =
      set
        .stream()
        // Ident. + BiFunc. + Bin.Oper.
        .reduce("", String::concat, String::concat);
    show("Red 03 Args: " + red3Args);
  }

  private static void show(String text) {

    System.out.println(text);
  }
}