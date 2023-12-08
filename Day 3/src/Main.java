import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Code to show a list of files.
Helps to resolve relative file path issues.
    File file = new File(".");
    for(String fileNames : file.list()) System.out.println(fileNames);
*/

public class Main {

    public static List<String> reader(String filePath) {
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return lines;
    }

    public static void main(String[] args) {

        String filePath = "./Day 3/input.txt";

        List<String> lines = reader(filePath);

        char[] symbols = {'*', '/', '@', '&', '+', '=', '#', '%', '-', '$'};

        int sum = 0;

        // For each line in lines
        for (int i = 0; i < lines.size(); i++) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(lines.get(i));
            // Find each number and its start and end indices
            while (matcher.find()) {
                String number = matcher.group();
                int start = matcher.start();
                int end = matcher.end();

                char[] thisRow = lines.get(i).toCharArray();

                boolean containsSymbol = false;

                // CHECK CURRENT ROW
                // Check left of number
                if (start != 0) {
                    for (char symbol : symbols) {
                        if (thisRow[start - 1] == symbol) {
                            containsSymbol = true;
                        }
                    }
                }
                // Check right of number
                if (end != thisRow.length) {
                    for (char symbol : symbols) {
                        if (thisRow[end] == symbol) {
                            containsSymbol = true;
                        }
                    }
                }

                // CHECK THE ROW ABOVE
                // If row exists above current row
                if (i != 0) {
                    char[] rowAbove = lines.get(i - 1).toCharArray();
                    // Check if number is the middle
                        // If start != 0 && end != lines.get(i - 1).length()
                            // Check for symbol from start - 1 to end
                    if (start != 0 && end != rowAbove.length) {
                        int checkStart = start - 1;
                        int checkEnd = end;
                        for (int j = checkStart; j <= checkEnd; j++) {
                            for (char symbol : symbols) {
                                if (rowAbove[j] == symbol) {
                                    containsSymbol = true;
                                }
                            }
                        }
                        // Check if number is at the left
                            // Else if start == 0 and end != lines.get(i - 1).length()
                                // Check for symbol from start to end
                    } else if (start == 0 && end != rowAbove.length) {
                        int checkStart = start;
                        int checkEnd = end;
                        for (int j = checkStart; j <= checkEnd; j++) {
                            for (char symbol : symbols) {
                                if (rowAbove[j] == symbol) {
                                    containsSymbol = true;
                                }
                            }
                        }
                        // Check if number is at the right
                            // Else if start != 0 and end == lines.get(i -1).length()
                                // Check for symbol from start to end - 1
                    } else if (start != 0 && end == rowAbove.length) {
                        int checkStart = start - 1;
                        int checkEnd = end - 1;
                        for (int j = checkStart; j <= checkEnd; j++) {
                            for (char symbol : symbols) {
                                if (rowAbove[j] == symbol) {
                                    containsSymbol = true;
                                }
                            }
                        }
                    }
                }

                // CHECK THE ROW BELOW
                // If row exists below current row
                if (i != (lines.size() - 1)) {
                    char[] rowBelow = lines.get(i + 1).toCharArray();
                    // Check if number is the middle
                    // If start != 0 && end != lines.get(i - 1).length()
                    // Check for symbol from start - 1 to end
                    if (start != 0 && end != rowBelow.length) {
                        int checkStart = start - 1;
                        int checkEnd = end;
                        for (int j = checkStart; j <= checkEnd; j++) {
                            for (char symbol : symbols) {
                                if (rowBelow[j] == symbol) {
                                    containsSymbol = true;
                                }
                            }
                        }
                        // Check if number is at the left
                        // Else if start == 0 and end != lines.get(i - 1).length()
                        // Check for symbol from start to end
                    } else if (start == 0 && end != rowBelow.length) {
                        int checkStart = start;
                        int checkEnd = end;
                        for (int j = checkStart; j <= checkEnd; j++) {
                            for (char symbol : symbols) {
                                if (rowBelow[j] == symbol) {
                                    containsSymbol = true;
                                }
                            }
                        }
                        // Check if number is at the right
                        // Else if start != 0 and end == lines.get(i -1).length()
                        // Check for symbol from start to end - 1
                    } else if (start != 0 && end == rowBelow.length) {
                        int checkStart = start - 1;
                        int checkEnd = end - 1;
                        for (int j = checkStart; j <= checkEnd; j++) {
                            for (char symbol : symbols) {
                                if (rowBelow[j] == symbol) {
                                    containsSymbol = true;
                                }
                            }
                        }
                    }
                }

                // If no symbols are found in the checks above, add number to sum.
                if (containsSymbol) {
                    sum += Integer.parseInt(number);
                }

            }
        }

        System.out.println("\nSum: " + sum);

    }

}