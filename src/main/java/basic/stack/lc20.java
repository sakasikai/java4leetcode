package basic.stack;

import java.util.Stack;

class lc20 {
    public boolean isValid(String s) {
        // TODO Stack
        // TODO 遍历String中每个char
        Stack<Character> stk = new Stack<>(); // 必须是包装类 Character
        for(char c: s.toCharArray()){ // String转换成Collection，toCharArray
            stk.push(c);
            // reduce recurrently
            while(stk.size()>=2 && !isLeft(stk.peek())){
                c = stk.pop();
                if(match(stk.peek(), c)) stk.pop();
                else{
                    stk.push(c);
                    break;
                }
            }
        }

        return stk.empty();
    }

    boolean isLeft(char c){
        return c=='(' || c=='[' || c=='{';
    }

    // 左括和右括是否匹配
    boolean match(char a, char b){
        return a=='(' && b==')' || a=='[' && b==']' || a=='{' && b == '}';
    }
}