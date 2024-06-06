import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Collections;


public class ListOfPlants {
    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant plant){
        if (plant != null) {
            plants.add(plant);
        }
    }

    public Plant getPlant(int index){
        return plants.get(index);
    }

    public void removePlant(int index){
        plants.remove(index);
    }

    public List<Plant> getPlants(){
//        List<Plant> returnPlants =
//        returnPlants.addAll(this.plants);
        return new ArrayList<>(this.plants);
    }

    public void readPlantsFromFile(String path) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            Scanner scanner = new Scanner(bufferedReader);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split("\t");

                String name = parts[0];
                String description = parts[1];

                LocalDate planted = null; //LocalDate.now();
                try {
                    planted = LocalDate.parse(parts[4]);
                } catch (DateTimeParseException e){
                    System.err.println("Invalid Date format: " + parts[4]);
                }

                LocalDate watering = null; //LocalDate.now();
                try{
                    watering = LocalDate.parse(parts[3]);
                } catch (DateTimeParseException e){
                    System.err.println("Invalid Date format " + parts[3]);
                }
                int frequency = 0;
                try {
                    frequency = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e){
                    System.err.println("Invalid frequency of watering: " + parts[2]);
                }

                try {
                    addPlant(new Plant(name, description, planted, watering, frequency));
                } catch (PlantException e){
                    System.err.println(e.getMessage());
                }
            }

        } catch ( IOException  e) {
            e.printStackTrace();
        }
    }

    public void writePlantsToFile(String path){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (Plant plant : plants){
                String line = plant.getName() + "\t" + plant.getNotes() + "\t" + plant.getFrequencyOfWatering() +
                        "\t" + plant.getWatering() + "\t" + plant.getPlanted();
//                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
