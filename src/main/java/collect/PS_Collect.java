package collect;

import entity.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PS_Collect {
  public static void main(String[] args) {

    Collector c1 =
         Collectors
              .toMap(String::length, s -> s);
    System.out.println("toMap Charact's:" + c1.characteristics());

    Collector c2 =
         Collectors
              .toConcurrentMap(String::length, s -> s);
    System.out.println("toConcurrentMap Charact's:" + c2.characteristics());

    Collector c3 =
         Collectors
              .groupingBy(Person::getAge);
    System.out.println("groupingBy Charact's: " + c3.characteristics());

    Collector c4 =
         Collectors
              .groupingByConcurrent(
                   String::toString,
                   Collectors.toList()
              );
    System.out.println("groupingByConcurrent Charact's: " + c4.characteristics());

    Set<Person> list =
         Stream
              .generate(Person::new)
              .limit(5_000_000)
              .collect(Collectors.toSet());

//        testToMap(list);
    testGroupingBy(list);

  }

  static void testToMap(Set<Person> list) {

    long start = getMs();

    Map<Integer, Person> personMap =
         list.stream()
             .collect(
                  Collectors
                       .toMap(Person::getId, p -> p));
    System.out.println("Serial: toMap: " + (getMs() - start) + " ms");

    start = getMs();
    Map<Integer, Person> personMap1 =
         list.parallelStream()
             .collect(
                  Collectors
                       .toMap(Person::getId, p -> p));
    System.out.println("Parallel: toMap: " + (getMs() - start) + " ms");

    start = getMs();
    Map<Integer, Person> personMap2 =
         list.parallelStream()
             .collect(Collectors
                           .toConcurrentMap(Person::getId, p -> p));
    System.out.println("toConcurrentMap: " + (getMs() - start) + " ms");

  }

  static void testGroupingBy(Set<Person> list) {

    long start = getMs();

    Map<Integer, List<Person>> m =
         list.stream()
              .collect(
                   Collectors
                        .groupingBy(Person::getAge));
    System.out.println("Serial-groupingBy: " + (getMs() - start) + " ms");

    start = getMs();
    Map<Integer, List<Person>> m1 =
         list.parallelStream()
              .collect(
                   Collectors
                        .groupingBy(Person::getAge));
    System.out.println("Parallel-groupingBy: " + (getMs() - start) + " ms");

    start = getMs();
    Map<Integer, List<Person>> m2 =
         list.parallelStream()
             .collect(
                  Collectors.
                       groupingByConcurrent(Person::getAge));
    System.out.println("groupingByConcurrent: " + (getMs() - start) + " ms");

  }

  private static long getMs() {

    return System.currentTimeMillis();
  }
}