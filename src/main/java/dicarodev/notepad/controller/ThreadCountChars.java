package dicarodev.notepad.controller;

import javax.swing.*;

public class ThreadCountChars extends Thread {

    private Controller controller;
    private JLabel lbl_caracteres;
    private JTextArea text;

    public ThreadCountChars(Controller controller, JTextArea text, JLabel lbl_caracteres) {
        this.controller = controller;
        this.lbl_caracteres = lbl_caracteres;
        this.text = text;
    }

    @Override
    public void run() {
        /*System.out.println(lbl_caracteres.getText());*/
        while (true){

            int chars = controller.countChars(text.getText());

            if (chars == 1) {
                lbl_caracteres.setText(chars + " carácter.");
            } else {
                lbl_caracteres.setText(chars + " caracteres.");
            }
        }

    }
}
