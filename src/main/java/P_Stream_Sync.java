import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P_Stream_Sync {
  public static void main(String[] args) {

    List<Integer> stateFullList = DataProvider.getData();
    var CYCLES = 3;

    System.out.println("Non-Thread-Safe Simple Collection");
    List<Integer> list1 = new ArrayList<>();


    for (int i = 0; i < CYCLES; i++) {
      list1.clear();
      stateFullList
       .parallelStream()
       .forEach((s) -> {
         list1.add(0, s);
       });
      System.out.println(list1);
    }

    System.out.println("\nNon-Thread-Safe Simple Collection based in VIEW");
    List<Integer> list2 =
         new ArrayList<>(stateFullList.subList(1, 2));

    for (int i = 0; i < CYCLES; i++) {
      list2.clear();
      stateFullList
           .parallelStream()
           .forEach((s) -> {
             list2.add(0, s);
           });
      System.out.println(list2);
    }

    System.out.println("\nThread-Safe Collection");
    List<Integer> list3= Collections.synchronizedList(new ArrayList<>());
    for (int i = 0; i < CYCLES; i++) {
      list3.clear();
      stateFullList
       .parallelStream()
       .forEach((s) -> {
         list3.add(0, s);
       });
      System.out.println(list3);
    }

    System.out.println("\nThread-Safe Collection from VIEW - Statefull Harm");
    List<Integer> list4=
         Collections.synchronizedList(stateFullList.subList(1, 2));
    for (int i = 0; i < CYCLES; i++) {
      list4.clear();
      stateFullList
           .parallelStream()
           .forEach((s) -> {
             list4.add(0, s);
           });
      System.out.println(list4);
    }

  }
}