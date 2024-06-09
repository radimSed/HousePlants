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

    public void readPlantsFromFile(String path, String delimiter) throws PlantException{
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            Scanner scanner = new Scanner(bufferedReader);
            int lineNumber = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                lineNumber++;

                String[] parts = line.split(delimiter);

                String name = parts[0];
                String description = parts[1];

                LocalDate planted = null; //LocalDate.now();
                try {
                    planted = LocalDate.parse(parts[4]);
                } catch (DateTimeParseException e){
                    //System.err.println("Invalid Date format: );
                    throw new PlantException("Invalid date format " + parts[4] + " on line " + lineNumber + " of file \"" + path + "\".");
                }

                LocalDate watering = null; //LocalDate.now();
                try{
                    watering = LocalDate.parse(parts[3]);
                } catch (DateTimeParseException e){
                    throw new PlantException("Invalid date format " + parts[3] + " on line " + lineNumber + " of file \"" + path + "\".");
                }
                int frequency = 0;
                try {
                    frequency = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e){
                    //System.err.println("Invalid frequency of watering: " + parts[2]);
                    throw new PlantException("Invalid frequency of watering: " + parts[2] + " on line " + lineNumber + " of file \"" + path + "\".");
                }

//                try {
                    addPlant(new Plant(name, description, planted, watering, frequency));
//                } catch (PlantException e){
//                    System.err.println(e.getMessage());
//                }
            }

        } catch ( IOException  e) {
//            e.printStackTrace();
            throw new PlantException("An issue with file " + path + "occured:-(");
        }
    }

    public void writePlantsToFile(String path, String delimiter) throws PlantException{
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))){
            for (Plant plant : plants){
                String line = plant.getName() + delimiter + plant.getNotes() + delimiter + plant.getFrequencyOfWatering() +
                        delimiter + plant.getWatering() + delimiter + plant.getPlanted();
//                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
//            e.printStackTrace();
            throw new PlantException("An issue with file " + path + "occured:-(");
        }
    }

    public void sort(){
        Collections.sort(this.plants);
    }
}
