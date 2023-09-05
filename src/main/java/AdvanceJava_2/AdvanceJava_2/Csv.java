package AdvanceJava_2.AdvanceJava_2;

import java.io.File;
import java.io.FileReader;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



import org.hibernate.Session;
import org.hibernate.query.Query;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Csv extends TimerTask {

	private Timer timer;
	private Session session;
	private String color;
	private String size;
	private String gender;
	private String outputPreference;

	public Csv(Timer timer, Session session, String color, String size, String gender, String outputPreference) {

		this.timer = timer;
		this.session = session;
		this.color = color;
		this.size = size;
		this.gender = gender;
		this.outputPreference = outputPreference;
	}

	public Csv() {
		// TODO Auto-generated constructor stub
	}

	public  Boolean getData() {
        Boolean found=false;
		// getting filename by passing folder-path
		// File directory = new File();
		File directory = new File("C:\\Users\\satakshi\\Desktop\\CsvFiles");
		String[] file = directory.list();

		// if there is no file

		if (file == null) {
			System.out.println("no file found");
		} else {
			for (int i = 0; i < file.length; i++) {

				String filename = file[i];
				// getting full path of file and passing for further computation
				// readData("C:\\Users\\chandankumar06\\Desktop\\Assigment Links\\" + filename);
				readData("C:\\Users\\satakshi\\Desktop\\CsvFiles\\" + filename);
			}

			session.getTransaction().begin();
			String qryString;
		    Query<Tshirt> query = null;
			if (outputPreference.equals("Price")) {

				qryString = "from Tshirt s where s.color=:color and s.size=:size and s.gender=:gender order by s.price";
				query = session.createQuery(qryString, Tshirt.class);

				query.setParameter("color", color);
				query.setParameter("size", size);
				query.setParameter("gender", gender);

			} else if (outputPreference.equals("Rating")) {
				qryString = "from Tshirt s where s.color=:color and s.size=:size and s.gender=:gender order by s.rating desc";
				query = session.createQuery(qryString, Tshirt.class);

				query.setParameter("color", color);
				query.setParameter("size", size);
				query.setParameter("gender", gender);

			} else if (outputPreference.equals("both")) {
				qryString = "from Tshirt s where s.color=:color and s.size=:size and s.gender=:gender order by s.rating desc,s.price";
				query = session.createQuery(qryString, Tshirt.class);

				query.setParameter("color", color);
				query.setParameter("size", size);
				query.setParameter("gender", gender);

			}
            List<Tshirt>tshirts=query.list();
            if(tshirts.size()==0&&Helper.firstVisit) {
            	System.out.println("T-shirt is not found");
            	Helper.firstVisit=false;
            	
            }
            else if(tshirts.size()>0){
            	for(Tshirt tshirt:tshirts) {
            		System.out.println(tshirt);
            	}
            	found=true;
            }
            session.getTransaction().commit();
            session.clear();
           }
		if(found) {
			session.close();
		}
		return found;
	}

	/*
	 * This method help in to read csv file and store data. this function also
	 * 
	 * remove duplicate data on the basis of id
	 * 
	 * @ param file path of file
	 * 
	 * @return false
	 */

	public void readData(String file) {

		try {

			FileReader filereader = new FileReader(file);

			CSVParser parser = new CSVParserBuilder().withSeparator('|').build();

			CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();

			List<String[]> allData = csvReader.readAll();

			int firstRow = 0;

			for (String[] row : allData) {
				if (firstRow == 0) {
					firstRow = 1;
					continue;
				}

				Tshirt tshirt = new Tshirt();
				int index = 0;
				Boolean idContainInset = false;
				for (String cell : row) {

					if (index == 5 || index == 6) {
						if (index == 5) {
							tshirt.setPrice(Double.parseDouble(cell));
						} else {
							tshirt.setRating(Double.parseDouble(cell));
						}
					} else {
						if (index == 0) {

							tshirt.setId(cell);
						} else if (index == 1) {
							tshirt.setName(cell);
						} else if (index == 2) {
							tshirt.setColor(cell);
						} else if (index == 3) {
							tshirt.setGender(cell);
						} else if (index == 4) {
							tshirt.setSize(cell);
						} else if (index == 7) {
							tshirt.setAvailblity(cell);
						}
					}
					index++;
				}

				session.getTransaction().begin();
				session.saveOrUpdate(tshirt);
				session.getTransaction().commit();
				session.clear();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
        synchronized(this) {
		if(getData())
        timer.cancel();
	}}

}