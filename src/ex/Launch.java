/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.beans.PropertyVetoException;

/**
 *
 * @author 10405
 */
public class Launch {

    public static void main(String[] args) throws PropertyVetoException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                JWindow window = new JWindow();
//                window.getContentPane().add(new JLabel("Splash Screen"));
//                window.setBounds(500, 150, 300, 200);
//                window.setVisible(true);
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                window.setVisible(false);
                new MainFrame("樹狀結構範例").setVisible(true);
            }
        });
    }
}