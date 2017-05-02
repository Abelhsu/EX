/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 *
 * @author 10405
 */
public class MainAction extends AbstractAction implements ItemListener{

    public MainAction(String text, ImageIcon icon,
            String desc, Integer mnemonic) {
        super(text, icon);
//        putValue(SHORT_DESCRIPTION, desc);
//        putValue(MNEMONIC_KEY, mnemonic);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action event detected: " + e.getActionCommand());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("itemStateChanged");
    }
}