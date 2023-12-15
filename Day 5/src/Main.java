import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static long findMapping(long inputSource, String[] input) {
        for (String line : input) {
            String[] parts = line.split(" ");
            long destination = Long.parseLong(parts[0]);
            long source = Long.parseLong(parts[1]);
            long range = Long.parseLong(parts[2]);
            if (inputSource >= source && inputSource < source + range) {
                return destination + (inputSource - source);
            }
        }
        return inputSource;
    }

    public static void main(String[] args) {

        long msStartMain = System.currentTimeMillis();
        long nsStartMain = System.nanoTime();

        String filePath = "./Day 5/sample.txt";
        ArrayList<String> lines = CSVUtility.reader(filePath);

        ArrayList<String> seedsInput = new ArrayList<>(Arrays.asList(lines.getFirst().replaceAll("seeds: ", "").split(" ")));
        ArrayList<String> seedToSoilMapInput = new ArrayList<>();
        ArrayList<String> soilToFertilizerMapInput = new ArrayList<>();
        ArrayList<String> fertilizerToWaterMapInput = new ArrayList<>();
        ArrayList<String> waterToLightMapInput = new ArrayList<>();
        ArrayList<String> lightToTemperatureMapInput = new ArrayList<>();
        ArrayList<String> temperatureToHumidityMapInput = new ArrayList<>();
        ArrayList<String> humidityToLocationMapInput = new ArrayList<>();

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

        String[] seedToSoilMap = seedToSoilMapInput.toArray(new String[0]);
        String[] soilToFertilizerMap = soilToFertilizerMapInput.toArray(new String[0]);
        String[] fertilizerToWaterMap = fertilizerToWaterMapInput.toArray(new String[0]);
        String[] waterToLightMap = waterToLightMapInput.toArray(new String[0]);
        String[] lightToTemperatureMap = lightToTemperatureMapInput.toArray(new String[0]);
        String[] temperatureToHumidityMap = temperatureToHumidityMapInput.toArray(new String[0]);
        String[] humidityToLocationMap = humidityToLocationMapInput.toArray(new String[0]);


        System.out.println("\n--Part One");
        long msStartPartOne = System.currentTimeMillis();
        long nsStartPartOne = System.nanoTime();
        long seedIDPartOne = 0;
        long minLocationPartOne = Long.MAX_VALUE;
        for (String seed : seedsInput) {
            long currentSeed = Long.parseLong(seed);
            long soil = findMapping(currentSeed, seedToSoilMap);
            long fertilizer = findMapping(soil, soilToFertilizerMap);
            long water = findMapping(fertilizer, fertilizerToWaterMap);
            long light = findMapping(water, waterToLightMap);
            long temperature = findMapping(light, lightToTemperatureMap);
            long humidity = findMapping(temperature, temperatureToHumidityMap);
            long location = findMapping(humidity, humidityToLocationMap);
            if (location < minLocationPartOne) {
                minLocationPartOne = location;
                seedIDPartOne = Long.parseLong(seed);
            }
        }
        long msEndPartOne = System.currentTimeMillis();
        long nsEndPartOne = System.nanoTime();
        System.out.println("Seed ID: " + seedIDPartOne + " has the lowest location: " + minLocationPartOne);


        System.out.println("\n--Part Two");
        long msStartPartTwo = System.currentTimeMillis();
        long nsStartPartTwo = System.nanoTime();
        long seedIDPartTwo = 0;
        long minLocationPartTwo = Long.MAX_VALUE;

        for (int i = 0; i < seedsInput.size(); i += 2) {
            for (long j = 0; j < Long.parseLong(seedsInput.get(i + 1)); j++) {
                long currentSeed = Long.parseLong(seedsInput.get(i)) + j;
                long soil = findMapping(currentSeed, seedToSoilMap);
                long fertilizer = findMapping(soil, soilToFertilizerMap);
                long water = findMapping(fertilizer, fertilizerToWaterMap);
                long light = findMapping(water, waterToLightMap);
                long temperature = findMapping(light, lightToTemperatureMap);
                long humidity = findMapping(temperature, temperatureToHumidityMap);
                long location = findMapping(humidity, humidityToLocationMap);
                if (location < minLocationPartTwo) {
                    minLocationPartTwo = location;
                    seedIDPartTwo = currentSeed;
                }
            }
        }
        long msEndPartTwo = System.currentTimeMillis();
        long nsEndPartTwo = System.nanoTime();
        System.out.println("Seed ID: " + seedIDPartTwo + " has the lowest location: " + minLocationPartTwo);


        long msEndMain = System.currentTimeMillis();
        long nsEndMain = System.nanoTime();
        System.out.println("\n\nPart One execution time: " + (msEndPartOne - msStartPartOne) + "ms" + ", " + (nsEndPartOne - nsStartPartOne) + "ns");
        System.out.println("Part Two execution time: " + (msEndPartTwo - msStartPartTwo) + "ms" + ", " + (nsEndPartTwo - nsStartPartTwo) + "ns");
        System.out.println("Program execution time: " + (msEndMain - msStartMain) + "ms" + ", " + (nsEndMain - nsStartMain) + "ns");
    }
}
