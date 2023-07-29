package mainpackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
public class EngineersLoginApp {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("engineersinfo.xml");
		Engineers engineers = ctx.getBean("engineers", Engineers.class);
		Scanner scanner = new Scanner(System.in);
		boolean loginFail = true;
		boolean ifNotExit = true;
		while (loginFail) {
			System.out.println("Enter 'exit' for both username and password if you want to exit.");
			System.out.println("Enter username: ");
	        String inputname = scanner.nextLine();
	        System.out.println("Enter password: ");
	        String inputpw = scanner.nextLine();
	        ifNotExit = !inputname.equals("exit") || !inputpw.equals("exit");
	        loginFail = engineers.login(inputname, inputpw) && ifNotExit;
		}
		if(loginFail == false && ifNotExit == true) {
			String inputCsvFile = "C:\\Users\\berkehan\\Downloads\\WorkedHoursForDay.csv";
			try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile))) {
	            String outputCsvFile = "TotalSalaryForDay.csv";
	            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputCsvFile))) {
	            	processCEngSalary(engineers.getCe1(), br, bw);
	            	processCEngSalary(engineers.getCe2(), br, bw);
	            	processCEngSalary(engineers.getCe3(), br, bw);
	            	processCEngSalary(engineers.getCe4(), br, bw);
	            	processCEngSalary(engineers.getCe5(), br, bw);
	            	processCEngSalary(engineers.getCe6(), br, bw);
	            	processCEngSalary(engineers.getCe7(), br, bw);
	            	processEngSalary(engineers.getE1(), br, bw);
	            	processEngSalary(engineers.getE2(), br, bw);
	            	processEngSalary(engineers.getE3(), br, bw);
	            	processEngSalary(engineers.getE4(), br, bw);
	                System.out.println("Data has been written to " + outputCsvFile);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		scanner.close();
		ctx.close();
	}
	
	public static void processCEngSalary(ComputerEngineer compeng, BufferedReader br, BufferedWriter bw) {
		try {
			String line = br.readLine();
			int value = Integer.parseInt(line.trim())*compeng.getSalaryPerHour()+ComputerEngineer.getDailySalary();
	        bw.write(String.valueOf(value));
	        bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void processEngSalary(Engineer eng, BufferedReader br, BufferedWriter bw) {
		try {
			String line = br.readLine();
			int value = Integer.parseInt(line.trim())*eng.getSalaryPerHour()+Engineer.getDailySalary();
	        bw.write(String.valueOf(value));
	        bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}