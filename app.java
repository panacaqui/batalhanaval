package br.edu.ifg.batalhanaval.gui;

import javax.swing.JFrame;

public class app {

    public static void main(String[] args) {
        Gui gui = new Gui();

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setResizable(false);

        gui.setSize(610, 680);
        gui.setVisible(true);
    }

}
