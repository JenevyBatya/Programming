package org.lab.Gui;

import org.lab.Models.Organization;
import org.lab.Models.OrganizationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

public class OrganizationGraph extends JFrame {

    private Hashtable<Integer, Organization> organizations;
    private static final int GRAPH_SIZE = 2000;
    private static final int POINT_SIZE = 10;

    public OrganizationGraph(Hashtable<Integer, Organization> organizations) {
        this.organizations = organizations;

        setTitle("OrganizationGraph");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(GRAPH_SIZE, GRAPH_SIZE);
        setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                for (Organization org : organizations.values()) {
                    int centerX = getWidth() / 2;
                    int centerY = getHeight() / 2;
                    double scaleX = (double) (getWidth() - 20) / GRAPH_SIZE;
                    double scaleY = (double) (getHeight() - 20) / GRAPH_SIZE;
                    int orgX = centerX + (int) (org.getCoordinates().getX() * scaleX);
                    int orgY = centerY - (int) (org.getCoordinates().getY() * scaleY);

                    if (Math.abs(x - orgX) <= POINT_SIZE && Math.abs(y - orgY) <= POINT_SIZE) {
                        showOrganizationInfo(org);
                        break;
                    }
                }
            }
        });

        setVisible(true);
    }

    private void showOrganizationInfo(Organization organization) {
        String message = "Organization Info:\n" +
                "ID: " + organization.getId() + "\n" +
                "Name: " + organization.getName() + "\n" +
                "Full name: " + organization.getFullName() + "\n" +
                "X: " + organization.getCoordinates().getX() + "\n" +
                "Y: " + organization.getCoordinates().getY() + "\n" +
                "Type: " + organization.getType() + "\n" +
                "Annual Turnover: " + organization.getAnnualTurnover() + "\n" +
                "Employees Count: " + organization.getEmployeesCount() + "\n" +
                "Creation Date: " + organization.getCreationDate();

        Object[] options = {"Изменить", "Закрыть"};
        int option = JOptionPane.showOptionDialog(this, message, "Organization Details",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (option == 0) {
            // Обработка нажатия на псевдокнопку "Изменить"
            // Ваш код для обработки изменения данных организации
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Отрисовка осей координат
        g.setColor(Color.BLACK);
        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0, centerX, getHeight());

        // Масштабирование точек
        double scaleX = (double) (getWidth() - 20) / GRAPH_SIZE;
        double scaleY = (double) (getHeight() - 20) / GRAPH_SIZE;

        // Отрисовка точек
        for (Organization org : organizations.values()) {
            int x = centerX + (int) (org.getCoordinates().getX() * scaleX);
            int y = centerY - (int) (org.getCoordinates().getY() * scaleY);

            Color pointColor = getColorForOrganizationType(org.getType());
            g.setColor(pointColor);
            g.fillOval(x - POINT_SIZE / 2, y - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
        }
    }

    private Color getColorForOrganizationType(OrganizationType type) {
        switch (type) {
            case COMMERCIAL:
                return Color.RED;
            case PUBLIC:
                return Color.BLUE;
            case GOVERNMENT:
                return Color.GREEN;
            case TRUST:
                return Color.ORANGE;
            case PRIVATE_LIMITED_COMPANY:
                return Color.MAGENTA;
            default:
                return Color.BLACK;
        }
    }
}
