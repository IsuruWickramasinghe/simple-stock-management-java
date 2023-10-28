package com.applestock.view;

import com.applestock.controller.login_handler;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class login_panel extends JFrame{

    public login_panel() throws HeadlessException {

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int midWidth = (screenWidth / 2) - (800 / 2);
        int midHeight = (screenHeight / 2) - (600 / 2);

        try {
            setTitle("Apple Stock - Login");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setIconImage(new ImageIcon(getClass().getResource("/app-store.png")).getImage());
            setSize(800, 600);
            setResizable(false);
            setLocation(midWidth, midHeight);

            setLayout(new GridLayout(1, 2));

            JPanel left_panel = new JPanel();
            JPanel right_panel = new JPanel();

            leftPanel(left_panel);
            rightPanel(right_panel);

            add(left_panel);
            add(right_panel);

            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void leftPanel(@NotNull JPanel left_panel) {
        JPanel login_desc_panel = new JPanel();

        JLabel apple_stock = new JLabel("APPLE STOCK");
        apple_stock.setFont(new Font("Arial", Font.BOLD, 42));
        apple_stock.setForeground(Color.decode(new colors().bg_clr));
        apple_stock.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel img = new JLabel();
        img.setIcon(new ImageIcon(getClass().getResource("/package.png")));
        img.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel footer = new JLabel("designed and developed by: isuruW");
        footer.setForeground(Color.decode(new colors().bg_clr));
        footer.setFont(new Font("Arial", Font.BOLD, 10));

        left_panel.setBorder(new EmptyBorder(25, 5, 5, 5));
        left_panel.setBackground(Color.decode(new colors().s_clr));
        left_panel.setLayout(new BorderLayout());
        left_panel.add(login_desc_panel, BorderLayout.CENTER);

        login_desc_panel.setLayout(new BorderLayout());
        login_desc_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        login_desc_panel.setBackground(Color.decode(new colors().s_clr));
        login_desc_panel.add(apple_stock, BorderLayout.NORTH);
        login_desc_panel.add(img, BorderLayout.CENTER);
        login_desc_panel.add(footer, BorderLayout.SOUTH);
    }

    public void rightPanel(@NotNull JPanel right_panel) {
        right_panel.setBackground(Color.decode(new colors().bg_clr));
        right_panel.setLayout(new BorderLayout());
        right_panel.setBorder(new EmptyBorder(100, 10, 10, 10));

        JPanel top_panel = new JPanel();
        JPanel center_panel = new JPanel();
        JPanel login_form = new JPanel();
        login_form.setLayout(new GridBagLayout());

        top_panel.setBackground(Color.decode(new colors().bg_clr));
        center_panel.setBackground(Color.decode(new colors().bg_clr));
        login_form.setBackground(Color.decode(new colors().bg_clr));

        center_panel.setBorder(new EmptyBorder(30,0,0,0));

        JLabel loginIcon = new JLabel();
        loginIcon.setIcon(new ImageIcon(getClass().getResource("/app-store.png")));
        loginIcon.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField user_name = new JTextField(20);
        JTextField user_pass = new JPasswordField(20);
        JButton loginButton = new JButton("LOGIN");
        JLabel errorMsgLabel = new JLabel("");

        user_name.setColumns(20);
        user_pass.setColumns(20);

        user_name.setBorder(new MatteBorder(0,0,1,0,Color.black));
        user_pass.setBorder(new MatteBorder(0,0,1,0,Color.black));

        user_name.setFont(new Font("Arial", Font.PLAIN, 14));
        user_pass.setFont(new Font("Arial", Font.PLAIN, 14));

        loginButton.setBorder(new EmptyBorder(10,50,10,50));
        loginButton.setBackground(Color.decode(new colors().s_clr));
        loginButton.setForeground(Color.decode(new colors().bg_clr));

        errorMsgLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        errorMsgLabel.setForeground(Color.red);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        login_form.add(new JLabel("Username:"), gbc);

        gbc.gridx = 10;
        gbc.gridy = 0;
        login_form.add(user_name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        login_form.add(new JLabel("Password:"), gbc);

        gbc.gridx = 10;
        gbc.gridy = 4;
        login_form.add(user_pass, gbc);

        gbc.gridx = 10;
        gbc.gridy = 8;
        login_form.add(errorMsgLabel, gbc);

        gbc.gridx = 10;
        gbc.gridy = 12;
        login_form.add(loginButton, gbc);

        right_panel.add(top_panel, BorderLayout.NORTH);
        right_panel.add(center_panel, BorderLayout.CENTER);
        center_panel.add(login_form);
        top_panel.add(loginIcon);

        //! login action
        loginButton.addActionListener(e -> {
            boolean loginSuccess = new login_handler(user_name.getText(),user_pass.getText()).user_login();
            if (loginSuccess){
                login_panel.this.dispose();
            }
            else {
                errorMsgLabel.setText("incorrect user name or password");
            }
        });


    }

}
