import java.util.ArrayList;
import java.util.List;

public class Parall_Stream_Create {
  public static void main(String[] args) {

    List<Integer> list = DataProvider.getData();

    System.out.println("Serial Stream");
    for (int i = 0; i < 3; i++) {
    list
         .stream()
         .forEach((s) -> System.out.print(s + " "));
      System.out.println();
    }

    System.out.println("\n----- parallel -----");
    for (int i = 0; i < 3; i++) {
      list
           .stream()
           .parallel()
           .forEach((s) -> System.out.print(s + " "));
      System.out.println();
    }

    System.out.println("\n----- parallelStream -----");
    for (int i = 0; i < 3; i++) {
      list
           .parallelStream()
           .forEach((s) -> System.out.print(s + " "));
      System.out.println();
    }

    /*╔═════════════════════════════════════════════════╗
      ║                 FOR_EACH_ORDERED                ║
      ╠═════════════════════════════════════════════════╣
      ║ Force order in Parallel                         ║
      ║ But any performance from ParallelStream is gone ║
      ║ But performance remain from previous operations ║
      ╚═════════════════════════════════════════════════╝*/
    System.out.println("\n----- parallelStream Ordered -----");
    for (int i = 0; i < 3; i++) {
      list
           .parallelStream()
           .forEachOrdered((s) -> System.out.print(s + " "));
      System.out.println();
    }

    /*╔═══════════════════════════════════════════════════════════╗
      ║                 STATEFULL LAMBDA OR METHOD                ║
      ╠═══════════════════════════════════════════════════════════╣
      ║ Lambda CANNOT change the state of a 'outside'             ║
      ║ variable - sort of IFR                                    ║
      ║ Stateful lambda result dependn of a outside state-element ║
      ║ 'Exception ... java.util.ConcurrentModificationException' ║
      ╚═══════════════════════════════════════════════════════════╝*/
    list
     .parallelStream()
           .map((s) -> {
             list.add(s);
             return s;
           })
           .forEachOrdered((s) -> System.out.print(s + " "));


    //  Create parallel stream from integerList using parallelStream() method
    System.out.println("----- Using stateful lambda expression -----");
      List<Integer> unsynchList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      unsynchList.clear();
      list
        .parallelStream()
        .forEach((s) -> {
          unsynchList.add(0, s);
        });
      System.out.println(unsynchList);

    }
  }
}