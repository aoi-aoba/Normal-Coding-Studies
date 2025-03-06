# 추상 데이터 타입(ADT)
- Abstract Data Type
- 세부 사항에서 벗어나 추상적으로 정의한 데이터 타입
- 어떤 데이터 타입이 어떤 작업으로 이루어지는지만 표현

## 리스트의 ADT 표현 예시
1. 원소를 첫 번째, 두 번째, ..., i번째 원소로 가리킬 수 있는 자료구조
2. i번째 자리에 새 원소 넣기
원소 x 찾기
i번째 자리의 원소 삭제하기
- 특정 대상에 대한 ADT는 유동적이며 제공하는 사람의 주관적 관점이 포함됨
- 효율적이고 고급스러운 프로그래밍 작업을 위한 것이지 혼란을 주며 장애가 되어서는 안 됨

## Java Interface
- JAVA에서 클래스의 상위 개념인 Interface(인터페이스)
- 추상 데이터 타입(ADT)에 가장 잘 대응되는 개념
- ADT 설계 → 인터페이스 설계 → 클래스 설계 → 사용자 프로그램

```
ADT 'List'
	작업:
    	i번째 자리에 새 원소 넣기
        원소 x 찾기
        i번째 자라의 원소 삭제하기
```

``` JAVA
public interface ListInterface {
	public void add(int i, Object x);
    public Object get(int i);
    public void remove(int i);
}
```

- interface 기능으로 ADT를 구현
- implement를 활용해 interface를 따르는 class를 정의하면 해당 class는 interface를 따라 method를 지원
- 해당 class는 interface에서 지원하는 method의 return type과 parameter를 고려하여 사용
- 사용자 프로그램에서는 interface만 보고 class 객체를 할당받아 명시된 parameter와 return type에 맞게 함수를 사용

그러면, 리스트의 내부 구현과 무관하게 "첫 번째 자리에 원소 x 삽입", "3번째 원소 삭제"와 같은 추상적 수준에서 사용이 가능
