#!/usr/bin/env sh

java -classpath libs/asm-6.2.jar:libs/asm-util-6.2.jar:../out/production/classes \
    org.objectweb.asm.util.ASMifier \
    com.androidpi.training.asm.SampleClass