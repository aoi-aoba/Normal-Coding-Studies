package queue;
import stack.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {
    private static boolean isPalindrome(String target) {
        LinkedStack stack = new LinkedStack();
        LinkedQueue queue = new LinkedQueue();
        for (int i=0; i < target.length(); i++) {
            stack.push(target.charAt(i));
            queue.enqueue(target.charAt(i));
        }
        // 임의의 문자열을 받으면 문자들을 앞에서부터 읽으면서 스택과 큐에 동시에 저장

        while(!stack.isEmpty() && stack.pop() == queue.dequeue()) {}
        // 스택과 큐에서 하나씩 읽어들이면서 삭제하는데 읽어 들인 문자가 동일하면 계속 진행
        // 이때 isEmpty = false 유지된 상태로 pop == dequeue 유지되면 condition true, 계속 반복됨
        // 왜 이게 가능하냐면, 스택과 큐의 원소 삭제 위치가 서로 반대인데 좌우동형이 아니면 서로 같은 글자가 아니라서 반복될 수 없기 때문임

        if(stack.isEmpty()) return true; // 큐가 비어서 중단되면 좌우동형, 스택이 빈 것을 확인해도 됨
        else return false; // 아니라면 좌우동형 아님
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        boolean result = isPalindrome(str);
        System.out.println(str + " is" + (result ? " " : " not") + " a palindrome");
    }
}
