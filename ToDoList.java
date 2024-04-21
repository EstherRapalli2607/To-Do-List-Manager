package Practise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

	private static final String FILE_PATH = "toDoList.txt";
	private static List<String> toDoList;
	public static void main(String[] args){
		
		//Initialize the to-do List
		toDoList = readToDoListFromFile();
		
		//Display menu and handle user input
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n To-Do List Manager");
			System.out.println("1. View To-Do List");
			System.out.println("2. Add Task");
			System.out.println("3. Edit Task");
			System.out.println("4. Delete Tast");
			System.out.println("5. Exit");
			System.out.println("Enter you choice:- ");
			choice = scanner.nextInt();
			switch(choice) {
			case 1: 
				viewToDoList();
				break;
			case 2:
				addTask(scanner);
				break;
			case 3:
				editTask(scanner);
				break;
			case 4:
				deleteTask(scanner);
				break;
			case 5:
				System.out.println("Existing.......");
				break;
			case 6:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}while(choice !=5);
		
		//Save the to-do List to file before exiting
		saveToDoListToFile();
		scanner.close();
		
	}
  //To save to do list file before existing
	private static void saveToDoListToFile() {
		// TODO Auto-generated method stub
		try(PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))){
			for(String task : toDoList) {
				writer.println(task);
			}
			System.out.println("To-Do List Saved to file...");
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Failed To save To-DO list to file..");
		}
		
	}

  //To delete the particular task of To-Do List
	private static void deleteTask(Scanner scanner) {
		// TODO Auto-generated method stub
		viewToDoList();
		System.out.println("Enter task number to delete:- ");
		int taskNumber = scanner.nextInt();
		scanner.nextLine(); //Consume New Line
		if(taskNumber < 1 || taskNumber > toDoList.size()) {
			System.out.println("Invalid task number");
		}else {
			String deletedTask = toDoList.remove(taskNumber - 1 );
			System.out.println("Task Deleted: " + deletedTask);
		}
		
	}

  //To edit the particular task of To-Do list
	private static void editTask(Scanner scanner) {
		// TODO Auto-generated method stub
		viewToDoList();
		System.out.println("Enter task number to edit:- ");
		int taskNumber = scanner.nextInt();
		scanner.nextLine(); //Consume New line
		if(taskNumber < 1 || taskNumber > toDoList.size() ) {
			System.out.println("Invalid task number. ");
		}else {
			System.out.println("Enter updated task: ");
			String updatedtask = scanner.nextLine();
			toDoList.set(taskNumber -1 , updatedtask);
			System.out.println("Task updated. ");
		}
		
	}
  //To add the particular task of To-Do List
	private static void addTask(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter new Task:- ");
		String task = scanner.nextLine();
		toDoList.add(task);
		System.out.println("Task added: "+task);
		
	}
  //To view the To_Do List
	private static void viewToDoList() {
		// TODO Auto-generated method stub
		if(toDoList.isEmpty()) {
			System.out.println("To-Do List is Empty");
		}else {
			System.out.println("To-Do List: ");
			for(int i=0; i< toDoList.size(); i++) {
				System.out.println((i+1) + "." + toDoList.get(i));
			}
		}
		
	}

  //To read the file if not present then create a new file 
	private static List<String> readToDoListFromFile() {
		// TODO Auto-generated method stub
		
		List<String> list = new ArrayList<>();
		try {
			File file = new File(FILE_PATH);
			if(!file.exists()) {
				file.createNewFile();
				System.out.println("Created new To-Do List file.");
			}
		BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
		}catch(IOException e) {
			System.out.println("Error reading To-Do List file." + e.getMessage());
		}
		return list;
	}
}
