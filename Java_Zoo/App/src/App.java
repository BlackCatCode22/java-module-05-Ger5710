import java.io.*;
import java.util.*;
import java.util.logging.*;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        Map<String, List<Animal>> speciesMap = new HashMap<>();
        Map<String, Integer> speciesCount = new HashMap<>();

        // File paths
        File arrivingFile = new File("src/arrivingAnimals.txt");
        File animalNamesFile = new File("src/animalNames.txt");

        System.out.println("Looking for: " + arrivingFile.getAbsolutePath());
        System.out.println("Looking for: " + animalNamesFile.getAbsolutePath());

        if (!arrivingFile.exists() || !animalNamesFile.exists()) {
            System.err.println("ERROR: One or both files are missing!");
            return;
        } else {
            System.out.println("Both files found successfully!");
        }

        try (BufferedReader animalReader = new BufferedReader(new FileReader(arrivingFile));
             BufferedReader nameReader = new BufferedReader(new FileReader(animalNamesFile))) {

            String animalLine, nameLine;
            while ((animalLine = animalReader.readLine()) != null &&
                    (nameLine = nameReader.readLine()) != null) {
                String[] animalData = animalLine.split(",");
                String name = animalData[0].trim();
                int age = Integer.parseInt(animalData[1].trim());
                String species = nameLine.trim();

                // Create the correct subclass using enhanced switch
                Animal animal = switch (species.toLowerCase()) {
                    case "lion" -> new Animal.Lion(name, age);
                    case "tiger" -> new Animal.Tiger(name, age);
                    case "bear" -> new Animal.Bear(name, age);
                    case "hyena" -> new Animal.Hyena(name, age);
                    default -> new Animal(name, species, age);
                };

                // Add to the animal list
                animals.add(animal);

                // Organize by species
                speciesMap.putIfAbsent(species, new ArrayList<>());
                speciesMap.get(species).add(animal);

                // Count species
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading input files: " + e.getMessage(), e);
            return;
        }

        // Print and write the report with behaviors
        generateReport(animals, speciesMap, speciesCount);
    }

    private static void generateReport(List<Animal> animals, Map<String, List<Animal>> speciesMap, Map<String, Integer> speciesCount) {
        String outputFilePath = "newAnimals.txt";
        System.out.println("Writing report to: " + outputFilePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Animal Report\n");
            writer.write("=============\n\n");

            // Print and write all animals with behavior
            System.out.println("Animal Report\n=============");
            for (Animal animal : animals) {
                String behavior = getAnimalBehavior(animal);
                String animalDetails = "Name: " + animal.getAnimalName() + ", Species: " + animal.getSpecies() +
                        ", Age: " + animal.getAge() + ", Behavior: " + behavior;

                System.out.println(animalDetails);
                writer.write(animalDetails + "\n");
            }

            // Print and write species breakdown
            for (Map.Entry<String, List<Animal>> entry : speciesMap.entrySet()) {
                String species = entry.getKey();
                List<Animal> speciesAnimals = entry.getValue();

                String speciesDetails = "\nSpecies: " + species + "\nTotal Count: " + speciesCount.get(species) + "\nAnimals:";
                System.out.println(speciesDetails);
                writer.write(speciesDetails + "\n");

                for (Animal animal : speciesAnimals) {
                    String speciesAnimalDetails = " - " + animal.getAnimalName() + ", Age: " + animal.getAge();
                    System.out.println(speciesAnimalDetails);
                    writer.write(speciesAnimalDetails + "\n");
                }
                System.out.println();
                writer.write("\n");
            }

            writer.write("End of Report\n");
            System.out.println("End of Report\n");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing the report: " + e.getMessage(), e);
        }
    }

    private static String getAnimalBehavior(Animal animal) {
        if (animal instanceof Animal.Lion lion) {
            lion.roar();
            return "Roars loudly! 🦁";
        }
        if (animal instanceof Animal.Tiger tiger) {
            tiger.growl();
            return "Growls fiercely! 🐯";
        }
        if (animal instanceof Animal.Bear bear) {
            bear.hibernate();
            return "Goes into hibernation! 🐻💤";
        }
        if (animal instanceof Animal.Hyena hyena) {
            hyena.laugh();
            return "Laughs mischievously! 😂";
        }
        return "No special behavior";
    }
}





