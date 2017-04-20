/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.embed.swing.JFXPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author 10405
 */
public class MainFrame extends JFrame {

    private JMenuBar mbMain;
    private JMenu mnFile;
    private JMenu mnEdit;
    private JMenu mnHelp;
    private JToolBar tbMain;
    private JTree trMain;
    private JPanel plContent;
    private JPanel plStatusBar;
    private JPanel plSearch;
    private JMenuItem miCreate;
    private JMenuItem miOpen;
    private JMenuItem miSave;
    private JLabel lblStatus1;
    private JLabel lblStatus2;
    private JLabel lblStatus3;
    private JLabel keyword;
    private JButton btnCreate;
    private JButton btnOpen;
    private JButton btnSave;
    private JMenuItem miClose;
    private JTextArea taContent;
    private DefaultMutableTreeNode dmtnOOP;
    private DefaultMutableTreeNode dmtnC;
    private DefaultMutableTreeNode dmtnJava;
    private DefaultMutableTreeNode dmtnRoot;
    private DefaultMutableTreeNode dmtnHtml;
    private JScrollPane spTreeMain;
    private JFXPanel fxpMain;

    public MainFrame() {
        initComponents();
    }

    public MainFrame(String title) {
        this();
        this.setTitle(title);
    }

    private void initComponents() {
        // UIManager's properties
        UIManager.put("Menu.font", new Font("微軟正黑體", Font.PLAIN, 16));
        UIManager.put("MenuItem.font", new Font("微軟正黑體", Font.PLAIN, 16));
        UIManager.put("Tree.font", new Font("微軟正黑體", Font.PLAIN, 14));
        // Initilize objects
        initMenuBar();
        initToolBar();
        initTreeScrollPane();
        initStatusBar();
        initSearch();
        initTextArea();
        initPanel();
        initFrame();
    }

    private void initToolBar() {
        tbMain = new JToolBar();
        btnCreate = new JButton();
        btnOpen = new JButton();
        btnSave = new JButton();
        tbMain.add(btnCreate);
        tbMain.add(btnOpen);
        tbMain.add(btnSave);
    }

    private void initMenuBar() {
        mbMain = new JMenuBar();

        //
        mnFile = new JMenu("檔案");
        miCreate = new JMenuItem("新增");
        miOpen = new JMenuItem("開啟");
        miSave = new JMenuItem("存檔");
        miClose = new JMenuItem("關閉");
        mnFile.add(miCreate);
        mnFile.add(miOpen);
        mnFile.add(miSave);
        mnFile.add(miClose);
        //
        miClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //
        mnEdit = new JMenu("編輯");
        mnHelp = new JMenu("輔助");
        mbMain.add(mnFile);
        mbMain.add(mnEdit);
        mbMain.add(mnHelp);
    }

    private void initFrame() {
        setSize(800, 640);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(mbMain);
        getContentPane().add(plContent, BorderLayout.WEST);
        pack();
    }

    private void initTreeScrollPane() {

        //
        dmtnOOP = new DefaultMutableTreeNode("物件導向程式設計", true);
        dmtnC = new DefaultMutableTreeNode("C 語言", true);
        dmtnJava = new DefaultMutableTreeNode("Java 語言", true);
        dmtnHtml = new DefaultMutableTreeNode("Html 語言", true);
        dmtnRoot = new DefaultMutableTreeNode("根目錄", true);
        dmtnRoot.add(dmtnOOP);
        dmtnOOP.add(dmtnC);
        dmtnOOP.add(dmtnJava);
        dmtnOOP.add(dmtnJava);
        dmtnRoot.add(dmtnHtml);
        //
        trMain = new JTree(dmtnRoot, true);
        trMain.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                trMainValueChanged(e);
            }

        });
        trMain.putClientProperty("JTree.lineStyle", "Angled");
        spTreeMain = new JScrollPane(trMain, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spTreeMain.setWheelScrollingEnabled(true);
    }

    private void initPanel() {
        plContent = new JPanel();
        plContent.add(tbMain, BorderLayout.NORTH);
        plContent.add(spTreeMain, BorderLayout.WEST);
        plContent.add(plStatusBar, BorderLayout.SOUTH);
        plContent.add(plSearch, BorderLayout.EAST);
        plContent.add(taContent, BorderLayout.CENTER);
        plContent.setSize(800, 640);
    }

    private void initStatusBar() {
        plStatusBar = new JPanel(new FlowLayout());
        lblStatus1 = new JLabel("輸出");
        lblStatus2 = new JLabel("使用者");
        lblStatus3 = new JLabel("時間");
        plStatusBar.add(lblStatus1);
        plStatusBar.add(lblStatus2);
        plStatusBar.add(lblStatus3);
    }

    private void initSearch() {
        plSearch = new JPanel();
        keyword = new JLabel("輸入關鍵字");
        plSearch.add(keyword);
    }

    private void initTextArea() {
        taContent = new JTextArea();
    }

    private void trMainValueChanged(TreeSelectionEvent e) {
        System.out.println(e.getPath());
    }
}
