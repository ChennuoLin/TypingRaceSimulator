import java.util.ArrayList;

public class SetSymbolArray {

    public ArrayList<String> SymbolArray;

    public SetSymbolArray(){
        SymbolArray = new ArrayList<>();
        SymbolArray.add("①");
        SymbolArray.add("②");
        SymbolArray.add("③");
        SymbolArray.add("④");
        SymbolArray.add("⑤");
        SymbolArray.add("⑥");
    }

    public void addData(String Symbols){SymbolArray.add(Symbols);}
    public void removeData(String Symbols){
        SymbolArray.remove(Symbols);
    }

    public String[][] getData(){
        String[][] Symbols = new String[SymbolArray.size()][3];
        for (int i = 0;i<SymbolArray.size();i++){
            Symbols[i][0] = SymbolArray.get(i);
            Symbols[i][1] = "";
            Symbols[i][2] = "";
        }

        return Symbols;
    }

}

