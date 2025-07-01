package Task_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // To store tasks in memory

public class ToDoApp extends JFrame {

    // GUI Components
    private JTextField taskInputField;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel; // Model for JList

    // Data storage for tasks
    private ArrayList<String> tasks;

    public ToDoApp() {
        setTitle("My To-Do List App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Data Initialization
        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();

        // Layout Managers  
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 

        taskInputField = new JTextField(25);
        taskInputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(taskInputField);

        addButton = new JButton("Add Task");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(100, 180, 100));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Center Panel (Task List)  
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel (Delete Button)  
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        deleteButton = new JButton("Delete Selected Task");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(200, 80, 80));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Event Handling  
        addButton.addActionListener(new AddButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());

        // Allow pressing Enter in the text field to add task
        taskInputField.addActionListener(new AddButtonListener());

        // Make the frame visible
        setVisible(true);
    }

    // ActionListener for Add Buttno
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String taskText = taskInputField.getText().trim();
            if (!taskText.isEmpty()) {
                tasks.add(taskText);   
                listModel.addElement(taskText);
                taskInputField.setText("");
            } else {
                JOptionPane.showMessageDialog(ToDoApp.this,
                        "Task cannot be empty!",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ActionListener for Delete Button
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex();

            if (selectedIndex != -1) {
                int confirm = JOptionPane.showConfirmDialog(ToDoApp.this,
                        "Are you sure you want to delete the selected task?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    tasks.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                }
            } else {
                JOptionPane.showMessageDialog(ToDoApp.this,
                        "Please select a task to delete.",
                        "No Task Selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoApp());
    }
}