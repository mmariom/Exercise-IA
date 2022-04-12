import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private  String name;
    private String data;
    private List<Integer> dataList = new ArrayList<>();



    public Person(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return this.data;
    }

    // This method will find just banknotes from this.data and add them in to the List
    public  void extractBankNotes(){
        String regex = "(?<number>\\d+)\\b.*?";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(this.data);

        while (matcher.find()){
            this.dataList.add(Integer.parseInt(matcher.group("number")));
        }

    }

    //Find min value
    public int minimumValBankNote(){
        extractBankNotes();
        int minimum = this.dataList.get(0);
        for (int i = 1; i < this.dataList.size(); i++) {
            if (minimum > this.dataList.get(i))
                minimum = this.dataList.get(i);
        }

        return minimum;
    }
    //Find max value
    public int maxValBankNote(){
        extractBankNotes();
        int maximum = this.dataList.get(0);
        for (int i = 1; i < this.dataList.size(); i++) {
            if (maximum < this.dataList.get(i))
                maximum = this.dataList.get(i);
        }

        return maximum;
    }


//    public void showAllCoinsAndNotes(){
//       for (int i = 0; i < this.mojList.size(); i++){
//
//            // Printing and display the elements in ArrayList
//            System.out.print(this.mojList.get(i) + " ");
//        }
//    }

    //It will return min and max banknote in string format
    public String getMinAndMaxInString(){
        String minVal = Integer.toString(minimumValBankNote());
        String maxVal = Integer.toString(maxValBankNote());
        return "minimum valued banknote is "+ minVal + " and max valued is " + maxVal;
    }




}

