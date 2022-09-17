import java.util.ArrayList;
import java.util.List;

public class P_Stream_State {
  public static void main(String[] args) {

    List<Integer> stateFullList = DataProvider.getData();
    var CYCLES = 3;

    System.out.println("Serial Stream: External Lambda Change");
    List<Integer> externalList1 = new ArrayList<>();
    for (int i = 0; i < CYCLES; i++) {
      externalList1.clear();
      stateFullList
           .stream()
           .forEach((s) -> externalList1.add(0, s));
      System.out.println(externalList1);
    }

    System.out.println("\nParallel Stream: External Lambda Change");
    List<Integer> externalList2 = new ArrayList<>();
    for (int i = 0; i < CYCLES; i++) {
      externalList2.clear();
      stateFullList
           .parallelStream()
           .forEach((s) -> externalList2.add(0, s));
      System.out.println(externalList2);
    }

/*╔══════════════════════════════════════════════════════════╗
  ║        INTERFERENCE - STATEFULL LAMBDA OR METHOD         ║
  ╠══════════════════════════════════════════════════════════╣
  ║ Interference(modify) is done with the source-stream,     ║
  ║ when the stream, by itself, is being processed.          ║
  ║ 'Exception ... java.util.ConcurrentModificationException'║
  ╚══════════════════════════════════════════════════════════╝*/
    System.out.println("\nParallel Stream: Lambda - Statefull Harm");
    stateFullList
         .parallelStream()
         .map((element) -> {
           stateFullList.add(1);
           stateFullList.add(element + 1);
           return element;})
         .forEachOrdered((s) -> System.out.print(s + " "));

    System.out.println("\nSerial Stream: Lambda - Statefull Harm");
    for (int i = 0; i < CYCLES; i++) {
      stateFullList
           .stream()
           .forEach((element) -> {
             stateFullList.add(1);});
      System.out.println(externalList1);
    }

  }
}