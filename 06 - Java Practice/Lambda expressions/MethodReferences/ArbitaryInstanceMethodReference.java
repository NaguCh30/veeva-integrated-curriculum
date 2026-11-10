package MethodReferences;

interface ConvertString {
    String convertString(String string);
}

public class ArbitaryInstanceMethodReference {
    public static void main(String[] args) {
        ConvertString convert = String::toUpperCase;

        System.out.println(convert.convertString("nagu chavala"));
    }
}
