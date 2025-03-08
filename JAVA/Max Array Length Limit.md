# 배열의 최대 Index 값에 대하여
여느 날처럼 그냥 평범하게 코드를 짜고 있던 나에게 주어진 문제의 조건 중 하나, N의 최대가 $$10^{18}$$이라는 것이다. 그래서 배열을 사용해서
풀고 있던 나는 자연스럽게 `long N`에 대하여 `long[] b = new long[N]`을 사용하려 했다. 그런데 이상하게도, 우리의 갓-텔리제이께서 말씀하길
"**너가 하려는 짓은 안 되니까 N을 int로 type-casting** 하여라~" 하시는 것이다. 이게 왜 그런걸까에 대한 생각을 해볼 필요가 있었다.

![image](https://github.com/user-attachments/assets/9e6ec7bc-5ea8-4cc7-b16a-a5a7cc1ffc6d)

## 도대체 왜 안되는 것인가?
Java 프로그램은 특정 크기까지만의 배열 할당이 정해져 있는데, 이는 일반적으로 우리가 사용하는 JVM과 플랫폼에 따라 다르다.
```JAVA
public class Main {
    public static void main(String[] args) throws IOException {
        for (int i = 2; i >= 0; i--) {
            try {
                int[] arr = new int[Integer.MAX_VALUE - i];
                System.out.println("Max-Size : " + arr.length);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
```
간단한 배열 활용 예시를 실행해보면,

![image](https://github.com/user-attachments/assets/2f49c8a1-ee59-4308-a4d2-68c0003220c4)

안 된다고 노하시며 오류를 보내주시는데 잘 읽어보면
```
java.lang.OutOfMemoryError: Java heap space
	at Main.main(Main.java:9)
java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	at Main.main(Main.java:9)
java.lang.OutOfMemoryError: Requested array size exceeds VM limit
```
첫 오류에서는 힙 메모리 제한에 대하여 언급하며 이 메모리를 넘겼기에 오류가 발생했다고 말하고 있다.   
뒤의 두 오류는 VM 제한을 언급하고 있다.   

VM 인수 -Xms9G -Xmx9G 를 사용하여 이를 확인한 사이트를 참고해보면,
```
Max-Size: 2147483645
java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	at com.example.demo.ArraySizeCheck.main(ArraySizeCheck.java:8)
java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	at com.example.demo.ArraySizeCheck.main(ArraySizeCheck.java:8)
```
라는 방식으로 확인 결과 **2,147,483,645라는 결과값**이 나온다. 배열의 byte, boolean, long 및 기타 데이트 유형에 대해
동일한 동작이 관찰되며 결과도 동일하다고 한다.
(출처 : https://www.baeldung.com/java-arrays-max-size)

후술할 깃헙 링크를 통해 ArraysSupport.java 파일에 들어가게 된다면 `public static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;`이라는 부분을 볼 수 있다.

![image](https://github.com/user-attachments/assets/fc7186f4-1beb-4493-b0cd-85cac4211a5a)

주석에도 이와 같은 내용이 적혀 있다.
- The maximum length of array to allocate (unless necessary).
- Attempts to allocate larger arrays may result in {@code OutOfMemoryError: Requested array size exceeds VM limit}
즉, **할당 가능한 array의 길이를 한정해두었고** 이를 넘기려고 시도하면 OutOfMemoryError가 생길 것이라는 얘기다.
(링크 : https://github.com/openjdk/jdk14u/blob/84917a040a81af2863fddc6eace3dda3e31bf4b5/src/java.base/share/classes/jdk/internal/util/ArraysSupport.java#L577)

영문 위키피디아의 **Criticism of Java** 문서를 참고해보면, 이런 부분이 적혀 있다.   
(링크 : https://en.wikipedia.org/wiki/Criticism_of_Java#Large_arrays)   
<br>
![image](https://github.com/user-attachments/assets/ad3c7955-5dd4-4ae2-b6a5-3115dc6510f8)
> (직접 번역) Java는 2의 31승 (약 21억) 이상의 원소 개수에 대해 지원하지 않는 점에 있어 비판받아왔다. 이는 JAVA
언어의 한계이다. \<Java Language Specification\>의 Section 10.4를 확인해보면 "배열은 int 정수형을 통해 index가 명시되어야
한다... long 정수형 값을 통해 배열 구성요소에 접근하려고 시도한다면 컴파일 오류를 발생할 것이다."라고 적혀 있다. 큰 범위의
배열을 지원하기 위해서는 JVM의 변화도 필요하다. 이러한 제한은 컬렉션이 20억 개의 요소만을 가질 수 있게 하고, 2GB 이상의
연속 파일 세그먼트를 메모리 매핑할 수 없는 경우처럼 문제가 발생한다. Java는 또한 다차원 배열 역시 부족하여 과학 및 기술
컴퓨팅 성능에도 한계가 있다.

## 좀 더 이해해보자
- 메모리 관리 : 배열은 메모리 상에 연속된 공간을 차지하고 각 요소는 인덱스를 통해 접근한다. 배열의 크기는 해당 배열이 차지
하는 메모리 공간을 의미하며 이 크기는 **배열이 생성되면 한 번 결정되고 그 이후 변경될 수 없다.**
- int형의 범위 : 대부분의 경우에서 **int는 unsigned까지 감안하면 42억 정도를 지원** 하므로 충분하게 쓰인다.
- 메모리 효율성 : 더 큰 데이터 타입을 사용하여 배열 크기를 지정하면 **더 많은 메모리를 사용하여 메모리 효율성이 저하**된다.
(이 부분은 다음 부분인 'int 배열의 메모리 사용량'을 참고하자)

필요하게 된다면, 배열 내에 요소를 객체로서 사용하는 것도 가능하기 때문에 크기 제한을 피하기 위해 이런 방식을 사용하지 않을까.

## int 배열의 메모리 사용량
java의 primitive 자료형의 type size에 대해서 이전에 공부한 적이 있어서 기억해보자면, 다음과 같다.   

<table>
    <tr>
        <td><strong>자료형(type)</strong></td>
        <td><strong>메모리 크기(size)</strong></td>
    </tr>
    <tr>
        <td>byte</td>
        <td>8 bits (1 byte)</td>
    </tr>
    <tr>
        <td>short</td>
        <td>16 bits (2 byte)</td>
    </tr>
    <tr>
        <td>int</td>
        <td>32 bits (4 byte)</td>
    </tr>
    <tr>
        <td>long</td>
        <td>64 bits (8 byte)</td>
    </tr>
</table>

배열의 크기는 각 데이터 타입의 크기에 배열의 크기를 곱하는 방식으로 작동한다. 그러면 int 배열에 대해서 명시된 크기를 활용,
$$32×2,147,483,645=68,719,476,640$$ 즉 68억 비트가 나온다. 이는 8억 바이트 정도고, 약 8GB정도가 되는 것이다.
