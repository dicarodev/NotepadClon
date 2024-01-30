package dicarodev.notepad.controller;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class ThreadCountChars extends Thread {

    private Controller controller;
    private JLabel lbl_caracteres;
    private JLabel lbl_lines;
    private JLabel lbl_columns;
    private JTextArea textArea;

    public ThreadCountChars(Controller controller, JTextArea textArea, JLabel lbl_caracteres, JLabel lbl_columns, JLabel lbl_lines) {
        this.controller = controller;
        this.lbl_caracteres = lbl_caracteres;
        this.textArea = textArea;
        this.lbl_columns = lbl_columns;
        this.lbl_lines = lbl_lines;
    }

    @Override
    public void run() {
        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                countChars();
                updateCursorPosition();
                checkAndHandleTextSelection();
            }
        });
    }

    private void countChars() {
        String text = textArea.getText();
        int chars = controller.countChars(text);
        String message = (chars == 1) ? "%d carácter" : "%d caracteres";
        lbl_caracteres.setText(String.format(message, chars));
    }

    private void checkAndHandleTextSelection() {
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();

        if (start != end) {
            String text = textArea.getSelectedText();
            int chars = controller.countChars(text);
            String message = (chars == 1) ? "%d de %d carácter" : "%d de %d caracteres";
            lbl_caracteres.setText(String.format(message, chars, controller.countChars(textArea.getText())));
        }
    }

    private void updateCursorPosition() {
        try {
            int caretPosition = textArea.getCaretPosition();
            int lineNumber = textArea.getLineOfOffset(caretPosition) + 1;
            int columnNumber = caretPosition - textArea.getLineStartOffset(lineNumber - 1) + 1;

            lbl_lines.setText(lineNumber + " Ln");
            lbl_columns.setText(columnNumber + " Col");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
