# 생성자(Constructor)
- **new 연산자**&ZeroWidthSpace;와 함께 사용됨
- 클래스에서 객체 생성 시 호출되어 **초기화를 담당**
- **필드 초기화 혹은 메소드 호출**&ZeroWidthSpace;로 객체 사용 준비를 하는 것
- 생성자가 실행되면서 힙 영역에 객체가 생성, 주소가 리턴
- 리턴된 주소는 클래스 타입 변수에 저장되어 접근에 사용
- 에러가 발생할 경우 객체는 생성되지 않음

## 기본 생성자(Default Constructor)
생성자 선언을 생략할 경우 중괄호 블록 내용이 빈 생성자를 바이트 코드에 자동으로 추가되는데, 이를 **기본 생성자**&ZeroWidthSpace;라고 한다.
```JAVA
[public] className() {}

public class Car {
	public Car() { } // auto-addition
}

class Tire {
	Tire() { } // no access modifier
}
```
클래스가 선언될 때 public으로 선언되면 기본 생성자에도 public이 붙지만, **그렇지 않다면 기본 생성자에도 붙지 않는다.** 그래서 클래스에 생성자를 선언하지 않아도 new 연산자에 기본 생성자를 호출해서 `Class className = new class();`와 같은 형태로 객체 생성이 가능하다.   
명시적으로 선언한 생성자를 통해 **다양한 객체 초기화**&ZeroWidthSpace;가 가능하기 때문에, 개발자는 생성자를 명시적으로 선언하며 컴파일러는 이 경우 **기본 생성자를 생성하지 않는다.**

## 생성자 선언
명시적 생성자 선언 블록은 다음의 형식을 따른다:
```JAVA
Class(type parameter1, type parameter2, ...) {
	// Objcet initialize code
}
```
- 메소드와 비슷하나 **리턴 타입이 없고**&ZeroWidthSpace;, 클래스 이름과 동일하게 사용
- 필드에 초기값을 저장하거나 메소드를 호출
- 매개 변수 선언은 생략이 가능하고 여러 개를 선언해도 됨
- 매개 변수로 하여금 **외부의 값을 블록 내부로** 가져올 수 있음
<br>

**[ 예시 ]**
```JAVA
public class Character {
	Character(String name, int hp, int level) { ... }
}

Character myCharacter = new Character("Joe", 100, 12);
Character myCharacter = new Character();
```
- 외부의 이름, hp값, 레벨값을 안쪽으로 전달할 수 있음
- 객체 생성 두 번째 줄의 **기본 생성자는 호출이 되지 않으므로** 객체 생성이 되지 않음

## 필드 초기화(Field Initialize)
객체 생성 시 필드는 기본 초기값으로 자동 설정되는데, 이를 다른 값으로 초기화하려면 두 가지 방법이 있다.

### 필드 선언 시 초기값을 주는 방법
```JAVA
public class Korean() {
	String nation = "대한민국";
    String name;
}
```
- 초기값을 주게 되면서 동일 클래스에서 생성되는 **모든 객체들이 같은 값을 저장**&ZeroWidthSpace;하게 한다.
- 객체 생성 후 변경은 가능하나 **생성 시점에 모두 동일** 데이터를 가지게 된다.

하지만, `name`(이름)의 경우는 클래스 작성에 초기값을 줄 수 없고 생성 시점에 **외부의 값을 할당해야 하는** 경우에 해당하므로 이 방법은 적절하지 않다.

### 생성자의 매개값으로 초기값을 주는 방법
```JAVA
public class Korean() {
	String nation = "대한민국"; // 필드 선언 초기값
	String name;
    
    public Korean(String n) {
    	name = n;
    }
}
````
- `new Korean("송하영");`와 같은 방식으로 매개값을 받아오는 방법이다.
- **매개 변수(parameter)** n을 통하여 이름이 전달되어 필드 초기값에 저장된다.

> 매개 변수 이름이 짧으면 **코드 가독성이 저하**&ZeroWidthSpace;되므로 가능하면 필드 이름과 비슷하거나 동일한 이름을 사용하고, 관례적으로는 **동일한 이름을** 갖는 매개 변수를 사용하기를 권장한다.

필드와 매개변수 이름이 같게 된다면 생성자 내부에서 필드 접근이 불가능하다. **매개변수가 우선순위가 높으므로** 같은 이름이라면   모두 매개변수로 취급되기 때문이다. 그래서 `this.`를 활용해주면 된다.

```JAVA
public Korean(String name) {
	name = name; // 매개변수 = 매개변수 형태이므로 접근 불가
    this.name = name; // 필드 = 매개변수의 형태로 인식
}
```

필드가 많아질수록 생성자에서 초기화하기 위해 받아야 할 **매개변수의 수가 늘어난다.** 하지만 실제로는 중요한 몇 개만 필드-매개변수를 통해 초기화되고, 나머지는 선언 시에 초기화하거나 생성자 내부에서 임의 값으로 초기화한다. 아니면 생성 후 별도로 저장하기도 한다. 코드 길이가 길어져서 가독성이 떨어지기 때문이다.

## 생성자 오버로딩(Overloading)

생성자는 경우에 따라 여러 데이터를 처리해 객체 초기화가 가능하게 **오버로딩(Overloading)**&ZeroWidthSpace;을 지원한다. **매개 변수를 달리하는 생성자**&ZeroWidthSpace;를 여러 개 선언하는 것이다.
```JAVA
public class Character {
	Character() { ... }
    Character(String name) { ... }
    Character(String name, int level) { ... }
}

Character character1 = Character();
Character character2 = Character("Joe");
Character character3 = Character("Aoba", 20);
```
- 생성자 오버로딩은 매개변수의 타입과 개수에 영샹을 받음
- 타입과 개수는 같은데 순서만 바꾼다고 오버로딩이 아님
- new 연산자로 호출 시 제공할 변수에 따라 호출할 생성자를 고를 수 있음

## 다른 생성자 호출(this())
생성자의 오버로딩이 많아지면 중복 코드가 발생하기 쉽다. 변수 **수만 달라지는데 초기화 내용이 비슷하면** 이런 경우가 많다. 이는 **생성자 하나에 필드 초기화를 집중**&ZeroWidthSpace;시키고, 다른 생성자는 해당 생성자를 호출하는 방법으로 개선한다. 이때 쓰이는 것이 `this()`코드이다.

```JAVA
public class Character {
	String name;
    int level;
    int HP;
    
    Character(String name) {
    	this(name, 1, 100);
   	}
    Character(String name, int level) {
    	this(name, level, 100);
    }
    Character(String name, int level, int HP) {
    	this.name = name;
        this.level = level;
        this.HP = HP;
    }
}
```
- 위쪽의 두 생성자는 **맨 아래 생성자를 호출**
- 아래쪽의 생성자가 추가적인 매개변수를 더 받아 수행
- **공통 실행 코드를 여러 번 적는 수고를 덜 수 있음**

> 중요한 것은, this()는 다른 생성자를 호출하는 코드이나 반드시 **첫 줄에서만** 실행해야 한다는 것이다. 물론, 이후에 추가적인 실행문을 작성해도 된다. 이는 생성 이후 **다시 원래대로 돌아와서 진행이 가능**&ZeroWidthSpace;하다는 것이다.
