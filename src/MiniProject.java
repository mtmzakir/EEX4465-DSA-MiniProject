import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MiniProject {
    public static void main(String[] args) {
        ArrayList < String > wordsList = new ArrayList < String > (); //to Store Unique Words in List
        ArrayList < Integer > hashList = new ArrayList < Integer > (); //to Store Corresponding Hash Keys in List
        ArrayList < Integer > newHashList = new ArrayList < Integer > ();
        ArrayList < Integer > probeList = new ArrayList < Integer > ();

        System.out.println("(\uD83D\uDCAFwordsHK7, \uD83D\uDCAFwordsQHK7) Files Created Successfully...");
        System.out.println("Please Check Your MiniProject Folder...\uD83D\uDE09");
        System.out.println("Please Check Your MiniProject Folder...\uD83D\uDE09");

        // Inserting file7.txt (Because My Reg.No Starts with 7 ~ 721420419)
        try (BufferedReader buffer = new BufferedReader(
                new FileReader("C:\\Users\\Zakir\\Desktop\\EEX4465 MP\\file7.txt"))) {
            String str;

            while ((str = buffer.readLine()) != null) {
                String[] words = str.replaceAll("[^a-zA-Z ]", "").split(" ");

                for (String word: words) {

                    int hash = 0;
                    for (int i = 0; i < word.length(); i++) {
                        char c = word.charAt(i);
                        if (c >= 'a' && c <= 'z') {
                            hash += (int) c - 97;
                        } else if (c >= 'A' && c <= 'Z') {
                            hash += (int) c - 39;
                        }
                    }

                    int index = wordsList.indexOf(word);
                    if (index == -1) {
                        wordsList.add(word);
                        hashList.add(hash);
                    } else {
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        wordsHKFileWrite("C:\\Users\\Zakir\\Desktop\\EEX4465 MP\\wordsHK7.txt", wordsList, hashList);
        QuadraticFunction(167, 1, 1, hashList, wordsList, probeList, newHashList);
    }

    public static void wordsHKFileWrite(String outputFile, ArrayList < String > wordList, ArrayList < Integer > newHashValues) {
        try {

            FileWriter writer = new FileWriter(outputFile);
            writer.write("Word index j\t|\tWord\t\t\t\t\tHash key, kj\n");
            writer.write("----------------------------------------------------------------------\n");
            for (int i = 0; i < wordList.size(); i++) {
                writer.write(i + "\t\t\t|\t" + wordList.get(i) + "\t\t\t\t\t" + newHashValues.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void QuadraticFunction(int tableSize, int C1, int C2, ArrayList < Integer > hashList,
                                         ArrayList < String > wordsList, ArrayList < Integer > probeList, ArrayList < Integer > newHashList) {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Zakir\\Desktop\\EEX4465 MP\\wordsQHK7.txt");
            writer.write("Word index j \t|\tWord \t\t\t\tHash key, kj\t\tNew hash key, Qkj\t\t\tQuadratic h-f, h(j, i)\n");
            writer.write("------------------------------------------------------------------------------------------------------------------------------\n");
            for (int j = 0; j < wordsList.size(); j++) {
                int kj = hashList.get(j);
                int newHash = kj;
                int i = 1;
                int hji = newHash % tableSize;
                while (probeList.contains(hji) && probeList.indexOf(hji) != j) {
                    hji = (kj + C1 * (i * i) + C2 * i) % tableSize;
                    newHash = (kj + C1 * (i * i) + C2 * i);
                    i++;
                }
                newHashList.add(newHash);
                probeList.add(hji);
                writer.write(j + "\t\t\t|\t" + wordsList.get(j) + "\t\t\t\t" + kj + "\t\t\t\t" + newHashList.get(j) + "\t\t\t\t\t" +
                        probeList.get(j) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}