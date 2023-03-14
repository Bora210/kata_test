import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter expression");
        String expression = scan.nextLine();
        int number;
        String[] rom_units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] rom_tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] operatorsAndNumbers = {"+", "-", "*", "/", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int count = 0;
        int index = 0;

        for (int i = 0; i < expression.length(); i++) {
            String[] sub_operatorsAndNumbers = Arrays.copyOfRange(operatorsAndNumbers, 0, 4); // создал подмассив массива "operatorsAndNumbers"
            if (Arrays.asList(sub_operatorsAndNumbers).contains(String.valueOf(expression.charAt(i)))) {  // проверяю на вхождение в "sub_operatorsAndNumbers" элементов (+, -, /, *) из "expression" предварительно приведя тип данных Char в String
                count += 1;      // если вхождение есть "count" - увеличиваю на 1
                index = i;
            }
        }
        if (count == 1) {
            int arabCount = 0;
            int romCount = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (Arrays.asList(operatorsAndNumbers).indexOf(String.valueOf(expression.charAt(i))) >= 5 && Arrays.asList(operatorsAndNumbers).indexOf(String.valueOf(expression.charAt(i))) <= 13) {
                    arabCount += 1;
                } else if (Arrays.asList(operatorsAndNumbers).indexOf(String.valueOf(expression.charAt(i))) >= 15) {   // создаю две переменные-счетчики, в одну кладу кол-во вхождений арабских цифр в другую римских
                    romCount += 1;
                }
            }
            if (arabCount > 0 && romCount > 0) {   // если это условие верно - выскакивает исключение
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            } else if (arabCount > 0 && romCount == 0 && index > 0 && index < expression.length() - 1) {     // если это условие верно - возможен арабский вариант
                int firstOperand = Integer.parseInt(expression.substring(0, index));     // часть введенного выражения пользователем до символа (+, -, /, *), конвертированная из String в Int
                int secondOperand = Integer.parseInt(expression.substring(index + 1));  // часть введенного выражения пользователем после символа (+, -, /, *), конвертированная из String в Int
                if (firstOperand > 0 && secondOperand != 0) {   // проверяю - не является ли первый или второй операнд "0"
                    if (expression.charAt(index) == '+') {
                        System.out.println(firstOperand + secondOperand);   // в зависимости от оператора (+, -, /, *) вывожу результат
                    } else if (expression.charAt(index) == '-') {
                        System.out.println(firstOperand - secondOperand);
                    } else if (expression.charAt(index) == '*') {
                        System.out.println(firstOperand * secondOperand);
                    } else if (expression.charAt(index) == '/') {
                        System.out.println(firstOperand / secondOperand);
                    }
                }
                else {
                    System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - операнды должны быть в диапазоне от 1 и до 10?");
                }
            } else if (arabCount > 0 && romCount == 0 && index == 0 || index == expression.length() - 1) {  // если цифры только арабские, а оператор стоит в начале или в конце введенного пользователем выражения
                System.out.println("throws Exception //т.к. строка не является математической операцией");  // выскакивает исключение
            } else if (arabCount == 0 && romCount > 0 && index > 0 && index < expression.length() - 1) {    // если это условие верно - возможен римский вариант
                String firstOperand1 = expression.substring(0, index);                   // часть введенного выражения пользователем до символа (+, -, /, *), конвертированная из String в Int
                String secondOperand1 = expression.substring(index + 1);       // часть введенного выражения пользователем после символа (+, -, /, *), конвертированная из String в Int
                if (expression.charAt(index) == '+') {                                 // в зависимости от оператора (+, -, /, *) результат сохраняю в переменную "number"
                    number = Arrays.asList(operatorsAndNumbers).indexOf(firstOperand1) + Arrays.asList(operatorsAndNumbers).indexOf(secondOperand1) - 28;
                    System.out.println(rom_tens[number / 10] + rom_units[number % 10]);   // вывод результата римскими символами
                } else if (expression.charAt(index) == '-') {
                    number = Arrays.asList(operatorsAndNumbers).indexOf(firstOperand1) - Arrays.asList(operatorsAndNumbers).indexOf(secondOperand1);
                    if (number < 0) {                               // проверка на отрицательный результат
                        System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                    } else {
                        System.out.println(rom_tens[number / 10] + rom_units[number % 10]);
                    }
                } else if (expression.charAt(index) == '*') {
                    number = (Arrays.asList(operatorsAndNumbers).indexOf(firstOperand1) - 14) * (Arrays.asList(operatorsAndNumbers).indexOf(secondOperand1) - 14);
                    System.out.println(rom_tens[number / 10] + rom_units[number % 10]);
                } else if (expression.charAt(index) == '/') {
                    number = (Arrays.asList(operatorsAndNumbers).indexOf(firstOperand1) - 14) / (Arrays.asList(operatorsAndNumbers).indexOf(secondOperand1) - 14);
                    System.out.println(rom_tens[number / 10] + rom_units[number % 10]);
                }
            } else if (arabCount == 0 && romCount > 0 && index == 0) { // если цифры только арабские, а оператор стоит в начале или в конце введенного пользователем выражения
                System.out.println("throws Exception //т.к. строка не является математической операцией");  // выскакивает исключение
            }
        } else if (count == 0) {   // если переменная-счетчик равна нулю - оператор (+, -, /, *) во введенном выражении пользователем отсутствует
            System.out.println("throws Exception //т.к. строка не является математической операцией");  // и выскакивает исключение
        } else {   //  в этом случае кол-во операторов (+, -, /, *) введенных пользователем более одного
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");  // и выскакивает исключение
        }
    }
}
