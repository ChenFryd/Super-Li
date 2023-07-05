package InterfaceLayer.GUI.HRModule.HRManager;

import BussinessLayer.HRModule.Controllers.Facade;
import BussinessLayer.HRModule.Objects.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class PrintEmployees extends JFrame {
    private final Facade _facade = Facade.getInstance();

    public PrintEmployees() {
        // Set the size and layout of the frame
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Create a text area to display employee information
        JTextArea employeeInfoArea = new JTextArea();
        employeeInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(employeeInfoArea);



        // Add the text area to the frame
        add(scrollPane, BorderLayout.CENTER);

        try {
            // Retrieve the list of employees from the facade
            List<Employee> employees = _facade.getAllEmployees();

            // Display the employee information in the text area
            for (Employee employee : employees) {
                employeeInfoArea.append(employee.toString() + "\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(PrintEmployees.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Create a "Close" button and add an ActionListener
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        HRmenu hrmenu = new HRmenu();
                        // Hide this frame
                        setVisible(false);

                        // Show the main menu
                        //hrmenu.setVisible(true);
                    }
                });

        // Add a WindowListener to the frame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Create an instance of the main menu frame
                HRmenu mainMenu = new HRmenu();
                //mainMenu.setVisible(true);
            }
        });

        // Add the "Close" button to the frame
        add(closeButton, BorderLayout.SOUTH);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrintEmployees();
            }
        });
    }
}
