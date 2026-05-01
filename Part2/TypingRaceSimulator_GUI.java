import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class TypingRaceSimulator_GUI {

    private static JSlider setParagraphSlider,setPeopleSlider;
    private static JRadioButton Normalmode,Coffeemode,Nightshiftmode;
    private static JButton StartButton ,ContrastiveButton;
    private static DisplayListClass PlayerDisplay, RankingList;
    private static MainStartDisplay GameDisplay;
    //private static JList<String> PlayerDisplay;

    public static void main(String[] args) {

        SetSymbolArray SymbolsArrays = new SetSymbolArray();

        JFrame frame = new JFrame("Typing Race Simulator");
        frame.setSize(1020, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        //main Display show 1234
        GameDisplay = new MainStartDisplay();
        frame.add(GameDisplay.getDisplayArea(),BorderLayout.NORTH);


        JPanel CentreBorder = new JPanel();
        CentreBorder.setLayout(new GridLayout(2,1));

        CentreBorder.add(SliderPanel());
        CentreBorder.add(PlayerPanel(SymbolsArrays));

        frame.add(CentreBorder,BorderLayout.CENTER);//Editor's Homepage

        frame.add(StartAndRankingList(),BorderLayout.WEST);

        frame.setVisible(true);


    }


    public static JPanel StartAndRankingList(){

        JPanel Centre = new JPanel();
        Centre.setLayout(new BorderLayout());
        Centre.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));

        JPanel Centremini = new JPanel();
        Centremini.setLayout(new BorderLayout());

        JLabel RankingLabel = new JLabel("Ranking List");
        RankingLabel.setFont(new Font("", Font.BOLD ,20));
        Centremini.add(RankingLabel,BorderLayout.NORTH);

        RankingList = new DisplayListClass();

        Centremini.add(RankingList,BorderLayout.CENTER);
        Centre.add(Centremini,BorderLayout.CENTER);

        StartButton = new JButton(" ==START== ");
        StartButton.setFont(new Font("", Font.BOLD ,30));
        Centre.add(StartButton,BorderLayout.NORTH);
        //=============================================================STARTSTARTSTARTSTART
        StartButton.addActionListener(e -> {
            String select = "None";
            if (Normalmode.isSelected()) select = "Normal mode";
            if (Coffeemode.isSelected()) select = "Coffee mode";
            if (Nightshiftmode.isSelected()) select = "Night shift mode";

            int PeopleNumber = setPeopleSlider.getValue();

            ArrayList<Typist> seatTypist = new ArrayList<>();

            for(int i = 0; i<PlayerDisplay.getModel().getSize();i++){
                seatTypist.add(PlayerDisplay.getDataClass(i));
            }
            seatTypist.sort(Comparator.comparing(Typist::getSymbol));

            if (PeopleNumber < PlayerDisplay.getModel().getSize() || PlayerDisplay.getModel().getSize() < 2){
                JOptionPane.showMessageDialog(null, "The number of participants does not match the preset quota! ("+PlayerDisplay.getModel().getSize()+"/"+PeopleNumber+")", "The number of people is incorrect.",JOptionPane.ERROR_MESSAGE);
            }else{
                TypingRaceGUI race = new TypingRaceGUI(setParagraphSlider.getValue(), select, seatTypist, GameDisplay);
                race.startRace();



            }

            RankingList.ClearData();

            for(int i = 0; i<PlayerDisplay.getModel().getSize();i++){
                RankingList.addData(PlayerDisplay.getDataClass(i));
            }

            RankingList.refreshData(false);

        });


        return Centre;

    }

    //Sliding bar component (for controlling the number of people and distance)
    public static JPanel SliderPanel(){

        JPanel Centre = new JPanel();
        Centre.setLayout(new GridLayout(3,1));
        Centre.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
        //====================================

        JPanel PeopleSliderPanel = new JPanel();
        PeopleSliderPanel.setLayout(new GridLayout(1,2));

        JLabel PeopleSliderLabel = new JLabel("Number of participants:");
        PeopleSliderLabel.setFont(new Font("", Font.PLAIN ,20));

        setPeopleSlider = new JSlider(JSlider.HORIZONTAL, 2, 6, 2);

        setPeopleSlider.setMajorTickSpacing(1);
        setPeopleSlider.setPaintTicks(true);
        setPeopleSlider.setPaintLabels(true);

        PeopleSliderPanel.add(PeopleSliderLabel);
        PeopleSliderPanel.add(setPeopleSlider);

        //==========================================================

        JPanel ParagraphSliderPanel = new JPanel();
        ParagraphSliderPanel.setLayout(new GridLayout(1,2));

        JLabel ParagraphSliderLabel = new JLabel("Paragraph spacing:");
        ParagraphSliderLabel.setFont(new Font("", Font.PLAIN ,20));

        setParagraphSlider = new JSlider(JSlider.HORIZONTAL, 10, 80, 45);

        setParagraphSlider.setMajorTickSpacing(10);
        setParagraphSlider.setMinorTickSpacing(5);
        setParagraphSlider.setPaintTicks(true);
        setParagraphSlider.setPaintLabels(true);
        setParagraphSlider.setSnapToTicks(true);

        ParagraphSliderPanel.add(ParagraphSliderLabel);
        ParagraphSliderPanel.add(setParagraphSlider);


        Centre.add(PeopleSliderPanel);
        Centre.add(ParagraphSliderPanel);
        Centre.add(difficultyChange());


        return Centre;
    }

    //The Player (deletion and editing of participants)
    public static JPanel PlayerPanel(SetSymbolArray SymbolsArrays){

        JPanel Centre = new JPanel();
        Centre.setLayout(new BorderLayout());
        Centre.setBorder(BorderFactory.createEmptyBorder(5, 30, 20, 30));

        JLabel NameLabel = new JLabel("Name  |  Symbol number  |  Accuracy");
        NameLabel.setFont(new Font("", Font.BOLD ,20));
        Centre.add(NameLabel,BorderLayout.NORTH);

        //===================================

        PlayerDisplay = new DisplayListClass();

        Centre.add(PlayerDisplay,BorderLayout.CENTER);

        //=========================
        JPanel PlayerButton = new JPanel();
        PlayerButton.setLayout(new GridLayout(2,1));

        JButton addPlayer = new JButton("ADD Player");
        addPlayer.setFont(new Font("", Font.PLAIN ,20));

        addPlayer.addActionListener(e -> {
            GameDisplay.SetDisplayheight(PlayerDisplay.getModel().getSize());
            if (setPeopleSlider.getValue()>PlayerDisplay.getModel().getSize()) {

                new ADDPLAYER_Win(null,SymbolsArrays,PlayerDisplay);

            }

        });

        PlayerButton.add(addPlayer);



        JButton DetailPlayer = new JButton("Player Detail");
        DetailPlayer.setFont(new Font("", Font.PLAIN ,20));

        //===============================
        DetailPlayer.addActionListener(e -> {

            try {
                int selectedIndex = PlayerDisplay.getSelectedIndex();
                Typist selectedClass = PlayerDisplay.getDataClass(selectedIndex);

                new ADDPLAYER_Win(selectedClass, SymbolsArrays, PlayerDisplay);
            }catch (IndexOutOfBoundsException ex){

            }

        });
        PlayerButton.add(DetailPlayer);

        Centre.add(PlayerButton,BorderLayout.EAST);

        return Centre;

    }

    public static JPanel difficultyChange(){
        JPanel Centre = new JPanel();
        //Centre.setLayout(new GridLayout(1,4));
        Centre.setBorder(BorderFactory.createEmptyBorder(0, 30,0, 30));

        JLabel ModeLabel = new JLabel("Mode:");
        ModeLabel.setFont(new Font("", Font.BOLD ,20));
        Centre.add(ModeLabel);

        Normalmode = new JRadioButton("Normal mode");
        Normalmode.setFont(new Font("", Font.PLAIN ,20));
        Centre.add(Normalmode);

        Coffeemode = new JRadioButton("Coffee mode");
        Coffeemode.setFont(new Font("", Font.PLAIN ,20));
        Centre.add(Coffeemode);

        Nightshiftmode = new JRadioButton("Night shift mode");
        Nightshiftmode.setFont(new Font("", Font.PLAIN ,20));
        Centre.add(Nightshiftmode);

        ButtonGroup modeSelect = new ButtonGroup();
        modeSelect.add(Normalmode);
        modeSelect.add(Coffeemode);
        modeSelect.add(Nightshiftmode);

        Normalmode.setSelected(true);

        return Centre;

    }

}
