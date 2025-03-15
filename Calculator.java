

public class Calculator<T>{


public double evaluatePostFix(ResizableArrayStack<T> postfix){
        ResizableArrayStack<Double> valueStack = new ResizableArrayStack<>();
        double result;

        while (!postfix.isEmpty()) {
            char nextCharacter = (char) postfix.pop();
            
            switch(nextCharacter){
                case '+': case '-': case'*': case '/':
                    double operandTwo = valueStack.pop();
                    double operandOne = valueStack.pop();
                switch(nextCharacter){
                    case '+':
                        result = operandOne + operandTwo;
                        valueStack.push(result);
                        break;
                    case '-':
                        result = operandOne - operandTwo;
                        valueStack.push(result);
                        break;
                    case '*':
                        result = operandOne * operandTwo;
                        valueStack.push(result);
                        break;
                    case '/':
                        result = Math.floor(operandOne / operandTwo);
                        valueStack.push(result);
                        break;
                    case '^':
                        result = Math.pow(operandOne, operandTwo);
                        valueStack.push(result);
                        break;
                } // end switch
                case '1': case'2': case'3': case'4': case'5': case'6': case'7': case'8': case'9': case'0':
                    valueStack.push((double) Character.getNumericValue(nextCharacter));
                    break;
                default:
                    break;
                
            }

    } // end evaluatePostFix

    return valueStack.peek();
}
}