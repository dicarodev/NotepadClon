package dicarodev.notepad.view;

import dicarodev.notepad.controller.Controller;
import dicarodev.notepad.controller.ThreadCountChars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private JPanel panel;
    private JMenuBar menu_bar;
    private JMenu menu_file;
    private JMenuItem mi_openFile;
    private JMenuItem mi_save;
    private JMenuItem mi_saveAs;
    private JTextArea textArea;
    private JLabel lbl_lines;
    private JLabel lbl_caracteres;
    private JRadioButton rb_dark;
    private JRadioButton rb_light;
    private JLabel lbl_columns;
    private final Controller controller;

    public View(){
        controller = new Controller();

        ThreadCountChars countChars = new ThreadCountChars(controller, textArea, lbl_caracteres, lbl_columns, lbl_lines);
        countChars.start();

        mi_openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilder = controller.openFile();

                if (stringBuilder != null){
                    String fileRead = stringBuilder.toString();
                    textArea.setText(fileRead);
                }
            }
        });
        mi_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToSave = textArea.getText();
                controller.save(textToSave);
            }
        });
        mi_saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToSave = textArea.getText();
                controller.saveAs(textToSave);
            }
        });

        rb_dark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeTheme(panel, rb_dark);
            }
        });
        rb_light.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeTheme(panel, rb_light);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
