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
                new MainFrame("樹狀結構範例").setVisible(true);
            }
        });
    }
}