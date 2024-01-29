package dicarodev.notepad.controller;

import javax.swing.*;

public class ThreadCountChars extends Thread {

    private Controller controller;
    private JLabel lbl_caracteres;
    private JLabel lbl_columns;
    private JTextArea textArea;

    public ThreadCountChars(Controller controller, JTextArea textArea, JLabel lbl_caracteres, JLabel lbl_columns) {
        this.controller = controller;
        this.lbl_caracteres = lbl_caracteres;
        this.textArea = textArea;
        this.lbl_columns = lbl_columns;
    }

    @Override
    public void run() {

        while (true) {
            int chars = controller.countChars(textArea.getText());


            // No funciona bien, esta en bucle contando los caracteres de todas las lineas
            /*String[] lines = textArea.getText().split("\n");

            for (int i = 0; i < lines.length; i++) {
                int charsLine = controller.countChars(lines[i]) + 1;
                lbl_columns.setText(charsLine + "Col");
            }*/

            if (chars == 1) {
                lbl_caracteres.setText(chars + " carácter.");
            } else {
                lbl_caracteres.setText(chars + " caracteres.");
            }
        }
    }
}
