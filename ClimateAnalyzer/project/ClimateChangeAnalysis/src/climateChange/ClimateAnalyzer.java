package climateChange;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class ClimateAnalyzer extends TreeSet<Temperature> implements IClimateAnalyzer {

	static String defaultFile = "data/taskXX_climate_info.csv"; // output file name format
	static String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" }; // array of Month names to use for indexing
	static String[] months2 = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "Dececember" }; // array of full month names for print statements
	double temperature;
	int year;
	String monthString, country, code, name;

	static ArrayList<ITemperature> original = new ArrayList<ITemperature>(); // array of original file
	static ArrayList<ITemperature> taskList = new ArrayList<ITemperature>(); // array to be written to a file
	static Scanner scan = new Scanner(System.in);

	// Gets the name of the file user wants to use;
	public void fileName(String name) {

		this.name = name;
	}

	// TASK A-1
	// for all data that matches the specified month, get the lowest temperature
	// reading
	public ITemperature getLowestTempByMonth(String country, int month) {

		monthString = months[month - 1]; // converts monthString to int
		double low = Double.MAX_VALUE;
		ITemperature lowest = null;
		for (ITemperature t : original) {
			if (t.getCountry().equals(country) && (t.getMonth().equals(monthString))) { // checks file for specific
																						// country
																						// country
				if (t.getTemperature(false) < low) { // and it's lowest temp for given month
					low = t.getTemperature(false);
					lowest = t;
				}
			}
		}
		return lowest;
	}

	// TASK A-1
	// for all data that matches the specified month, get the highest temperature
	// reading
	public ITemperature getHighestTempByMonth(String country, int month) {
		monthString = months[month - 1];
		double high = -1000000;
		ITemperature highest = null;
		for (ITemperature t : original) {
			if (t.getCountry().equals(country) && (t.getMonth().equals(monthString))) { // checks file for specific
																	// country
				if (t.getTemperature(false) > high) { // and it's highest temp for given month
					high = t.getTemperature(false);
					highest = t;

				}
			}
		}
		return highest;
	}

	// TASK A-2
	// for all data that matches the specified year, get the lowest temperature
	// reading
	public ITemperature getLowestTempByYear(String country, int year) {
		double low = Double.MAX_VALUE;
		ITemperature lowest = null;
		for (ITemperature t : original) { // checks file for specific country
			if (t.getCountry().equals(country) && (t.getYear() == (year))) { // and it's lowest temp for given year
				if (t.getTemperature(false) < low) {
					low = t.getTemperature(false);
					lowest = t;
				}
			}
		}
		return lowest;
	}

	// TASK A-2
	// for all data that matches the specified year, get the highest temperature
	// reading
	public ITemperature getHighestTempByYear(String country, int year) {

		double high = Double.MIN_VALUE;
		ITemperature highest = null;
		for (ITemperature t : original) { // checks file for specific country
			if (t.getCountry().equals(country) && (t.getYear() == year)) { // and it's highest temp for given year
				if (t.getTemperature(false) > high) {
					high = t.getTemperature(false);
					highest = t;
				}
			}
		}
		return highest;
	}

	// TASK A-3
	// get all temperature data that fall within the given temperature range
	// the set is sorted from lowest to highest temperature
	// input parameter values are in Celsius
	public TreeSet<ITemperature> getTempWithinRange(String country, double rangeLowTemp, double rangeHighTemp) {

		TreeSet<ITemperature> result = new TreeSet<ITemperature>();
		for (ITemperature t : original) {
			if (t.getCountry().equals(country) && t.getTemperature(false) >= rangeLowTemp // checks file for specific
																							// country
					&& t.getTemperature(false) <= rangeHighTemp) { // with temps within given range
				result.add(t);
			}
		}
		return result;
	}

	// TASK A-4
	// 1. get the lowest temperature reading amongst all data for that country
	public ITemperature getLowestTempYearByCountry(String country) {
		double low = Double.MAX_VALUE;
		ITemperature lowest = null;
		for (ITemperature t : original) {
			if (t.getCountry().equals(country)) { // checks for lowest temp for
																						// given country
				if (t.getTemperature(false) < low) {
					low = t.getTemperature(false);
					lowest = t;
				}
			}
		}
		return lowest;
	}

	// TASK A-4
	// 1. get the highest temperature reading amongst all data for that country
	public ITemperature getHighestTempYearByCountry(String country) {
		double high = Double.MIN_VALUE;
		ITemperature highest = null;
		for (ITemperature t : original) {
			if (t.getCountry().equals(country)) { // checks for highest temp for given country
				if (t.getTemperature(false) > high) {
					high = t.getTemperature(false);
					highest = t;
				}
			}
		}
		return highest;
	}

	// TASK B-1
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp(int month) {
		TreeSet<ITemperature> result = new TreeSet<ITemperature>();
		HashSet<String> names = new HashSet<String>();
		TreeSet<ITemperature> sorted = new TreeSet<ITemperature>();
		for (ITemperature t : original) {
			if (t.getMonth().equals(months[month - 1])) { // creates treeset with all data in the given month
				result.add(t);
			}
		}
		taskList.addAll(result);

		for (int i = 0; i < taskList.size() - 1; i++) {
			if (!(names.contains(taskList.get(i).getCountry()))) {  //checks if country name is already in hashset
				sorted.add(taskList.get(i));						//if not add it to arraylist and add name to hashset
				names.add(taskList.get(i).getCountry());
			}
		}
		taskList.clear();
		taskList.addAll(sorted);
		return taskList = new ArrayList<ITemperature>(taskList.subList(0, 10)); // returns first 10 objects in tree set
																				// which are lowest temps
	}

	// TASK B-1
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp(int month) {
		TreeSet<ITemperature> result = new TreeSet<ITemperature>();
		HashSet<String> names = new HashSet<String>();
		TreeSet<ITemperature> sorted = new TreeSet<ITemperature>();
		for (ITemperature t : original) {
			if (t.getMonth().equals(months[month - 1])) { // creates treeset with all data in the given month
				result.add(t);							
			}
		}
		taskList.addAll(result.descendingSet());

		for (int i = 0; i < taskList.size() - 1; i++) {
			if (!(names.contains(taskList.get(i).getCountry()))) {    //checks if country name is already in hashset
				sorted.add(taskList.get(i));						  //if not add it to arraylist and add name to hashset
				names.add(taskList.get(i).getCountry());
			}
		}
		taskList.clear();
		taskList.addAll(sorted);
		return taskList = new ArrayList<ITemperature>(taskList.subList(taskList.size() - 10, taskList.size()));
		// returns last 10 objects in arraylist which are highest temps
	}

	// TASK B-2
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp() {

		TreeSet<ITemperature> result = new TreeSet<ITemperature>();
		HashSet<String> names = new HashSet<String>();
		TreeSet<ITemperature> sorted = new TreeSet<ITemperature>();
		for (ITemperature t : original) {
			result.add(t);
		}
		taskList.addAll(result);

		for (int i = 0; i < taskList.size() - 1; i++) {
			if (!(names.contains(taskList.get(i).getCountry()))) {			//checks if country name is already in hashset
				sorted.add(taskList.get(i));							    //if not add it to arraylist and add name to hashset
				names.add(taskList.get(i).getCountry());
			}
		}
		taskList.clear();
		taskList.addAll(sorted);

		return taskList = new ArrayList<ITemperature>(taskList.subList(0, 10)); // returns first 10 objects in tree set
																				// which are lowest temps
	}

	// TASK B-2
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp() {
		TreeSet<ITemperature> result = new TreeSet<ITemperature>();
		HashSet<String> names = new HashSet<String>();
		TreeSet<ITemperature> sorted = new TreeSet<ITemperature>();
		for (ITemperature t : original) {
			result.add(t);
		}
		taskList.addAll(result.descendingSet());

		for (int i = 0; i < taskList.size() - 1; i++) {
			if (!(names.contains(taskList.get(i).getCountry()))) {			//checks if country name is already in hashset
				sorted.add(taskList.get(i));								//if not add it to arraylist and add name to hashset
				names.add(taskList.get(i).getCountry());
			}
		}
		taskList.clear();
		taskList.addAll(sorted);
		return taskList = new ArrayList<ITemperature>(taskList.subList(taskList.size() - 10, taskList.size()));
		// returns last 10 objects in arraylist which are highest temps
	}

	// TASK B-3
	// 1. the return list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange(double lowRangeTemp, double highRangeTemp) {
		TreeSet<ITemperature> result = new TreeSet<ITemperature>();
		for (ITemperature t : original) {
			// checks for all data where temps fall under given range
			if (t.getTemperature(false) >= lowRangeTemp && t.getTemperature(false) <= highRangeTemp) {
				result.add(t);
			}
		}
		taskList.addAll(result);
		return taskList;
	}

	// TASK C-1
	// 1. the countries with the largest temperature differences (absolute value) of
	// the same month between 2 given years.
	// 2. the return list is sorted from lowest to highest temperature delta
	public ArrayList<ITemperature> allCountriesTop10TempDelta(int month, int year1, int year2) {
		TreeSet<ITemperature> difference = new TreeSet<ITemperature>();
		TreeSet<ITemperature> result = new TreeSet<ITemperature>();
		HashSet<String> names = new HashSet<String>();
		TreeSet<ITemperature> sorted = new TreeSet<ITemperature>();
		// checks if data equals given data
		for (ITemperature t : original) {
			if (t.getMonth().equals(months[month - 1]) && (t.getYear() == (year1) || t.getYear() == (year2))) {
				result.add(t);
			}
		}
		for (ITemperature t : result) {
			for (ITemperature t2 : result) {
				if (t.getCountry().equals(t2.getCountry()) && t.getYear() == (year1) && t2.getYear() == (year2)) {
					// creates new object with new data
					try {
						difference.add(new Temperature(Math.abs(t.getTemperature(false) - t2.getTemperature(false)),
								Math.abs(year2 - year1), months[month - 1], t2.getCountry(),
								t2.getCountry3LetterCode()));
					} catch (IOException e) {
						// checks if new object was created
						sop("New object to be written to file was not created");
						sop(e);
					}
				}

			}
		}
		taskList.addAll(difference.descendingSet());
		for (int i = 0; i < taskList.size() - 1; i++) {							
			if (!(names.contains(taskList.get(i).getCountry()))) {		//checks if country name is already in hashset
				sorted.add(taskList.get(i));							//if not add it to arraylist and add name to hashset
				names.add(taskList.get(i).getCountry());
			}
		}
		taskList.clear();
		taskList.addAll(sorted);
		return taskList = new ArrayList<ITemperature>(taskList.subList(taskList.size() - 10, taskList.size()));
	}

	// System.out.println method
	private static void sop(Object x) {
		System.out.println(x);
	}
	// checks if month is the right format and within the right range
	public static int monthFormat() {
		scan.reset();
		int month = 0;

		boolean intFormat = false;
		boolean inRange = true;

		while (!intFormat || !inRange) {
			intFormat = false;
			inRange = false;

			sop("Please enter the month in integer format [1-12] (Jan = 1, Feb = 2, Mar = 3; etc...): ");
			try {
				month = Integer.parseInt(scan.nextLine());
				intFormat = true;
				if (month < 1 || month > 12) {
					inRange = false;
					throw new Exception();
				}
				inRange = true;
				intFormat = true;
			} catch (Exception e) {
				sop("Entered input is either not in integer format or not in the month range!");
				intFormat = false;
			}
		}
		scan.reset();
		return month;
	}

	// checks if year is the right format and within the right range
	public static int yearFormat(String file) {
		scan.reset();
		WeatherIO weather = new WeatherIO();
		ClimateAnalyzer.original = weather.readDataFromFile(file);

		ArrayList<Integer> yearRange = new ArrayList<Integer>();
		for (ITemperature o : original) {
			yearRange.add(o.getYear());
		}
		int year = 0;

		boolean intFormat = false;
		boolean inRange = true;

		while (!intFormat || !inRange) {
			intFormat = false;
			inRange = false;

			sop("Please enter the year within range " + Collections.min(yearRange) + "-" + Collections.max(yearRange)
					+ ": ");
			try {
				year = Integer.parseInt(scan.nextLine());
				intFormat = true;
				if (year < Collections.min(yearRange) || year > Collections.max(yearRange)) {
					inRange = false;
					throw new Exception();
				}
				inRange = true;
				intFormat = true;
			} catch (Exception e) {
				sop("Entered input is either not in integer format or not in the year range!");
				intFormat = false;
			}
		}
		scan.reset();
		return year;
	}

	// checks if t is the right format and within the right range
	public static double tempFormat(String file) {
		scan.reset();
		WeatherIO weather = new WeatherIO();
		ClimateAnalyzer.original = weather.readDataFromFile(file);

		ArrayList<Double> tempRange = new ArrayList<Double>();
		for (ITemperature o : original) {
			tempRange.add(o.getTemperature(false));
		}
		double temp = 0;

		boolean doubleFormat = false;
		boolean inRange = true;

		while (!doubleFormat || !inRange) {
			doubleFormat = false;
			inRange = false;

			sop("Please enter the temperature within range " + Collections.min(tempRange) + " to "
					+ Collections.max(tempRange) + ": ");
			try {
				temp = Double.parseDouble(scan.nextLine());
				doubleFormat = true;
				if (temp < Collections.min(tempRange) || temp > Collections.max(tempRange)) {
					inRange = false;
					throw new Exception();
				}
				inRange = true;
				doubleFormat = true;
			} catch (Exception e) {
				sop("Entered input is either not in integer format or not in the temperature range!");
				doubleFormat = false;
			}
		}
		scan.reset();
		return temp;
	}

	public void runClimateAnalyzer() {

		// create scanner object to read input from user
		int month = 0;
		int year = 0, year1 = 0, year2 = 0;
		double temp1 = 0, temp2 = 0;
		File tempFile;

		ClimateAnalyzer ca = new ClimateAnalyzer();

		sop("Climate Analyzer is now running.");
		sop("--------------------------------");

		sop("What data set file would you like to use(full path included): ");
		name = scan.nextLine();
		fileName(name);
		tempFile = new File(name);
		while (!tempFile.exists()) {
			sop("Data set file does not exists, what file would you like to use(full path included): ");
			name = scan.nextLine();
			fileName(name);
			tempFile = new File(name);
		}
		scan.reset();
		WeatherIO weather = new WeatherIO();
		ClimateAnalyzer.original = weather.readDataFromFile(name);
		ArrayList<String> countryNameAL = new ArrayList<String>();
		ArrayList<Double> tempRange = new ArrayList<Double>();
		ArrayList<Integer> yearRange = new ArrayList<Integer>();
		for (ITemperature o : original) {
			countryNameAL.add(o.getCountry());
			tempRange.add(o.getTemperature(false));
			yearRange.add(o.getYear());
		}

		// TASK A1 lowest
		sop("");
		sop("Task A-1: This task will get the lowest and highest temperature reading for a specific country and month.");
		sop("Please enter the full name of the country: ");
		String countryName = scan.nextLine();
		while (!countryNameAL.contains(countryName)) {
			sop("Country was not found in file. Please enter the full name of the country with a captial letter as first character: ");
			countryName = scan.nextLine();
		}
		month = monthFormat();
		scan.reset();
		taskList.add(ca.getLowestTempByMonth(countryName, month));
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "A1"), "The Lowest Temperature for the Country of "
				+ countryName + " in the month of " + months2[month - 1] + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "A1")), "Task A1", taskList);
		taskList.clear();
		// TASK A1 highest
		taskList.add(ca.getHighestTempByMonth(countryName, month));
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "A1"), "The Highest Temperature for the Country of "
				+ countryName + " in the month of " + months2[month - 1] + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "A1")), "Task A1", taskList);
		taskList.clear();

		// TASK A2 lowest
		sop("");
		sop("Task-A2: This task will get the lowest and highest temperature reading for a specific country and year.");
		sop("Please enter the full name of the country: ");
		countryName = scan.nextLine();
		while (!countryNameAL.contains(countryName)) {
			sop("Country was not found in file. Please enter the full name of the country with a captial letter as first character: ");
			countryName = scan.nextLine();
		}
		year = yearFormat(name);
		taskList.add(ca.getLowestTempByYear(countryName, year));
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "A2"),
				"The Lowest Temperature for the Country of " + countryName + " in the Year of " + year + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "A2")), "Task A2", taskList);
		taskList.clear();

		// TASK A2 highest
		taskList.add(ca.getHighestTempByYear(countryName, year));
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "A2"),
				"The Highest Temperature for the Country of " + countryName + " in the Year of " + year + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "A2")), "Task A2", taskList);
		taskList.clear();
		scan.reset();

		// TASK A3 all temps within range
		sop("");
		sop("Task A-3: This task gets all the temperature data for a specific country based on a range of temperatures.");
		sop("Please enter the full name of the country: ");
		countryName = scan.nextLine();
		while (!countryNameAL.contains(countryName)) {
			sop("Country was not found in file. Please enter the full name of the country with a captial letter as first character: ");
			countryName = scan.nextLine();
		}
		temp1 = tempFormat(name);
		temp2 = tempFormat(name);
		TreeSet<ITemperature> result = ca.getTempWithinRange(countryName, temp1, temp2);
		taskList.addAll(result);
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "A3"),
				"All Temperature Data within Temperature Range: " + temp1 + " - " + temp2 + " for the country of "
						+ countryName + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "A3")), "Task A3", taskList);
		taskList.clear();
		scan.reset();

		// TASK A4 lowest
		sop("");
		sop("Task A-4: This task gets the lowest and highest temperature reading for a specific country.");
		sop("Please enter the full name of the country: ");
		countryName = scan.nextLine();
		while (!countryNameAL.contains(countryName)) {
			sop("Country was not found in file. Please enter the full name of the country with a captial letter as first character: ");
			countryName = scan.nextLine();
		}
		taskList.add(ca.getLowestTempYearByCountry(countryName));
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "A4"),
				"The Lowest Temperature for the Country of " + countryName + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "A4")), "Task A4", taskList);
		taskList.clear();

		// TASK A4 highest
		taskList.add(ca.getHighestTempYearByCountry(countryName));
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "A4"),
				"The Highest Temperature for the Country of " + countryName + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "A4")), "Task A4", taskList);
		taskList.clear();
		scan.reset();

		// TASK B1 lowest
		sop("");
		sop("Task B-1: This task returns a sorted list of the top 10 lowest and highest temperatures for all countries for a specific month.");
		month = monthFormat();
		taskList = allCountriesGetTop10LowestTemp(month);
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "B1"),
				"10 Unique Countries with the Lowest Temperatures in the Month of " + months2[month - 1] + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "B1")), "Task B1", taskList);
		taskList.clear();

		// TASK B1 highest
		taskList = allCountriesGetTop10HighestTemp(month);
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "B1"),
				"10 Unique Countries with the Highest Temperatures in the Month of " + months2[month - 1] + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "B1")), "Task B1", taskList);
		taskList.clear();
		scan.reset();

		// TASK B2 lowest
		sop("");
		sop("Task B-2: This task returns a sorted list of the lowest and highest temperature for any country, year, or month.");
		taskList = allCountriesGetTop10LowestTemp();
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "B2"),
				"10 Unique Countries with the Lowest Temperatures in Whole File");
		weather.writeDataToFile((defaultFile.replace("XX", "B2")), "Task B2", taskList);
		sop("Top 10 lowest temperatures of any country, month, or year created.");
		taskList.clear();

		// TASK B2 highest
		taskList = allCountriesGetTop10HighestTemp();
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "B2"),
				"10 Unique Countries with the Highest Temperatures in Whole File");
		weather.writeDataToFile((defaultFile.replace("XX", "B2")), "Task B2", taskList);
		sop("Top 10 highest temperatures of any country, month, or year created.");
		taskList.clear();
		scan.reset();

		// TASK B3 all data with range
		sop("");
		sop("Task B-3: This task gets all temperature data for temperatures within specific range.");
		temp1 = tempFormat(name);
		temp2 = tempFormat(name);
		taskList = allCountriesGetAllDataWithinTempRange(temp1, temp2);
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "B3"),
				"All countries that have recorded a temperature between: " + temp1 + " - " + temp2 + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "B3")), "Task B3", taskList);
		taskList.clear();
		scan.reset();

		// TASK C1 absolute value of temp change between range
		sop("");
		sop("Task C-1: This task gets the top 10 largest temperature differences(absolute value) of the same month between given years.");
		month = monthFormat();
		year1 = yearFormat(name);
		year2 = yearFormat(name);
		taskList = ca.allCountriesTop10TempDelta(month, year1, year2);
		weather.writeSubjectHeaderInFile(defaultFile.replace("XX", "C1"),
				"10 Unique Countries with the Largest Temperature Differences (absolute value)" + " for the Month of "
						+ months2[month - 1] + " between years " + year1 + " - " + year2 + ".");
		weather.writeDataToFile((defaultFile.replace("XX", "C1")), "Task C1", taskList);

		scan.reset();
		scan.close();
		sop("------------------------------");
		sop("Climate Analyzer is completed.");
	}

	public static void main(String[] arg) {

		ClimateAnalyzer ca = new ClimateAnalyzer();
		ca.runClimateAnalyzer();

	}

}
