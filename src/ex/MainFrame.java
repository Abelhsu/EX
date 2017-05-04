/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import ex.db.Employees;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeSelectionModel;

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
    private JPanel plStatusBar;
    private JPanel plSearch;
    private JMenuItem miCreate;
    private JMenuItem miOpen;
    private JMenuItem miSave;
    private JLabel lbStatus;
    private JLabel keyword;
    private JButton btnCreate;
    private JButton btnOpen;
    private JButton btnSave;
    private JMenuItem miClose;
    private DefaultMutableTreeNode dmtnOOP;
    private DefaultMutableTreeNode dmtnC;
    private DefaultMutableTreeNode dmtnJava;
    private DefaultMutableTreeNode dmtnRoot;
    private DefaultMutableTreeNode dmtnHtml;
    private JScrollPane scpTreeMain;
    private DefaultTreeModel dtmRoot;
    private TreeSelectionModel sm;
    private Dimension dimScreen;
    private JSplitPane sppTreeMain;
    private JTable tbHR;
    private DefaultTableModel tmMain;
    private TableCellRenderer crMain;
    private Action mainAction;
    private Action exitAction;
    private JButton btnExit;
    private EntityManagerFactory emfHR;
    private EntityManager emEmployees;
    private EntityManagerFactory emfOE;
    private EntityManagerFactory emfPM;
    private EntityManagerFactory emfSCOTT;
    private EntityManager emCountries;
    private EntityManager emDepartments;

    public MainFrame() {
        initDB();
        initMetaData();
        initFonts();
        initActions();
        initComponents();
    }

    public MainFrame(String title) {
        this();
        this.setTitle(title);
    }

    private void initMetaData() {

    }

    private void initFonts() {
        UIManager.put("Menu.font", new Font("微軟正黑體", Font.PLAIN, 16));
        UIManager.put("MenuItem.font", new Font("微軟正黑體", Font.PLAIN, 16));
        UIManager.put("Tree.font", new Font("微軟正黑體", Font.PLAIN, 14));
    }

    private void initActions() {
        mainAction = new MainAction("Action", new ImageIcon(getClass().getResource("./icon/copy.gif"), "圖示"), "Action Desc", new Integer(KeyEvent.VK_L));
        exitAction = new ExitAction("Action", new ImageIcon(getClass().getResource("./icon/copy.gif")));
    }

    private void initComponents() {
        // Initilize objects
        initMenuBar();
        initToolBar();
        initTreeScrollPane();
        initStatusBar();
        initSearch();
        initTableHR();
        initFrame();
    }

    private void initToolBar() {
        tbMain = new JToolBar();
        // Buttons
        btnCreate = new JButton("新增");

        btnOpen = new JButton(mainAction);
        btnOpen.setText("開啟");
        btnSave = new JButton("存檔");
        btnExit = new JButton(exitAction);
        btnExit.setText("離開");
        // Button's Listener
        btnCreate.addActionListener(e -> {
            btnCreateActionListener(e);
        });
        btnOpen.addActionListener(e -> {
            btnOpenActionListener(e);
        });
        btnSave.addActionListener(e -> {
            btnSaveActionListener(e);
        });

        // 
        tbMain.add(btnCreate);
        tbMain.add(btnOpen);
        tbMain.add(btnSave);
        tbMain.add(btnExit);
    }

    private void initMenuBar() {
        mbMain = new JMenuBar();

        //
        mnFile = new JMenu("檔案");
        miCreate = new JMenuItem("新增");
        miOpen = new JMenuItem(mainAction);
        miOpen.setText("開啟");
        miSave = new JMenuItem("存檔");
        miClose = new JMenuItem(exitAction);
        miClose.setText("關閉");
        miCreate.addActionListener(e -> {
            System.out.println(e.getActionCommand());
        });
        mnFile.add(miCreate);
        mnFile.add(miOpen);
        mnFile.add(miSave);
        mnFile.add(miClose);
        //
//        miClose.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
        //
        mnEdit = new JMenu("編輯");
        mnHelp = new JMenu("輔助");
        mbMain.add(mnFile);
        mbMain.add(mnEdit);
        mbMain.add(mnHelp);
    }

    private void initFrame() {
        // Initial Frame
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Initial Menu Bar
        setJMenuBar(mbMain);
        // Content Pane Layout
        getContentPane().add(tbMain, BorderLayout.NORTH);
        getContentPane().add(scpTreeMain, BorderLayout.WEST);
        getContentPane().add(plStatusBar, BorderLayout.SOUTH);
        getContentPane().add(plSearch, BorderLayout.EAST);
        getContentPane().add(tbHR, BorderLayout.CENTER);
        pack();
    }

    private void initTreeScrollPane() {
        //
        dmtnOOP = new DefaultMutableTreeNode("物件導向程式設計", true);
        dmtnC = new DefaultMutableTreeNode("C 語言", true);
        dmtnJava = new DefaultMutableTreeNode("Java 語言", true);
        dmtnHtml = new DefaultMutableTreeNode("Html 語言", true);
        dmtnRoot = new DefaultMutableTreeNode("根目錄", true);

        //
        dmtnRoot.add(dmtnOOP);
        dmtnOOP.add(dmtnC);
        dmtnOOP.add(dmtnJava);
        dmtnOOP.add(dmtnJava);
        dmtnRoot.add(dmtnHtml);
        dtmRoot = new DefaultTreeModel(dmtnRoot);

        dtmRoot.addTreeModelListener(new TreeModelListener() {
            @Override
            public void treeNodesChanged(TreeModelEvent e) {
                dtmRootTreeNodesChanged();
            }

            @Override
            public void treeNodesInserted(TreeModelEvent e) {

            }

            @Override
            public void treeNodesRemoved(TreeModelEvent e) {

            }

            @Override
            public void treeStructureChanged(TreeModelEvent e) {

            }

        });
        //
        trMain = new JTree();

        trMain.setModel(dtmRoot);
        //
        trMain.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                System.out.println("treeExpanded" + event.getPath());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                System.out.println("treeCollapsed" + event.getPath());
            }

        });
        trMain.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                System.out.println("treeWillExpand" + event.getPath());
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                System.out.println("treeWillCollapse" + event.getPath());
            }

        });
        trMain.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                System.out.println("valueChanged");

            }
        });
        sm = trMain.getSelectionModel();
        sm.clearSelection();
        sm.setSelectionMode(DefaultTreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        //
        trMain.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                trMainTreeExpanded(event);
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                trMainTreeCollapsed(event);
            }

        });
        trMain.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                trMainValueChanged(e);
            }

        });
        trMain.putClientProperty("JTree.lineStyle", "Angled");
        scpTreeMain = new JScrollPane(trMain, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scpTreeMain.setPreferredSize(new Dimension(200, 800));
        scpTreeMain.setWheelScrollingEnabled(true);
        sppTreeMain = new JSplitPane();

    }

    private void initStatusBar() {
        plStatusBar = new JPanel(new FlowLayout());
        lbStatus = new JLabel("顯示狀態");
        plStatusBar.add(lbStatus);
    }

    private void initSearch() {
        plSearch = new JPanel();
        keyword = new JLabel("輸入關鍵字");
        plSearch.add(keyword);
    }

    private void initTableHR() {
        tbHR = new JTable(60, 6);
        tmMain = (DefaultTableModel) tbHR.getModel();
//        crMain = tbHR.getCellRenderer(10, 20);
        // Listener
        tmMain.addTableModelListener((e) -> {
            tmMainTableModelListener(e);
        });
        btnCreate.addActionListener(e -> {
            btnCreateActionListener(e);
        });
        //
        TableColumn colTitle = tbHR.getColumnModel().getColumn(0);
        colTitle.setPreferredWidth(400);
        tbHR.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tbHR.setSelectionBackground(Color.YELLOW);
        tbHR.setCellSelectionEnabled(true);
    }

    private void trMainValueChanged(TreeSelectionEvent e) {
        System.out.println(e.getPath());
    }

    private void tmMainTableModelListener(TableModelEvent e) {

        System.out.println("tmMainTableModelListener");
    }

    private void trMainTreeCollapsed(TreeExpansionEvent event) {
        lbStatus.setText(event.getPath().toString());
    }

    private void trMainTreeExpanded(TreeExpansionEvent event) {
        lbStatus.setText(event.getPath().getLastPathComponent().toString());
    }

    private void dtmRootTreeNodesChanged() {
        System.out.println("addTreeModelListener treeNodesChanged");
    }

    private void btnCreateActionListener(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    private void btnOpenActionListener(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    private void btnSaveActionListener(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    private void initDB() {
        emfHR = Persistence.createEntityManagerFactory("HRPU");
        emfOE = Persistence.createEntityManagerFactory("OEPU");
        emfPM = Persistence.createEntityManagerFactory("PMPU");
        emfSCOTT = Persistence.createEntityManagerFactory("SCOTTPU");
        emEmployees = emfHR.createEntityManager();
        emCountries = emfHR.createEntityManager();
        emDepartments = emfHR.createEntityManager();
        //
        TypedQuery<Employees> query = emEmployees.createQuery("select e from Employees e", Employees.class);
        List<Employees> results = query.getResultList();
        //
        for (Employees result : results) {
            System.out.print(result.getFirstName() + " " + result.getLastName());
            System.out.println();
        }
//        Employees emp = new Employees(158);
//        em.persist(emp);
    }

}
