package com.company;

import Controllers.StartController;
import Views.StartView;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                new StartController(new StartView());
            }
        });


    }
}
