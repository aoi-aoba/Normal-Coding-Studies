package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StackStringReverse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String reversedStr = reverse(str);
        System.out.println("Input : " + str);
        System.out.println("Reversed : " + reversedStr);
        br.close();
    }

    private static String reverse(String s) {
        ArrayStack<Character> st = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++)
            st.push(s.charAt(i));
        String output = "";
        while (!st.isEmpty())
            output = output + st.pop();
        return output;
    }
}