package stack;

public class Solution {

    /*
    给定一个只包括 （）{} []的字符串
    判断字符串是否有效
    括号必须以正确的顺序关闭
     */

    public static boolean isValid (String str) {

        ArrayStack<Character> stack = new ArrayStack();
        for (int i=0 ; i<str.length();i++) {
            char c = str.charAt(i);
            if (c=='{' || c=='('|| c=='['){
                stack.push(c);
            }
            else{
                if (stack.isEmpty()){
                    return false;
                }
                char topChar = stack.pop();
                if (c=='}' && topChar !='{'){
                    return false;
                }
                if (c==']' && topChar !='['){
                    return false;
                }
                if (c==')' && topChar !='('){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[]args){
        boolean flag = Solution.isValid("{[()]}");
        System.out.println(flag);
        System.out.println(Solution.isValid("{[}()]"));
    }



}
