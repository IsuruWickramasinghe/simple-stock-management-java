package com.applestock.view;

import com.applestock.model.Employee;
import com.applestock.model.Product;
import com.applestock.controller.manager_handler;
import com.applestock.controller.products_handler;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
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

public class manager_panel extends JFrame {

    private JTable userTable,productTable,homeTable;
    private CardLayout cardLayout;
    private JPanel out_let;
    private static JButton home_btn,profile_btn,addUser_btn,users_btn, addProduct_btn, updateProduct_btn;

    manager_handler manager_handler;

    public manager_panel(String emp_id, String emp_name, boolean isManager) throws HeadlessException {
        manager_handler = new manager_handler(emp_id, emp_name, isManager);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int midWidth = (screenWidth / 2) - (1366 / 2);
        int midHeight = (screenHeight / 2) - (768 / 2);

        try {
            setTitle("Apple Stock - Manager : " + emp_name);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/app-store.png"))).getImage());
            setSize(1200, 720);
            setMinimumSize(new Dimension(800, 700));
            setResizable(true);
            setLocation(midWidth, midHeight);

            setLayout(new BorderLayout());

            JPanel nav_bar = new JPanel();
            out_let = new JPanel();

            cardLayout = new CardLayout();
            out_let.setLayout(cardLayout);

            navBar(nav_bar);
            outLet(out_let);
            add(nav_bar, BorderLayout.WEST);
            add(out_let, BorderLayout.CENTER);

            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navBar(@NotNull JPanel nav_bar) {
        nav_bar.setBackground(decode(new colors().bg_clr));
        nav_bar.setLayout(new BorderLayout());
        nav_bar.setBorder(new EmptyBorder(10, 5, 5, 5));

        JPanel navBar_buttons = new JPanel();
        navBar_buttons.setBackground(decode(new colors().bg_clr));
        navBar_buttons.setLayout(new GridBagLayout());

        JButton logOut = new JButton("LOG OUT");
        logOut.setBackground(decode(new colors().delete));
        logOut.setForeground(decode(new colors().bg_clr));
        logOut.setBorder(BorderFactory.createCompoundBorder((new MatteBorder(1,1,1,1, decode(new colors().bg_clr))),(new EmptyBorder(10,20,10,20))));
        logOut.setFont(new Font("Arial",Font.BOLD,14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 2, 10, 2);
        gbc.anchor = GridBagConstraints.WEST;

        home_btn = new JButton("HOME");
        profile_btn = new JButton("PROFILE");
        addUser_btn = new JButton("ADD USER");
        users_btn = new JButton("EDIT USER");
        addProduct_btn = new JButton("ADD PRODUCT");
        updateProduct_btn = new JButton("UPDATE PRODUCT");

        JLabel home_lbl = new JLabel();
        home_lbl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/home.png"))));
        JLabel profile_lbl = new JLabel();
        profile_lbl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/user.png"))));
        JLabel addUser_lbl = new JLabel();
        addUser_lbl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plus.png"))));
        JLabel users_lbl = new JLabel();
        users_lbl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/groups.png"))));
        JLabel addProduct_lbl = new JLabel();
        addProduct_lbl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add-file.png"))));
        JLabel updateProducts_lbl = new JLabel();
        updateProducts_lbl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/updated.png"))));

        home_btn.setBackground(Color.WHITE);
        home_btn.setHorizontalAlignment(SwingConstants.LEFT);
        home_btn.setBorder(new EmptyBorder(10, 10, 10, 75));
        profile_btn.setBackground(Color.WHITE);
        profile_btn.setHorizontalAlignment(SwingConstants.LEFT);
        profile_btn.setBorder(new EmptyBorder(10, 10, 10, 60));
        addUser_btn.setBackground(Color.WHITE);
        addUser_btn.setHorizontalAlignment(SwingConstants.LEFT);
        addUser_btn.setBorder(new EmptyBorder(10, 10, 10, 52));
        users_btn.setBackground(Color.WHITE);
        users_btn.setHorizontalAlignment(SwingConstants.LEFT);
        users_btn.setBorder(new EmptyBorder(10, 10, 10, 50));
        addProduct_btn.setBackground(Color.WHITE);
        addProduct_btn.setHorizontalAlignment(SwingConstants.LEFT);
        addProduct_btn.setBorder(new EmptyBorder(10, 10, 10, 30));
        updateProduct_btn.setBackground(Color.WHITE);
        updateProduct_btn.setHorizontalAlignment(SwingConstants.LEFT);
        updateProduct_btn.setBorder(new EmptyBorder(10, 10, 10, 10));

        gbc.gridx = 0;
        gbc.gridy = 0;
        navBar_buttons.add(home_lbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        navBar_buttons.add(home_btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        navBar_buttons.add(profile_lbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        navBar_buttons.add(profile_btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        navBar_buttons.add(addUser_lbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        navBar_buttons.add(addUser_btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        navBar_buttons.add(users_lbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        navBar_buttons.add(users_btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        navBar_buttons.add(addProduct_lbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        navBar_buttons.add(addProduct_btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        navBar_buttons.add(updateProducts_lbl, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        navBar_buttons.add(updateProduct_btn, gbc);

        nav_bar.add(navBar_buttons, BorderLayout.NORTH);
        nav_bar.add(logOut,BorderLayout.SOUTH);

        home_btn.addActionListener(e -> {
            new products_handler().refreshHomeTable(this.homeTable);
            cardLayout.show(out_let, "home");
            activeBtn("home");
        });
        profile_btn.addActionListener(e -> {
            cardLayout.show(out_let, "profile");
            activeBtn("profile");
        });
        addUser_btn.addActionListener(e -> {
            cardLayout.show(out_let, "addUser");
            activeBtn("add");
        });
        users_btn.addActionListener(e -> {
            manager_handler.refreshUserTable(this.userTable);
            cardLayout.show(out_let, "users");
            activeBtn("users");
        });
        addProduct_btn.addActionListener(e -> {
            cardLayout.show(out_let, "addProduct");
            activeBtn("addProduct");
        });
        updateProduct_btn.addActionListener(e -> {
            new products_handler().refreshProductTable(this.productTable);
            cardLayout.show(out_let, "updateProducts");
            activeBtn("updateProducts");
        });
        logOut.addActionListener(e -> {
            manager_panel.this.dispose();
            new login_panel();
        });
    }

    public void outLet(@NotNull JPanel out_let) {

        JPanel homePanel = new JPanel();
        JPanel profilePanel = new JPanel();
        JPanel addUserPanel = new JPanel();
        JPanel usersPanel = new JPanel();
        JPanel addProductPanel = new JPanel();
        JPanel updateProductsPanel = new JPanel();

        homePanel.setBackground(decode(new colors().bg_clr));
        profilePanel.setBackground(decode(new colors().bg_clr));
        addUserPanel.setBackground(decode(new colors().bg_clr));
        usersPanel.setBackground(decode(new colors().bg_clr));
        addProductPanel.setBackground(decode(new colors().bg_clr));
        updateProductsPanel.setBackground(decode(new colors().bg_clr));

        homePanel(homePanel);
        userProfilePanel(profilePanel);
        addUserPanel(addUserPanel);
        updateUsersPanel(usersPanel);
        addProductPanel(addProductPanel);
        updateProductsPanel(updateProductsPanel);

        out_let.add(homePanel, "home");
        out_let.add(profilePanel, "profile");
        out_let.add(addUserPanel, "addUser");
        out_let.add(usersPanel, "users");
        out_let.add(addProductPanel, "addProduct");
        out_let.add(updateProductsPanel, "updateProducts");
    }

    public static void activeBtn(@NotNull String activeBtn){
        home_btn.setBackground(Color.WHITE);
        profile_btn.setBackground(Color.WHITE);
        addUser_btn.setBackground(Color.WHITE);
        users_btn.setBackground(Color.WHITE);
        addProduct_btn.setBackground(Color.WHITE);
        updateProduct_btn.setBackground(Color.WHITE);

        switch (activeBtn) {
            case "home" -> home_btn.setBackground(decode(new colors().p_clr));
            case "profile" -> profile_btn.setBackground(decode(new colors().p_clr));
            case "add" -> addUser_btn.setBackground(decode(new colors().p_clr));
            case "users" -> users_btn.setBackground(decode(new colors().p_clr));
            case "addProduct" -> addProduct_btn.setBackground(decode(new colors().p_clr));
            case "updateProducts" -> updateProduct_btn.setBackground(decode(new colors().p_clr));
        }

    }

    //! ======================= PANELS ======================= //

    private void homePanel(@NotNull JPanel homePanel) {
        homePanel.setLayout(new BorderLayout());
        homePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel();
        JPanel productsPanel = new JPanel(new BorderLayout());

        searchBox(searchPanel);
        productTablePanel(productsPanel);

        homePanel.add(searchPanel, BorderLayout.NORTH);
        homePanel.add(productsPanel, BorderLayout.CENTER);
    }

    private void userProfilePanel(@NotNull JPanel profilePanel){
        profilePanel.setLayout(new BorderLayout());
        profilePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel panelName = new JLabel("User Profile");
        panelName.setFont(new Font("Arial", Font.BOLD, 22));
        panelName.setForeground(decode(new colors().txt_clr));
        panelName.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel content_wrapper = new JPanel();
        content_wrapper.setLayout(new BorderLayout());
        content_wrapper.setBackground(decode(new colors().bg_clr));

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setBackground(decode(new colors().bg_clr));
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,3,20,3);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbl_id = new JLabel("User ID:");
        JLabel lbl_name = new JLabel("User Name:");
        JLabel lbl_pass = new JLabel("User Password:");
        JLabel lbl_isManager = new JLabel("Manager:");
        JLabel lbl_address = new JLabel("User Address:");
        JLabel lbl_phone = new JLabel("User Phone:");
        JLabel lbl_email = new JLabel("User Email:");
        JLabel resultMsg = new JLabel("");

        resultMsg.setFont(new Font("Arial", Font.PLAIN, 12));
        resultMsg.setForeground(Color.red);

        JLabel[] labels = {lbl_id, lbl_name, lbl_pass, lbl_isManager, lbl_address, lbl_phone, lbl_email};
        for (JLabel lbl : labels){
            lbl.setFont(new Font("Arial", Font.PLAIN, 16));
            lbl.setBorder(new EmptyBorder(5,10,5,10));
        }

        JTextField txt_id = new JTextField();
        txt_id.setEnabled(false);
        JTextField txt_name = new JTextField();
        JTextField txt_pass = new JTextField();
        JCheckBox txt_manager = new JCheckBox();
        JTextField txt_address = new JTextField();
        JTextField txt_phone = new JTextField();
        JTextField txt_email = new JTextField();

        JTextField[] inputs = {txt_id, txt_name, txt_pass, txt_address, txt_phone, txt_email};
        for (JTextField txt : inputs) {
            txt.setFont(new Font("Arial", Font.PLAIN, 16));
            Border matteBorder = new MatteBorder(1, 1, 1, 1, decode(new colors().s_clr));
            Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
            txt.setBorder(BorderFactory.createCompoundBorder(matteBorder, emptyBorder));
            txt.setColumns(20);
        }


        JButton updateBtn = new JButton("UPDATE");
        Border matteBorder = new MatteBorder(1,1,1,1, decode(new colors().s_clr));
        Border emptyBorder = new EmptyBorder(10,30,10,30);
        updateBtn.setBorder(BorderFactory.createCompoundBorder(matteBorder,emptyBorder));
        updateBtn.setForeground(decode(new colors().bg_clr));
        updateBtn.setBackground(decode(new colors().s_clr));

        String[] userData = manager_handler.getProfile();
        boolean isManager = manager_handler.getIsManager();

        txt_id.setText(userData[0]);
        txt_name.setText(userData[1]);
        txt_pass.setText(userData[2]);
        txt_address.setText(userData[3]);
        txt_phone.setText(userData[4]);
        txt_email.setText(userData[5]);
        if (isManager){txt_manager.setSelected(true);}

        JLabel profile_img = new JLabel();
        profile_img.setPreferredSize(new Dimension(400,400));
        profile_img.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/profile_img.png"))));
        profile_img.setBorder(new EmptyBorder(0,200,0,0));
        profile_img.setIconTextGap(10);

        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(panelName , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(lbl_id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(txt_id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        content.add(lbl_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        content.add(txt_name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        content.add(lbl_pass, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        content.add(txt_pass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        content.add(lbl_isManager, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        content.add(txt_manager, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        content.add(lbl_address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        content.add(txt_address, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        content.add(lbl_phone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        content.add(txt_phone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        content.add(lbl_email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        content.add(txt_email, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        content.add(resultMsg, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        content.add(updateBtn, gbc);

        content_wrapper.add(content, BorderLayout.NORTH);
        profilePanel.add(content_wrapper, BorderLayout.WEST);
        profilePanel.add(profile_img, BorderLayout.CENTER);

        //! ================ BUTTON ACTIONS ================ //
        updateBtn.addActionListener(e -> {
            int confirmResult = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
            if (confirmResult == 0){
                boolean updateState = manager_handler.updateProfile(
                        txt_name.getText(),
                        txt_pass.getText(),
                        txt_manager.isSelected(),
                        txt_address.getText(),
                        txt_phone.getText(),
                        txt_email.getText());
                if (updateState){
                    resultMsg.setText("successfully updated!");
                    resultMsg.setForeground(decode(new colors().success));
                    Timer timer = new Timer(1000, e1 -> resultMsg.setText(""));
                    timer.setRepeats(false);
                    timer.start();
                }
                else {
                    resultMsg.setText("error with updating data!");
                    Timer timer = new Timer(1000, e1 -> resultMsg.setText(""));
                    timer.setRepeats(false);
                    timer.start();
                }
            }

        });

    }

    private void addUserPanel(@NotNull JPanel addUserPanel){
        addUserPanel.setLayout(new BorderLayout());
        addUserPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel panelName = new JLabel("Add New User");
        panelName.setFont(new Font("Arial", Font.BOLD, 22));
        panelName.setForeground(decode(new colors().txt_clr));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelName.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel content_wrapper = new JPanel();
        content_wrapper.setLayout(new BorderLayout());
        content_wrapper.setBackground(decode(new colors().bg_clr));

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setBackground(decode(new colors().bg_clr));
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,3,20,3);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbl_id = new JLabel("ID:");
        JLabel lbl_name = new JLabel("Name:");
        JLabel lbl_pass = new JLabel("Password:");
        JLabel lbl_isManager = new JLabel("Manager?:");
        JLabel lbl_address = new JLabel("Address:");
        JLabel lbl_phone = new JLabel("Phone:");
        JLabel lbl_email = new JLabel("Email:");
        JLabel resultMsg = new JLabel("");

        resultMsg.setFont(new Font("Arial", Font.PLAIN, 12));
        resultMsg.setForeground(Color.red);

        JLabel[] labels = {lbl_id, lbl_name, lbl_pass, lbl_isManager, lbl_address, lbl_phone, lbl_email};
        for (JLabel lbl : labels){
            lbl.setFont(new Font("Arial", Font.PLAIN, 16));
            lbl.setBorder(new EmptyBorder(5,10,5,10));
        }

        JTextField txt_id = new JTextField();
        txt_id.setEnabled(false);
        JTextField txt_name = new JTextField();
        JTextField txt_pass = new JTextField();
        JCheckBox txt_manager = new JCheckBox();
        JTextField txt_address = new JTextField();
        JTextField txt_phone = new JTextField();
        JTextField txt_email = new JTextField();

        JTextField[] inputs = {txt_id, txt_name, txt_pass, txt_address, txt_phone, txt_email};
        for (JTextField txt : inputs) {
            txt.setFont(new Font("Arial", Font.PLAIN, 16));
            Border matteBorder = new MatteBorder(1, 1, 1, 1, decode(new colors().s_clr));
            Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
            txt.setBorder(BorderFactory.createCompoundBorder(matteBorder, emptyBorder));
            txt.setColumns(20);
        }


        JButton addNewUserBtn = new JButton("SUBMIT");
        Border matteBorder = new MatteBorder(1,1,1,1, decode(new colors().s_clr));
        Border emptyBorder = new EmptyBorder(10,30,10,30);
        addNewUserBtn.setBorder(BorderFactory.createCompoundBorder(matteBorder,emptyBorder));
        addNewUserBtn.setForeground(decode(new colors().bg_clr));
        addNewUserBtn.setBackground(decode(new colors().s_clr));

        JButton createId = new JButton("Generate ID");
        createId.setForeground(decode(new colors().txt_clr));
        createId.setBackground(decode(new colors().bg_clr));
        createId.setBorder(new EmptyBorder(5,5,5,5));

        JLabel add_user_img = new JLabel();
        add_user_img.setPreferredSize(new Dimension(400,400));
        add_user_img.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/app_user_img.png"))));
        add_user_img.setBorder(new EmptyBorder(0,100,0,0));
        add_user_img.setIconTextGap(10);

        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(panelName , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(lbl_id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(txt_id, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        content.add(createId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        content.add(lbl_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        content.add(txt_name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        content.add(lbl_pass, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        content.add(txt_pass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        content.add(lbl_isManager, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        content.add(txt_manager, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        content.add(lbl_address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        content.add(txt_address, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        content.add(lbl_phone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        content.add(txt_phone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        content.add(lbl_email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        content.add(txt_email, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        content.add(resultMsg, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        content.add(addNewUserBtn, gbc);

        content_wrapper.add(content, BorderLayout.NORTH);
        addUserPanel.add(content_wrapper, BorderLayout.WEST);
        addUserPanel.add(add_user_img, BorderLayout.CENTER);

        //! ================ BUTTON ACTIONS ================ //

        createId.addActionListener(e -> {
            int count = manager_handler.generateId();
            txt_id.setText(String.valueOf(count));
        });

        addNewUserBtn.addActionListener(e -> {
            if (
                    txt_id.getText().equals("") ||
                    txt_name.getText().equals("") ||
                    txt_pass.getText().equals("") ||
                    txt_address.getText().equals("") ||
                    txt_phone.getText().equals("") ||
                    txt_email.getText().equals("") )
            {
                JOptionPane.showMessageDialog(new JFrame(),"Please check again!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                int confirmResult = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
                if (confirmResult == 0) {
                    boolean success = manager_handler.addNewUser(
                            txt_id.getText(),
                            txt_name.getText(),
                            txt_pass.getText(),
                            txt_manager.isSelected(),
                            txt_address.getText(),
                            txt_phone.getText(),
                            txt_email.getText());
                    if (success){
                        resultMsg.setText("new user successfully added");
                        resultMsg.setForeground(decode(new colors().success));
                        Timer timer = new Timer(1000, e1 -> {
                            resultMsg.setText("");
                            txt_id.setText("");
                            txt_name.setText("");
                            txt_pass.setText("");
                            txt_manager.setSelected(false);
                            txt_address.setText("");
                            txt_phone.setText("");
                            txt_email.setText("");
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(),"user id already existed in the database!");
                    }
                }
            }
        });

    }

    private void updateUsersPanel(@NotNull JPanel editUsers) {

        editUsers.setLayout(new BorderLayout());
        editUsers.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel panelName = new JLabel("Edit Users");
        panelName.setFont(new Font("Arial", Font.BOLD, 22));
        panelName.setForeground(decode(new colors().txt_clr));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelName.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel content_wrapper = new JPanel();
        content_wrapper.setLayout(new BorderLayout());
        content_wrapper.setBackground(decode(new colors().bg_clr));

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setBackground(decode(new colors().bg_clr));
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,3,20,3);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbl_id = new JLabel("ID:");
        JLabel lbl_name = new JLabel("Name:");
        JLabel lbl_pass = new JLabel("Password:");
        JLabel lbl_isManager = new JLabel("Manager?:");
        JLabel lbl_address = new JLabel("Address:");
        JLabel lbl_phone = new JLabel("Phone:");
        JLabel lbl_email = new JLabel("Email:");
        JLabel resultMsg = new JLabel("");

        resultMsg.setFont(new Font("Arial", Font.PLAIN, 12));
        resultMsg.setForeground(Color.red);

        JLabel[] labels = {lbl_id, lbl_name, lbl_pass, lbl_isManager, lbl_address, lbl_phone, lbl_email};
        for (JLabel lbl : labels){
            lbl.setFont(new Font("Arial", Font.PLAIN, 16));
            lbl.setBorder(new EmptyBorder(5,10,5,10));
        }

        JTextField txt_id = new JTextField();
        txt_id.setEnabled(false);
        JTextField txt_name = new JTextField();
        JTextField txt_pass = new JTextField();
        JCheckBox txt_manager = new JCheckBox();
        JTextField txt_address = new JTextField();
        JTextField txt_phone = new JTextField();
        JTextField txt_email = new JTextField();

        JTextField[] inputs = {txt_id, txt_name, txt_pass, txt_address, txt_phone, txt_email};
        for (JTextField txt : inputs) {
            txt.setFont(new Font("Arial", Font.PLAIN, 16));
            Border matteBorder = new MatteBorder(1, 1, 1, 1, decode(new colors().s_clr));
            Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
            txt.setBorder(BorderFactory.createCompoundBorder(matteBorder, emptyBorder));
            txt.setColumns(20);
        }

        Border matteBorder = new MatteBorder(1,1,1,1, decode(new colors().bg_clr));
        Border emptyBorder = new EmptyBorder(10,30,10,30);

        JButton updateUserBtn = new JButton("UPDATE");
        updateUserBtn.setBorder(BorderFactory.createCompoundBorder(matteBorder,emptyBorder));
        updateUserBtn.setForeground(decode(new colors().bg_clr));
        updateUserBtn.setBackground(decode(new colors().s_clr));

        JButton deleteUserBtn = new JButton("DELETE");
        deleteUserBtn.setBorder(BorderFactory.createCompoundBorder(matteBorder,emptyBorder));
        deleteUserBtn.setForeground(decode(new colors().bg_clr));
        deleteUserBtn.setBackground(decode(new colors().delete));

        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(panelName , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(lbl_id, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(txt_id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        content.add(lbl_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        content.add(txt_name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        content.add(lbl_pass, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        content.add(txt_pass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        content.add(lbl_isManager, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        content.add(txt_manager, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        content.add(lbl_address, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        content.add(txt_address, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        content.add(lbl_phone, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        content.add(txt_phone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        content.add(lbl_email, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        content.add(txt_email, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        content.add(resultMsg, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        content.add(updateUserBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        content.add(deleteUserBtn, gbc);

        content_wrapper.add(content, BorderLayout.NORTH);
        editUsers.add(content_wrapper, BorderLayout.WEST);

        updateUsersTable(editUsers, txt_id, txt_name, txt_pass, txt_manager, txt_address, txt_phone, txt_email);

        //! ================ BUTTON ACTIONS ================ //

        updateUserBtn.addActionListener(e -> {
            if (
                    txt_id.getText().equals("") ||
                    txt_name.getText().equals("") ||
                    txt_pass.getText().equals("") ||
                    txt_address.getText().equals("") ||
                    txt_phone.getText().equals("") ||
                    txt_email.getText().equals("") )
            {
                JOptionPane.showMessageDialog(new JFrame(),"Please check again!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                int confirmResult = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
                if (confirmResult == 0) {
                    boolean success = manager_handler.updateUser(
                            txt_id.getText(),
                            txt_name.getText(),
                            txt_pass.getText(),
                            txt_manager.isSelected(),
                            txt_address.getText(),
                            txt_phone.getText(),
                            txt_email.getText());

                    if (success){
                        resultMsg.setForeground(decode(new colors().success));
                        resultMsg.setText("selected user successfully updated!");
                        Timer timer = new Timer(1000, e1 -> {
                            manager_handler.getUsers();
                            resultMsg.setText("");
                            txt_id.setText("");
                            txt_name.setText("");
                            txt_pass.setText("");
                            txt_manager.setSelected(false);
                            txt_address.setText("");
                            txt_phone.setText("");
                            txt_email.setText("");
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(),"error with updating selected user!");
                    }

                }
            }
            manager_handler.refreshUserTable(this.userTable);
        });

        deleteUserBtn.addActionListener(e -> {
            if (
                    txt_id.getText().equals("") ||
                    txt_name.getText().equals("") ||
                    txt_pass.getText().equals("") ||
                    txt_address.getText().equals("") ||
                    txt_phone.getText().equals("") ||
                    txt_email.getText().equals("") )
            {
                JOptionPane.showMessageDialog(new JFrame(),"Please check again!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                int confirmResult = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
                if (confirmResult == 0) {
                    boolean success = manager_handler.deleteUser(
                            txt_id.getText(),
                            txt_name.getText());

                    if (success){
                        resultMsg.setForeground(decode(new colors().success));
                        resultMsg.setText("selected user removed successfully!");
                        Timer timer = new Timer(2000, e1 -> {
                            manager_handler.getUsers();
                            resultMsg.setText("");
                            txt_id.setText("");
                            txt_name.setText("");
                            txt_pass.setText("");
                            txt_manager.setSelected(false);
                            txt_address.setText("");
                            txt_phone.setText("");
                            txt_email.setText("");
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(),"error with removing selected user!");
                    }

                }
            }
            manager_handler.refreshUserTable(this.userTable);
        });
    }

    private void addProductPanel(@NotNull JPanel addProductPanel){
        addProductPanel.setLayout(new BorderLayout());
        addProductPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel panelName = new JLabel("Add New Product");
        panelName.setFont(new Font("Arial", Font.BOLD, 22));
        panelName.setForeground(decode(new colors().txt_clr));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelName.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel content_wrapper = new JPanel();
        content_wrapper.setLayout(new BorderLayout());
        content_wrapper.setBackground(decode(new colors().bg_clr));

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setBackground(decode(new colors().bg_clr));
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,3,20,3);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbl_product_slug = new JLabel("Slug:");
        JLabel lbl_product_name = new JLabel("Name:");
        JLabel lbl_product_price = new JLabel("Price:");
        JLabel lbl_product_qty = new JLabel("Quantities:");
        JLabel lbl_product_desc = new JLabel("Description:");
        JLabel lbl_product_cat = new JLabel("Category");
        JLabel resultMsg = new JLabel("");

        resultMsg.setFont(new Font("Arial", Font.PLAIN, 12));
        resultMsg.setForeground(Color.red);

        JLabel[] labels = {lbl_product_slug, lbl_product_name, lbl_product_price, lbl_product_qty, lbl_product_desc, lbl_product_cat};
        for (JLabel lbl : labels){
            lbl.setFont(new Font("Arial", Font.PLAIN, 16));
            lbl.setBorder(new EmptyBorder(5,10,5,10));
        }

        JTextField txt_product_slug = new JTextField();
        txt_product_slug.setEnabled(false);
        JTextField txt_product_name = new JTextField();
        JTextField txt_product_price = new JTextField();
        JTextField txt_product_qty = new JTextField();
        JTextArea txt_product_desc = new JTextArea(5,20);
        JComboBox<String> txt_product_cat = new JComboBox<>(new products_handler().getProductCat());

        txt_product_cat.setBackground(Color.decode(new colors().bg_clr));

        txt_product_desc.setLineWrap(true);
        txt_product_desc.setWrapStyleWord(true);
        JScrollPane textArea_pane = new JScrollPane(txt_product_desc);
        textArea_pane.setBorder(new MatteBorder(1,1,1,1,Color.black));

        JTextField[] inputs = {txt_product_slug, txt_product_name, txt_product_price, txt_product_qty};
        for (JTextField txt : inputs) {
            txt.setFont(new Font("Arial", Font.PLAIN, 16));
            Border matteBorder = new MatteBorder(1, 1, 1, 1, decode(new colors().s_clr));
            Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
            txt.setBorder(BorderFactory.createCompoundBorder(matteBorder, emptyBorder));
            txt.setColumns(20);
        }

        JButton addNewProduct = new JButton("ADD PRODUCT");
        Border matteBorder = new MatteBorder(1,1,1,1, decode(new colors().s_clr));
        Border emptyBorder = new EmptyBorder(10,30,10,30);
        addNewProduct.setBorder(BorderFactory.createCompoundBorder(matteBorder,emptyBorder));
        addNewProduct.setForeground(decode(new colors().bg_clr));
        addNewProduct.setBackground(decode(new colors().s_clr));

        JButton createSlug = new JButton("Generate Slug");
        createSlug.setForeground(decode(new colors().txt_clr));
        createSlug.setBackground(decode(new colors().bg_clr));
        createSlug.setBorder(new EmptyBorder(5,5,5,5));

        JLabel add_product_img = new JLabel();
        add_product_img.setPreferredSize(new Dimension(400,400));
        add_product_img.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add_product_img.png"))));
        add_product_img.setBorder(new EmptyBorder(0,100,0,0));
        add_product_img.setIconTextGap(10);

        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(panelName , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(lbl_product_slug, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(txt_product_slug, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        content.add(createSlug, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        content.add(lbl_product_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        content.add(txt_product_name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        content.add(lbl_product_price, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        content.add(txt_product_price, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        content.add(lbl_product_qty, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        content.add(txt_product_qty, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        content.add(lbl_product_desc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        content.add(textArea_pane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        content.add(lbl_product_cat, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        content.add(txt_product_cat, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        content.add(resultMsg, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        content.add(addNewProduct, gbc);

        content_wrapper.add(content, BorderLayout.NORTH);
        addProductPanel.add(content_wrapper, BorderLayout.WEST);
        addProductPanel.add(add_product_img, BorderLayout.CENTER);

        //! ================ BUTTON ACTIONS ================ //

        createSlug.addActionListener(e -> {
            if (txt_product_name.getText().equals("")){
                JOptionPane.showMessageDialog(new JFrame(),"please enter product name before create slug");
                return;
            }
            txt_product_slug.setText(txt_product_cat.getSelectedItem() + " | " + txt_product_name.getText());

        });

        addNewProduct.addActionListener(e -> {
            if (
                    txt_product_slug.getText().equals("") ||
                    txt_product_name.getText().equals("") ||
                    txt_product_price.getText().equals("") ||
                    txt_product_qty.getText().equals("") ||
                    txt_product_desc.getText().equals(""))
            {
                JOptionPane.showMessageDialog(new JFrame(),"Please check again!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                int confirmResult = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
                if (confirmResult == 0) {
                    boolean success = manager_handler.addNewProduct(
                            txt_product_slug.getText(),
                            txt_product_name.getText(),
                            Double.parseDouble(txt_product_price.getText()),
                            Integer.parseInt(txt_product_qty.getText()),
                            txt_product_desc.getText(),
                            (String) txt_product_cat.getSelectedItem());

                    if (success){
                        resultMsg.setText("new product successfully added!");
                        resultMsg.setForeground(decode(new colors().success));
                        Timer timer = new Timer(1000, e1 -> {
                            resultMsg.setText("");
                            txt_product_slug.setText("");
                            txt_product_name.setText("");
                            txt_product_price.setText("");
                            txt_product_qty.setText("");
                            txt_product_desc.setText("");
                            txt_product_cat.setSelectedIndex(0);
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(),"product slug already existed in the database!");
                    }
                }
            }
        });
    }

    private void updateProductsPanel(@NotNull JPanel updateProductsPanel){
        updateProductsPanel.setLayout(new BorderLayout());
        updateProductsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel panelName = new JLabel("Add New Product");
        panelName.setFont(new Font("Arial", Font.BOLD, 22));
        panelName.setForeground(decode(new colors().txt_clr));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelName.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel content_wrapper = new JPanel();
        content_wrapper.setLayout(new BorderLayout());
        content_wrapper.setBackground(decode(new colors().bg_clr));

        JPanel content = new JPanel();
        content.setBorder(new EmptyBorder(10,10,10,10));
        content.setBackground(decode(new colors().bg_clr));
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,3,20,3);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbl_product_slug = new JLabel("Slug:");
        JLabel lbl_product_name = new JLabel("Name:");
        JLabel lbl_product_price = new JLabel("Price:");
        JLabel lbl_product_qty = new JLabel("Quantities:");
        JLabel lbl_product_desc = new JLabel("Description:");
        JLabel lbl_product_cat = new JLabel("Category");
        JLabel resultMsg = new JLabel("");

        resultMsg.setFont(new Font("Arial", Font.PLAIN, 12));
        resultMsg.setForeground(Color.red);

        JLabel[] labels = {lbl_product_slug, lbl_product_name, lbl_product_price, lbl_product_qty, lbl_product_desc, lbl_product_cat};
        for (JLabel lbl : labels){
            lbl.setFont(new Font("Arial", Font.PLAIN, 16));
            lbl.setBorder(new EmptyBorder(5,10,5,10));
        }

        JTextField txt_product_slug = new JTextField();
        txt_product_slug.setEnabled(false);
        JTextField txt_product_name = new JTextField();
        JTextField txt_product_price = new JTextField();
        JTextField txt_product_qty = new JTextField();
        JTextArea txt_product_desc = new JTextArea(5,20);
        JComboBox<String> txt_product_cat = new JComboBox<>(new products_handler().getProductCat());

        txt_product_cat.setBackground(decode(new colors().bg_clr));

        txt_product_desc.setLineWrap(true);
        txt_product_desc.setWrapStyleWord(true);
        JScrollPane textArea_pane = new JScrollPane(txt_product_desc);
        textArea_pane.setBorder(new MatteBorder(1,1,1,1,Color.black));

        JTextField[] inputs = {txt_product_slug, txt_product_name, txt_product_price, txt_product_qty};
        for (JTextField txt : inputs) {
            txt.setFont(new Font("Arial", Font.PLAIN, 16));
            Border matteBorder = new MatteBorder(1, 1, 1, 1, decode(new colors().s_clr));
            Border emptyBorder = new EmptyBorder(5, 10, 5, 10);
            txt.setBorder(BorderFactory.createCompoundBorder(matteBorder, emptyBorder));
            txt.setColumns(20);
        }

        JButton updateUserBtn = new JButton("UPDATE");
        Border matteBorder = new MatteBorder(1,1,1,1, decode(new colors().s_clr));
        Border emptyBorder = new EmptyBorder(10,30,10,30);
        updateUserBtn.setBorder(BorderFactory.createCompoundBorder(matteBorder,emptyBorder));
        updateUserBtn.setForeground(decode(new colors().bg_clr));
        updateUserBtn.setBackground(decode(new colors().s_clr));

        JButton deleteProductBtn = new JButton("DELETE");
        deleteProductBtn.setBorder(BorderFactory.createCompoundBorder(matteBorder,emptyBorder));
        deleteProductBtn.setForeground(decode(new colors().bg_clr));
        deleteProductBtn.setBackground(decode(new colors().delete));

        gbc.gridx = 1;
        gbc.gridy = 0;
        content.add(panelName , gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        content.add(lbl_product_slug, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        content.add(txt_product_slug, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        content.add(lbl_product_name, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        content.add(txt_product_name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        content.add(lbl_product_price, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        content.add(txt_product_price, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        content.add(lbl_product_qty, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        content.add(txt_product_qty, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        content.add(lbl_product_desc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        content.add(textArea_pane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        content.add(lbl_product_cat, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        content.add(txt_product_cat, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        content.add(resultMsg, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        content.add(updateUserBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        content.add(deleteProductBtn, gbc);

        content_wrapper.add(content, BorderLayout.NORTH);
        updateProductsPanel.add(content_wrapper, BorderLayout.WEST);

        updateProductsTable(updateProductsPanel,txt_product_slug,txt_product_name,txt_product_price,txt_product_qty,txt_product_desc,txt_product_cat);

        //! ================ BUTTON ACTIONS ================ //

        updateUserBtn.addActionListener(e -> {
            if (
                    txt_product_slug.getText().equals("") ||
                    lbl_product_name.getText().equals("") ||
                    txt_product_price.getText().equals("") ||
                    txt_product_qty.getText().equals("") ||
                    txt_product_desc.getText().equals(""))
            {
                JOptionPane.showMessageDialog(new JFrame(),"Please check again!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                int confirmResult = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
                if (confirmResult == 0) {
                    boolean success = manager_handler.updateProducts(
                            txt_product_slug.getText(),
                            txt_product_name.getText(),
                            Double.parseDouble(txt_product_price.getText()),
                            Integer.parseInt(txt_product_qty.getText()),
                            txt_product_desc.getText(),
                            (String) txt_product_cat.getSelectedItem());

                    if (success){
                        resultMsg.setForeground(decode(new colors().success));
                        resultMsg.setText("selected product successfully updated!");
                        Timer timer = new Timer(1000, e1 -> {
                            new products_handler().getProducts();
                            resultMsg.setText("");
                            txt_product_slug.setText("");
                            txt_product_name.setText("");
                            txt_product_price.setText("");
                            txt_product_qty.setText("");
                            txt_product_desc.setText("");
                            txt_product_cat.setSelectedIndex(0);
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(),"error with updating selected product!");
                    }

                }
            }
            new products_handler().refreshProductTable(this.productTable);
        });

        deleteProductBtn.addActionListener(e -> {
            if (
                    txt_product_slug.getText().equals("") ||
                    txt_product_name.getText().equals("") ||
                    txt_product_price.getText().equals("") ||
                    txt_product_qty.getText().equals("") ||
                    txt_product_desc.getText().equals("") )
            {
                JOptionPane.showMessageDialog(new JFrame(),"Please check again!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                int confirmResult = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
                if (confirmResult == 0) {
                    boolean success = manager_handler.deleteProduct(
                            txt_product_slug.getText(),
                            txt_product_name.getText());

                    if (success){
                        resultMsg.setForeground(decode(new colors().success));
                        resultMsg.setText("selected product removed successfully!");
                        Timer timer = new Timer(1000, e1 -> {
                            new products_handler().getProducts();
                            resultMsg.setText("");
                            resultMsg.setText("");
                            txt_product_slug.setText("");
                            txt_product_name.setText("");
                            txt_product_price.setText("");
                            txt_product_qty.setText("");
                            txt_product_desc.setText("");
                            txt_product_cat.setSelectedIndex(0);
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(),"error with removing selected product!");
                    }

                }
            }
            new products_handler().refreshProductTable(this.productTable);
        });
    }

    //! ======================= TABLES ======================= //

    private void updateUsersTable(JPanel editUsers, JTextField txt_id, JTextField txt_name, JTextField txt_pass, JCheckBox txt_manager, JTextField txt_address, JTextField txt_phone, JTextField txt_email) {

        DefaultTableModel tableColumns = new DefaultTableModel();

        tableColumns.addColumn("Emp ID");
        tableColumns.addColumn("Emp Name");
        tableColumns.addColumn("Emp Pass");
        tableColumns.addColumn("Is Manager");
        tableColumns.addColumn("Emp Address");
        tableColumns.addColumn("Emp Phone");
        tableColumns.addColumn("Emp Email");

        userTable = new JTable(tableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable.setBackground(decode(new colors().bg_clr));

        userTable.setBackground(decode(new colors().bg_clr));
        userTable.setRowHeight(20);
        userTable.setShowGrid(false);
        userTable.setIntercellSpacing(new Dimension(0, 0));


        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) cell).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
                return cell;
            }
        };
        for (int i = 0; i < userTable.getColumnModel().getColumnCount(); i++) {
            userTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        //! ----------------------------------------

        userTable.setBackground(decode(new colors().bg_clr));
        JTableHeader header = userTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(new Font("Arial", Font.PLAIN, 14));
        header.setBackground(Color.decode("#176B87"));
        header.setBorder(new LineBorder(Color.decode("#176B87")));
        header.setForeground(Color.WHITE);

        //! ----------------------------------------

        JTable finalTable = userTable;
        userTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = finalTable.getSelectedRow();
                if (selectedRow != -1) {
                    txt_id.setText((String) finalTable.getValueAt(selectedRow, 0));
                    txt_name.setText((String) finalTable.getValueAt(selectedRow, 1));
                    txt_pass.setText((String) finalTable.getValueAt(selectedRow, 2));
                    txt_manager.setSelected((boolean) finalTable.getValueAt(selectedRow, 3));
                    txt_address.setText((String) finalTable.getValueAt(selectedRow, 4));
                    txt_phone.setText((String) finalTable.getValueAt(selectedRow, 5));
                    txt_email.setText((String) finalTable.getValueAt(selectedRow, 6));
                }
            }
        });

        for (Employee employee : manager_handler.getUsers()) {
            tableColumns.addRow(new Object[]{
                    employee.getEmpId(),
                    employee.getEmpName(),
                    employee.getEmpPass(),
                    employee.isManager(),
                    employee.getEmpAddress(),
                    employee.getEmpPhone(),
                    employee.getEmpEmail()
            });
        }

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(Color.white);
        scrollPane.setViewportBorder(null);

        editUsers.add(scrollPane, BorderLayout.CENTER);

    }

    private void updateProductsTable(JPanel updateProductsPanel, JTextField txt_product_slug, JTextField txt_product_name, JTextField txt_product_price, JTextField txt_product_qty, JTextArea txt_product_desc, JComboBox<String> txt_product_cat){

        DefaultTableModel tableColumns = new DefaultTableModel();

        tableColumns.addColumn("Slug");
        tableColumns.addColumn("Name");
        tableColumns.addColumn("Price");
        tableColumns.addColumn("Quantities");
        tableColumns.addColumn("Description");
        tableColumns.addColumn("Category");

        //! ----------------------------------------

        productTable = new JTable(tableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        productTable.setBackground(decode(new colors().bg_clr));
        productTable.setRowHeight(20);
        productTable.setShowGrid(false);
        productTable.setIntercellSpacing(new Dimension(0, 0));


        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) cell).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
                return cell;
            }
        };
        for (int i = 0; i < productTable.getColumnModel().getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        //! ----------------------------------------

        productTable.setBackground(decode(new colors().bg_clr));
        JTableHeader header = productTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(new Font("Arial", Font.PLAIN, 14));
        header.setBackground(Color.decode("#610C9F"));
        header.setBorder(new LineBorder(Color.decode("#610C9F")));
        header.setForeground(Color.WHITE);

        //! ----------------------------------------

        JTable finalTable = productTable;
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = finalTable.getSelectedRow();
                if (selectedRow != -1) {
                    txt_product_slug.setText((String) finalTable.getValueAt(selectedRow, 0));
                    txt_product_name.setText((String) finalTable.getValueAt(selectedRow, 1));
                    txt_product_price.setText(String.valueOf((double) finalTable.getValueAt(selectedRow, 2)));
                    txt_product_qty.setText(String.valueOf((int) finalTable.getValueAt(selectedRow, 3)));
                    txt_product_desc.setText((String) finalTable.getValueAt(selectedRow, 4));
                    txt_product_cat.setSelectedItem( finalTable.getValueAt(selectedRow, 5));
                }
            }
        });

        for (Product product : new products_handler().getProducts()) {
            tableColumns.addRow(new Object[]{
                    product.getProductSlug(),
                    product.getProductName(),
                    product.getProductPrice(),
                    product.getProductQty(),
                    product.getProductDesc(),
                    product.getProductCat(),
            });
        }

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(Color.white);
        scrollPane.setViewportBorder(null);

        updateProductsPanel.add(scrollPane, BorderLayout.CENTER);

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
        tableColumns.addColumn("Category");
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
                    product.getProductName(),
                    product.getProductPrice(),
                    product.getProductQty(),
                    product.getProductCat(),
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
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0, 1, 3));

        }
    }

}
