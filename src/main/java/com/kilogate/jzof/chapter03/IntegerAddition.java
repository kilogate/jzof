package com.kilogate.jzof.chapter03;

import java.util.Scanner;

/**
 * 实现任意两个整数的加法运算
 *
 * @author fengquanwei
 * @create 2020/3/12 下午11:35
 **/
public class IntegerAddition {
    public static String sum(String numStr1, String numStr2) {
        // 1 校验数字

        if (numStr1 == null || numStr1.length() == 0 || numStr2 == null || numStr2.length() == 0) {
            return null;
        }

        char[] num1 = numStr1.toCharArray();
        char[] num2 = numStr2.toCharArray();

        if (!isNumber(num1) || !isNumber(num2)) {
            return null;
        }

        // 2 取符号位
        char sign1 = num1[0] == '-' ? '-' : '+';
        char sign2 = num2[0] == '-' ? '-' : '+';

        // 3 去符号位和前缀0
        num1 = getTrimNumber(num1);
        num2 = getTrimNumber(num2);

        // 4 计算结果

        // 4.1 符号相同求和
        if (sign1 == sign2) {
            char[] sum;

            if (isMoreThan(num1, num2)) {
                sum = getSum(num1, num2);
            } else {
                sum = getSum(num2, num1);
            }

            if (sum.length == 1 && sum[0] == '0') {
                return String.valueOf(sum);
            }

            if (sign1 == '+') {
                return String.valueOf(sum);
            } else {
                return "-" + String.valueOf(sum);
            }
        }

        // 4.2 符号不同求差
        char[] sub;
        char sign;

        if (isMoreThan(num1, num2)) {
            sub = getSub(num1, num2);
            sign = sign1;
        } else {
            sub = getSub(num2, num1);
            sign = sign2;
        }

        if (sub.length == 1 && sub[0] == '0') {
            return String.valueOf(sub);
        }

        if (sign == '+') {
            return String.valueOf(sub);
        } else {
            return "-" + String.valueOf(sub);
        }
    }

    private static char[] getSum(char[] big, char[] small) {
        char[] result = new char[big.length + 1];

        // 从第一位开始加，直到加到最后一位
        boolean carry = false;
        for (int i = 1; i <= big.length; i++) {
            int bigIndex = big.length - i;
            int smallIndex = small.length - i;

            int b = bigIndex >= 0 ? big[bigIndex] - '0' : 0;
            int s = smallIndex >= 0 ? small[smallIndex] - '0' : 0;

            int sum = b + s;

            if (carry) {
                sum += 1;
            }

            if (sum > 9) {
                carry = true;
                sum -= 10;
            } else {
                carry = false;
            }

            result[bigIndex + 1] = (char) (sum + '0');
        }

        if (carry) {
            result[0] = '1';
        } else {
            result = getTrimNumber(result);
        }

        return result;
    }

    private static char[] getSub(char[] big, char[] small) {
        char[] result = new char[big.length];

        // 从第一位开始减，直到减到最后一位
        boolean borrow = false;
        for (int i = 1; i <= big.length; i++) {
            int bigIndex = big.length - i;
            int smallIndex = small.length - i;

            int b = bigIndex >= 0 ? big[bigIndex] - '0' : 0;
            int s = smallIndex >= 0 ? small[smallIndex] - '0' : 0;

            if (borrow) {
                b -= 1;
            }

            if (b < s) {
                borrow = true;
                b += 10;
            } else {
                borrow = false;
            }

            result[bigIndex] = (char) (b - s + '0');
        }

        if (result[0] == 0) {
            result = getTrimNumber(result);
        }

        return result;
    }

    private static char[] getTrimNumber(char[] num) {
        int trimLength = 0;

        // 最后一位不能切除
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i] == '+' || num[i] == '-' || num[i] == '0' || num[i] == 0) {
                trimLength++;
            } else {
                break;
            }
        }

        if (trimLength == 0) {
            return num;
        }

        char[] result = new char[num.length - trimLength];

        int idx = 0;
        for (int i = trimLength; i < num.length; i++) {
            result[idx++] = num[i];
        }

        return result;
    }

    private static boolean isNumber(char[] num) {
        // 首位
        char first = num[0];

        // 首位是不是符号位？
        boolean sign = first == '+' || first == '-';

        // 只有符号位不行
        if (sign && num.length == 1) {
            return false;
        }

        // 首位必须是数字或者符号
        if (!Character.isDigit(first) && !sign) {
            return false;
        }

        // 其他位必须是数字
        for (int i = 1; i < num.length; i++) {
            if (!Character.isDigit(num[i])) {
                return false;
            }
        }

        return true;
    }

    private static boolean isMoreThan(char[] num1, char[] num2) {
        if (num1.length > num2.length) {
            return true;
        }

        if (num1.length < num2.length) {
            return false;
        }

        for (int i = 0; i < num1.length; i++) {
            if (num1[i] - '0' > num2[i] - '0') {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== 欢迎使用加法器 =====");

            System.out.println("请输入第一个整数并回车");
            String num1 = scanner.nextLine();

            if (num1.equals("exit")) {
                System.out.println("===== 谢谢使用 =====");
                System.exit(0);
            }

            System.out.println("请输入第二个整数并回车");
            String num2 = scanner.nextLine();

            if (num2.equals("exit")) {
                System.out.println("===== 谢谢使用 =====");
                System.exit(0);
            }

            System.out.println(String.format("结果：%n%s + %s = %s%n", num1, num2, sum(num1, num2)));
        }
    }
}
