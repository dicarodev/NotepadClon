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

            // No funcionabien, esta leyenco todas la lineas continuamente
            String[] lines = textArea.getText().split("\n");

            for (String line : lines) {
                int charsLine = controller.countChars(line) + 1;
                lbl_columns.setText(charsLine + "Col");
            }

            if (chars == 1) {
                lbl_caracteres.setText(chars + " carácter.");
            } else {
                lbl_caracteres.setText(chars + " caracteres.");
            }
        }
    }
}
