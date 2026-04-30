import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainStartDisplay {
    private JTextArea gameTextArea;

    public MainStartDisplay() {

        gameTextArea = new JTextArea();

        gameTextArea.setRows(10);

        gameTextArea.setBorder(new LineBorder(Color.BLACK, 2));

        gameTextArea.setFont(new Font("", Font.PLAIN, 20));

        gameTextArea.setEditable(false);

        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);
    }

    public JTextArea getDisplayArea() {
        return gameTextArea;
    }

    public void SetDisplayheight(int n){
        gameTextArea.setRows(n+5);
    }
    public void showText(String text) {
        gameTextArea.append(text+"\n");
    }

    public String multiplePrint(char character, int num){

        int i = 0;
        String longStrings = "";
        while (i < num)
        {
            longStrings=longStrings+character;
            i = i + 1;
        }
        return longStrings;
    }

    // 清空显示区域
    public void clearDisplay() {
        gameTextArea.setText("");
    }

    public void ShowWPM(int Number, Typist players){

        gameTextArea.append("No."+Number+" | "+players.getName()+"("+players.getSymbol()+") | WPM:"+String.format("%.2f", players.getWPM())+"\n");

    }
}