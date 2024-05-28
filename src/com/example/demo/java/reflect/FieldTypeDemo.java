package com.example.demo.java.reflect;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class FieldTypeDemo {
    public static void main(String[] args) {
        Field []fields = User.class.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getGenericType().toString());
        }
    }

    private static class User{
        private Integer IntegerF;
        private int intF;
        private String StringF;
        private boolean booleanF;
        private Boolean BooleanF;
        private char charF;
        private Date DateF;
        private List<String> list;

        public Integer getIntegerF() {
            return IntegerF;
        }

        public void setIntegerF(Integer integerF) {
            IntegerF = integerF;
        }

        public int getIntF() {
            return intF;
        }

        public void setIntF(int intF) {
            this.intF = intF;
        }

        public String getStringF() {
            return StringF;
        }

        public void setStringF(String stringF) {
            StringF = stringF;
        }

        public boolean isBooleanF() {
            return booleanF;
        }

        public void setBooleanF(boolean booleanF) {
            this.booleanF = booleanF;
        }

        public Boolean getBooleanF() {
            return BooleanF;
        }

        public void setBooleanF(Boolean booleanF) {
            BooleanF = booleanF;
        }

        public char getCharF() {
            return charF;
        }

        public void setCharF(char charF) {
            this.charF = charF;
        }
    }
}
