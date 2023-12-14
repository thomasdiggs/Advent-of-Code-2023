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

    public static void findMapping(ArrayList<Seed> seeds, String[] input, String setMethod, String getMethodSource, String getMethodDestination) {
        for (String line : input) {
            HashMap<Long, Long> map = mapEachLine(line.split(" "));
            for (Seed seed : seeds) {
                if (map.containsKey(seed.callGetMethod(getMethodSource))) {
                    seed.callSetMethod(setMethod, map.get(seed.callGetMethod(getMethodSource)));
                }
            }
        }
        for (Seed seed : seeds) {
            if (seed.callGetMethod(getMethodDestination) == 0) {
                seed.callSetMethod(setMethod, seed.callGetMethod(getMethodSource));
            }
        }
    }

    public static void main(String[] args) {

        String filePath = "./Day 5/sample.txt";
        ArrayList<String> lines = CSVUtility.reader(filePath);

        ArrayList<Seed> seeds = new ArrayList<>();
        ArrayList<String> seedToSoilMapInput = new ArrayList<>();
        ArrayList<String> soilToFertilizerMapInput = new ArrayList<>();
        ArrayList<String> fertilizerToWaterMapInput = new ArrayList<>();
        ArrayList<String> waterToLightMapInput = new ArrayList<>();
        ArrayList<String> lightToTemperatureMapInput = new ArrayList<>();
        ArrayList<String> temperatureToHumidityMapInput = new ArrayList<>();
        ArrayList<String> humidityToLocationMapInput = new ArrayList<>();

        String seedsInput = lines.getFirst().replaceAll("seeds: ", "");
        for (String seed : seedsInput.split(" ")) {
            seeds.add(new Seed(Long.parseLong(seed)));
        }
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

        findMapping(seeds, seedToSoilMapInput.toArray(new String[0]), "setSoil", "getSeed", "getSoil");
        findMapping(seeds, soilToFertilizerMapInput.toArray(new String[0]), "setFertilizer", "getSoil", "getFertilizer");
        findMapping(seeds, fertilizerToWaterMapInput.toArray(new String[0]), "setWater", "getFertilizer", "getWater");
        findMapping(seeds, waterToLightMapInput.toArray(new String[0]), "setLight", "getWater", "getLight");
        findMapping(seeds, lightToTemperatureMapInput.toArray(new String[0]), "setTemperature", "getLight", "getTemperature");
        findMapping(seeds, temperatureToHumidityMapInput.toArray(new String[0]), "setHumidity", "getTemperature", "getHumidity");
        findMapping(seeds, humidityToLocationMapInput.toArray(new String[0]), "setLocation", "getHumidity", "getLocation");

        for (Seed seed : seeds) {
            System.out.println(seed);
        }






//        String seedsInput = "79 14 55 13";
//        ArrayList<Seed> seeds = new ArrayList<>();
//
//        for (String seed : seedsInput.split(" ")) {
//            seeds.add(new Seed(Long.parseLong(seed)));
//        }
//
//        String[] seedToSoilMapInput = {"50 98 2", "52 50 48"};
//        String[] soilToFertilizerMapInput = {"0 15 37", "37 52 2", "39 0 15"};
//        String[] fertilizerToWaterMapInput = {"49 53 8", "0 11 42", "42 0 7", "57 7 4"};
//        String[] waterToLightMapInput = {"88 18 7", "18 25 70"};
//        String[] lightToTemperatureMapInput = {"45 77 23", "81 45 19", "68 64 13"};
//        String[] temperatureToHumidityMapInput = {"0 69 1", "1 0 69"};
//        String[] humidityToLocationMapInput = {"60 56 37", "56 93 4"};
//
//        System.out.println("Seed to Soil Map");
//        for (String line : seedToSoilMapInput) {
//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            System.out.println(map);
//        }
//
//        System.out.println("\nSoil to Fertilizer Map");
//        for (String line : soilToFertilizerMapInput) {
//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            System.out.println(map);
//        }
//
//        System.out.println("\nFertilizer to Water Map");
//        for (String line : fertilizerToWaterMapInput) {
//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            System.out.println(map);
//        }
//
//        System.out.println("\nWater to Light Map");
//        for (String line : waterToLightMapInput) {
//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            System.out.println(map);
//        }
//
//        System.out.println("\nLight to Temperature Map");
//        for (String line : lightToTemperatureMapInput) {
//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            System.out.println(map);
//        }
//
//        System.out.println("\nTemperature to Humidity Map");
//        for (String line : temperatureToHumidityMapInput) {
//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            System.out.println(map);
//        }
//
//        System.out.println("\nHumidity to Location Map");
//        for (String line : humidityToLocationMapInput) {
//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            System.out.println(map);
//        }

        /*
        System.out.println();
        findMapping(seeds, seedToSoilMapInput, "setSoil", "getSeed", "getSoil");
        findMapping(seeds, soilToFertilizerMapInput, "setFertilizer", "getSoil", "getFertilizer");
        findMapping(seeds, fertilizerToWaterMapInput, "setWater", "getFertilizer", "getWater");
        findMapping(seeds, waterToLightMapInput, "setLight", "getWater", "getLight");
        findMapping(seeds, lightToTemperatureMapInput, "setTemperature", "getLight", "getTemperature");
        findMapping(seeds, temperatureToHumidityMapInput, "setHumidity", "getTemperature", "getHumidity");
        findMapping(seeds, humidityToLocationMapInput, "setLocation", "getHumidity", "getLocation");

        for (Seed seed : seeds) {
            System.out.println(seed);
        }
        */

    }

}
