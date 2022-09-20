package reduce;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PS_Reduce {
  public static void main(String[] args) {

    int tests = 10;

    List<Integer> list;

    double[] pAvgs = new double[tests];
    double[] sAvgs = new double[tests];
    int[] pFirsts = new int[tests];
    int[] sFirsts = new int[tests];
    int[] pAnys = new int[tests];
    int[] sAnys = new int[tests];

    list =
         Stream
              .iterate(1, (t) -> t <= 100000, (t) -> t + 1)
              .collect(Collectors.toList());

    for (int i = 0; i < tests; i++) {

    /*╔══════════════════════════════════════════════════════════╗
      ║                    Average Reduction                     ║
      ╠══════════════════════════════════════════════════════════╣
      ║ Deterministic - Predictable Order                        ║
      ║ works the same in parallel or serial                     ║
      ║ skip + limit 'order the Stream' resulting in same result ║
      ╚══════════════════════════════════════════════════════════╝*/
      OptionalDouble pAvg =
           list
                .parallelStream()
                .mapToInt((s) -> s)
                .skip(1000)
                .limit(50000)
                .average();
      pAvgs[i] = pAvg.getAsDouble();

      OptionalDouble sAvg =
           list
                .stream()
                .mapToInt((s) -> s)
                .skip(1000)
                .limit(50000)
                .average();
      sAvgs[i] = sAvg.getAsDouble();

    /*╔════════════════════════════════════════╗
      ║           FindAny Reduction            ║
      ╠════════════════════════════════════════╣
      ║ Non-Deterministic - Unpredictable Order║
      ╚════════════════════════════════════════╝*/
      // Non-Deterministic - Unpredictable Order
      Optional pAny =list.parallelStream().findAny();
      pAnys[i] = (Integer) pAny.get();
      Optional sAny =list.stream().findAny();
      sAnys[i] = (Integer) sAny.get();

    /*╔══════════════════════════════════════╗
      ║          FindFirst Reduction         ║
      ╠══════════════════════════════════════╣
      ║ Deterministic - Predictable Order    ║
      ║ works the same in parallel or serial ║
      ╚══════════════════════════════════════╝*/
      Optional pFirst =list.parallelStream().findFirst();
      pFirsts[i] = (Integer) pFirst.get();
      Optional sFirst = list.stream().findFirst();
      sFirsts[i] = (Integer) sFirst.get();

    }

    System.out.println("Average's: same in parallel or serial - Deterministic");
    System.out.println("P_Avgs: " + Arrays.toString(pAvgs));
    System.out.println("S_Avgs: " + Arrays.toString(sAvgs));
    System.out.println("\nFindFirst's: same in parallel or serial - Deterministic");
    System.out.println("P_First: " + Arrays.toString(pFirsts));
    System.out.println("S_First: " + Arrays.toString(sFirsts));
    System.out.println("\n FindAny's: Non-Deterministic [Unpredictable Order]");
    System.out.println("P_Anys: " + Arrays.toString(pAnys));
    System.out.println("S_Anys: " + Arrays.toString(sAnys));
  }
}