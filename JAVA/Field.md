# 필드(Field)
필드는 객체의 **고유 데이터, 부품 객체, 현재 상태** 데이터를 저장하는 곳이다. 선언 형태는 변수와 비슷하지만, 필드를 변수라 부르지는 않는다. 변수는 생성자와 메소드 **내에서만 사용**&ZeroWidthSpace;되며, 실행 종료 시 자동 소멸된다. 하지만 생성자와 메소드 **전체에서 사용되며** 객체가 소멸하지 않는 한 객체와 함께 존재한다.

- 객체 : 자동차(public class Car)
- 고유 데이터 : 제작회사(String company), 모델(String model), 색깔(String color), 최고 속도(int maxSpeed)
- 상태 : 현재 속도(int speed), 엔진 회전수(int rpm)
- 부품 : 차체(Body body), 엔진(Engine engine), 타이어(Tire tire);

## 필드의 선언
클래스 중괄호 블록 어디서든 존재할 수 있으나, **생성자와 메소드 블록 내부**&ZeroWidthSpace;에는 선언될 수 없다. 블록 내부에 생성될 경우 **로컬 변수(local variable)**&ZeroWidthSpace;가 되기 때문이다.

```JAVA
String company = "hyundai";
String model = "sonata";
int maxSpeed = 300;
int currentSpeed;
boolean engineStart;
```

필드 타입은 기본 타입과 참조 타입이 모두 올 수 있고, 초기값은 **선언 시 주어져도, 주어지지 않아도 괜찮다.**
- 초기값이 주어지지 않은 정수 타입은 char(\u0000, 빈 공백) 및 long(0L)을 제외하면 모두 0으로 초기화된다.
- 초기값이 주어지지 않은 실수 타입은 float는 0.0F, double은 0.0으로 초기화된다.
- 초기값이 주어지지 않은 논리 타입 boolean은 false로 초기화된다.
- 초기값이 주어지지 않은 참조 타입인 배열, 클래스(String 포함), 인터페이스는 null(비객체참조상태)로 초기화된다.

## 필드의 사용
필드를 사용하는 것은 값을 읽고 변경하는 것을 의미한다.
- 생성자와 메소드에서 읽을 때는 단순히 필드 이름으로 변수처럼 사용하면 된다.
- 클래스 외부에서 사용할 때는 객체를 먼저 생성해서 필드가 존재한 상태로 사용해야 한다.
- 객체 생성 후 도트(.) 연산자를 활용하여 필드에 접근할 수 있다.

```JAVA
// Car Class
int speed;

Car() { speed = 0; } // Constructor
void setLowSpeed(...) {
	speed = 10;
} // method

// Person Class
void useCar() {
	Car myCar = new Car();
    myCar.speed = 60;
}
```
가령, 위와 같이 Person 클래스가 Car 클래스를 사용하는 관계에 있다고 하자.
- `Car` 클래스에 있는 필드는 기본 타입 int형 `speed` 필드&ZeroWidthSpace;이다.
- 필드 speed를 굳이 `speed = 0`으로 초기화하지 않아도 자동으로 int 타입은 0으로 초기화된다.
- 생성자 `Car()` 및 메소드 `setLowSpeed()`는 변수처럼 필드를 사용하여 값을 대입한다.
- 외부 클래스인 `Person`은 `Car myCar`을 통해 객체를 생성 후 도트 연산자로 `myCar.speed`를 통해 접근한다.
