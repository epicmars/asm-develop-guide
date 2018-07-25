package com.androidpi.training.asm.event.clazz.generating;

public class AsmClassLoader extends ClassLoader{

    /**
     * Load class from byte array presentation of a compiled class.
     * @param b  byte array presentation of a compiled class.
     * @return   the loaded class.
     */
    public Class<?> loadClass(byte[] b) {
        return defineClass(null, b, 0, b.length);
    }

}
