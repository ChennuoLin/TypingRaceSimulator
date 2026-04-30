import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DisplayListClass extends JList<String> {
    private ArrayList<Typist> peopleData;


    public DisplayListClass() {
        PlayerClassArray();
        this.setVisibleRowCount(6);
        this.setBorder(new LineBorder(Color.BLACK, 2));
        this.setFont(new Font("", Font.PLAIN, 20));
    }

    public void refreshData(boolean DorR) {

        setListData(setupData(DorR));

    }

    public void PlayerClassArray(){

        peopleData = new ArrayList<>();

    }

    public void addData(Typist peopleclass){

        peopleData.add(peopleclass);

    }

    public Typist getDataClass(int indexs){
        return peopleData.get(indexs);
    }


    public String[] setupData(boolean DorR){

        peopleData.sort((p1, p2) -> Integer.compare(p2.sumScoreR(), p1.sumScoreR()));

        String[] returnName = new String[peopleData.size()];

        for (int i = 0;i<peopleData.size();i++){

            String getfName = peopleData.get(i).getName();
            if (getfName.length()>12)getfName = getfName.substring(0,12)+"...";
            char getfSymbol = peopleData.get(i).getSymbol();
            double getfAccuracy = peopleData.get(i).getAccuracy();

            if (DorR) {
                returnName[i] = getfName + "  |  " + getfSymbol + "  |  " + getfAccuracy * 100 + "%";
            }else {
                returnName[i] = "No." + (i+1)+ " " + getfName + " | " + getfSymbol + " | " + peopleData.get(i).sumScoreR() +" Score";
            }

        }

        return returnName;
        //PlayerDisplay.setListData(returnName);
    }


    public void removeData(Typist TyClass){
        peopleData.remove(TyClass);
    }
    public void ClearData(){
        peopleData.clear();
    }
}