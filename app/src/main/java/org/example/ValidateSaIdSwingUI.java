package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValidateSaIdSwingUI extends JFrame {
    private JTextField idInputField;
    private JLabel resultLabel;

    public ValidateSaIdSwingUI() {
        setTitle("SA ID Validator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 180);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel promptLabel = new JLabel("Enter South African ID Number:");
        idInputField = new JTextField();
        JButton validateButton = new JButton("Validate");
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16f));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(promptLabel, BorderLayout.NORTH);
        inputPanel.add(idInputField, BorderLayout.CENTER);
        inputPanel.add(validateButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateId();
            }
        });
        idInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateId();
            }
        });
    }

    private void validateId() {
        String id = idInputField.getText().trim();
        boolean valid = ValidateSaId.isIdNumberValid(id);
        if (valid) {
            resultLabel.setText("✅ VALID SA ID number");
            resultLabel.setForeground(new Color(0, 128, 0));
        } else {
            resultLabel.setText("❌ INVALID SA ID number");
            resultLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ValidateSaIdSwingUI().setVisible(true);
        });
    }
}
