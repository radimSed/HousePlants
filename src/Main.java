import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ListOfPlants listOfPlants = new ListOfPlants();
        listOfPlants.readPlantsFromFile("kvetiny.txt");
    }
}