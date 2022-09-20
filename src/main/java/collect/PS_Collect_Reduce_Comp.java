package collect;

import java.util.Set;

import static datasource.LettersProvider.getStrings;

public class PS_Collect_Reduce_Comp {
  public static void main(String[] args) {

    Set<String> set = getStrings();

    String strReduce =
         set
              .stream()              // Suppl+BiCons+BiCons
              .reduce("",
                      String::concat,
                      String::concat
              );

    StringBuilder sbReduce =
         set
              .stream()
              .map(s -> new StringBuilder(s))
              .reduce(new StringBuilder(),   // Ident+BiFunc+BinOper
                      (a, b) -> a.append(b),
                      (a, b) -> a.append(b)
              );

    String strCollect1 =
         set
              .stream()                     // Suppl+BiCons+BiCons
              .collect(() -> new String("xxx"),
                       String::concat,
                       String::concat);

    String strCollect2 =
         set
              .stream()                     // Suppl+BiCons+BiCons
              .collect(String::new,
                       String::concat,
                       String::concat);

    StringBuilder sbCollect =
         set
              .stream()
              .map(s -> new StringBuilder(s))
              .collect(StringBuilder::new,    // Suppl+BiCons+BiCons
                       (a, b) -> a.append(b),
                       (a, b) -> a.append(b));

    show("strReduce: " + strReduce);
    show("sbReduce: " + sbReduce);
    show("\nstrCollect1: " + strCollect1);
    show("strCollect2: " + strCollect2);
    show("SBCollect: " + sbCollect);

    StringBuilder sbReduceParall =
         set
              .parallelStream()
              .map(s -> new StringBuilder(s))
              .reduce(new StringBuilder(),   // Ident+BiFunc+BinOper
                      (a, b) -> a.append(b),
                      (a, b) -> a.append(b)
              );

    StringBuilder sbCollectParall =
         set
              .parallelStream()
              .map(s -> new StringBuilder(s))
              .collect(StringBuilder::new,    // Suppl+BiCons+BiCons
                       (a, b) -> a.append(b),
                       (a, b) -> a.append(b)
              );

    show("\nsbReduceParall: " + sbReduceParall);
    show("sbCollectParall: " + sbCollectParall);
  }

  private static void show(String text) {

    System.out.println(text);
  }
}