import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

public class Plant implements Comparable<Plant> {
    String name;
    String notes;
    LocalDate planted;
    LocalDate watering;
    int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException{
        if (frequencyOfWatering < 1) {
            throw new PlantException("Invalid value for frequency of watering. Plant \"" + name + "\" not created!");
        }

        if (watering == null) {
            throw new PlantException("Invalid value for watering date. Plant \"" + name + "\" not created!");
        }

        if (planted == null) {
            throw new PlantException("Invalid value for planted date. Plant \"" + name + "\" not created!");
        }

        if (watering.isBefore(planted)){
            throw new PlantException("Invalid value for watering date: " + watering + " is before planting. Plant \"" + name + "\" not created!");
        }


        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException{
        this(name, "", planted, LocalDate.now(),frequencyOfWatering );

    }

    public Plant(String name) throws PlantException{
        this(name, "", LocalDate.now(), LocalDate.now(),7 );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWateringInfo(){
        LocalDate dateOfNextWatering = this.watering.plusDays(frequencyOfWatering);
        return this.name + ", " + this.watering + ", " + dateOfNextWatering;
    }

    @Override
    public int compareTo(Plant otherPlant) {
        return this.getName().compareTo(otherPlant.getName());
    }
}
