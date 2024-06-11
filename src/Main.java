import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите математическое выражение: ");
            String input = scanner.nextLine();
            try {
                System.out.println("Результат: " + calc(input));
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.exit(1);
            }
            System.out.println();
        }

    }

    public static String calc(String input) throws Exception {

        Converter converter = new Converter();

        String[] parts = input.split(" ");
        if(parts.length !=3){
            throw new Exception("Неверный формат ввода");
        }

        if(converter.isRoman(parts[0]) == converter.isRoman(parts[2])){
            int a, b;
            String  operator = parts[1];

            boolean isRoman = converter.isRoman(parts[0]);

            if(isRoman){
                    a = converter.romanToInt(parts[0]);
                    b = converter.romanToInt(parts[2]);
            }else {

                try {
                    a = Integer.parseInt(parts[0]);
                    b = Integer.parseInt(parts[2]);
                }catch (NumberFormatException e){
                    throw new Exception("Неверный формат ввода");
                }
            }

            if (a < 1 || a > 10 || b < 1 || b > 10) {
                if(isRoman){
                    throw new Exception("Числа должны быть от I до X включительно");
                }else{
                    throw new Exception("Числа должны быть от 1 до 10 включительно");
                }
            }

            if(converter.trueRoman(parts[0]) || converter.trueRoman(parts[2])){
                throw new Exception("Неверный формат ввода римских цифр");
            }


            int result;
            switch (operator){
                case "+":
                    result = a + b;
                    break;

                case "-":
                    result = a - b;
                    break;

                case "*":
                    result = a * b;
                    break;

                case "/":
                    if (b != 0) {
                        result = a / b;
                        break;
                    } else {
                        throw new Exception("Делить на ноль нельзя");
                    }

                default:
                    throw new Exception("Арифметическое действие не было найдено");
            }

            if(isRoman){
                if(result < 1){
                    throw new Exception("В римской системе нет отрицательных чисел");
                }else{
                    return converter.intToRoman(result);
                }

            }
            else{
                return String.valueOf(result);
            }

        }else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
    }
}