package com.pjatk_gui;

import java.util.Arrays;

public class Password {
    private final byte[] hash;
    private final byte[] salt;
    private final int iterations;

    public Password(int iterations, byte[] salt, byte[] hash){
        this.iterations = iterations;
        this.salt = salt.clone();
        this.hash = hash.clone();
    }

    //SET and GET
    public byte[] getHash() {
        return hash;
    }
    public int getIterations() {
        return iterations;
    }
    public byte[] getSalt() {
        return salt;
    }

    public boolean equals(byte[] b) {
        return Arrays.equals(hash, b);
    }
}
