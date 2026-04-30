import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * 游戏显示文本区域类
 * 封装了 JTextArea，用于展示游戏信息（如你需要的 1234 显示）
 */
public class MainStartDisplay {
    // 文本显示区域（成员变量）
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

    public void ShowWPM(Typist players){

        gameTextArea.append(players.getName()+"WPM:"+players.getWPM());

    }
}