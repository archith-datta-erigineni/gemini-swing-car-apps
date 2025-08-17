package com.example;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class CarIssues extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new CarIssues();
            frame.setTitle("Car Diagnostic Helper");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(550, 750);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    JLabel lblMake, lblModel, lblYear, lblProblem;
    JTextField txtMake, txtModel, txtYear, txtProblem;
    JButton btnCommonProblems, btnSubmit;

    CarIssues() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new java.awt.Color(245, 245, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        java.awt.Font bigFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20);
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18);

        JLabel title = new JLabel("Car Diagnostic Helper", SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        title.setForeground(new java.awt.Color(60, 60, 120));
        add(title, BorderLayout.NORTH);

        lblMake = new JLabel("Make:");
        lblMake.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblMake, gbc);
        txtMake = new JTextField(15);
        txtMake.setFont(bigFont);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtMake, gbc);

        lblModel = new JLabel("Model:");
        lblModel.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblModel, gbc);
        txtModel = new JTextField(15);
        txtModel.setFont(bigFont);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtModel, gbc);

        lblYear = new JLabel("Year:");
        lblYear.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblYear, gbc);
        txtYear = new JTextField(15);
        txtYear.setFont(bigFont);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtYear, gbc);

        lblProblem = new JLabel("Describe Problem:");
        lblProblem.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblProblem, gbc);
        txtProblem = new JTextField(15);
        txtProblem.setFont(bigFont);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(txtProblem, gbc);

        btnSubmit = new JButton("Get Fix & Cost");
        btnSubmit.setFont(bigFont);
        btnSubmit.setBackground(new java.awt.Color(100, 180, 255));
        btnSubmit.setForeground(java.awt.Color.GREEN);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        btnCommonProblems = new JButton("See Common Issues");
        btnCommonProblems.setFont(bigFont);
        btnCommonProblems.setBackground(new java.awt.Color(120, 200, 180));
        btnCommonProblems.setForeground(java.awt.Color.RED);
        btnCommonProblems.setFocusPainted(false);
        btnCommonProblems.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new java.awt.Color(245, 245, 255));
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnCommonProblems);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);

        // Add a nice border and padding
        ((JComponent) getContentPane()).setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new java.awt.Color(180, 180, 220), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // Add action listeners
        btnCommonProblems.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String make = txtMake.getText();
                String model = txtModel.getText();
                String yearStr = txtYear.getText();

                Check test = new Check(make, model, yearStr);

                if (!test.verifyModelYear()) {
                    JOptionPane.showMessageDialog(CarIssues.this, "Invalid Car (Car Does Not Exist In Database)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    Gem gem = new Gem("gemini-1.5-flash");
                    String response = gem.text("Common issues with "+ yearStr + " "+make+" "+model+"");
                    JTextArea ta = new JTextArea(response, 12, 40);
                    ta.setLineWrap(true);
                    ta.setWrapStyleWord(true);
                    ta.setCaretPosition(0);
                    ta.setEditable(false);
                    JScrollPane scroll = new JScrollPane(ta);
                    scroll.setPreferredSize(new java.awt.Dimension(450, 250));
                    JOptionPane.showMessageDialog(CarIssues.this, scroll, "Common Issues", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String make = txtMake.getText();
                String model = txtModel.getText();
                String yearStr = txtYear.getText();
                String problem = txtProblem.getText();

                Check test = new Check(make, model, yearStr);

                if (!test.verifyModelYear()) {
                    JOptionPane.showMessageDialog(CarIssues.this, "Invalid Car (Car Does Not Exist In Database)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    Gem gem = new Gem("gemini-1.5-flash");
                    String response = gem.text("My "+ yearStr + " "+make+" "+model+" has this issue: "+problem+". How fix + approx cost?");
                    JTextArea ta = new JTextArea(response, 12, 40);
                    ta.setLineWrap(true);
                    ta.setWrapStyleWord(true);
                    ta.setCaretPosition(0);
                    ta.setEditable(false);
                    JScrollPane scroll = new JScrollPane(ta);
                    scroll.setPreferredSize(new java.awt.Dimension(450, 250));
                    JOptionPane.showMessageDialog(CarIssues.this, scroll, "Fix & Cost Estimate", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    }