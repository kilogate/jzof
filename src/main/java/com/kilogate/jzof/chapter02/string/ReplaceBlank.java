package com.kilogate.jzof.chapter02.string;

/**
 * 替换空格
 *
 * @author fengquanwei
 * @create 2020/2/23 下午12:23
 **/
public class ReplaceBlank {
    public static char[] replaceBlank(char[] chars) {
        if (chars == null || chars.length == 0) {
            return new char[0];
        }

        int blankCount = 0;

        for (char c : chars) {
            if (c == ' ') {
                blankCount++;
            }
        }

        char[] result = new char[chars.length + 2 * blankCount];

        int index = 0;
        for (char c : chars) {
            if (c == ' ') {
                result[index++] = '%';
                result[index++] = '2';
                result[index++] = '0';
            } else {
                result[index++] = c;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        char[] chars1 = "".toCharArray();
        char[] chars2 = "HelloWorld".toCharArray();
        char[] chars3 = "Hello world!".toCharArray();
        char[] chars4 = " Hello  world! ".toCharArray();

        System.out.println(String.format("chars: %s, result: %s", String.valueOf(chars1), String.valueOf(replaceBlank(chars1))));
        System.out.println(String.format("chars: %s, result: %s", String.valueOf(chars2), String.valueOf(replaceBlank(chars2))));
        System.out.println(String.format("chars: %s, result: %s", String.valueOf(chars3), String.valueOf(replaceBlank(chars3))));
        System.out.println(String.format("chars: %s, result: %s", String.valueOf(chars4), String.valueOf(replaceBlank(chars4))));
    }
}
