import java.util.ArrayList;
import java.util.Arrays;

public class Main {

//    public static void findMapping(ArrayList<Seed> seeds, String[] input, String setMethod, String getMethodSource, String getMethodDestination) {
//        for (String line : input) {
//            long destination = Long.parseLong(line.split(" ")[0]);
//            long source = Long.parseLong(line.split(" ")[1]);
//            long range = Long.parseLong(line.split(" ")[2]);
//            for (Seed seed : seeds) {
//                if (seed.callGetMethod(getMethodSource) >= source && seed.callGetMethod(getMethodSource) < source + range) {
//                    seed.callSetMethod(setMethod, destination + (seed.callGetMethod(getMethodSource) - source));
//                }
//            }
//        }
//        for (Seed seed : seeds) {
//            if (seed.callGetMethod(getMethodDestination) == 0) {
//                seed.callSetMethod(setMethod, seed.callGetMethod(getMethodSource));
//            }
//        }
//    }

    //// TESTING
    public static long findMapping(long inputSource, String[] input) {
        long outputDestination = inputSource;
        for (String line : input) {
            long destination = Long.parseLong(line.split(" ")[0]);
            long source = Long.parseLong(line.split(" ")[1]);
            long range = Long.parseLong(line.split(" ")[2]);
            if (inputSource >= source && inputSource < source + range) {
                outputDestination = destination + (inputSource - source);
            }
        }
        return outputDestination;
    }
    //// TESTING

    public static void main(String[] args) {

        String filePath = "./Day 5/sample.txt";
        ArrayList<String> lines = CSVUtility.reader(filePath);

//        ArrayList<Seed> seeds = new ArrayList<>();
//        ArrayList<String> seeds = new ArrayList<>();
        ArrayList<String> seeds = new ArrayList<>(Arrays.asList(lines.getFirst().replaceAll("seeds: ", "").split(" ")));
        ArrayList<String> seedToSoilMapInput = new ArrayList<>();
        ArrayList<String> soilToFertilizerMapInput = new ArrayList<>();
        ArrayList<String> fertilizerToWaterMapInput = new ArrayList<>();
        ArrayList<String> waterToLightMapInput = new ArrayList<>();
        ArrayList<String> lightToTemperatureMapInput = new ArrayList<>();
        ArrayList<String> temperatureToHumidityMapInput = new ArrayList<>();
        ArrayList<String> humidityToLocationMapInput = new ArrayList<>();

//        String seedsInput = lines.getFirst().replaceAll("seeds: ", "");
//        for (String seed : seedsInput.split(" ")) {
//            seeds.add(new Seed(Long.parseLong(seed), 0));
//        }
        for (int i = 2; i < lines.size(); i++) {
            if (lines.get(i).startsWith("seed-to-soil map:")) {
                i++;
                while (!lines.get(i).startsWith("soil-to-fertilizer map:") && !lines.get(i).isEmpty()) {
                    seedToSoilMapInput.add(lines.get(i));
                    i++;
                }
            }
            if (lines.get(i).startsWith("soil-to-fertilizer map:")) {
                i++;
                while (!lines.get(i).startsWith("fertilizer-to-water map:") && !lines.get(i).isEmpty()) {
                    soilToFertilizerMapInput.add(lines.get(i));
                    i++;
                }
            }
            if (lines.get(i).startsWith("fertilizer-to-water map:")) {
                i++;
                while (!lines.get(i).startsWith("water-to-light map:") && !lines.get(i).isEmpty()) {
                    fertilizerToWaterMapInput.add(lines.get(i));
                    i++;
                }
            }
            if (lines.get(i).startsWith("water-to-light map:")) {
                i++;
                while (!lines.get(i).startsWith("light-to-temperature map:") && !lines.get(i).isEmpty()) {
                    waterToLightMapInput.add(lines.get(i));
                    i++;
                }
            }
            if (lines.get(i).startsWith("light-to-temperature map:")) {
                i++;
                while (!lines.get(i).startsWith("temperature-to-humidity map:") && !lines.get(i).isEmpty()) {
                    lightToTemperatureMapInput.add(lines.get(i));
                    i++;
                }
            }
            if (lines.get(i).startsWith("temperature-to-humidity map:")) {
                i++;
                while (!lines.get(i).startsWith("humidity-to-location map:") && !lines.get(i).isEmpty()) {
                    temperatureToHumidityMapInput.add(lines.get(i));
                    i++;
                }
            }
            if (lines.get(i).startsWith("humidity-to-location map:") && !lines.get(i).isEmpty()) {
                i++;
                while (i < lines.size()) {
                    humidityToLocationMapInput.add(lines.get(i));
                    i++;
                }
            }
        }

        //// TESTING
        long seedID = 0;
        long minimum = Long.MAX_VALUE;
        for (String seed : seeds) {
            long soil = findMapping(Long.parseLong(seed), seedToSoilMapInput.toArray(new String[0]));
            long fertilizer = findMapping(soil, soilToFertilizerMapInput.toArray(new String[0]));
            long water = findMapping(fertilizer, fertilizerToWaterMapInput.toArray(new String[0]));
            long light = findMapping(water, waterToLightMapInput.toArray(new String[0]));
            long temperature = findMapping(light, lightToTemperatureMapInput.toArray(new String[0]));
            long humidity = findMapping(temperature, temperatureToHumidityMapInput.toArray(new String[0]));
            long location = findMapping(humidity, humidityToLocationMapInput.toArray(new String[0]));
            if (location < minimum) {
                minimum = location;
                seedID = Long.parseLong(seed);
            }
//            System.out.println("Seed: " + seed +
//                    " Soil: " + soil +
//                    " Fertilizer: " + fertilizer +
//                    " Water: " + water +
//                    " Light: " + light +
//                    " Temperature: " + temperature +
//                    " Humidity: " + humidity +
//                    " Location: " + location);
        }
        System.out.println("\nPart One");
        System.out.println("Seed ID: " + seedID + " has the lowest location: " + minimum);
        //// TESTING

//        findMapping(seeds, seedToSoilMapInput.toArray(new String[0]), "setSoil", "getSeed", "getSoil");
//        findMapping(seeds, soilToFertilizerMapInput.toArray(new String[0]), "setFertilizer", "getSoil", "getFertilizer");
//        findMapping(seeds, fertilizerToWaterMapInput.toArray(new String[0]), "setWater", "getFertilizer", "getWater");
//        findMapping(seeds, waterToLightMapInput.toArray(new String[0]), "setLight", "getWater", "getLight");
//        findMapping(seeds, lightToTemperatureMapInput.toArray(new String[0]), "setTemperature", "getLight", "getTemperature");
//        findMapping(seeds, temperatureToHumidityMapInput.toArray(new String[0]), "setHumidity", "getTemperature", "getHumidity");
//        findMapping(seeds, humidityToLocationMapInput.toArray(new String[0]), "setLocation", "getHumidity", "getLocation");
//
//        for (Seed seed : seeds) {
//            System.out.println(seed);
//        }
//
//        long seedID = 0;
//        long minimum = Long.MAX_VALUE;
//        for (Seed seed : seeds) {
//            if (seed.getLocation() < minimum) {
//                minimum = seed.getLocation();
//                seedID = seed.getSeed();
//            }
//        }
//
//        System.out.println("Seed ID: " + seedID + " has the lowest location: " + minimum);

        // TESTING
        String[] seedsRanges = lines.getFirst().replaceAll("seeds: ", "").split(" ");
        long seedIDWithLowestLocation = 0;
        long lowestLocation = Long.MAX_VALUE;
        for (int i = 0; i < seedsRanges.length; i += 2) {
            for (long j = 0; j < Long.parseLong(seedsRanges[i + 1]); j++) {
                long currentSeed = Long.parseLong(seedsRanges[i]) + j;
                long soil = findMapping(currentSeed, seedToSoilMapInput.toArray(new String[0]));
                long fertilizer = findMapping(soil, soilToFertilizerMapInput.toArray(new String[0]));
                long water = findMapping(fertilizer, fertilizerToWaterMapInput.toArray(new String[0]));
                long light = findMapping(water, waterToLightMapInput.toArray(new String[0]));
                long temperature = findMapping(light, lightToTemperatureMapInput.toArray(new String[0]));
                long humidity = findMapping(temperature, temperatureToHumidityMapInput.toArray(new String[0]));
                long location = findMapping(humidity, humidityToLocationMapInput.toArray(new String[0]));
                if (location < lowestLocation) {
                    lowestLocation = location;
                    seedIDWithLowestLocation = currentSeed;
                }
            }
        }
        System.out.println("\nPart Two");
        System.out.println("Seed ID: " + seedIDWithLowestLocation + " has the lowest location: " + lowestLocation);
        // TESTING

    }
}
