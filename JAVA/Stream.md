# Stream (스트림)
스트림(Stream)이라는 것은 JAVA 8 버전부터 추가된 배열을 포함한 컬렉션의 저장 요소를 **하나씩 참조하여 람다식으로 처리**
할 수 있게 해주는 **반복자**&ZeroWidthSpace;이다.
<br>
<br>

## 반복자 스트림
JAVA 7 이전까지는 List<String> 컬렉션의 요소 처리를 순차적으로 하기 위해 Iterator 반복자를 사용해왔다.
```JAVA
List<String>>> list = Arrays.asList("A", "B", "C");
Iterator<String> iterator = list.iterator();
whlie(iterator.hasNext()) {
  String name = iterator.next();
  Ssytem.out.println(name);
}
```
출력값
```
A
B
C
```
Stream을 활용하면 이것을
```JAVA
List<String> list = Arrays.asList("A", "B", "C");
Steam<String> stream = list.stream();
stream.forEach( name -> System.out.println(name) );
```
다음과 같이 바꿀 수 있다. 컬렉션(java.util.Collection)의 stream() 메소드로 스트림 객체를 얻어서 foreach 메소드를
통해 요소를 하나씩 출력한다. Consumer 함수적 인터페이스 타입의 매개값을 가지므로 컬렉션의 요소를 소비할 코드를 
람다식으로 기술할 수 있다.
```JAVA
void forEach(Consumer<T> action)
```
<br>
<br>

## Stream의 특징
- **람다식**으로 요소 처리 코드를 제공함 : 대부분의 요소 처리 메소드는 함수적 인터페이스 매개 타입을 가지므로 람다식
또는 메소드 참조를 이용해 요소 처리 내용을 매개값으로 전달할 수 있다.
```JAVA
public class Student {
  private String name;
  private int score;
  public Student (String name, int score) {
    this.name = name;
    this.score = score;
  } // Student class의 constructor
  public String getName() { return name; }
  public int getScore() { return score; }
}

public class Main {
  public static void main(String[] args) {
    List<Student> list = Arrays.asList(new Student("A", 90), new Student("B", 92));
    // list 내에 Student 객체를 직접 생성하는 방식 (A는 90점, B는 92점)
    Stream<Student> stream = list.stream(); // 스트림을 받아옴
    stream.forEach( s -> {
      String name = s.getName();
      int score = s.getScore();
      System.out.println(name + "-" + score);
    }); // 스트림을 받아온 것에 대해 각각의 객체를 getName/getScore 통해서 출력
  }
}
```
- **내부 반복자**를 사용하기 때문에 **병렬 처리**가 쉬움   
외부 반복자(external iterator)는 개발자가 코드로 직접 요소를 반복해 가져오는 코드 패턴이다. for문과 while문은 index와
Iterator를 활용하기 때문에 외부 반복자에 해당한다. 하지만 내부 반복자(internal iterator)는 **내부에서 요소를 반복**
하기 때문에 개발자는 요소당 처리할 코드만 제공하면 된다. 내부 반복자는 **요소들의 반복 순서를 변경하거나** 멀티 코어
CPU를 최대한으로 활용하기 위해 **요소를 분배해 병렬 작업을 할 수 있게** 도와주므로 효율적인 요소 반복이 가능하다.

  **예)** 외부 반복자는 반복할 부분과 방식을 직접 구성해야 하고, 각 요소를 어떻게 처리해야 할지도 구성해야 한다.
  하지만 내부 반복자는 요소를 처리할 코드만 제공하면 컬렉션이 알아서 요소를 반복해준다. 그러면 멀티코어의 경우에서도
  각 코어에서 처리할 코드를 적용하기만 하면 된다.

- **병렬 처리(Parallel)**   
한 작업을 서브 작업으로 나누고 서브 작업을 분리된 스레드에서 병렬적으로 처리하는 것이다. 병렬 처리 스트림을 통해
런타임 시 하나의 작업을 서브 작업으로 자동으로 나눠서 그 결과를 자동 취합해 최종 결과물을 생성한다.
1) 순차 처리 스트림(list.stream)의 경우 : 하나의 스레드가 요소를 순차적으로 읽어 합을 구한다.
   ```JAVA
   Stream<String> stream = list.stream();
   stream.forEach(ParellelExample :: print); // s -> ParellelExample.print(s)와 동일
   ```
   > 출력값의 경우, 모두 main 스레드만을 참조하기 때문에 'main'으로 출력될 것
2) 병렬 처리 스트림(list.parallelStream)의 경우 : 여러개의 스레드가 요소를 부분적으로 합해 이 부분합을 결합한다.
   ```JAVA
   Stream<String> parallelStream = list.parallelStream();
   parallelStream.forEach(ParellelExample :: print);
   ```
   > 출력값의 경우, ForkJoinPool(스레드풀)의 작업 스레드들이 병렬적으로 요소를 처리하므로 main 스레드를 포함해 출력된다.
