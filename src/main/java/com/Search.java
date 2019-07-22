package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;


public class Search extends JFrame {

    private static JFrame form;
    private static JLabel lb1, lb2, lb3, lb4, lb5, lbEducation;
    private static  JCheckBox cb1, cb2, cb3;
    private static JTextField tf1, tf2, tf3, tf4;
    private  static  DefaultListModel lm;
    private  static  JList jl;
    private  static  JPanel jp;
    private  static  JButton bt1, bt2, bt3, bt4, bt5, bt6;
    private  static Font font = new Font("Times New Roman", Font.BOLD, 16);
    private  static  Font font1 = new Font("Times New Roman", Font.PLAIN, 14);
    private  static Font font2 = new Font("Arial", Font.BOLD, 13);
    private static  Font font3 = new Font("Arial", Font.ROMAN_BASELINE, 15);



    private static JComboBox educationSelectList;


    Search() {

        form = new JFrame();
        form.setBounds(10, 20, 1100, 600);
        form.setTitle("СОРСИНГ КАНДИДАТОВ НА РАБОТУ");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLayout(null);


        lb1 = new JLabel();
        lb1.setBounds(15, 10, 200, 40);
        lb1.setText("ИСТОЧНИКИ ПОИСКА : ");
        lb1.setFont(font);
        form.add(lb1);

        lb2 = new JLabel();
        lb2.setBounds(15, 220, 200, 40);
        lb2.setText("ПАРАМЕТРЫ ПОИСКА : ");
        lb2.setFont(font);
        form.add(lb2);

        lb3 = new JLabel();
        lb3.setBounds(15, 280, 100, 20);
        lb3.setText("ВАКАНСИЯ : ");
        lb3.setFont(font1);
        form.add(lb3);

        tf1 = new JTextField();
        tf1.setBounds(300, 280, 150, 20);
        form.add(tf1);

        lb4 = new JLabel();
        lb4.setBounds(15, 320, 150, 20);
        lb4.setText("СПЕЦИАЛЬНОСТЬ : ");
        lb4.setFont(font1);
        form.add(lb4);

        tf2 = new JTextField();
        tf2.setBounds(300, 320, 150, 20);
        tf2.setText("");
        form.add(tf2);

        lb5 = new JLabel();
        lb5.setBounds(15, 360, 370, 20);
        lb5.setText("ЗНАНИЕ ЯЗЫКА ПРОГРАММИРОВАНИЯ : ");
        lb5.setFont(font1);
        form.add(lb5);

        tf3 = new JTextField();
        tf3.setBounds(300, 360, 150, 20);
        tf3.setText("");
        form.add(tf3);

        lbEducation = new JLabel();
        lbEducation.setBounds(15, 400, 370, 20);
        lbEducation.setText("ОБРАЗОВАНИЕ : ");
        lbEducation.setFont(font1);
        form.add(lbEducation);


        educationSelectList = new JComboBox();
        educationSelectList.setBounds(300, 400, 150, 20);
        educationSelectList.addItem("высшее");
        educationSelectList.addItem("неполное высшее");
        educationSelectList.addItem("средне-специальное");
        educationSelectList.addItem("Неважно");
        form.add(educationSelectList);

        lbEducation = new JLabel();
        lbEducation.setBounds(730, 10, 370, 40);
        lbEducation.setText("СПИСОК НАЙДЕННЫХ КАНДИДАТОВ : ");
        lbEducation.setFont(font);
        form.add(lbEducation);

        cb1 = new JCheckBox();
        cb1.setBounds(20, 60, 150, 30);
        cb1.setText("SUPERJOB");
        cb1.setSelected(false);
        cb1.setFont(font2);
        cb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //
            }
        });
        form.add(cb1);

        cb2 = new JCheckBox();
        cb2.setBounds(20, 100, 150, 30);
        cb2.setText("ZARPLATA");
        cb2.setSelected(false);
        cb2.setFont(font2);
        cb2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //
            }
        });
        form.add(cb2);

        cb3 = new JCheckBox();
        cb3.setBounds(20, 140, 150, 30);
        cb3.setText("VK");
        cb3.setSelected(false);
        cb3.setFont(font2);
        cb3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                //
            }
        });
        form.add(cb3);

        jp = new JPanel();
        jp.setBounds(730, 60, 330, 300);

        lm = new DefaultListModel();
        jl = new JList(lm);
        jl.setBounds(730, 60, 330, 300);
        jl.setVisibleRowCount(10);
        jl.setSelectionMode(1);
        jl.setSelectedIndex(-1);
        jp.add(jl);

        form.add(jl);

        bt1 = new JButton();
        bt1.setBounds(500, 230, 150, 45);
        bt1.setText("ПОИСК");
        form.add(bt1);
        bt1.setBackground(Color.WHITE);
        bt1.setForeground(Color.darkGray);
        bt1.setFont(font2);
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SuperJob superJob = new SuperJob();
                List<String> resultLinks = superJob.startSearch();
                for (String elem: resultLinks) {
                    lm.addElement(elem);
                }

                //
            }
        });

        bt2 = new JButton();
        bt2.setBounds(500, 300, 150, 45);
        bt2.setText("СОХРАНИТЬ");
        bt2.setBackground(Color.WHITE);
        bt2.setForeground(Color.darkGray);
        bt2.setFont(font2);
        form.add(bt2);
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        bt3 = new JButton();
        bt3.setBounds(850, 500, 200, 35);
        bt3.setText("ЗАГРУЗИТЬ ИЗ ФАЙЛА");
        bt3.setBackground(Color.white);
        bt3.setForeground(Color.black);
        bt3.setFont(font3);
        form.add(bt3);
        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        bt4 = new JButton();
        bt4.setBounds(730, 380, 170, 25);
        bt4.setText("ОЧИСТИТЬ СПИСОК");
        bt4.setBackground(Color.gray);
        bt4.setForeground(Color.white);
        bt4.setFont(font2);
        form.add(bt4);
        bt4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        bt5 = new JButton();
        bt5.setBounds(910, 380, 150, 25);
        bt5.setText("ОЧИСТИТЬ ФАЙЛ");
        bt5.setBackground(Color.gray);
        bt5.setForeground(Color.white);
        bt5.setFont(font2);
        form.add(bt5);
        bt5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        form.show();
    }

    public static String getIdEducation() {
        return (String) educationSelectList.getItemAt(educationSelectList.getSelectedIndex());
    }

    public static void main(String[] args) {
        Search object = new Search();
    }
}

