# Leading Zero (전치영)
전치영은 0이 아닌 값에 대해 앞에 굳이 필요하지 않거나 자료의 일관성 등을 위해 사용된 0을 말한다.   
가령, 10만 저장해도 되는 것을 모두 6자리로 통일하기 위해 000010과 같이 사용하는 것이다.   
BOJ 1252 (이진수 덧셈) 문제에서는 이진수가 주어질 때 **Leading Zero가 사용되지 않는다는 조건이 없는** 상황이었다.   
그렇기 때문에 이 전치영을 없애줄 수 있는 방식이 필요했다.

## Step 1: Integer(Long) 클래스의 parseInt(parseLong) Method
Integer 혹은 Long 클래스가 지원하는 parseInt(parseLong) 메소드를 사용해보자.   

**public static int parseInt(String s) throws NumberFormatException**
- String 인수를 10진수 signed integer로 변환한다.
- String 내에 존재하는 것은 모두 10진수 숫자여야 하며, `-`(\u002D) 혹은 `+`(\u002B)`의 부호만 사용이 가능하다.

**parameters**
String s : integer 범위 내의 숫자로 변환할 수 있는 수 및 -, +만을 포함한 문자열

**Throws**
- NumberFormatException : string이 int로 변환할 수 있지 않은 경우 (범위 바깥이거나 불필요한 문자를 포함한 경우)

parseInt 혹은 parseLong의 경우, 이들은 모두 앞에 전치영이 있더라도 올바른 int 범위 내의 숫자로만 구성되어 있는 한 이를 
무시하고 문제없이 작동하여 숫자만을 변환해준다.
```JAVA
String num = "00000010";
int strToInt = Integer.parseInt(num);
```
와 같이 사용한다면 Leading Zero를 없앨 수 있다.

---

## Step 2: String 클래스의 ReplaceFirst Method
BOJ 문제에서 주어진 경우는 이진수의 길이가 최대 80글자기 때문에 대략 $$2^{80} < 3×10^{24}$$ 정도이므로 int, long 등으로 치환될 수 없다.
String에서 지원하는 ReplaceFirst 메소드를 사용해보자.   
(설명 원문 : https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#replaceFirst-java.lang.String-java.lang.String-)   

**public String replaceFirst(String regex, String replacement)**
- Since JAVA 1.4 version
- regex에서 주어진 정규식(regular expression) 조건에 맞는 부분 문자열(substring)을 replacement에 주어진 string으로 바꾼다.
- replaceFirst Method는 아래와 정확히 일치하는 결과를 산출한다:
```Pattern.compile(regex).matcher(str).replaceFirst(repl)```
- 백슬래시(\\)와 달러 기호($)는 대체 문자열 기능으로 인해 문자열로 취급되어 결과가 달라질 수 있다.

**parameters**
1. regex : String 내에서 구분해낼 substring의 regular expression 조건
2. replacement : 첫 번째로 regex에 합당하게 나타난 substring을 대체할 문자열

**Throws**
- PatternSyntaxException : regular expression 문법이 틀린 경우 예외가 나타날 수 있음

정규식을 활용한다면, 문자열 시작을 의미하는 `^`, 한 개 이상의 0 패턴인 `0+`을 활용해
```JAVA
String num = "00000010";
String str = num.replaceFirst("^+0", "");
```
와 같이 사용한다면 Leading Zero를 없앨 수 있다.

---

## Step 3: Stringutils 클래스의 stripStart Method
`org.apache.commons.lang`의 StringUtils 클래스는 따로 의존성을 디팬더시를 해주어야 한다.
```
<dependency>
   <groupId>org.apache.commons</groupId>
   <artifactId>commons-lang3</artifactId>
   <version>${apache.common.version}</version>
</dependency>
```
기본적으로 null-safe 한 연산을 해주기 때문에 아주 유용하고 기본 String에는 없는 유용한 메서드들이 있다.   
이 클래스 내의 stripStart 메소드를 사용해보자.
(설명 원문 : https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html)

**public static String stripStart(String str, String stripChars)**
- 문자열 시작 부분에서 문자 집합을 제거한다.
- stripChars에 주어지는 String이 null이라면 whitespace는 Character.isWhitespace(char)에 의해 판단된다.
```JAVA
 StringUtils.stripStart(null, *)          = null
 StringUtils.stripStart("", *)            = ""
 StringUtils.stripStart("abc", "")        = "abc"
 StringUtils.stripStart("abc", null)      = "abc"
 StringUtils.stripStart("  abc", null)    = "abc"
 StringUtils.stripStart("abc  ", null)    = "abc  "
 StringUtils.stripStart(" abc ", null)    = "abc "
 StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
```

**parameters**
1. str : 없앨 문자가 존재하는 String 형태의 문자열, null도 가능
2. stripChars : 없앨 문자(char 형태), null도 가능한데 입력하는 경우 whitespace를 지칭하는 것으로 간주

정규식을 활용한다면, 문자열 시작을 의미하는 `^`, 한 개 이상의 0 패턴인 `0+`을 활용해
```JAVA
import org.apache.commons.lang3.StringUtils;
...
String num = "00000010";
String stripedNum = StringUtils.stripStart(num, "0");
```
와 같이 사용한다면 Leading Zero를 없앨 수 있다.
다만 외부 라이브러리이므로 백준에서의 사용은 불가능하다!
