public class Animal {

    private final String animalName;
    private final String species;
    private final int age;
    public static int numOfAnimals = 0;

    // Constructor
    public Animal(String name, String species, int age) {
        this.animalName = name;
        this.species = species;
        this.age = age;
        numOfAnimals++;  // Increment number of animals whenever a new animal is created
    }

    // Getters
    public String getAnimalName() {
        return animalName;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    // Method to return animal info as a String
    @Override
    public String toString() {
        return "Name: " + animalName + ", Species: " + species + ", Age: " + age;
    }

    // 🦁 Lion subclass
    public static class Lion extends Animal {
        public Lion(String name, int age) {
            super(name, "Lion", age);
        }

        public void roar() {
            System.out.println(getAnimalName() + " the lion roars loudly! 🦁");
        }
    }

    // 😂 Hyena subclass
    public static class Hyena extends Animal {
        public Hyena(String name, int age) {
            super(name, "Hyena", age);
        }

        public void laugh() {
            System.out.println(getAnimalName() + " the hyena laughs mischievously! 😂");
        }
    }

    // 🐯 Tiger subclass
    public static class Tiger extends Animal {
        public Tiger(String name, int age) {
            super(name, "Tiger", age);
        }

        public void growl() {
            System.out.println(getAnimalName() + " the tiger growls fiercely! 🐯");
        }
    }

    // 🐻 Bear subclass
    public static class Bear extends Animal {
        public Bear(String name, int age) {
            super(name, "Bear", age);
        }

        public void hibernate() {
            System.out.println(getAnimalName() + " the bear goes into hibernation! 💤🐻");
        }
    }
}
