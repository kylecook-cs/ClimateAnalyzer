package climateChange;

import java.util.*;
import java.io.*;
import java.text.*;

public class WeatherIO implements IWeatherIO {

	double temperature;
	int year;
	String month, country, code;
	ArrayList<ITemperature> fileList = new ArrayList<ITemperature>();
	String[] data;
	File file;

	public ArrayList<ITemperature> readDataFromFile(String fileName) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName)); // creates new BufferedReader
			while (br.ready()) {
				String[] data = br.readLine().split(", ");
				if (data[0].equals("Temperature")) // checks if first line is a header, if is then move to next line
					data = br.readLine().split(", ");

				temperature = Double.parseDouble(data[0]); // reads data from file into specific variable
				year = Integer.parseInt(data[1]);
				month = data[2];
				country = data[3];
				code = data[4];

				if (data[0] == null) // checks if not more data to add and closes BufferedReader
				{
					br.close();
					return null;
				} else {
					fileList.add(new Temperature(temperature, year, month, country, code)); // creates to Temperature
																							// object to add to
																							// arrayList
				}
			}
			br.close();
		} catch (Exception e) { // prints error if one occurs
			sop("File could not be read in");
			sop(e);

		}
		return fileList;
	}

	// Writes the Subject Header to each file
	public void writeSubjectHeaderInFile(String filename, String subject) {

		file = new File(filename);
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter output = new BufferedWriter(fw);
			// output.append("FileName:" + filename.substring(5));
			// output.newLine();
			output.write(subject);
			output.newLine();
			output.write("Temperature, Year, Month, Country, Country_Code");
			output.newLine();
			output.close();
			fw.close();
		} catch (Exception e) {
			sop("Header could not be written into file");
			sop(e);
		}

	}

	// writes ArrayList that is passed into it to a file
	public void writeDataToFile(String filename, String topic, ArrayList<ITemperature> theWeatherList) {

		DecimalFormat df = new DecimalFormat("#.##"); // format for the temperatures
		try {
			FileWriter fw = new FileWriter(filename, true); // create new writers
			BufferedWriter output = new BufferedWriter(fw);
			for (ITemperature t : theWeatherList) // iterate through array to get values to write to file
			{
				output.append(df.format(t.getTemperature(false)) + "(C) ");
				output.append(df.format(t.getTemperature(true)) + "(F)" + ", ");
				output.append(t.getYear() + ", ");
				output.append(t.getMonth() + ", ");
				output.append(t.getCountry() + ", ");
				output.append(t.getCountry3LetterCode());
				output.newLine();
			}
			output.close();
		} catch (Exception e) // catches error if one were to occur
		{
			sop("File could not be created or written");
			sop(e);
		}
	}

	// System.out.println method
	private static void sop(Object x) {
		System.out.println(x);
	}

}
