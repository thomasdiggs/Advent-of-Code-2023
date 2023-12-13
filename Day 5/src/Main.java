import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static HashMap<Long, Long> mapEachLine(String[] input) {
        HashMap<Long, Long> map = new HashMap<>();
        long destination = Long.parseLong(input[0]);
        long source = Long.parseLong(input[1]);
        long range = Long.parseLong(input[2]);
        for (int i = 0; i < range; i++) {
            map.put(source + i, destination + i);
        }
        return map;
    }

    public static void findMapping(ArrayList<Seed> seeds, String[] input, String setMethod) {
        for (String line : input) {
            HashMap<Long, Long> map = mapEachLine(line.split(" "));
            for (Seed seed : seeds) {
                if (setMethod.equals("setSoil")) seed.setSoil(map.getOrDefault(seed.getSeed(), seed.getSeed()));
                if (setMethod.equals("setFertilizer")) seed.setFertilizer(map.getOrDefault(seed.getSoil(), seed.getSoil()));
                if (setMethod.equals("setWater")) seed.setWater(map.getOrDefault(seed.getFertilizer(), seed.getFertilizer()));
                if (setMethod.equals("setLight")) seed.setLight(map.getOrDefault(seed.getWater(), seed.getWater()));
                if (setMethod.equals("setTemperature")) seed.setTemperature(map.getOrDefault(seed.getLight(), seed.getLight()));
                if (setMethod.equals("setHumidity")) seed.setHumidity(map.getOrDefault(seed.getTemperature(), seed.getTemperature()));
                if (setMethod.equals("setLocation")) seed.setLocation(map.getOrDefault(seed.getHumidity(), seed.getHumidity()));
            }
        }
    }

    public static void main(String[] args) {

        String filePath = "./Day 5/sample.txt";
        //ArrayList<String> lines = CSVUtility.reader(filePath);

        String seedsInput = "79 14 55 13";
        ArrayList<Seed> seeds = new ArrayList<>();

        for (String seed : seedsInput.split(" ")) {
            seeds.add(new Seed(Long.parseLong(seed)));
        }

        String[] seedToSoilMapInput = {"50 98 2", "52 50 48"};
        String[] soilToFertilizerMapInput = {"0 15 37", "37 52 2", "39 0 15"};
        String[] fertilizerToWaterMapInput = {"49 53 8", "0 11 42", "42 0 7", "57 7 4"};
        String[] waterToLightMapInput = {"88 18 7", "18 25 70"};
        String[] lightToTemperatureMapInput = {"45 77 23", "81 45 19", "68 64 13"};
        String[] temperatureToHumidityMapInput = {"0 69 1", "1 0 69"};
        String[] humidityToLocationMapInput = {"60 56 37", "56 93 4"};

        findMapping(seeds, seedToSoilMapInput, "setSoil");
        findMapping(seeds, soilToFertilizerMapInput, "setFertilizer");
        findMapping(seeds, fertilizerToWaterMapInput, "setWater");
        findMapping(seeds, waterToLightMapInput, "setLight");
        findMapping(seeds, lightToTemperatureMapInput, "setTemperature");
        findMapping(seeds, temperatureToHumidityMapInput, "setHumidity");
        findMapping(seeds, humidityToLocationMapInput, "setLocation");

        for (Seed seed : seeds) {
            System.out.println(seed);
        }

    }

}
