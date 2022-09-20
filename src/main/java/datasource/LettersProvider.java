package datasource;

import java.util.Set;
import java.util.TreeSet;

public class LettersProvider {
  public static Set<String> getStrings() {

    Set<String> set = new TreeSet<>(
         Set.of("b", "c", "d"));
    return set;
  }
}