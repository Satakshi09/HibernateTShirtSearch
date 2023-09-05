package AdvanceJava_2.AdvanceJava_2;

import java.util.Scanner;
import java.util.Timer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);

		String color, size, gender, outputPreference;

		System.out.println("please enter colour");
		color = sc.next();
		System.out.println("please enter size");
		size = sc.next();
		System.out.println("please enter gender");
		gender = sc.next();
		System.out.println("please enter output preference");
		outputPreference = sc.next();

    	Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Timer t = new Timer();
        Csv csv=new Csv(t,session,color,size,gender,outputPreference);
		t.scheduleAtFixedRate(csv,0,10*1000);
    }
}
