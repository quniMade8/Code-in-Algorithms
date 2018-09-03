package main.demo;

import main.infrastructure.Stack;

public class Evaluate {
    // Dijkstra双栈算术表达求值
    public static void main(String[] args) {
        Stack<Double> vals = new Stack<>();
        Stack<String> ops = new Stack<>();
        String expr = "";
        if (args.length < 1) {
            System.out.println("Missing args");
            System.exit(0);
        }
        expr = args[0];
        for (char c : expr.toCharArray()) {
            if (c == '(') ;
            else if (c == '+' || c == '-' || c == '*' || c == '/')
                ops.push(c + "");
            else if (c == ')') {
                double result = vals.pop();
                String op = ops.pop();
                if (op.equals("+")) result = result + vals.pop();
                else if (op.equals("-")) result = result - vals.pop();
                else if (op.equals("*")) result = result * vals.pop();
                else if (op.equals("/")) result = result / vals.pop();
                vals.push(result);
            } else
                vals.push(Double.parseDouble(c + ""));
        }
        System.out.println(vals.pop());
    }
}
