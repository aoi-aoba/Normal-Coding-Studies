# 참조
참조(Reference)는 번지를 통해서 객체를 참조한다는 의미를 내포하고 있다.

## 데이터 타입 분류
자바의 데이터 타입은 크게 기본 타입과 참조 타입으로 분류한다.
- **기본 타입(primitive type)** : 정수 타입(byte, char, short, int, long) / 실수 타입(float, double) / 논리 타입(boolean)
- **참조 타입(reference type)** : 배열 타입 / 열거 타입 / 클래스 / 인터페이스
둘의 차이점은 **저장되는 값**&ZeroWidthSpace;이 무엇이냐인데, 기본 타입이 저장하는 것은 **실제 값**&ZeroWidthSpace;이며
참조 타입이 저장하는 것은 **메모리의 번지**&ZeroWidthSpace;이다. 번지를 통해 객체를 참조하는 **참조 타입** 인 것이다.

## 메모리 사용 영역
JVM이 java.exe로 시작되면 운영체제에서 할당받은 메모리 영역(Runtime Data Area)을 세부 영역으로 구분해서 사용한다.

### 메소드(Method) 영역
- 코드에서 사용되는 클래스를 **클래스 로더**&ZeroWidthSpace;로 읽음
- Runtime Constant Pool, Field Data, Method Data, Method Code, Constructor Code 등을 분류하여 저장
- JVM이 **시작될 때 생성**&ZeroWidthSpace;되고 모든 스레드가 공유함

### 힙(Heap) 영역
- **객체와 배열**&ZeroWidthSpace;이 생성되는 영역
- JVM 스택 영역의 변수나 다른 객체의 필드에서 이를 참조함
- 참조하는 변수나 필드가 없다면 Garbage Collector 실행하여 힙에서 자동으로 제거
- 이로 인해 객체 제거 방법이 별도로 존재하지 않음

### JVM 스택(Stack) 영역
- 스레드마다 하나씩 존재하며 **스레드가 시작**&ZeroWidthSpace;될 때 할당
- 추가 스레드 생성이 없다면 main 스레드의 JVM 스택 하나만이 존재
- **메소드 호출**&ZeroWidthSpace;할 때마다 프레임(Frame) push-pop 진행
- printStackTrace() 메소드 통해 보여주는 각 라인은 하나의 프레임을 표현
- 프레임 내부에는 **로컬 변수 스택**&ZeroWidthSpace;이 있는데, 변수를 push-pop 하며 초기화(initialize) 진행 시 처음으로
  생성됨
- 변수는 **선언된 블록 안에서만** 스택에 존재하고 벗어나면 스택에서 제거됨
- 배열 변수는 스택 영역에 생성되고 힙 영역의 주소를 가지며, 힙 영역에 배열 객체가 존재

```JAVA
char v1 = 'A';          // 스택 영역에 v1(A) push, v1 존재
if(v1 == 'A) {          
  int v2 = 100;         // 스택 영역에 v2(100) push, v1/v2 존재
  double v3 = 3.14;     // 스택 영역에 v3(3.14) push, v1/v2/v3 존재
}                       // if 블록 벗어나면서 v2/v3 pop, v1만 존재
boolean v4 = true;      // 스택 영역에 v4(true) push, v1/v4 존재
int[] scores = {10, 20} // 스택 영역에 scores(번지) push, v1/v4/scores 존재
```

## 참조 변수의 ==, != 연산
참조 타입 변수의 경우 값을 비교하지 않고 **참조하는 객체의 동일성** 여부를 체크한다. 동일 객체를 참조하면 true, 아니면
false 결과를 연산값으로 return한다.

## null, NullPointerException
- 참조 타입 변수는 **null** 값을 가질 수 있음
- 힙 영역의 객체를 **참조하지 않는** 경우에만 값이 할당되고 초기값도 가능
- null로 초기화된 참조 변수도 **스택 영역에 생성** 가능함

```JAVA
int[] intArray =  null;
String str = null;
intArray[0] = 10; // NullPointerException
System.out.println(str.length()); // NullPointerException
```
- 배열 타입 변수인 int[]와 클래스 타입 String 모두 참조 타입
- null로 초기화되면 참조하는 배열 객체도 없고 클래스 객체도 없는 상태
- 배열의 경우 값을 저장할 수 없고, String은 메소드를 호출할 수 없음

## String 타입
String은 클래스 참조 타입 변수이기 때문에 **문자열 객체를 생성하고 이를 참조**&ZeroWidthSpace;하는 방식이다. 변수는 
스택 영역에 생성되고, 문자열 리터럴은 힙 영역에 String 객체로 생성되며 변수는 객체 주소 값을 저장한다. 문자열 리터럴이 
동일할 경우 **String 객체를 공유**&ZeroWidthSpace;하게 되어 있기 때문에 저장된 주소도 동일하다.   
new 연산자를 통해 String 객체를 직접 생성시킬 수도 있는데, 같은 문자열 리터럴이라도 new String(literal)을 사용할 경우 
**다른 String 객체를 참조**&ZeroWidthSpace;하게 된다. String 타입은 ==를 사용할 경우 동일한 객체를 참조하는지 여부를
확인하기 때문에 리터럴 비교를 위해서는 `String.equals()` 메소드를 사용해야 한다.

```JAVA
String name_a = new String("A");
String name_b = new String("A");
if(name_a == name_b) System.out.println("동일한 참조");
if(name_a.equals(name_b) System.out.println("같은 문자열");
name_a = null;
```
- 서로 다른 String 객체이므로 첫 번째 if문은 실행되지 않으며 두 번째 if문은 실행됨
- 리터럴만 동일하게 입력한 상태였다면 양쪽이 모두 실행될 수 있음
- null을 할당하면서 객체 참조가 사라진 name_a가 참조하던 String 객체는 Garbage Collector에 의해 자동으로 제거됨

## 배열 타입

### 배열의 특징
- **같은 타입의 데이터만** 저장 가능
- 선언과 **동시에** 저장 가능한 데이터 타입이 결정됨
- 다른 타입을 저장하려 하면 **Type mismatch 컴파일 오류**&ZeroWidthSpace;가 발생
- 길이를 늘이거나 줄일 수도 없음
- 배열 객체의 **length 필드**를 도트(.) 연산자를 통해 읽어들여 길이를 받아올 수 있음

### 배열 선언
- 배열은 `type[] variablename` 형태로 선언해야 하고, null 값으로 초기화가 가능함
- 값의 목록을 중괄호를 활용하여 {a, b, c} 와 같은 형태로 대입하여 배열 객체를 생성할 수 있음
- 힙에 있는 배열 객체 내의 배열 변수의 값은 대입 연산자로 변경이 가능
- 배열 선언 시 **초기값은 타입에 따라 다름**

| 분류        | 데이터 타입                          | 초기값                              |
|-----------|---------------------------------|----------------------------------|
| 기본 타입(정수) | byte, char, short, int, long 배열 | '\u0000'(char), 0L(long), 0(나머지) |
| 기본 타입(실수) | float, double 배열                | 0.0F(float), 0.0(double)         |
| 기본 타입(논리) | boolean 배열                      | false                            |
| 참조 타입     | 클래스 배열과 인터페이스 배열                | null                             |

### 커맨드 라인 입력
```JAVA
public static void main(String[] args)
```
`String[] args`의 경우 JVM은 길이가 0인 **String 배열을 먼저 생성하고 main() 메소드 호출 시 매개값으로 전달**
&ZeroWidthSpace;한다. 만약 java 클래스 뒤에 공백으로 구분된 문자열 목록을 주고 실행하면 문자열 목록으로 구성된
String[] 배열이 생성되고 main() 메소드 호출 시 매개값으로 전달된다. `main()` 메소드는 `String[] args` 매개 변수를
통해서 **커맨드 라인에서 입력된 데이터의 수와 데이터**&ZeroWidthSpace;를 알 수 있게 된다. 

### 다차원 배열
다차원 배열의 경우 세 개의 배열 객체를 힙 영역에 생성하는데, 스택 영역에 배열 변수와 배열 A의 주소를 생성한다. 
그리고, 배열 A는 또 각각 배열 B와 C를 참조한다. 배열 A의 length는 행의 크기, 배열 B와 C의 length는 열의 크기를
가진다. 만약 인덱스를 잘못 사용한다면 `ArrayIndexOutOfBoundsException` 오류를 발생시킨다.

### 객체 참조 배열
참조 타입 배열인 클래스 배열과 인터페이스 배열은 각 항목에 **객체의 번지**를 가지게 된다. 예를 들어, `String[]` 배열은
String 배열의 각각 요소에 String 객체의 번지를 참조하고 있다. 즉, 스택 영역에 String[] 배열 이름과 배열 객체의 주소를
가지게 되고, 힙 영역에서 String 배열 객체에서 각각의 항목에 String 리터럴 객체의 번지를 가지게 된다. 그래서 여기서도
마찬가지로 `==`와 `equals` 메소드의 활용을 적절히 해야 목적에 맞게 사용할 수 있다.

### 배열 복사
배열은 한 번 생성하면 변경이 불가하므로 더 큰 배열을 만들고 배열 항목을 복사하게 된다면 `System.arraycopy()` 메소드를
사용하면 된다.
```JAVA
System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
```
- src 파라미터는 원본 배열
- int형 srcPos 파라미터는 원본 배열에서 복사할 항목의 시작 인덱스
- dest 파라미터는 이동할 새 배열
- int형 destPos 파라미터는 새 배열에서 붙여넣기할 항목 위치의 시작 인덱스
- length는 복사할 개수
- 복사하지 않은 항목은 기본 초기값인 null이 그대로 유지되거나 따로 초기화한 값이 유지

### 향상된 for 문(enhanced for-loop)
`for(타입 변수 : 배열) 실행문` 형태로 사용하여, 괄호에는 배열에서 꺼낸 항목을 저장할 변수 선언과 `:`, 그리고 배열을
나란히 작성하여 처음 for문이 실행되면 **배열에서 가져올 첫 번째 값이 존재하는지 평가**&ZeroWidthSpace;한다. 값이
존재한다면 변수에 저장하여 실행문을 실행한다. 가져올 값이 없을 때 자동으로 for문을 나간다.

## 열거 타입
몇 가지로 한정된 값을 갖는 데이터(요일, 계절 등)를 보관하는 데이터 타입

### 열거 타입의 선언
- 열거 타입의 선언은 이름을 정하고 이름으로 **소스 파일(.java) 생성**&ZeroWidthSpace;이 필요함
- 소스 파일 내용은 열거 타입의 선언이 `public enum 열거타입이름 (...)`의 형식으로 주어짐
- **소스 파일명, 열거타입 이름** 모두가 대소문자가 일치해야 한다.
- 열거 상수를 내부에 선언할 때 모두 **대문자로 작성**&ZeroWidthSpace;하는 것이 관례적이다.
- 열거 타입은 `열거타입 변수 = 열거타입.열거상수;`의 형태로 사용한다. null도 저장할 수 있다.
- 열거 상수는 각각이 **모두 열거 객체**&ZeroWidthSpace;이므로 참조 타입에 속한다.

```JAVA
public enum Week {
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY
}

Week today = Week.SUNDAY;
Week lastWeekend = Week.SUNDAY; // today == lastWeekend는 true를 반환
```

### 열거 객체의 메소드
열거 객체는 `java.lang.Enum` 클래스에 선언된 메소드를 상속받는다.
- name() return String : 열거 객체의 문자열을 리턴
- ordinal() return int : 열거 객체의 순번(0부터 시작)을 리턴
- compareTo() return int : 열거 객체의 순번 차이를 비교하여 리턴
- valueOf(String name) return EnumType : 주어진 문자열의 열거 객체를 리턴함에 따라 열거 객체 참조가 가능하게 함
- values() return EnumArray : 모등 열거 객체들을 배열로서 리턴
