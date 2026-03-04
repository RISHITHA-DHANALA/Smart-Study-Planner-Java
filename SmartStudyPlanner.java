//Smart Study Planner

import java.io.*;
import java.util.*;

class Topic{
	String name;
	boolean isCompleted;
	
	Topic(String name){
		this.name=name;
		this.isCompleted=false;
	}
}

class Subject{
	String name;
	ArrayList<Topic> topics;
	
	Subject(String name){
		this.name=name;
		topics=new ArrayList<>();
	}
}

public class SmartStudyPlanner{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		ArrayList<Subject> subjects=new ArrayList<>();
		
		int choice;
		
		do{
			System.out.println("\n1.Add Subject");
			System.out.println("2.Add Topic");
			System.out.println("3.Mark Topic Completed");
			System.out.println("4.Show Progress");
			System.out.println("5.Exit");
			System.out.println("Enter choice:");
			
			choice=sc.nextInt();
			sc.nextLine();
			
			switch(choice){
				case 1:
				   System.out.println("Enter Subject Name: ");
				   String subName=sc.nextLine();
				   
				   subjects.add(new Subject(subName));
				   System.out.println("Subject Added!");
				   break;
				   
				case 2:
				    if(subjects.isEmpty()){
						System.out.println("No Subjects Available! Add Subject First.");
						break;
					}
					System.out.println("Available Subjects:");
					for(Subject sub: subjects){
						System.out.println("- "+sub.name);
					}
				    System.out.println("Enter Subject Name to Add Topic:");
					String subjectName=sc.nextLine();
					
					boolean subjectFound=false;
					
					for(Subject sub: subjects){
						if(sub.name.equalsIgnoreCase(subjectName)){
							System.out.println("Enter Topic name:");
							String topicName=sc.nextLine();
							
							sub.topics.add(new Topic(topicName));
							System.out.println("Topic Added!");
							
							subjectFound=true;
							break;
						}
					}
					if(!subjectFound){
						System.out.println("Subject Not Found!");
					}
					break;
					
				case 3:
					if(subjects.isEmpty()){
                       System.out.println("No Subjects Available!");
                        break;
					}
                    System.out.println("Enter Subject Name:");
                    String subjectName3=sc.nextLine();
					
					boolean subjectFound3=false;
					boolean topicFound=false;
					
					for(Subject sub: subjects){
						if(sub.name.equalsIgnoreCase(subjectName3)){
							subjectFound3=true;
							
							if(sub.topics.isEmpty()){
								System.out.println("No Topics Available in this Subject!");
								break;
							}
							System.out.println("Enter Topic Name to Mark Completed:");
							String topicName=sc.nextLine();
							
							for(Topic t:sub.topics){
								if(t.name.equalsIgnoreCase(topicName)){
									t.isCompleted=true;
									System.out.println("Topic Marked as Completed!");
									topicFound=true;
									break;
								}
							}
							if(!topicFound){
								System.out.println("Topic Not Found!");
							}
							break;
						}
					}if(!subjectFound3){
						System.out.println("Subject Not Found!");
					}
					break;
					
				case 4:
				    if(subjects.isEmpty()){
						System.out.println("No Subjects Available!");
						break;
					}
					
					for(Subject sub: subjects){
						int total=sub.topics.size();
						int completedCount=0;
						
						for(Topic t: sub.topics){
							if(t.isCompleted){
								completedCount++;
							}
						}
						System.out.println("\nSubject: "+sub.name);
						System.out.println("Total Topics: "+total);
						System.out.println("Completed Topics: "+completedCount);
						
						if(total>0){
							double percentage=(completedCount*100.0)/total;
							System.out.println("Progress: "+percentage+"%");
						}
						else{
							System.out.println("Progress:0%");
						}
					}
					break;
					
				case 5:
                   saveData(subjects);
				   System.out.println("Exiting Program...");
				   break;
		   }
        }while(choice!=5);
   }

   public static void saveData(ArrayList<Subject> subjects){
	   try{
		   FileWriter fw=new FileWriter("data.txt");
		   
		   for(Subject sub: subjects){
				   fw.write("Subject:"+sub.name+"\n");
				   for(Topic t:sub.topics){
					   fw.write("Topic:"+t.name+":"+t.isCompleted+"\n");
			       }
		        }
				fw.close();
				System.out.println("Data Saved Successfully!");
		   }
		   catch(IOException e){
			   System.out.println("Error Saving Data!");
		   }
   }
   
}
