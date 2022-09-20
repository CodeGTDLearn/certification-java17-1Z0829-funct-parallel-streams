package reduce;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class PS_Reduce_1Arg {
  public static void main(String[] args) {

    Set<String> set = new TreeSet<>(Set.of("b", "c", "d"));

    Optional<String> red1Arg =
      set
        .stream()
        // returns Optional
        .reduce(String::concat);
    String res1 = red1Arg.get();    show("Red 01 Arg: " + res1);
  }

  private static void show(String text) {

    System.out.println(text);
  }
}