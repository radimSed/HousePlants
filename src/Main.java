public class Main {
    public static void main(String[] args) {
        ListOfPlants listOfPlants = new ListOfPlants();
        listOfPlants.readPlantsFromFile("kvetiny.txt");
        listOfPlants.writePlantsToFile("kvetiny_out.txt");

//        for (Plant plant : listOfPlants.plants){
//            System.out.println(plant.getWateringInfo());
//        }

    }
}
