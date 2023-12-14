import java.util.ArrayList;
import java.util.HashMap;

public class Main {

//    public static HashMap<Long, Long> mapEachLine(String[] input) {
//        HashMap<Long, Long> map = new HashMap<>();
//        long destination = Long.parseLong(input[0]);
//        long source = Long.parseLong(input[1]);
//        long range = Long.parseLong(input[2]);
//        for (int i = 0; i < range; i++) {
//            map.put(source + i, destination + i);
//        }
//        return map;
//    }

    public static void findMapping(ArrayList<Seed> seeds, String[] input, String setMethod, String getMethodSource, String getMethodDestination) {
        for (String line : input) {

            //// TESTING
//            HashMap<Long, Long> map = new HashMap<>();
            long destination = Long.parseLong(line.split(" ")[0]);
            long source = Long.parseLong(line.split(" ")[1]);
            long range = Long.parseLong(line.split(" ")[2]);
            for (int i = 0; i < range; i++) {
//                map.put(source + i, destination + i);
                for (Seed seed : seeds) {
                    boolean isSet = false;
                    if (seed.callGetMethod(getMethodSource) == source + i && !isSet) {
                        seed.callSetMethod(setMethod, destination + i);
                        isSet = true;
                    }
                }
            }
            ////TESTING

//            HashMap<Long, Long> map = mapEachLine(line.split(" "));
//            for (Seed seed : seeds) {
//                if (map.containsKey(seed.callGetMethod(getMethodSource))) {
//                    seed.callSetMethod(setMethod, map.get(seed.callGetMethod(getMethodSource)));
//                }
//            }

        }
        for (Seed seed : seeds) {
            if (seed.callGetMethod(getMethodDestination) == 0) {
                seed.callSetMethod(setMethod, seed.callGetMethod(getMethodSource));
            }
        }
    }

    public static void main(String[] args) {

        String filePath = "./Day 5/input.txt";
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

        System.out.println("running seed-to-soil map");
        findMapping(seeds, seedToSoilMapInput.toArray(new String[0]), "setSoil", "getSeed", "getSoil");
        System.out.println("running soil-to-fertilizer map");
        findMapping(seeds, soilToFertilizerMapInput.toArray(new String[0]), "setFertilizer", "getSoil", "getFertilizer");
        System.out.println("running fertilizer-to-water map");
        findMapping(seeds, fertilizerToWaterMapInput.toArray(new String[0]), "setWater", "getFertilizer", "getWater");
        System.out.println("running water-to-light map");
        findMapping(seeds, waterToLightMapInput.toArray(new String[0]), "setLight", "getWater", "getLight");
        System.out.println("running light-to-temperature map");
        findMapping(seeds, lightToTemperatureMapInput.toArray(new String[0]), "setTemperature", "getLight", "getTemperature");
        System.out.println("running temperature-to-humidity map");
        findMapping(seeds, temperatureToHumidityMapInput.toArray(new String[0]), "setHumidity", "getTemperature", "getHumidity");
        System.out.println("running humidity-to-location map");
        findMapping(seeds, humidityToLocationMapInput.toArray(new String[0]), "setLocation", "getHumidity", "getLocation");

        for (Seed seed : seeds) {
            System.out.println(seed);
        }

    }

}
