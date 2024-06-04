import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfPlants {
    List<Plant> listOfPlants = new ArrayList<>();

    public void addPlant(Plant plant){
        if (plant != null) {
            listOfPlants.add(plant);
        }
    }

    public Plant getPlant(int index){
        return listOfPlants.get(index);
    }

    public void removePlant(int index){
        listOfPlants.remove(index);
    }

    public void readPlantsFromFile(String path) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            Scanner scanner = new Scanner(bufferedReader);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }

        } catch ( IOException  e) {
            e.printStackTrace();
        }


    }

}
