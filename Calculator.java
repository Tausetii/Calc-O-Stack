public class Calculator {

    private int getPrecedence(char value) {
        return switch (value) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    } // end getPrecedence

    public String convertToPostfix(String infix) {
        StackInterface<Character> operatorStack = new LinkedStack<>();
        String postfix = "";
        char nextCharacter;
        char topOperator;
        for (int i = 0; i < infix.length(); i++) {
            nextCharacter = infix.charAt(i);
            switch (nextCharacter) {
                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' -> postfix += nextCharacter;
                case '^' -> operatorStack.push(nextCharacter);
                case '+', '-', '*', '/' -> {
                    while (!operatorStack.isEmpty() && getPrecedence(nextCharacter) <= getPrecedence(operatorStack.peek())) {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                }
                case '(' -> operatorStack.push(nextCharacter);
                case ')' -> {
                    topOperator = operatorStack.pop();
                    while (topOperator != '(') {
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                }
                default -> {}
            }
        }
        while (!operatorStack.isEmpty()) {
            topOperator = operatorStack.pop();
            postfix += topOperator;
        }
        return postfix;
    } // end convertToPostfix

    public double evaluatePostFix(String postfix){
        ResizableArrayStack<Double> valueStack = new ResizableArrayStack<>();
        double result;

        for (int i = 0; i < postfix.length(); i++) {
            char nextCharacter = postfix.charAt(i);
            
            switch(nextCharacter){
                case '+', '-', '*', '/', '^' -> {
                    double operandTwo = valueStack.pop();
                    double operandOne = valueStack.pop();
                    switch(nextCharacter){
                        case '+' -> {
                            result = operandOne + operandTwo;
                            valueStack.push(result);
                        }
                        case '-' -> {
                            result = operandOne - operandTwo;
                            valueStack.push(result);
                        }
                        case '*' -> {
                            result = operandOne * operandTwo;
                            valueStack.push(result);
                        }
                        case '/' -> {
                            result = operandOne / operandTwo;
                            valueStack.push(result);
                        }
                        case '^' -> {
                            result = Math.pow(operandOne, operandTwo);
                            valueStack.push(result);
                        }
                    } // end switch
                }
                case '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> {
                    valueStack.push((double) Character.getNumericValue(nextCharacter));
                }
                default -> {}
            }

        } // end evaluatePostFix

        return valueStack.peek();
    } 
}