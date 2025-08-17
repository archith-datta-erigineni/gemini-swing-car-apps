package com.example;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CarSuggestions extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new CarSuggestions();
        frame.setTitle("New Car Suggestions");
        frame.setBounds(100, 100, 700, 700);
        frame.setVisible(true);
    }

    JLabel lblTitle, lblBudget, lblStyle, lblMpg, lblMaintenance, lblComments;
    JTextField txtBudget, txtStyle, txtMpg, txtMaintenance, txtComments;
    JButton btnSubmit;

    public CarSuggestions() {
        // Helper to add focus listeners for expanding/compressing fields
        java.awt.Dimension compressedDim = new java.awt.Dimension(200, 35);
        java.awt.Dimension expandedDim = new java.awt.Dimension(350, 35);
        java.awt.event.FocusListener expandOnFocus = new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                ((JTextField) e.getComponent()).setPreferredSize(expandedDim);
                ((JTextField) e.getComponent()).revalidate();
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                ((JTextField) e.getComponent()).setPreferredSize(compressedDim);
                ((JTextField) e.getComponent()).revalidate();
            }
        };
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new java.awt.GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(new java.awt.Color(245, 248, 255));

        java.awt.Font titleFont = new java.awt.Font("Arial", java.awt.Font.BOLD, 28);
        java.awt.Font labelFont = new java.awt.Font("Arial", java.awt.Font.PLAIN, 18);
        java.awt.Font fieldFont = new java.awt.Font("Arial", java.awt.Font.PLAIN, 16);
        java.awt.Font buttonFont = new java.awt.Font("Arial", java.awt.Font.BOLD, 20);

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        lblTitle = new JLabel("🚗 Car Purchase Preferences");
        lblTitle.setFont(titleFont);
        lblTitle.setForeground(new java.awt.Color(33, 64, 128));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        mainPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        lblBudget = new JLabel("💰 What is your approximate budget?");
        lblBudget.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblBudget, gbc);
        txtBudget = new JTextField(20);
        txtBudget.setFont(fieldFont);
        txtBudget.setPreferredSize(compressedDim);
        txtBudget.addFocusListener(expandOnFocus);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        mainPanel.add(txtBudget, gbc);

        lblStyle = new JLabel("⭐️ What Style? (Family, Sports, Luxury, EV, Compact, etc.):");
        lblStyle.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        mainPanel.add(lblStyle, gbc);
        txtStyle = new JTextField(20);
        txtStyle.setFont(fieldFont);
        txtStyle.setPreferredSize(compressedDim);
        txtStyle.addFocusListener(expandOnFocus);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        mainPanel.add(txtStyle, gbc);

        lblMpg = new JLabel("⛽️ Approximate MPG or Commute/Usage:");
        lblMpg.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        mainPanel.add(lblMpg, gbc);
        txtMpg = new JTextField(20);
        txtMpg.setFont(fieldFont);
        txtMpg.setPreferredSize(compressedDim);
        txtMpg.addFocusListener(expandOnFocus);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        mainPanel.add(txtMpg, gbc);

        lblMaintenance = new JLabel("🛠️ What extent of sustenance? (Maintenance, insurance, etc.):");
        lblMaintenance.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        mainPanel.add(lblMaintenance, gbc);
        txtMaintenance = new JTextField(20);
        txtMaintenance.setFont(fieldFont);
        txtMaintenance.setPreferredSize(compressedDim);
        txtMaintenance.addFocusListener(expandOnFocus);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        mainPanel.add(txtMaintenance, gbc);

        lblComments = new JLabel("❓ Any additional Comments?");
        lblComments.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        mainPanel.add(lblComments, gbc);
        txtComments = new JTextField(20);
        txtComments.setFont(fieldFont);
        txtComments.setPreferredSize(compressedDim);
        txtComments.addFocusListener(expandOnFocus);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        mainPanel.add(txtComments, gbc);

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(buttonFont);
        btnSubmit.setBackground(new java.awt.Color(33, 150, 243));
        btnSubmit.setForeground(java.awt.Color.GREEN);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        mainPanel.add(btnSubmit, gbc);

        this.add(mainPanel);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String budget = txtBudget.getText();
                String style = txtStyle.getText();
                String yearStr = txtMpg.getText();
                String maintenace = txtMaintenance.getText();
                String comments = txtComments.getText();

                Gem gem = new Gem("gemini-1.5-flash");
                String response = gem.text("Find new car to buy with these preferences: Budget: " + budget
                        + ", Style/Type: " + style + ", MPG/Commute/Usage: " + yearStr + ", " + maintenace
                        + ", Additional Comments: " + comments);
                JTextArea ta = new JTextArea(20, 50);
                ta.setText(response);
                ta.setCaretPosition(0);
                ta.setEditable(false);
                ta.setFont(fieldFont);
                JScrollPane scrollPane = new JScrollPane(ta);
                scrollPane.setPreferredSize(new java.awt.Dimension(600, 350));
                JOptionPane.showMessageDialog(null, scrollPane, "Car Suggestions", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public boolean relevance(String budget, String style, String yearStr, String maintenance, String comments) {
        Gem gem = new Gem("gemini-1.5-flash");
        System.out.println(budget + " " + style + " " + yearStr + " " + comments);
        String prompt = "Here are my answers to the car search preference questions. Please respond with true if all are valid responses, or false if at least one is not.\n"
                + "Budget: \"" + budget + "\"\n"
                + "Style/Type: \"" + style + "\"\n"
                + "MPG/Commute/Usage: \"" + yearStr + "\"\n"
                + "Maintenance/Insurance/Sustenance Costs: \"" + maintenance + "\"\n"
                + "Additional Comments: \"" + comments + "\"";
        String response = gem.text(prompt);
        return Boolean.parseBoolean(response);
    }
}
