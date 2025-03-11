# 메소드(Method)
- 객체의 동작에 해당하는 중괄호 블록
- 메소드를 호출하면서 **중활호 블록의 모든 코드가 일괄 실행**
- 필드를 읽고 수정하거나 다른 객체 생성도 가능
- 객체 간 **전달의 수단:** 외부로부터 매개값을 받거나 실행 후 값을 리턴

## 메소드의 선언

```JAVA
returnType methodName(type parameter1, type parameter2, ...) {
	// method execution block
}
```
- 선언부 : 리턴 타입, 메소드 이름, 매개변수 선언
- 실행 블록

### 리턴 타입(Return type)
메소드 실행 후 리턴하는 값의 타입으로, 있어도 되고 없어도 된다. 실행 결과를 넘겨주려면 **리턴값이 있어야** 한다. 
- 계산기 `class Calculator`에 대하여 전원을 켜는 `powerOn()` 메소드는 리턴값이 없어도 된다.
- 두 수를 나누는 기능인 `divide(int a, int b)` 메소드는 리턴값이 필요하다.

리턴값이 없는 경우 리턴 타입은 `void`를 사용한다. 나눗셈의 경우 실수 범위가 맞을 것이므로 `double res` 같은 변수를 선언하여 받아야 한다. 값 저장이 어려운 다른 `int`나 `long`과 같은 정수형 변수를 선언해서 리턴값을 받으려 한다면 **컴파일 에러가 발생**&ZeroWidthSpace;한다.

> 소스 실행만 해도 되는 경우 **굳이 리턴값을 변수 선언하여 받을 필요는 없다.**

### 메소드 이름
메소드 이름의 경우 자바 식별자 규칙에 맞게 작성해주면 된다. 되도록이면 이해하기 쉽게 **기능 이름**&ZeroWidthSpace;을 사용하자. 메소드명의 길이는 너무 짧지 않게 하자.

### 매개변수의 선언
메소드의 실행 중 필요한 데이터가 **외부에서 받아야 하는 경우** 사용하나, 필요가 없는 경우 빈칸으로 두어도 된다. 만약, 매개변수의 선언 중 개수를 모르는 경우 `...`을 사용하여 작성하면 메소드 호출 전에 배열을 선언하지 않아도 **넘겨준 값의 수에 따라 자동으로 배열을 생성**&ZeroWidthSpace;하기 때문에 편리하다.
```JAVA
int sum1(int[] values) { ... } // 배열을 직접 생성해야 함
int[] values = {1, 2, 3};
int result = sum1(values);

int sum2(int ... values) { ... } // 배열이 자동으로 값에 따라 생성됨
int result = sum2(1, 2, 3, 4, 5); // 매개변수를 리스트로 나열해주면 됨
int result2 = sum2(values); // 배열을 직접 사용해도 괜찮음
```

## 리턴(return)문
리턴 타입이 있는 경우 반드시 `return` 문을 활용하여 리턴값을 지정해야 한다. 없으면 컴파일 에러가 발생한다. `return`문이 실행되는 순간 **메소드는 즉시 종료**&ZeroWidthSpace;된다. 리턴 값은 리턴 타입과 동일하거나 변환될 수 있어야 한다. 가령, `int`를 리턴 타입으로 하는데 `double`을 리턴 값으로 부여하면 안 된다.
```JAVA
int plus(int x, int y) {
	int result = x + y;
    return result; // 메소드 종료
    System.out.println(result);
}

boolean isLeft() {
	if(val == 0) {
    	System.out.println("No one else");
        return false;
    }
    return true;
}
```
- `int plus` 메소드는 **Unreachable code** 컴파일 오류가 발생한다.
- return문 이후에 실행문이 오면 return문의 실행으로 인해 절대 실행될 수 없는 부분이기 때문이다. 
- `isLeft()` 메소드는 이상 없이 작동한다.
- if문의 조건식이 틀리게 되면 바깥의 `return true;`가 작동하고 맞게 되면 `return false`가 작동하기 때문이다.

`void`를 활용해 리턴값이 없는 메소드를 사용할 수도 있다. 이 경우 `return;`문을 사용하면 실행을 강제 종료한다. 주로 while문을 사용하는 메소드에서 강제 종료를 위해 사용하나, 습관적으로 마지막에 붙이는 습관을 들이자.

## 메소드의 호출
메소드는 클래스의 내부 혹은 외부에서 호출하여 실행된다.
- 객체 내부에서 호출하는 경우, 리턴값을 받는 경우 변수만 설정해주면 되고 그냥 메소드를 입력하면 된다.
가령, `int result = sum(x, y)`와 같이 사용하면 된다.
- 객체 외부에서 호출하는 경우, **객체를 생성**&ZeroWidthSpace;하는 것이 먼저이다. 객체가 없으면 메소드가 없기 때문이다.
- 그래서, `클래스 참조변수 = new 클래스(매개변수, ...)`와 같이 객체를 생성하는 것이 먼저이다.
- 그 뒤 `참조변수.메소드(매개변수, ...)`와 같이 사용한다.

```JAVA
Character Aoba = new Character();
Aoba.jump(); // 리턴값이 없는 void 함수
String name = Aoba.getName(); // 리턴값이 String인 함수
```

## 메소드 오버로딩(Method overloading)

메소드도 오버로딩이 가능하다. 하나의 메소드로 여러 기능을 담을 수 있는데, 조건은 매개변수의 **타입이나 개수, 순서 중 하나가 달라야** 한다. 또한, 매개변수의 타입과 개수, 순서가 같은데 리턴 타입만 다르고 매개 변수가 동일하면 이는 **오버로딩이 아니다.**

```JAVA
int plus(int x, int y) {
	return x + y;
}

double plus(double x, double y) {
	return x + y;
}

double plus(int x, int y) {
	return x + y;
}
```
- 첫 번째의 plus라는 메소드에 대해 두 번째는 오버로딩인데, 모든 매개변수가 double 타입으로 다르기 때문이다.
- 첫 번째의 메소드에 대해 세 번째는 오버로딩이 아닌데, **리턴 타입은 JVM의 메소드 선택에 도움을 주지 못하기 때문**&ZeroWidthSpace;에 컴파일 오류만이 진행될 뿐이다.

> 가장 대표적인 예시로 `System.out.println()`의 경우는 그 어떤 매개변수 타입도 출력이 가능하므로 메소드 오버로딩이 되어 있다고 볼 수 있다. 
