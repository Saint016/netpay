import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OvertimeCalculator extends JFrame {

    private JTextField txtEmployeeID, txtEmployeeName, txtRatePerHour, txtOvertimeRate;
    private JTextField[] txtHours = new JTextField[14];
    private JTextField txtTotalNetPay;

    public OvertimeCalculator() {
        super("Overtime Calculator");

        createEmployeeIdPanel();
        createTimesheetPanel();
        createNetPayPanel();

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(this::calculateOvertime);

        setLayout(new GridLayout(1, 3, 10, 10));
        add(createEmployeeIdPanel());
        add(createTimesheetPanel());
        add(createNetPayPanel());
        add(btnCalculate);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createEmployeeIdPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        String[] labels = {"Employee ID:", "Employee Name:", "Rate per Hour:", "Overtime Rate:"};
        txtEmployeeID = new JTextField();
        txtEmployeeName = new JTextField();
        txtRatePerHour = new JTextField();
        txtOvertimeRate = new JTextField();

        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            JTextField textField = i == 0 ? txtEmployeeID : (i == 1 ? txtEmployeeName : (i == 2 ? txtRatePerHour : txtOvertimeRate));
            panel.add(textField);
        }

        return panel;
    }

    private JPanel createTimesheetPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        String[] labels = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday",
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            txtHours[i] = new JTextField();
            panel.add(txtHours[i]);
        }

        return panel;
    }

    private JPanel createNetPayPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
        String[] labels = {"Total Net Pay:"};
        txtTotalNetPay = new JTextField();

        for (String label : labels) {
            panel.add(new JLabel(label));
        }
        panel.add(txtTotalNetPay);

        return panel;
    }

    private void calculateOvertime(ActionEvent e) {
        try {
            double totalRegularHours = 0;

            for (int i = 0; i < 14; i++) {
                double hours = Math.max(0, Double.parseDouble(txtHours[i].getText()));
                totalRegularHours += hours;
            }

            double regularPay = totalRegularHours * Double.parseDouble(txtRatePerHour.getText());
            double overtimePay = 0; // Assume no overtime for simplicity
            double totalNetPay = regularPay + overtimePay;

            txtTotalNetPay.setText(String.format("%.2f", totalNetPay));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OvertimeCalculator().setVisible(true));
    }
}
