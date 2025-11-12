package com.resume.ui;

import javax.swing.*;
import java.awt.*;
import com.resume.util.DBManager;
import com.resume.util.ResumeGenerator;

public class ResumeBuilderGUI extends JFrame {

    // Declare text fields
    private JTextField nameField, emailField, educationField, skillsField, experienceField;

    public ResumeBuilderGUI() {
        setTitle("Online Resume Builder");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Create UI Components
        JLabel nameLabel = new JLabel("Full Name:");
        JLabel emailLabel = new JLabel("Email ID:");
        JLabel educationLabel = new JLabel("Education:");
        JLabel experienceLabel = new JLabel("Experience:");
        JLabel skillsLabel = new JLabel("Skills:");

        nameField = new JTextField();
        emailField = new JTextField();
        educationField = new JTextField();
        experienceField = new JTextField();
        skillsField = new JTextField();

        JButton saveButton = new JButton("Save Data");
        JButton generateButton = new JButton("Generate Resume");

        // Add components to the frame
        add(nameLabel); add(nameField);
        add(emailLabel); add(emailField);
        add(educationLabel); add(educationField);
        add(experienceLabel); add(experienceField);
        add(skillsLabel); add(skillsField);
        add(saveButton); add(generateButton);

        // Save Data Button Logic
        saveButton.addActionListener(e -> {
            DBManager.saveUser(
                    nameField.getText(),
                    emailField.getText(),
                    educationField.getText(),
                    experienceField.getText(),
                    skillsField.getText()
            );
            JOptionPane.showMessageDialog(this, "User data saved successfully!");
        });

        // Generate Resume Button Logic
        generateButton.addActionListener(e -> {
            ResumeGenerator.createPDF(
                    nameField.getText(),
                    emailField.getText(),
                    educationField.getText(),
                    experienceField.getText(),
                    skillsField.getText()
            );
            JOptionPane.showMessageDialog(this, "Resume generated successfully!");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResumeBuilderGUI().setVisible(true));
    }
}
