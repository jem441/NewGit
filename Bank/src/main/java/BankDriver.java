import java.util.List;
import java.util.Scanner;

import com.example.models.Customer;
import com.example.models.Post;
import com.example.models.User;
import com.example.services.UserService;
import com.example.services.CustomerService;
import com.example.services.PostService;

public class BankDriver {

	
	private static CustomerService cServ = new CustomerService("users.txt");
private static PostService pServ = new PostService("posts.txt");
private static UserService uServ = new UserService("user.txt");
	
	public static void main(String[] args) {
Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to JMBanking, as a user you can either log in or sign up.");
		boolean done = false;
		User u = null;
		
		while (!done) {
			if(u == null) {
				System.out.println("Would you like to Login or Signup? Enter 1 to Login or enter 2 to Signup.");
				int choice = Integer.parseInt(scan.nextLine());
				if(choice == 1) {
					System.out.println("Please enter your username: ");
					String username = scan.nextLine();
					System.out.println("Please enter your password: ");
					String password = scan.nextLine();
					
					try {
						u = uServ.login(username, password);
						System.out.println("Welcome " + u.getFirstName() + " we are happy to have you today.");
					} catch(Exception e) {
						System.out.println("Your username or password was incorrect, please try again later.");
						done= true;
				}
			} else {
					System.out.println("Please enter your first name: ");
					String first = scan.nextLine();
					System.out.println("Please enter your last name: ");
					String last = scan.nextLine();
					System.out.println("Please enter a secure password: ");
					String password = scan.nextLine();
					
					try {
						u = uServ.signUp(first, last, password);
						System.out.println("You now have the login username of: " + u.getUsername());
					} catch (Exception e) {
						System.out.println("Sorry, we could not process your request");
						System.out.println("Please try again later");
						done = true;
					}
				}
		} else {
			System.out.println("To apply for a new customer bank account, press 1. To log in as a customer, please press 2.");
			//need to add customer registration and customer log in
			
			System.out.println("To view posts press 1, to enter a new post press 2");
			int choice = Integer.parseInt(scan.nextLine());
			Customer c = null;
			//If the user chooses 1, we will show them the list of posts
			if(choice == 1) {
				List<Post> posts = pServ.getAllPosts();
				for(Post post: posts) {
					System.out.println(post.getCustomer() + ":");
					System.out.println(post.getContent());
					System.out.println();
				}
				System.out.println("Are you finished? Press 1 for yes, press 2 for no");
				choice = Integer.parseInt(scan.nextLine());
				done = (choice == 1) ? true : false;
			} else {
				System.out.println("Please enter your content below:");
				String content = scan.nextLine();
				Post p = new Post(u.getUsername(), content);
				pServ.addPost(p);
				System.out.println("Post was received, are you finished? Press 1 for yes, press 2 for no");
				choice = Integer.parseInt(scan.nextLine());
				done = (choice == 1) ? true : false;
			} 
		}
		}
		System.out.println("Thank you for joining us today, goodbye.");
		scan.close();
	}
}
