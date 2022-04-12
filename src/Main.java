

//Zadanie:
//        Napiste program ktory nacita vstupny CSV subor, ktory obsahuje meno majitela penazenky a mince/bankovky v nej.
//        (format je: prvy stpec je meno majitela penazenky, ostatne stlpce mince/bankovky ).
//        - program dostane ako parameter meno majitela a vypise najmensiu a najvacsiu hodnotu bankovky v jeho penazenke.
//        Ako mince su povazovane hodnoty konciace sa pismenom "m", zvysok su bankovky.
//        Program je dodavany ako kompilovatelne a spustitelne riesenie s pouzitim OOP principov, zaroven obsahuje unit testy.
//        Akakolvek invencia je vitana :)
//
//
//        Adam;2m;20;1m;20;5;10;500;15;25
//        Fero;1m;8m;2m;30;50;20;100;5m;10
//        Dusan;20;5m;10;2m;1m;50;70;25;12;4m
//        Brano;2222;2m;22;11;111;81;14


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {


        String filePath = "src/mince.csv";
        String data = fileToString(filePath);

        // Get input from user and will use method parseinput
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter any name from a csv file:");
        String tempName = scanner.nextLine();
        String name = parseInput(tempName);

        
        //This will extract name and  data
        String peopleRegex = "(?<name>\\w+)\\;(?<data>.*)";
        Pattern peoplePattern = Pattern.compile(peopleRegex);
        Matcher peopleMatcher = peoplePattern.matcher(data);


        Map<String,String> members = new HashMap<>();


        while(peopleMatcher.find()){
            // This will initialize  new person class with name and data
            Person people = new Person(peopleMatcher.group("name"),peopleMatcher.group("data"));
            //   save name as a key and data as value  in map
            members.put(people.getName(),people.getMinAndMaxInString());
  
        }

        System.out.printf("%s's %s%n" ,name, members.get(name));




    



    }

//  It will  divide input into the first character and rest ,
//  capitalize the first character and lowercase rest,
//  so no matter if user input all lowercase ,uppercase  or mixed.
private   static String parseInput(String input){

    String firstCharacter =  input.substring(0,1);
    String secondCharacter =  input.substring(1);
    return  firstCharacter.toUpperCase()+secondCharacter.toLowerCase();

}


private static String fileToString(String filePath)
    {


        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream
                     = Files.lines(Paths.get(filePath),
                StandardCharsets.UTF_8)) {
            stream.forEach(
                    s -> contentBuilder.append(s).append("\n"));
        }

        catch (IOException e) {

            e.printStackTrace();
        }
        return contentBuilder.toString();
    }



}


