package dicarodev.notepad.controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class Controller {

    private String pathOpenedFile;

    public StringBuilder openFile() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = null;

        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Abrir fichero de texto");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros .txt", "txt");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                pathOpenedFile = fileChooser.getSelectedFile().getAbsolutePath();

                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                String line ;
                stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder;

    }

    public void save (String text){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            fileWriter = new FileWriter(pathOpenedFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(text);

            JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "El fichero se ha guardado con exito", "Guardado con exito", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAs(String text) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Guardar fichero de texto");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros .txt", "txt");
            fileChooser.setFileFilter(filter);

            int returnValue = fileChooser.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                fileWriter = new FileWriter(file);
                bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(text);

                JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "El fichero se ha guardado con exito", "Guardado con exito", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) bufferedWriter.close();
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public int countChars(String text){
        return text ==  null ? 0 : text.length();
    }

    public void changeTheme(JPanel panel, JRadioButton radioButton){
        if (radioButton.getText().equalsIgnoreCase("dark")){
            try{
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(panel); // Actualiza los componentes
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        } else if (radioButton.getText().equalsIgnoreCase("light")) {
            try{
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(panel); // Actualiza los componentes
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        }

    }
}
