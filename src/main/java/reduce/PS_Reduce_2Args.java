package reduce;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static datasource.LettersProvider.getStrings;

public class PS_Reduce_2Args {
  public static void main(String[] args) {

    Set<String> set = getStrings();

    String red2Args =
      set
        .stream()
        .reduce("", String::concat);// Ident. + Bin.Oper.
    show("Red 02 Args: " + red2Args);
  }

  private static void show(String text) {

    System.out.println(text);
  }
}