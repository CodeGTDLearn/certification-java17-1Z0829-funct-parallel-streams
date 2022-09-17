import java.util.List;

public class P_Stream_Create {
  public static void main(String[] args) {

    List<Integer> list = DataProvider.getData();
    var CYCLES = 2;

    System.out.println("\nSerial Stream");
    for (int i = 0; i < CYCLES; i++) {
    list
         .stream()
         .forEach((s) -> System.out.print(s + " "));
      System.out.println();
    }

    System.out.println("\n.parallel() Stream: Interm.Operation");
    for (int i = 0; i < CYCLES; i++) {
      list
        .stream()
        .parallel()
        .forEach((s) -> System.out.print(s + " "));
      System.out.println();
    }

    System.out.println("\n.parallelStream() Stream: Source Operation");
    for (int i = 0; i < CYCLES; i++) {
      list
           .parallelStream()
           .forEach((s) -> System.out.print(s + " "));
      System.out.println();
    }

/*╔═════════════════════════════════════════════════╗
  ║   FOR_EACH_ORDERED - ORDER PARALLEL OPERATIONS  ║
  ╠═════════════════════════════════════════════════╣
  ║ HOWEVER performance from ParallelStream is gone ║
  ║ Although, operations prior the forEachOrdered   ║
  ║ can benefit from Parallel performance           ║
  ╚═════════════════════════════════════════════════╝*/
    System.out.println("\nParallelStream Ordered");
    for (int i = 0; i < CYCLES; i++) {
      list
           .parallelStream()
           .forEachOrdered((s) -> System.out.print(s + " "));
      System.out.println();
    }
  }
}