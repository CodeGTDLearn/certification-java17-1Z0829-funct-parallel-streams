package datasource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerProvider {
  public static List<Integer> getData() {

    List<Integer> integerList =
         Stream
              .iterate(
                   1, (t) -> t <= 5, (t) -> t + 1)
              .collect(Collectors.toList());

    return integerList;
  }
}