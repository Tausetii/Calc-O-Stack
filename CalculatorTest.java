public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String infix = "a*b/(c-a)+d*e";
        String result1 = calculator.convertToPostfix(infix);
        System.out.println(infix + " in postfix is: \n" + result1);

        // a b * c a - / d e * +
        // a = 2, b = 3, c = 4, d = 5, e = 6
        String postfix = "23*42-/56*+";
        double result2 = calculator.evaluatePostFix(postfix);
        System.out.println(postfix + " = " + result2);
    }
}