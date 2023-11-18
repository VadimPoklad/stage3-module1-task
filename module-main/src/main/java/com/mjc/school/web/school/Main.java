package com.mjc.school.web.school;

import com.mjc.school.web.implementation.Application;

public class Main {
    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.run();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}