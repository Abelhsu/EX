/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author 10405
 */
public class NewClass extends JFrame {

    private JButton[] btns;
    private final Container cp;
    private final Dimension dim;

    public NewClass() throws HeadlessException {
        super("New Class");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        cp = getContentPane();
        btns = new JButton[20];
        for (int i = 0; i < 20; i++) {
            btns[i] = new JButton("Button " + (i + 1));
            cp.add(btns[i]);
        }
        dim = getToolkit().getScreenSize();
        setLocation((int) (dim.width - getWidth()) / 2, (int) (dim.height - getHeight()) / 2);
        pack();
    }

    public static void main(String[] args) {
        new NewClass().setVisible(true);
    }

}
