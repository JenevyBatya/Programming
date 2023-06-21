package org.lab_5.Gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.lab_5.*;
import org.lab_5.Models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreeSectionFrame extends JFrame {

    private Hashtable<Integer, Organization> hashtable;
    private Object[] rowData;
    ObjectMapper objectMapper = new ObjectMapper();
    ReceptionReader receptionReader = new ReceptionReader();
    private String message;
    CommandExecute receivedMessage;
    private JButton coordinateButton;
    private JComboBox<String> languageComboBox;

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox<String> comboBox;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTable organizationTable;
    private BufferedReader input;
    private PrintWriter output;
    Socket socket;
    Request request;
    JScrollPane tableScrollPane;
    JPanel inputPanel;
    JPanel thirdSectionPanel;
    Container container;
    private int thirdSectionWidth;
    private int inputSectionWidth;
    private int tableSectionWidth;
    private String source = "Русский";
    ResourceBundle bundle = ResourceBundle.getBundle("messages_ru", new UTF8Control());

    // Добавьте остальные поля JTextField

    public ThreeSectionFrame(Hashtable<Integer, Organization> hashtable, BufferedReader input, PrintWriter output, Socket socket) {
        this.hashtable = hashtable;
        this.input = input;
        this.output = output;
        this.socket = socket;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        DatabaseCheckerThread checkerThread = new DatabaseCheckerThread();
        executorService.scheduleAtFixedRate(checkerThread, 0, 1500, TimeUnit.MILLISECONDS);

        setVisible(true);
        // Установка размеров окна
        setMinimumSize(new Dimension(1400, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание контейнера для размещения компонентов
        container = getContentPane();
        container.setLayout(new BorderLayout());

        // Создание компонента для первой секции (таблицы)
        tableScrollPane = createTableComponent();
        container.add(tableScrollPane, BorderLayout.EAST);

        // Создание компонента для второй секции (поля ввода)
        inputPanel = createInputPanel();
        container.add(inputPanel, BorderLayout.CENTER);

        // Создание компонента для третьей секции (пустой)
        thirdSectionPanel = createThirdSection();
        container.add(thirdSectionPanel, BorderLayout.WEST);

        // Настройка размеров секций в процентах от размеров окна

        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Выполняем необходимые действия при закрытии окна
                try {
                    // Закрываем сокет
                    // ...
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private JScrollPane createTableComponent() {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Запретить редактирование ячеек
            }
        };
        tableModel.setColumnIdentifiers(new String[]{
                "id", "name", "xCoordinates", "yCoordinates", "annualTurnover", "fullName", "employeeCount",
                "organizationType", "street", "xLocation", "yLocation", "zLocation"
        });
        for (Organization organization : hashtable.values()) {
            rowData = new Object[]{organization.getId(), organization.getName(), organization.getCoordinates().getX(), organization.getCoordinates().getY(),
                    organization.getAnnualTurnover(), organization.getFullName(), organization.getEmployeesCount(), organization.getType().toString(), organization.getPostalAddress().getStreet(),
                    organization.getPostalAddress().getTown().getX(), organization.getPostalAddress().getTown().getY(), organization.getPostalAddress().getTown().getZ()};
            // Добавление остальных полей объекта Organization
            tableModel.addRow(rowData);
        }

        organizationTable = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
// Установка объекта RowSorter для таблицы
        organizationTable.setRowSorter(sorter);

        // Запретить выбор отдельных ячеек
        DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        organizationTable.setSelectionModel(selectionModel);

        // Отменить редактирование ячеек при двойном щелчке
        organizationTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Отменить редактирование ячеек
                    for (int column = 0; column < organizationTable.getColumnCount(); column++) {
                        Class<?> columnClass = organizationTable.getColumnClass(column);
                        organizationTable.setDefaultEditor(columnClass, null);
                    }
                }
            }
        });

        // Добавляем слушатель событий выбора строки
        organizationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Получаем индекс выбранной строки
                int selectedRow = organizationTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int selectedRowIndex = organizationTable.convertRowIndexToModel(selectedRow);
                    textField1.setText(tableModel.getValueAt(selectedRowIndex, 0) != null ? tableModel.getValueAt(selectedRowIndex, 0).toString() : "");
                    textField2.setText(tableModel.getValueAt(selectedRowIndex, 1) != null ? tableModel.getValueAt(selectedRowIndex, 1).toString() : "");
                    textField3.setText(tableModel.getValueAt(selectedRowIndex, 2) != null ? tableModel.getValueAt(selectedRowIndex, 2).toString() : "");
                    textField4.setText(tableModel.getValueAt(selectedRowIndex, 3) != null ? tableModel.getValueAt(selectedRowIndex, 3).toString() : "");
                    textField5.setText(tableModel.getValueAt(selectedRowIndex, 4) != null ? tableModel.getValueAt(selectedRowIndex, 4).toString() : "");
                    textField6.setText(tableModel.getValueAt(selectedRowIndex, 5) != null ? tableModel.getValueAt(selectedRowIndex, 5).toString() : "");
                    String comboBoxValue = tableModel.getValueAt(selectedRowIndex, 7) != null ? tableModel.getValueAt(selectedRowIndex, 7).toString() : "";
                    comboBox.setSelectedItem(comboBoxValue);
                    textField8.setText(tableModel.getValueAt(selectedRowIndex, 6) != null ? tableModel.getValueAt(selectedRowIndex, 6).toString() : "");
                    textField9.setText(tableModel.getValueAt(selectedRowIndex, 8) != null ? tableModel.getValueAt(selectedRowIndex, 8).toString() : "");
                    textField10.setText(tableModel.getValueAt(selectedRowIndex, 9) != null ? tableModel.getValueAt(selectedRowIndex, 9).toString() : "");
                    textField11.setText(tableModel.getValueAt(selectedRowIndex, 10) != null ? tableModel.getValueAt(selectedRowIndex, 10).toString() : "");
                    textField12.setText(tableModel.getValueAt(selectedRowIndex, 11) != null ? tableModel.getValueAt(selectedRowIndex, 11).toString() : "");

                    // Добавьте обработку для остальных полей JTextField
                }
            }
        });
        organizationTable.getTableHeader().addMouseListener(new MouseAdapter() {
            private int clickCount = 0;
            private int columnIndex = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                int newColumnIndex = organizationTable.columnAtPoint(e.getPoint());
                if (newColumnIndex == columnIndex) {
                    clickCount++;
                } else {
                    clickCount = 1;
                    columnIndex = newColumnIndex;
                }

                // Определяем порядок сортировки (по возрастанию или убыванию)
                SortOrder sortOrder;
                if (clickCount % 3 == 1) {
                    sortOrder = SortOrder.ASCENDING;
                } else if (clickCount % 3 == 2) {
                    sortOrder = SortOrder.DESCENDING;
                } else {
                    // Возвращаем первоначальный порядок (без сортировки)
                    organizationTable.getRowSorter().setSortKeys(null);
                    return;
                }

                // Создаем список сортировки для выбранного столбца
                java.util.List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(newColumnIndex, sortOrder));

                // Устанавливаем список сортировки в таблицу
                organizationTable.getRowSorter().setSortKeys(sortKeys);
            }
        });


        // Создание панели с прокруткой для таблицы
        JScrollPane scrollPane = new JScrollPane(organizationTable);

        // Установка политики прокрутки
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        tableSectionWidth = (int) (getWidth() * 0.6); // Ширина секции таблицы (30% от ширины окна)
        scrollPane.setPreferredSize(new Dimension(tableSectionWidth, getHeight()));

        return scrollPane;
    }

    private JPanel createInputPanel() {
        // Создание панели для полей ввода
        JPanel panel = new JPanel(new GridBagLayout());

        // Задание переменной для ширины и высоты полей ввода
        int fieldWidth = 150;  // Ширина поля ввода
        int fieldHeight = 30;  // Высота поля ввода

        // Создание и добавление полей ввода и надписей
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 14);

        JLabel label1 = new JLabel("id:");
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        textField1.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textField1, gbc);

        JLabel label2 = new JLabel(bundle.getString("name"));
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(textField2, gbc);

        JLabel label3 = new JLabel(bundle.getString("coorX"));
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(textField3, gbc);

        JLabel label4 = new JLabel(bundle.getString("coorY"));
        textField4 = new JTextField();
        textField4.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(label4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(textField4, gbc);

        JLabel label5 = new JLabel(bundle.getString("annual"));
        textField5 = new JTextField();
        textField5.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(label5, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(textField5, gbc);

        JLabel label6 = new JLabel(bundle.getString("full"));
        textField6 = new JTextField();
        textField6.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(label6, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(textField6, gbc);

        JLabel label7 = new JLabel(bundle.getString("type"));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(label7, gbc);

        // Создание выборного бокса
        String[] organizationTypes = {"COMMERCIAL", "PUBLIC", "GOVERNMENT", "TRUST", "PRIVATE_LIMITED_COMPANY"};
        comboBox = new JComboBox<>(organizationTypes);
        comboBox.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(comboBox, gbc);

        JLabel label8 = new JLabel(bundle.getString("count"));
        textField8 = new JTextField();
        textField8.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(label8, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(textField8, gbc);

        JLabel label9 = new JLabel(bundle.getString("street"));
        textField9 = new JTextField();
        textField9.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(label9, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(textField9, gbc);

        JLabel label10 = new JLabel(bundle.getString("locX"));
        textField10 = new JTextField();
        textField10.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(label10, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(textField10, gbc);

        JLabel label11 = new JLabel(bundle.getString("locY"));
        textField11 = new JTextField();
        textField11.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(label11, gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(textField11, gbc);

        JLabel label12 = new JLabel(bundle.getString("locZ"));
        textField12 = new JTextField();
        textField12.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(label12, gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        panel.add(textField12, gbc);

        coordinateButton = new JButton("Отобразить оси координат");
        coordinateButton.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 0;
        gbc.gridy = 14;
        panel.add(coordinateButton, gbc);

        // Создание и добавление комбо-бокса
        String[] languages = {"Русский", "Lietuviškas", "Norsk", "English (Ireland)"};
        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setSelectedItem(source);
        languageComboBox.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        gbc.gridx = 1;
        gbc.gridy = 15;
        panel.add(languageComboBox, gbc);

        // Добавьте остальные поля JTextField и JLabel
        JButton clearButton = new JButton(bundle.getString("empty_button"));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Снимаем выделение строки в таблице
                organizationTable.clearSelection();

                // Очищаем все поля ввода
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField8.setText("");
                textField9.setText("");
                textField10.setText("");
                textField11.setText("");
                textField12.setText("");

            }
        });
        gbc.gridx = 1;
        gbc.gridy = 12;
        panel.add(clearButton, gbc);

        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                source = languageComboBox.getSelectedItem().toString();
                System.out.println(source);
                switch (source) {
                    case "Norsk":
                        System.out.println("1");
                        bundle = ResourceBundle.getBundle("messages_no", new UTF8Control());
                        System.out.println(bundle.getBaseBundleName());
                        break;
                    case "English (Ireland)":
                        System.out.println("2");
                        bundle = ResourceBundle.getBundle("messages_en_IE", new UTF8Control());
                        System.out.println(bundle.getBaseBundleName());
                        break;
                    case "Lietuviškas":
                        System.out.println("3");
                        bundle = ResourceBundle.getBundle("messages_lt", new UTF8Control());
                        System.out.println(bundle.getBaseBundleName());
                        break;
                    case "Русский":
                        bundle = ResourceBundle.getBundle("messages_ru", new UTF8Control());
                        break;

                }
                container.remove(inputPanel);
                container.remove(thirdSectionPanel);
                inputPanel = createInputPanel();
                container.add(inputPanel, BorderLayout.CENTER);

                // Создание компонента для третьей секции (пустой)
                thirdSectionPanel = createThirdSection();
                container.add(thirdSectionPanel, BorderLayout.WEST);
                container.repaint();
                container.revalidate();
                System.out.println(bundle.getString("name"));
                System.out.println(bundle.getBaseBundleName());
                // В этом месте вы можете выполнить необходимые действия при изменении значения JComboBox
            }
        });

        inputSectionWidth = (int) (getWidth() * 0.3); // Ширина секции полей ввода (80% от ширины окна)
        panel.setPreferredSize(new Dimension(inputSectionWidth, getHeight()));


        return panel;
    }

    private JPanel createThirdSection() {
        JPanel panel = new JPanel(new GridLayout(11, 1, 10, 10));

        JButton averageButton = new JButton("Average of annual turnover");
        JButton clearButton = new JButton("Clear");
        JButton executeScriptButton = new JButton("Execute Script");
        JButton helpButton = new JButton("Help");
        JButton historyButton = new JButton("History");
        JButton infoButton = new JButton("Info");
        JButton insertButton = new JButton("Insert");
        JButton removeKeyButton = new JButton("Remove Key");
        JButton removeLowerButton = new JButton("Remove Lower");
        JButton replaceIfGreaterButton = new JButton("Replace if Greater");
        JButton updateButton = new JButton("Update");

        panel.add(averageButton);
        panel.add(clearButton);
        panel.add(executeScriptButton);
        panel.add(helpButton);
        panel.add(historyButton);
        panel.add(infoButton);
        panel.add(insertButton);
        panel.add(removeKeyButton);
        panel.add(removeLowerButton);
        panel.add(replaceIfGreaterButton);
        panel.add(updateButton);
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна
                request = new Request("average_of_annual_turnover", null, null);
                try {
                    receivedMessage = sendAndReceive(request);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JDialog dialog = new JDialog();
                dialog.setTitle("Average of Annual Turnover");
                dialog.setModal(true);

                // Добавление компонентов в диалоговое окно
                JLabel descriptionLabel = new JLabel(bundle.getString("avg_text"));
                JLabel valueLabel = new JLabel(receivedMessage.getResponse()); // Замените на ваше фиксированное число
                JPanel contentPane = (JPanel) dialog.getContentPane();
                contentPane.setLayout(new FlowLayout());
                contentPane.add(descriptionLabel);
                contentPane.add(valueLabel);
                // Установка размеров диалогового окна
                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this); // Установка положения окна относительно основного окна

                // Отображение диалогового окна
                dialog.setVisible(true);
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Удаление всех данных из таблицы
                // Вывод временной надписи "Очистка прошла успешно"
                request = new Request("clear", null, null);
                try {
                    receivedMessage = sendAndReceive(request);
                    System.out.println(receivedMessage.getResponse());
                    JDialog successDialog = new JDialog();
                    successDialog.setTitle(bundle.getString("answer_from_the_server"));
                    successDialog.setModal(true);
                    JLabel successLabel = new JLabel(bundle.getString("clear_answer"));

                    successLabel.setForeground(Color.GREEN);

                    successDialog.add(successLabel);

                    successDialog.setSize(300, 150);
                    successDialog.setLocationRelativeTo(ThreeSectionFrame.this);

                    successDialog.setVisible(true);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        executeScriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна для ввода пути до файла
                JDialog dialog = new JDialog();
                dialog.setTitle("Execute Script");
                dialog.setModal(true);

                JLabel filePathLabel = new JLabel(bundle.getString("execute_text"));
                JTextField filePathField = new JTextField(20);
                JButton executeButton = new JButton(bundle.getString("done"));

                JPanel contentPane = (JPanel) dialog.getContentPane();
                contentPane.setLayout(new FlowLayout());
                contentPane.add(filePathLabel);
                contentPane.add(filePathField);
                contentPane.add(executeButton);

                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String filePath = filePathField.getText();

                        if (filePath.isEmpty()) {
                            // Вывод красного предупреждения о пустом поле ввода
                            JOptionPane.showMessageDialog(dialog, bundle.getString("execute_error"), "", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this);

                dialog.setVisible(true);
            }
        });


        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна с описанием команд
                request = new Request("help", null, null);
                try {
                    receivedMessage = sendAndReceive(request);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JDialog dialog = new JDialog();
                dialog.setTitle("Help");
                dialog.setModal(true);

                JTextArea helpTextArea = new JTextArea();
                helpTextArea.setEditable(false);
                helpTextArea.setText(bundle.getString("help_answer"));
                JScrollPane scrollPane = new JScrollPane(helpTextArea);
                dialog.getContentPane().add(scrollPane);

                dialog.setSize(400, 300);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this);

                dialog.setVisible(true);

            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна с историей команд
                request = new Request("history", null, null);
                try {
                    receivedMessage = sendAndReceive(request);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JDialog dialog = new JDialog();
                dialog.setTitle("History");
                dialog.setModal(true);

                JTextArea historyTextArea = new JTextArea();
                historyTextArea.setEditable(false);
                // Здесь необходимо получить историю команд и отобразить их в текстовом поле
                //TODO
                String history = receivedMessage.getResponse(); // Пример получения истории команд

                historyTextArea.setText(history);

                JScrollPane scrollPane = new JScrollPane(historyTextArea);
                dialog.getContentPane().add(scrollPane);

                dialog.setSize(400, 300);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this);

                dialog.setVisible(true);
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                request = new Request("info", null, null);
                try {
                    receivedMessage = sendAndReceive(request);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Создание диалогового окна с информацией о компонентах таблицы
                JDialog dialog = new JDialog();
                dialog.setTitle("Info");
                dialog.setModal(true);

                JTextArea infoTextArea = new JTextArea();
                infoTextArea.setEditable(false);
                // Здесь необходимо получить информацию о компонентах таблицы и отобразить ее в текстовом поле
                //TODO
                String[] data = receivedMessage.getResponse().split(" ");
                String info =  bundle.getString("info_type") + data[0] + "\n" + bundle.getString("info_size") + data[1];      // Пример получения информации о таблице

                infoTextArea.setText(info);

                JScrollPane scrollPane = new JScrollPane(infoTextArea);
                dialog.getContentPane().add(scrollPane);

                dialog.setSize(400, 300);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this);

                dialog.setVisible(true);
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String organizationData;

                try {
                    if (!textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("insert_clean_error"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);
                    } else if (textField2.getText().isEmpty() || textField3.getText().isEmpty() || Integer.parseInt(textField3.getText()) > (-612) ||
                            textField4.getText().isEmpty() || Long.parseLong(textField4.getText()) > (420) ||
                            textField5.getText().isEmpty() || Integer.parseInt(textField5.getText()) < (0) ||
                            textField8.getText().isEmpty() || Integer.parseInt(textField8.getText()) < (0) ||
                            textField9.getText().isEmpty() || textField9.getText().length() > 34 ||
                            textField10.getText().isEmpty() || textField11.getText().isEmpty()) {
                        throw new NumberFormatException();
                    } else {
                        try {
                            Long.parseLong(textField10.getText());
                            Float.parseFloat(textField11.getText());
                            if (!textField12.getText().isEmpty()) {
                                Float.parseFloat(textField12.getText());
                            }
                            organizationData = textField2.getText().replaceAll(" ", "~") + " " + textField3.getText().replaceAll(" ", "~") + " " + textField4.getText().replaceAll(" ", "~") + " " +
                                    LocalDate.now() + " " + textField5.getText().replaceAll(" ", "~") + " " + textField6.getText().replaceAll(" ", "~") + " " + textField8.getText().replaceAll(" ", "~") + " " +
                                    comboBox.getSelectedItem().toString() + " " + textField9.getText() + " " + textField10.getText() + " " +
                                    textField11.getText().replaceAll(" ", "~") + " " + textField12.getText().replaceAll(" ", "~");
                            System.out.println(organizationData);
                            request = new Request("insert", null, organizationData);
                            try {
                                receivedMessage = sendAndReceive(request);
                                System.out.println(receivedMessage.getResponse());
                                JDialog successDialog = new JDialog();
                                successDialog.setTitle(bundle.getString("answer_from_the_server"));
                                successDialog.setModal(true);
                                JLabel successLabel;
                                if (receivedMessage.isSuccess()) {
                                    successLabel=new JLabel(bundle.getString("insert_success"));
                                    successLabel.setForeground(Color.GREEN);
                                } else {
                                    successLabel=new JLabel(bundle.getString("insert_success"));
                                    successLabel.setForeground(Color.RED);
                                }
                                successDialog.add(successLabel);

                                successDialog.setSize(300, 150);
                                successDialog.setLocationRelativeTo(ThreeSectionFrame.this);

                                successDialog.setVisible(true);

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } catch (NumberFormatException ex) {
                            throw new NumberFormatException();
                        }
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("invalid_data"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        removeKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна для ввода значения
                JDialog dialog = new JDialog();
                dialog.setTitle("Remove Key");
                dialog.setModal(true);

                JLabel keyLabel = new JLabel(bundle.getString("id_data"));
                JTextField keyField = new JTextField(20);
                JButton removeButton = new JButton(bundle.getString("done"));

                JPanel contentPane = (JPanel) dialog.getContentPane();
                contentPane.setLayout(new FlowLayout());
                contentPane.add(keyLabel);
                contentPane.add(keyField);
                contentPane.add(removeButton);

                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String keyValue = keyField.getText();
                        if (keyValue.isEmpty()) {
                            JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("remove_empty_error"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);
                        } else {
                            try {
                                Integer.parseInt(keyValue);
                                request = new Request("remove_key", keyValue, null);
                                try {
                                    receivedMessage = sendAndReceive(request);
                                    System.out.println(receivedMessage.getResponse());
                                    JDialog successDialog = new JDialog();
                                    successDialog.setTitle(bundle.getString("answer_from_the_server"));
                                    successDialog.setModal(true);
                                    JLabel successLabel;
                                    if (receivedMessage.isSuccess()) {
                                        successLabel=new JLabel(bundle.getString("remove_key_success"));
                                        successLabel.setForeground(Color.GREEN);
                                    } else {
                                        successLabel=new JLabel(bundle.getString("remove_key_unsuccess"));
                                        successLabel.setForeground(Color.RED);
                                    }
                                    successDialog.add(successLabel);

                                    successDialog.setSize(300, 150);
                                    successDialog.setLocationRelativeTo(ThreeSectionFrame.this);

                                    successDialog.setVisible(true);

                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("invalid_data"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);

                            }
                        }
                    }
                });

                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this);

                dialog.setVisible(true);
            }
        });
        removeLowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна для ввода значения
                JDialog dialog = new JDialog();
                dialog.setTitle("Remove Lower");
                dialog.setModal(true);

                JLabel valueLabel = new JLabel(bundle.getString("id_data"));
                JTextField valueField = new JTextField(20);
                JButton removeButton = new JButton(bundle.getString("done"));

                JPanel contentPane = (JPanel) dialog.getContentPane();
                contentPane.setLayout(new FlowLayout());
                contentPane.add(valueLabel);
                contentPane.add(valueField);
                contentPane.add(removeButton);

                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String keyValue = valueField.getText();
                        if (keyValue.isEmpty()) {
                            JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("remove_lower_empty_error"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);
                        } else {
                            try {
                                Integer.parseInt(keyValue);
                                request = new Request("remove_key", keyValue, null);
                                try {
                                    receivedMessage = sendAndReceive(request);
                                    System.out.println(receivedMessage.getResponse());
                                    JDialog successDialog = new JDialog();
                                    successDialog.setTitle(bundle.getString("answer_from_the_server"));
                                    successDialog.setModal(true);
                                    JLabel successLabel=new JLabel(bundle.getString("remove_lower_success"));
                                    if (receivedMessage.isSuccess()) {
                                        successLabel.setForeground(Color.GREEN);
                                    }
                                    successDialog.add(successLabel);

                                    successDialog.setSize(300, 150);
                                    successDialog.setLocationRelativeTo(ThreeSectionFrame.this);

                                    successDialog.setVisible(true);

                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("invalid_data"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);

                            }
                        }
                    }
                });

                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this);

                dialog.setVisible(true);
            }
        });
        replaceIfGreaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Создание диалогового окна для ввода значений
                JDialog dialog = new JDialog();
                dialog.setTitle("Replace if Greater");
                dialog.setModal(true);

                JLabel idLabel = new JLabel(bundle.getString("id_data"));
                JTextField idField = new JTextField(10);

                JLabel columnLabel = new JLabel(bundle.getString("replace_column"));
                JComboBox<String> columnComboBox = new JComboBox<String>(new String[]{"annualTurnover", "employeesCount"});

                JLabel valueLabel = new JLabel(bundle.getString("replace_data"));
                JTextField valueField = new JTextField(10);

                JButton replaceButton = new JButton(bundle.getString("done"));

                JPanel contentPane = (JPanel) dialog.getContentPane();
                contentPane.setLayout(new FlowLayout());
                contentPane.add(idLabel);
                contentPane.add(idField);
                contentPane.add(columnLabel);
                contentPane.add(columnComboBox);
                contentPane.add(valueLabel);
                contentPane.add(valueField);
                contentPane.add(replaceButton);

                replaceButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String id = idField.getText();
                        String column = (String) columnComboBox.getSelectedItem();
                        String value = valueField.getText();
                        if (id.isEmpty() || value.isEmpty()) {
                            JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("invalid_data"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);
                        } else {
                            try {
                                if (column.equals("annualTurnover")) {
                                    Double.parseDouble(value);
                                } else {
                                    Integer.parseInt(value);
                                }
                                request = new Request("replace_if_greater", id, column + " " + value);
                                try {
                                    receivedMessage = sendAndReceive(request);
                                    System.out.println(receivedMessage.getResponse());
                                    JDialog successDialog = new JDialog();
                                    successDialog.setTitle(bundle.getString("answer_from_the_server"));
                                    successDialog.setModal(true);
                                    JLabel successLabel;
                                    if (receivedMessage.isSuccess()) {
                                        successLabel=new JLabel(bundle.getString("replace_success"));
                                        successLabel.setForeground(Color.GREEN);
                                    } else {
                                        successLabel=new JLabel(bundle.getString("replace_unsuccess"));
                                        successLabel.setForeground(Color.RED);
                                    }
                                    successDialog.add(successLabel);

                                    successDialog.setSize(300, 150);
                                    successDialog.setLocationRelativeTo(ThreeSectionFrame.this);

                                    successDialog.setVisible(true);

                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("invalid_data"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);

                            }
                        }

                    }
                });

                dialog.setSize(400, 200);
                dialog.setLocationRelativeTo(ThreeSectionFrame.this);

                dialog.setVisible(true);
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("update_empty_error"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);
                    } else if (textField2.getText().isEmpty() || textField3.getText().isEmpty() || Integer.parseInt(textField3.getText()) > (-612) ||
                            textField4.getText().isEmpty() || Long.parseLong(textField4.getText()) > (420) ||
                            textField5.getText().isEmpty() || Double.parseDouble(textField5.getText()) < (0) ||
                            textField8.getText().isEmpty() || Integer.parseInt(textField8.getText()) < (0) ||
                            textField9.getText().isEmpty() || textField9.getText().length() > 34 ||
                            textField10.getText().isEmpty() || textField11.getText().isEmpty()) {
                        System.out.println(textField1.getText());
                        System.out.println(textField2.getText());
                        System.out.println(textField3.getText());
                        System.out.println(textField4.getText());
                        System.out.println(textField5.getText());
                        System.out.println(textField6.getText());
                        System.out.println(textField8.getText());
                        System.out.println(textField9.getText());
                        System.out.println(textField10.getText());
                        System.out.println(textField11.getText());
                        System.out.println(textField12.getText());
                        throw new NumberFormatException();
                    } else {
                        try {
                            System.out.println(textField1.getText());
                            System.out.println(textField2.getText());
                            System.out.println(textField3.getText());
                            System.out.println(textField4.getText());
                            System.out.println(textField5.getText());
                            System.out.println(textField6.getText());
                            System.out.println(textField8.getText());
                            System.out.println(textField9.getText());
                            System.out.println(textField10.getText());
                            System.out.println(textField11.getText());
                            System.out.println(textField12.getText());
                            Long.parseLong(textField10.getText());
                            Float.parseFloat(textField11.getText());
                            if (!textField12.getText().isEmpty()) {
                                Float.parseFloat(textField12.getText());
                            }
                            String organizationData = textField2.getText().replaceAll(" ", "~") + " " + textField3.getText().replaceAll(" ", "~") + " " + textField4.getText().replaceAll(" ", "~") + " " +
                                    textField5.getText().replaceAll(" ", "~") + " " + textField6.getText().replaceAll(" ", "~") + " " + textField8.getText().replaceAll(" ", "~") + " " +
                                    comboBox.getSelectedItem().toString() + " " + textField9.getText() + " " + textField10.getText() + " " +
                                    textField11.getText().replaceAll(" ", "~") + " " + textField12.getText().replaceAll(" ", "~");
                            System.out.println(organizationData);
                            request = new Request("update", textField1.getText(), organizationData);
                            try {
                                receivedMessage = sendAndReceive(request);
                                System.out.println(receivedMessage.getResponse());
                                JDialog successDialog = new JDialog();
                                successDialog.setTitle("Ответ от сервера");
                                successDialog.setModal(true);
                                JLabel successLabel;
                                if (receivedMessage.isSuccess()) {
                                    successLabel=new JLabel(bundle.getString("update_success"));
                                    successLabel.setForeground(Color.GREEN);
                                } else {
                                    successLabel=new JLabel(bundle.getString("update_unsuccess"));
                                    successLabel.setForeground(Color.RED);
                                }
                                successDialog.add(successLabel);

                                successDialog.setSize(300, 150);
                                successDialog.setLocationRelativeTo(ThreeSectionFrame.this);

                                successDialog.setVisible(true);

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } catch (NumberFormatException ex) {
                            throw new NumberFormatException();
                        }
                    }
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(ThreeSectionFrame.this, bundle.getString("invalid_data"), bundle.getString("attention"), JOptionPane.WARNING_MESSAGE);

                }
            }

        });
        coordinateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizationGraph organizationGraph = new OrganizationGraph(hashtable);
            }
        });

        int thirdSectionWidth = getWidth() - tableSectionWidth - inputSectionWidth; // Ширина третьей секции
        panel.setPreferredSize(new Dimension(thirdSectionWidth, getHeight()));
        return panel;
    }

    public CommandExecute sendAndReceive(Request request) throws IOException {
        String json = objectMapper.writeValueAsString(request);
        output.println(json);
        message = input.readLine();
        return receptionReader.read(message);
    }

    private class DatabaseCheckerThread implements Runnable {
        private static final String file = "C:\\Users\\theal\\Desktop\\data.txt";
        private static final List<String> lines;

        static {
            try {
                lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
                System.out.println(lines);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private final String[] data = lines.toArray(new String[0]);
        private final String SSH_HOST = data[0].split(" ")[1];
        private final String SSH_USER = data[1].split(" ")[1];
        private final String SSH_PASSWORD = data[2].split(" ")[1];
        private final int SSH_PORT = 2222;
        private final String BD_USERNAME = data[3].split(" ")[1];
        private final String BD_PASSWORD = data[4].split(" ")[1];
        private final int CHECK_INTERVAL = 1500;
        Connection connection;

        public void connect() throws JSchException, IOException, SQLException {
            System.out.println(lines);
            JSch jsch = new JSch();
            Session session = jsch.getSession(SSH_USER, SSH_HOST, SSH_PORT);
            session.setPassword(SSH_PASSWORD);
            // Настройка параметров сессии SSH
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            // Установка соединения через SSH
            session.connect();
            String url = "jdbc:postgresql://localhost:" + 1236 + "/" + "studs";
            connection = DriverManager.getConnection(url, BD_USERNAME, BD_PASSWORD);

        }

        @Override
        public void run() {

            try {
                connect();
                while (true) {
                    // Подключение к базе данных
                    // Выполнение запроса для получения значения boolean
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT value FROM Charger WHERE id = 1");
                    if (resultSet.next()) {
                        boolean value = resultSet.getBoolean("value");
                        // Обновление GUI с использованием метода SwingUtilities.invokeLater()
                        if (value) {
                            resultSet = statement.executeQuery("select * from organization");
                            Hashtable<Integer, Organization> organizationHashtable = new Hashtable<>();
                            while (resultSet.next()) {
                                Organization organization = new Organization(resultSet.getInt("id"), resultSet.getString("name"),
                                        new Coordinates(resultSet.getInt("coordinateX"), resultSet.getLong("coordinateY")),
                                        resultSet.getDate("creationDate").toLocalDate(), resultSet.getDouble("annualTurnover"), resultSet.getString("fullName"),
                                        resultSet.getInt("employeesCount"), OrganizationType.valueOf(resultSet.getString("type")),
                                        new Address(resultSet.getString("street"), new Location(resultSet.getLong("locationX"), resultSet.getFloat("locationY"), resultSet.getFloat("locationZ"))));
                                organizationHashtable.put(organization.getId(), organization);
                            }
                            hashtable = organizationHashtable;
                            container.remove(tableScrollPane);
                            tableScrollPane = createTableComponent();
                            container.add(tableScrollPane, BorderLayout.EAST);
                            container.repaint();
                            container.revalidate();
                            statement.executeUpdate("update Charger set value=false where id=1");
                        }
                        resultSet.close();
                        statement.close();

                        // Закрытие соединения
//                        connection.close();

                        // Приостановка потока на указанное время
                        Thread.sleep(CHECK_INTERVAL);
                    }
                }
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            } catch (JSchException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
