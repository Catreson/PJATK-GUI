package com.pjatk_gui;

public class Supervisor {

    private static Supervisor supervisor;

    private Supervisor(){}

    public static Supervisor getSupervisor(){
        if (supervisor == null) 
            supervisor = new Supervisor();
        return supervisor;
    }
}
