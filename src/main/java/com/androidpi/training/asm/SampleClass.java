package com.androidpi.training.asm;

public class SampleClass extends SampleAbstractClass implements SampleInterface{

    private int x;
    private int y;
    public String name;

    @Override
    public void doSomething(String input) {

    }

    @Override
    public void doThis(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            System.out.println("doThis is interrupted!");
        }
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
