import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static datasource.NamesProvider.getOrderedNames;

public class PS_Reduce_Strings {
  public static void main(String[] args) {

    ArrayList<String> a;
    String[] names = getOrderedNames();

    /*╔══════════════════════════════════════════════╗
      ║            Serial - Deterministic            ║
      ╠══════════════════════════════════════════════╣
      ║1) Serial Stream:                             ║
      ║   - follow the 'Source Order'                ║
      ║2) Reduce:                                    ║
      ║   - Identity (,) is not used between elements║
      ║   - follow the 'Stream Order'                ║
      ╚══════════════════════════════════════════════╝*/
    String result =
         Arrays.stream(names)
               .peek(System.out::print)
               .reduce(",", (s1, s2) -> s1 + s2);

    show(":\n\tSerial|String concat: " + result);

    /*╔═══════════════════════════════════════════════════════╗
      ║               Parallel - UnDeterministic              ║
      ╠═══════════════════════════════════════════════════════╣
      ║1) Parallel Stream:                                    ║
      ║   - Suggests to mult-threading to CPU (not guarantted)║
      ║   - DO-NOT follow the 'Source Order'                  ║
      ║2) Reduce:                                             ║
      ║   - Identity (,) MAY be used between elements         ║
      ║   - follow the 'Source Order'                         ║
      ╚═══════════════════════════════════════════════════════╝*/
    result =
         Arrays.stream(names)
               .parallel()
               .peek(System.out::print)
               .reduce(",", (s1, s2) -> s1 + s2);
    show(":\n\tParallel|String concat: " + result);

    /*╔═════════════════════════════════════╗
      ║  Set - Parallel - UnDeterministic   ║
      ╠═════════════════════════════════════╣
      ║1) Set Parallel Stream:              ║
      ║   - DO-NOT follow the 'Source Order'║
      ║2) Reduce:                           ║
      ║   - Identity (_) is used            ║
      ║   - DO-NOT follow the 'Stream Order'║
      ╚═════════════════════════════════════╝*/
    result =
         Set.of(names)
            .stream()
            .parallel()
            .peek(System.out::print)
            .reduce(
                 "",
                 (s1, s2) -> s1 + s2,
                 (s1, s2) -> s1 + "_" + s2);

    show("::\n\tParallel|String concat: " + result);

  }

  private static void show(String text) {

    System.out.println(text + "\n");
  }


}