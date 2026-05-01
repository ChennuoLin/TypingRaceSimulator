import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ADDPLAYER_Win {

    private static ComboxPanel equipCombobox, keyboardsCombobox, symbolCombobox, TypingStyleCombobox;

    private static JTextField nameEnter;

    private static JLabel displayAtt;

    private static JFrame frameAdd;

    private static SetSymbolArray SymbolsArrays;
    private static DisplayListClass PlayerDisplays;

    static class ComboxPanel extends JPanel{
        private final JLabel NameLabel;
        private final JComboBox<Item> ComboBoxArray;

        static class Item {
            String name;
            String desc;
            public Item(String TypeName, String name, String desc1,String desc2) {
                this.name = name;
                this.desc = "burnout:"+desc1+" & accuracy:"+desc2;
                if (TypeName.equals("Symbol: ")) this.desc = "";

            }
            @Override
            public String toString() {

                return name;
            }
        }

        public ComboxPanel(String Name, String[][] CArray){
            this.setLayout(new BorderLayout());
            this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            Item[] items = new Item[CArray.length];
            for (int i = 0; i < CArray.length; i++) {
                items[i] = new Item(Name, CArray[i][0], CArray[i][1], CArray[i][2]);
            }

            NameLabel = new JLabel(Name);
            NameLabel.setFont(new Font("", Font.BOLD, 20));

            ComboBoxArray = new JComboBox<>(items);

            ComboBoxArray.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value,
                                                              int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Item item) {
                        if (index == -1) {
                            setText(item.name);
                        } else {
                            setText("<html><font size=5 color=black>" + item.name + "</font><br><font size=4 color=gray>" + item.desc + "</font></html>");
                        }
                    }
                    return this;
                }
            });

            this.add(NameLabel,BorderLayout.WEST);
            this.add(ComboBoxArray,BorderLayout.CENTER);
        }

        public char getSelectedName(){
            Item item = (Item) ComboBoxArray.getSelectedItem();

            return item.name.charAt(0);
        }

        public String getEquName(){
            Item item = (Item) ComboBoxArray.getSelectedItem();
            return item.name;
        }

        public Double[] getSelect(){
            Double[] returnNum = new Double[2];
            Item item = (Item) ComboBoxArray.getSelectedItem();
            if (item == null) return returnNum;
            String[] simplify = (item.desc).split(" & ");
            double renum1 = Double.parseDouble(simplify[0].split(":")[1]);
            double renum2 = Double.parseDouble(simplify[1].split(":")[1]);
            returnNum[0] = renum1;
            returnNum[1] = renum2;
            return returnNum;

        }

        public void SetData(String num){
             ArrayList<String> indexgetarr = new ArrayList<>();

            for (int i = 0; i < ComboBoxArray.getItemCount(); i++) {
                Item item = ComboBoxArray.getItemAt(i);
                indexgetarr.add(item.name);
            }

            ComboBoxArray.setSelectedIndex(indexgetarr.indexOf(num));

        }

    }


    public ADDPLAYER_Win(Typist TyClass,SetSymbolArray setSymbolArray, DisplayListClass PlayerDisplay){

        SymbolsArrays = setSymbolArray;
        PlayerDisplays = PlayerDisplay;

        frameAdd = new JFrame("Typing Race Simulator---ADD PLAYER");
        frameAdd.setSize(700, 450);
        frameAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAdd.setLocationRelativeTo(null);
        frameAdd.setLayout(new BorderLayout());

        frameAdd.add(AddPeopleSetPanel(),BorderLayout.CENTER);

        if (TyClass == null){
            ADDPlayer(TyClass);
        }else {
            EDITPlayer(TyClass);
        }

        frameAdd.setVisible(true);

    }

    public void ADDPlayer(Typist TyClass){
        frameAdd.add(displayAttribute(true,TyClass),BorderLayout.EAST);
    }

    public void EDITPlayer(Typist TyClass){
        frameAdd.add(displayAttribute(false,TyClass),BorderLayout.EAST);

        nameEnter.setText(TyClass.getName());

        ComboxPanel[] ComboBoxArray = {equipCombobox,keyboardsCombobox,TypingStyleCombobox};
        for (int i = 0; i<ComboBoxArray.length; i++){
            ComboBoxArray[i].SetData(TyClass.getEqu()[i]);
        }

        symbolCombobox.ComboBoxArray.setEditable(true);
        symbolCombobox.ComboBoxArray.getEditor().setItem(String.valueOf(TyClass.getSymbol()));
        symbolCombobox.ComboBoxArray.setEnabled(false);
    }

    public static JPanel AddPeopleSetPanel(){
        JPanel Centre = new JPanel();
        Centre.setLayout(new GridLayout(5,1));
        Centre.setBorder(BorderFactory.createEmptyBorder(10, 10,10, 10));

        JPanel UserName = new JPanel();
        UserName.setLayout(new BorderLayout());
        UserName.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel NameLabel = new JLabel("Name: ");
        NameLabel.setFont(new Font("", Font.BOLD ,20));

        nameEnter = new JTextField();
        nameEnter.setFont(new Font("", Font.PLAIN ,20));
        UserName.add(NameLabel,BorderLayout.WEST);
        UserName.add(nameEnter,BorderLayout.CENTER);
        Centre.add(UserName);



        JPanel TypingStylePanel = new JPanel();
        TypingStylePanel.setLayout(new BorderLayout());
        TypingStylePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel TypingStyleLabel = new JLabel("Typing Style: ");
        TypingStyleLabel.setFont(new Font("", Font.BOLD ,20));

        String[][]  TypingStyleArray = new String[][]{{"Not equipped","+0","+0"},{"Touch Typist","-0.3","-0.4"},
                {"Hunt & Peck","+0.3","-0.1"}, {"Phone Thumbs","+0.3","+0.1"},
                {"Voice-to-Tex","-0.4","+0.4"}};
        TypingStyleCombobox = new ComboxPanel("Typing Style: ",TypingStyleArray);

        Centre.add(TypingStyleCombobox);


        String[][]  KeyboardsArray = new String[][]{{"Not equipped","+0","+0"},{"Mechanical","+0","+0.3"},
                {"Membrane","+0","+0.2"}, {"Touchscreen","+0","-0.2"},
                {"Stenography","+0","-0.3"}};
        keyboardsCombobox = new ComboxPanel("Keyboard: ",KeyboardsArray);

        Centre.add(keyboardsCombobox);


        symbolCombobox = new ComboxPanel("Symbol: ",SymbolsArrays.getData());
        symbolCombobox.ComboBoxArray.setFont(new Font("",Font.BOLD,20));

        Centre.add(symbolCombobox);


        String[][] equipArray = {{"Not equipped","+0","+0"},{"Wrist Suppor","-0.2","+0.1"},
                {"Energy Drink","-0.1","+0.2"},{"Noise-Cancelling","0","+0.3"}};
        equipCombobox = new ComboxPanel("Add-ons: ",equipArray);

        Centre.add(equipCombobox);

        return Centre;
    }


    public static JPanel displayAttribute(boolean SaveOrChange,Typist TyClass){
        JPanel Centre = new JPanel();
        Centre.setLayout(new BorderLayout());
        Centre.setBorder(BorderFactory.createEmptyBorder(10, 10,25, 10));

        try{
            int[] countScores = TyClass.CountScore();
            double bestmax = 0.0;
            try {
                bestmax = Collections.max(TyClass.getWPMarray());
            }catch (NoSuchElementException e){

            }


            String res = String.format("<html>Standings:<br>No1 - %s<br>No2 - %s<br>No3 - %s<br>WPM-Average:%.2f<br>Best-WPM:%.2f<br>Total burnout:%d <br>Average burnout:%.2f<br>Number of Competitions:%d</html>",
                    countScores[0],countScores[1],countScores[2],TyClass.getWPMavage(), bestmax,TyClass.sumburnoutTime(), TyClass.burnoutTimeAverage(),TyClass.getjoinTime());
            displayAtt = new JLabel(res);
            displayAtt.setFont(new Font("", Font.BOLD, 20));
            Centre.add(displayAtt,BorderLayout.CENTER);

        }catch (NullPointerException e){

        }



        JPanel DeleteOrSave = new JPanel();
        DeleteOrSave.setLayout(new GridLayout(2,1));

        JButton save = new JButton("        SAVE        ");
        save.setFont(new Font("", Font.BOLD ,20));
        save.setBackground(new Color(50, 140, 50));
        save.setForeground(new Color(255, 255, 255));

        save.addActionListener(e -> {
            String labelText = nameEnter.getText();
            if (labelText.isEmpty()){
                JOptionPane.showMessageDialog(null, "Name is Empty!", "ABOUT NAME!",JOptionPane.WARNING_MESSAGE);
            }else {
                Double Burnout = 0.5;
                Double Accuracy = 0.5;

                ComboxPanel[] ComboBoxArray = {equipCombobox,keyboardsCombobox,TypingStyleCombobox};

                String[] equArray = new String[3];

                for (int i = 0; i<ComboBoxArray.length; i++){

                    equArray[i] = ComboBoxArray[i].getEquName();

                    Burnout+=ComboBoxArray[i].getSelect()[0];
                    Accuracy+=ComboBoxArray[i].getSelect()[1];
                }
                Burnout = Math.round(Burnout*100)/100.0;
                Accuracy = Math.round(Accuracy*100)/100.0;
                //System.out.println("Burnout:"+Burnout+"Accuracy:"+Accuracy);

                if(SaveOrChange) {

                    PlayerDisplays.addData(new Typist(symbolCombobox.getSelectedName(), nameEnter.getText(), Accuracy, equArray));
                    //!!!
                    SymbolsArrays.removeData(String.valueOf(symbolCombobox.getSelectedName()));

                }else {

                    char limit = TyClass.getSymbol();
                    ArrayList<Integer> limitR = TyClass.getDataR();

                    ArrayList<Double> limitWPM = TyClass.getWPMarray();

                    ArrayList<Integer> limitB = TyClass.getburnoutTimeList();

                    PlayerDisplays.removeData(TyClass);

                    Typist stta = new Typist(limit, nameEnter.getText(), Accuracy, equArray);
                    PlayerDisplays.addData(stta);

                    for (int n:limitR){
                        stta.addDataRanking(n);
                    }
                    for(Double n:limitWPM){

                        stta.addWPM(n);
                    }
                    for (int n: limitB){
                        stta.addburnoutTimeList(n);
                    }


                }
                PlayerDisplays.refreshData(true);
                frameAdd.dispose();
            }


        });

        DeleteOrSave.add(save);

        if (!SaveOrChange){
            JButton delete = new JButton("DELETE");
            delete.setFont(new Font("", Font.BOLD ,20));
            delete.setBackground(new Color(220, 50, 50));
            delete.setForeground(new Color(255, 255, 255));

            delete.addActionListener(e -> {
                char limit = TyClass.getSymbol();
                SymbolsArrays.addData(String.valueOf(limit));

                PlayerDisplays.removeData(TyClass);

                PlayerDisplays.refreshData(true);
                frameAdd.dispose();
            });
            DeleteOrSave.add(delete);

        }


        Centre.add(DeleteOrSave,BorderLayout.SOUTH);



        return Centre;
    }
}
