package com.mjc.school.web.implementation;

public class Application {
    Controller controller = Controller.getInstance();
    public void run(){
        runLoop();
    }
    private void runLoop(){
        while(controller.continueLoop) {
            runConsoleGUI();
        }
    }
    private void runConsoleGUI(){
        controller.controlMenuView();
    }
}
