package com.androidpi.training.asm;

public class SampleClass extends SampleAbstractClass implements SampleInterface{

    private int x;
    private int y;
    public String name;

    @Override
    public void doSomething(String input) {

    }

    @Override
    public void doThis() {

    }

    @Override
    public int doThat() {
        return 0;
    }

    public String aMethodOfSampleClass(String arg) {
        return String.format("return : %s", arg);
    }

    public static class SampleInnerClass {

        private String name;

        public String innerMethod() {
            return "Inner Method";
        }
    }
}
