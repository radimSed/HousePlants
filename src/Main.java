public class Main {
    public static void main(String[] args) {
        ListOfPlants listOfPlants = new ListOfPlants();
        listOfPlants.readPlantsFromFile("kvetiny.txt");

        for (Plant plant : listOfPlants.plants){
            System.out.println(plant.getWateringInfo());
        }

    }
}
