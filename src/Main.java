import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ListOfPlants listOfPlants = new ListOfPlants();
        listOfPlants.readPlantsFromFile("kvetiny.txt");

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

        listOfPlants.writePlantsToFile("kvetiny_out.txt");

//opětovné načtení vypsaného souboru
        ListOfPlants listOfPlants2 = new ListOfPlants();
        listOfPlants2.readPlantsFromFile("kvetiny_out.txt");

        List<Plant> plants = new ArrayList<>();
        plants = listOfPlants2.getPlants();

        //kontrolni vypis
        System.out.println("Kontrolní výpis před seřazením");
        for( Plant plant : plants){
            System.out.println(plant.getWateringInfo());
        }

        System.out.println();

        Collections.sort(plants);
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

    }


}
