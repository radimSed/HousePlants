import java.time.LocalDate;
import java.util.Comparator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileIn, fileOut, fileErr1, fileErr2;

        fileIn = "kvetiny.txt";
        fileOut = "kvetiny_out.txt";
        fileErr1 = "kvetiny-spatne-frekvence.txt";
        fileErr2 = "kvetiny-spatne-datum.txt";

        ListOfPlants listOfPlants = new ListOfPlants();
        try {
            listOfPlants.readPlantsFromFile(fileIn, "\t");
        } catch (PlantException e){
            e.getMessage();
        }
        try {
            listOfPlants.addPlant(new Plant("Mamilaria Plumosa", "Je to kaktus",
                    LocalDate.of(2023, 8, 1), LocalDate.of(2024, 6, 1), 30));
        } catch ( PlantException e){
            System.err.println(e.getMessage());
        }

        try {
            listOfPlants.addPlant(new Plant("Opuncia", "To je taky kaktus",
                    LocalDate.of(2022, 7, 2), LocalDate.of(2024, 5, 2), 28));
        } catch ( PlantException e){
            System.err.println(e.getMessage());
        }

        listOfPlants.removePlant(1);

        try {
            listOfPlants.writePlantsToFile(fileOut, "\t");
        } catch (PlantException e){
            System.err.println(e.getMessage());
        }

//opětovné načtení vypsaného souboru
        ListOfPlants listOfPlants2 = new ListOfPlants();
        try {
        listOfPlants2.readPlantsFromFile(fileOut, "\t");
        } catch (PlantException e){
            System.err.println(e.getMessage());
        }

//        List<Plant> plants = new ArrayList<>();
        List<Plant> plants = listOfPlants2.getPlants();

        //kontrolni vypis
        System.out.println("Kontrolní výpis před seřazením");
        for( Plant plant : plants){
            System.out.println(plant.getWateringInfo());
        }

        System.out.println();

        //Collections.sort(plants);
        listOfPlants2.sort();
        plants = listOfPlants2.getPlants();
        //kontrolni vypis po seřazení podle jména
        System.out.println("Kontrolní výpis po seřazení podle jména");
        for( Plant plant : plants){
            System.out.println(plant.getWateringInfo());
        }

        System.out.println();

        plants.sort(Comparator.comparing(Plant::getWatering));
        //kontrolni vypis po seřazení podle data zálivky
        System.out.println("Kontrolní výpis po seřazení podle data zálivky");
        for( Plant plant : plants){
            System.out.println(plant.getWateringInfo());
        }

        System.out.println();

        ListOfPlants listOfPlantsErr1 = new ListOfPlants();
        try {
        listOfPlantsErr1.readPlantsFromFile(fileErr1, "\t");
        } catch (PlantException e){
            System.err.println(e.getMessage());
        }
        List<Plant> plantsErr1 = listOfPlantsErr1.getPlants();
        //kontrolni vypis po nacteni vadnych frekvenci
        System.out.println("Kontrolní výpis po načtení souboru s vadnou frekvencí");
        for( Plant plant : plantsErr1){
            System.out.println(plant.getWateringInfo());
        }

        System.out.println();

        ListOfPlants listOfPlantsErr2 = new ListOfPlants();
        try {
            listOfPlantsErr2.readPlantsFromFile(fileErr2, "\t");
        } catch (PlantException e){
            System.err.println(e.getMessage());
        }
        List<Plant> plantsErr2 = listOfPlantsErr2.getPlants();

        //kontrolni vypis po nacteni vadnych datumu
        System.out.println("Kontrolní výpis po načtení souboru s vadným datem");
        for( Plant plant : plantsErr2){
            System.out.println(plant.getWateringInfo());
        }

    }


}
