package sorter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Test program to see if pulling opcodes from text file using columns will work.

public class Main {

	public static void main(String[] args) {
		List<String> rawNames = new ArrayList<String>();
		rawNames.add("raw_output.txt");
		List<String> filteredNames = new ArrayList<String>();
		filteredNames.add("filtered_output.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] columns = line.split("\t");
				if(columns.length > 2)
					rawNames.add(columns[2]);
			}
			outputProperly(rawNames, 2);
			br.close();
			
			filterInput(rawNames, filteredNames);
			outputProperly(filteredNames, 2);
			
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR");
		}

	}
	
	// Outputs the array either to screen or to file
	public static void outputProperly(List<String> names, int rule) {
		switch(rule) {
		case 1:
			for(int i = 0; i < names.size(); i++) {
				System.out.println(names.get(i));
				System.out.println('\n');
			}
			break;
		case 2:
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(names.get(0)));
				for(int i = 1; i < names.size(); i++) {
					bw.write(names.get(i));
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				System.out.println("ERROR NUMERO DOS");
			}
			break;
		}
	}
	
	// Method prints only one each word from input
	public static void filterInput(List<String> rawNames, List<String> filteredNames) {
		String current;
		for(int i = 0; i < rawNames.size(); i++) {
			current = rawNames.get(i);
			if(filteredNames.indexOf(current) == -1) {
				filteredNames.add(current);
			}
		}
	}
}
