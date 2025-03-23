import org.junit.*;

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

    @Test
    public void testConvertToPostfix() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(calculator.convertToPostfix("a+b"), "ab+");
        Assert.assertEquals(calculator.convertToPostfix("(a+b)*c"), "ab+c*");
        Assert.assertEquals(calculator.convertToPostfix("a+b*c"), "abc*+");
        Assert.assertEquals(calculator.convertToPostfix("a-b+c"), "ab-c+");
        Assert.assertEquals(calculator.convertToPostfix("a^b^c"), "abc^^");
        Assert.assertEquals(calculator.convertToPostfix("a/b*(c+(d-e))"), "ab/cde-+*");
        Assert.assertEquals(calculator.convertToPostfix("(a+b)/(c-d)"), "ab+cd-/");
        Assert.assertEquals(calculator.convertToPostfix("a/(b-c)*d"), "abc-/d*");
        Assert.assertEquals(calculator.convertToPostfix("a-(b/(c-d)*e+f)^g"), "abcd-/e*f+g^-");
    }

    @Test
    public void testEvaluatePostFix() {
        Calculator calculator = new Calculator();
        // a+b -> ab+, 2+3 = 5
        Assert.assertEquals(calculator.evaluatePostFix("23+"), 5, 0.0001);
        // (a+b)*c -> ab+c*, (4+2)*3 = 18
        Assert.assertEquals(calculator.evaluatePostFix("42+3*"), 18, 0.0001);
        // a+b*c -> abc*+, 6+4*8 = 38
        Assert.assertEquals(calculator.evaluatePostFix("648*+"), 38, 0.0001);
        // a-b+c -> ab-c+, 9-1+3 = 11
        Assert.assertEquals(calculator.evaluatePostFix("91-3+"), 11, 0.0001);
        // a^b^c -> abc^^, 2^2^3 = 256
        Assert.assertEquals(calculator.evaluatePostFix("223^^"), 256, 0.0001);
        // a/b*(c+(d-e)) -> ab/cde-+*, 1/2*(6+(7-3)) = 5
        Assert.assertEquals(calculator.evaluatePostFix("12/673-+*"), 5, 0.0001);
        // (a+b)/(c-d) -> ab+cd-/, (3+8)/(6-4) = 5.5
        Assert.assertEquals(calculator.evaluatePostFix("38+64-/"), 5.5, 0.0001);
        // a/(b-c)*d -> abc-/d*, 1/(2-3)*4 = -4
        Assert.assertEquals(calculator.evaluatePostFix("123-/4*"), -4, 0.0001);
        // a-(b/(c-d)*e+f)^g -> abcd-/e*f+g^-, 1-(2/(3-4)*5+6)^7 = 16385
        Assert.assertEquals(calculator.evaluatePostFix("1234-/5*6+7^-"), 16385, 0.0001);
    }
}