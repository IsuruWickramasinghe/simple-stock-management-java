package com.applestock.client;

import com.applestock.lib.Cart;
import com.applestock.lib.Product;
import com.applestock.server.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Objects;


import static java.awt.Color.decode;

public class cashier_panel extends JFrame {

    JTable homeTable, counterTable;
    cart_handler cart_handler;
    JLabel lbl_fullAmount = new JLabel();

    public cashier_panel(String emp_name) throws HeadlessException {

        cart_handler = new cart_handler();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int midWidth = (screenWidth / 2) - (1366 / 2);
        int midHeight = (screenHeight / 2) - (768 / 2);

        try {
            setTitle("Apple Stock - Cashier : " + emp_name);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/app-store.png"))).getImage());
            setSize(1366, 768);
            setResizable(true);
            setLocation(midWidth, midHeight);

            setLayout(new BorderLayout());

            JPanel searchPanel = new JPanel();
            JPanel productsPanel = new JPanel();
            JPanel invoiceGenPanel = new JPanel();
            JPanel footer = new JPanel(new BorderLayout());

            JButton logOut = new JButton("LOG OUT");
            logOut.setBackground(decode(new colors().delete));
            logOut.setForeground(decode(new colors().bg_clr));
            logOut.setBorder(BorderFactory.createCompoundBorder((new MatteBorder(1, 1, 1, 1, decode(new colors().bg_clr))), (new EmptyBorder(10, 20, 10, 20))));
            logOut.setFont(new Font("Arial", Font.BOLD, 14));

            footer.add(logOut, BorderLayout.WEST);

            searchBox(searchPanel);
            productTablePanel(productsPanel);
            cartPanel(invoiceGenPanel);
            footer.setBackground(Color.decode(new colors().bg_clr));

            add(searchPanel, BorderLayout.NORTH);
            add(productsPanel, BorderLayout.CENTER);
            add(invoiceGenPanel, BorderLayout.EAST);
            add(footer, BorderLayout.SOUTH);


            setVisible(true);

            logOut.addActionListener(e -> {
                cashier_panel.this.dispose();
                new login_panel();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //! ======================= HOME TABLES ======================= //

    private void searchBox(@NotNull JPanel searchPanel) {


        searchPanel.setBackground(Color.decode(new colors().bg_clr));

        JLabel lbl_searchIcon = new JLabel();
        lbl_searchIcon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/search.png"))));

        JTextField txt_searchBox = new JTextField();
        txt_searchBox.setColumns(30);
        txt_searchBox.setBorder(BorderFactory.createCompoundBorder(new MatteBorder(0, 0, 1, 0, Color.black),
                new EmptyBorder(5, 10, 5, 10)));
        txt_searchBox.setFont(new Font("Arial", Font.PLAIN, 16));


        txt_searchBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(txt_searchBox);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(txt_searchBox);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });


        searchPanel.add(txt_searchBox);
        searchPanel.add(lbl_searchIcon);

    }

    private void productTablePanel(@NotNull JPanel productsPanel) {

        productsPanel.setLayout(new BorderLayout());
        productsPanel.setBackground(decode(new colors().bg_clr));

        DefaultTableModel tableColumns = new DefaultTableModel();

        tableColumns.addColumn("Name");
        tableColumns.addColumn("Price");
        tableColumns.addColumn("Quantities");
        tableColumns.addColumn("Description");

        //! ----------------------------------------

        homeTable = new JTable(tableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        homeTable.setBackground(decode(new colors().bg_clr));
        homeTable.setRowHeight(30);
        homeTable.setShowGrid(false);
        homeTable.setIntercellSpacing(new Dimension(0, 0));

        //! ----------------------------------------

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) cell).setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
                return cell;
            }
        };
        for (int i = 0; i < homeTable.getColumnModel().getColumnCount(); i++) {
            homeTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        addToCart(homeTable);

        //! ----------------------------------------

        JTableHeader header = homeTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.decode("#610C9F"));
        header.setBorder(new LineBorder(Color.decode("#610C9F")));
        header.setForeground(Color.WHITE);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) homeTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setPreferredSize(new Dimension(1, 30));
        header.setDefaultRenderer(headerRenderer);

        //! ----------------------------------------

        for (Product product : new products_handler().getProducts()) {
            tableColumns.addRow(new Object[]{
                    product.getProductSlug(),
                    product.getProductPrice(),
                    product.getProductQty(),
                    product.getProductDesc()
            });
        }

        JScrollPane scrollPane = new JScrollPane(homeTable);

        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(Color.white);
        scrollPane.setViewportBorder(null);

        productsPanel.add(scrollPane, BorderLayout.CENTER);

    }

    private void filterTable(@NotNull JTextField txt_searchBox) {
        String searchText = txt_searchBox.getText().toLowerCase();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) homeTable.getModel());
        homeTable.setRowSorter(sorter);

        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0, 1));

        }
    }

    private void cartPanel(@NotNull JPanel invoiceGenPanel){

        invoiceGenPanel.setLayout(new BorderLayout());
        invoiceGenPanel.setBackground(Color.decode(new colors().bg_clr));
        invoiceGenPanel.setBorder(new EmptyBorder(10,10,10,10));

        JLabel lbl_cashCounter = new JLabel("Counter");
        JPanel pnl_footer = new JPanel(new BorderLayout());
        JPanel pnl_counterBtn = new JPanel(new BorderLayout());

        pnl_counterBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnl_counterBtn.setBackground(Color.decode(new colors().bg_clr));

        JButton btn_pay = new JButton("Pay");
        JButton btn_remove = new JButton("remove");

        btn_pay.setBackground(Color.decode("#176B87"));
        btn_pay.setForeground(Color.decode(new colors().bg_clr));
        btn_pay.setFont(new Font("Arial", Font.BOLD, 22));
        btn_pay.setBorder(new EmptyBorder(10,50,10,50));

        btn_remove.setBackground(Color.decode(new colors().delete));
        btn_remove.setForeground(Color.decode(new colors().bg_clr));
        btn_remove.setFont(new Font("Arial", Font.BOLD, 14));
        btn_remove.setBorder(new EmptyBorder(10,20,10,20));


        pnl_footer.setBackground(Color.decode(new colors().bg_clr));

        lbl_cashCounter.setFont(new Font("Arial", Font.BOLD, 24));
        lbl_cashCounter.setBorder(new EmptyBorder(0,10,0,0));
        lbl_cashCounter.setForeground(Color.decode("#176B87"));

        lbl_fullAmount.setFont(new Font("Arial", Font.BOLD, 24));
        lbl_fullAmount.setBorder(new EmptyBorder(0,10,0,10));
        lbl_fullAmount.setForeground(Color.decode("#176B87"));

        //! --------------------------------------

        DefaultTableModel tableColumns = new DefaultTableModel();
        tableColumns.addColumn("Item");
        tableColumns.addColumn("Price");
        tableColumns.addColumn("Quantities");

        counterTable = new JTable(tableColumns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        for (Cart item : cart_handler.getCartList()) {
            tableColumns.addRow(new Object[]{
                    item.getProduct_name(),
                    item.getProduct_price(),
                    item.getProduct_qty(),
            });
        }


        JTableHeader header = counterTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.decode("#176B87"));
        header.setBorder(new LineBorder(Color.decode("#176B87")));
        header.setForeground(Color.WHITE);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) counterTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setPreferredSize(new Dimension(1, 30));
        header.setDefaultRenderer(headerRenderer);

        //! --------------------------------------

        pnl_counterBtn.add(btn_pay, BorderLayout.CENTER);
        pnl_counterBtn.add(btn_remove, BorderLayout.EAST);

        pnl_footer.add(lbl_fullAmount, BorderLayout.WEST);
        pnl_footer.add(pnl_counterBtn, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(counterTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(Color.white);
        scrollPane.setViewportBorder(null);

        invoiceGenPanel.add(lbl_cashCounter, BorderLayout.NORTH);
        invoiceGenPanel.add(scrollPane, BorderLayout.CENTER);
        invoiceGenPanel.add(pnl_footer, BorderLayout.SOUTH);

        btn_remove.addActionListener(e -> {
            removeFromCart(counterTable);
        });

        btn_pay.addActionListener(e -> {
            invoiceGen();
        });

    }

    private void addToCart(@NotNull JTable homeTable){
        homeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = homeTable.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        int qty = Integer.parseInt(JOptionPane.showInputDialog(new JFrame(), "Quantities"));
                        String product_slug = (String) homeTable.getValueAt(selectedRow, 0);
                        System.out.println(product_slug);
                        cart_handler.setCartList(product_slug, qty);
                        cart_handler.refreshCart(counterTable);
                        new products_handler().refreshCashierHomeTable(homeTable);
                        lbl_fullAmount.setText("Rs."+cart_handler.getSubTotal());
                    }
                    catch (Exception e1){
                        System.out.println("empty_qty");
                    }

                }
            }
        });

    }

    private void removeFromCart(@NotNull JTable counterTable){
        int selectedRow = counterTable.getSelectedRow();
        if (selectedRow != -1) {
            String product_name = (String) counterTable.getValueAt(selectedRow, 0);
            int product_qty = (int) counterTable.getValueAt(selectedRow, 2);

            cart_handler.updateRemovedItem(product_name, product_qty);
            lbl_fullAmount.setText("Rs."+cart_handler.getSubTotal());

             cart_handler.refreshCart(counterTable);
             new products_handler().refreshCashierHomeTable(homeTable);
        }

    }

    private void invoiceGen(){
        int success = JOptionPane.showConfirmDialog(new JFrame(), "Set Payment Success!");
        if (success != 0){
            return;
        }
        cart_handler.invoiceGenerator();
        cart_handler.getCartList().clear();
        lbl_fullAmount.setText(String.valueOf(cart_handler.getSubTotal()));
        cart_handler.refreshCart(counterTable);
        new products_handler().refreshCashierHomeTable(homeTable);
    }

}
