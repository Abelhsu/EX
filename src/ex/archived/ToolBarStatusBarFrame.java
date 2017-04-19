/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex.archived;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/**
 *
 * @author 10405
 */
public class ToolBarStatusBarFrame extends JFrame {

    private JMenuBar mb;
    private JMenu mn1, mn2, mn3;
    private JToolBar toolBar;
    private JPanel panel;
    private JTextArea textArea;
    private JButton btn1, btn2, btn3, btn4, btn5, btnStatus1, btnStatus2, btnStatus3;
    private Font font1;
    private Container cp;

    public ToolBarStatusBarFrame() {
        initComponents();
    }

    public ToolBarStatusBarFrame(String title) {
        this();
        this.setTitle(title);
    }

    private void initMenuBar() {
        mb = new JMenuBar();
        mn1 = new JMenu("File");
        mn2 = new JMenu("Edit");
        mn3 = new JMenu("Help");
        toolBar = new JToolBar();
        mb.add(mn1);
        mb.add(mn2);
        mb.add(mn3);
        setJMenuBar(mb);
    }

    private void initToolBar() {
        btn1 = new JButton(new ImageIcon(getClass().getResource("./icon/copy.gif")));
        btn2 = new JButton(new ImageIcon(getClass().getResource("./icon/copy.gif")));
        btn3 = new JButton(new ImageIcon(getClass().getResource("./icon/paste.gif")));
        btn4 = new JButton(new ImageIcon(getClass().getResource("./icon/alert_lrg.gif")));
        btn5 = new JButton(new ImageIcon(getClass().getResource("./icon/requiredBang.gif")));
        toolBar.add(btn1);
        toolBar.add(btn2);
        toolBar.add(btn3);
        toolBar.add(btn4);
        toolBar.add(btn5);
        getContentPane().add(toolBar, BorderLayout.NORTH);
    }

    private void initTextArea() {
        panel = new JPanel();
        textArea = new JTextArea(5, 30);
        btnStatus1 = new JButton("狀態1");
        btnStatus2 = new JButton("狀態2");
        btnStatus3 = new JButton("狀態3");

        font1 = new Font("微軟正黑體", Font.PLAIN, 32);
        panel.setSize(800, 40);
        panel.add(btnStatus1);
        panel.add(btnStatus2);
        panel.add(btnStatus3);
        getContentPane().add(panel, BorderLayout.SOUTH);
        textArea.setFont(font1);
        getContentPane().add(textArea, BorderLayout.CENTER);
    }

    private void initFrame() {
        setSize(800, 640);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        initMenuBar();
        initToolBar();
        initTextArea();
        initFrame();
    }
}
