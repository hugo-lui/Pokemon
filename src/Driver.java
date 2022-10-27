//Name: Hugo Lui
//Date: 11/19/21
//Description: Stores all of my Pokemon cards
import java.io.*;
import java.util.*;

public class Driver
{
	//Description: Checks if an input is an integer
	//Parameters: The user input
	//Return: If the input is an integer
	public static boolean isInt(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	//Description: Displays the menu
	//Parameters: The menu to display, scanner for user input
	//Return: The menu choice of the user
	public static int displayMenu(int menuNum, Scanner user)
    {
		String choice = "";
		if(menuNum == 0)
		{
			System.out.println("----------  MAIN MENU  -----------");
			System.out.println("1) Accessing your list of albums");
			System.out.println("2) Accessing within a particular album");
			System.out.println("3) Exit");
			System.out.println("----------------------------------");
			System.out.print("\nPlease enter your choice: ");
			choice = user.nextLine();;
			while(!isInt(choice) || Integer.parseInt(choice) > 3 || Integer.parseInt(choice) < 1)
			{
				System.out.print("Please enter your choice: ");
				choice = user.nextLine();
			}
		}
		else if(menuNum == 1)
		{
			System.out.println("\n---------  SUB-MENU #1  ----------");
			System.out.println("1) Display a list of all albums");
			System.out.println("2) Display info on a particular album");
			System.out.println("3) Add an album");
			System.out.println("4) Remove an album");
			System.out.println("5) Show statistics");
			System.out.println("6) Return back to main menu.");
			System.out.println("----------------------------------");
			System.out.print("\nPlease enter your choice: ");
			choice = user.nextLine();;
			while(!isInt(choice) || Integer.parseInt(choice) > 6 || Integer.parseInt(choice) < 1)
			{
				System.out.print("Please enter your choice: ");
				choice = user.nextLine();;	
			}
		}
		else
		{
			System.out.println ("\n---------  SUB-MENU #2  ----------");
			System.out.println("1) Display all cards (in the last sorted order) ");
			System.out.println("2) Display info on a particular card");
			System.out.println("3) Add a card");
			System.out.println("4) Remove a card (4 options)");
			System.out.println("5) Edit attack");
			System.out.println("6) Sort cards (3 options)");
			System.out.println("7) Return back to main menu");
			System.out.println("----------------------------------");
			System.out.print("\nPlease enter your choice: ");
			choice = user.nextLine();;
			while(!isInt(choice) || Integer.parseInt(choice) > 7 || Integer.parseInt(choice) < 1)
			{
				System.out.print("Please enter your choice: ");
				choice = user.nextLine();;	
			}
		}			
		return Integer.parseInt(choice);
    }
	public static void main(String args[]) throws FileNotFoundException
	{
		//Adds the default album to the list
		ArrayList<Album> albumList = new ArrayList<>();
		String fileName = "Album5.txt";
		Scanner input = new Scanner(new File(fileName));
		int albumNum = Integer.parseInt(input.nextLine());
		String albumDate = input.nextLine();
		int albumCap = Integer.parseInt(input.nextLine());
		int numOfCards = Integer.parseInt(input.nextLine());
		albumList.add(new Album(albumNum, albumCap, albumDate, numOfCards));
		for(int i = 0; i < numOfCards; i++)
		{
			String name = input.nextLine();
			int health = Integer.parseInt(input.nextLine());
			String type = input.nextLine();
			String date = input.nextLine();
			albumList.get(albumList.size() - 1).addCardList(new Card(name, health, type, date));
			int numOfAttacks = Integer.parseInt(input.nextLine());
			for(int k = 0; k < numOfAttacks; k++)
			{
				String temp = input.nextLine();
				String damage = input.nextLine();
				if(temp.indexOf("-") == -1)
				{
					albumList.get(albumList.size() - 1).getCardList().get(i).addAttackList(new Attack(temp, damage));
				}
				else
				{
					albumList.get(albumList.size() - 1).getCardList().get(i).addAttackList(new Attack(temp.substring(0, temp.indexOf("-") - 1), temp.substring(temp.indexOf("-") + 2), damage));
				}				
			}
		}
		
		Scanner user = new Scanner(System.in);
		int mainMenuChoice = displayMenu(0, user);
		int subMenuChoice;
		
		while(mainMenuChoice != 3)
		{
			//List of albums
			if(mainMenuChoice == 1)
			{
				//Display sub-menu 1
			    subMenuChoice = displayMenu(1, user);
			    //Display list of albums
			    if(subMenuChoice == 1)
			    {
			    	//If there are no albums stored
			    	if(albumList.size() == 0)
			    	{
			    		System.out.println("There are currently no albums stored.");
			    	}
			    	else
			    	{
				    	for(Album a : albumList)
				    	{
				    		System.out.println("Album Number: " + a.getNum());
				    		System.out.println("Date: " + a.getDate());
				    	}
			    	}
			    }
			    //Display information on an album
			    else if(subMenuChoice == 2)
			    {
			    	//If there are no albums stored
			    	if(albumList.size() == 0)
			    	{
			    		System.out.println("There are currently no albums stored.");
			    	}
			    	else
			    	{
				    	for(int i = 0; i < albumList.size(); i++)
				    	{
				    		System.out.println(i + 1 + ") Album Number: " + albumList.get(i).getNum());
				    	}
			    		System.out.print("\nEnter the number corresponding to the album to display: ");
			    		String choice = user.nextLine();
			    		while(!isInt(choice) || Integer.parseInt(choice) > albumList.size() || Integer.parseInt(choice) < 1)
						{
							System.out.print("Enter the number corresponding to the album to display: ");
							choice = user.nextLine();
						}
			    		System.out.println(albumList.get(Integer.parseInt(choice) - 1));
			    	}
			    }
			    //Add an album
			    else if(subMenuChoice == 3)
			    {
			    	System.out.print("\nEnter the name of the album to add: ");
			    	fileName = user.nextLine();
			    	//Checks if the file does not exist
			    	while(true)
			    	{
			    		try
			    		{
			    			input = new Scanner(new File(fileName));
			    			break;
			    		}
			    		catch(FileNotFoundException e)
			    		{
			    			System.out.print("Enter the name of the album to add: ");
					    	fileName = user.nextLine();
			    		}
			    	}
					albumNum = Integer.parseInt(input.nextLine());
					//Checks if the album exists
					Collections.sort(albumList);
					if(Collections.binarySearch(albumList, new Album(albumNum)) > -1)
					{
						System.out.println("\nNo duplicate albums allowed.");
					}
					else
					{	
						albumDate = input.nextLine();
						albumCap = Integer.parseInt(input.nextLine());
						numOfCards = Integer.parseInt(input.nextLine());
						albumList.add(new Album(albumNum, albumCap, albumDate, numOfCards));
						for(int i = 0; i < numOfCards; i++)
						{
							String name = input.nextLine();
							int health = Integer.parseInt(input.nextLine());
							String type = input.nextLine();
							String date = input.nextLine();
							albumList.get(albumList.size() - 1).addCardList(new Card(name, health, type, date));
							int numOfAttacks = Integer.parseInt(input.nextLine());
							for(int k = 0; k < numOfAttacks; k++)
							{
								String temp = input.nextLine();
								String damage = input.nextLine();
								if(temp.indexOf("-") == -1)
								{
									albumList.get(albumList.size() - 1).getCardList().get(i).addAttackList(new Attack(temp, damage));
								}
								else
								{
									albumList.get(albumList.size() - 1).getCardList().get(i).addAttackList(new Attack(temp.substring(0, temp.indexOf("-") - 1), temp.substring(temp.indexOf("-") + 2), damage));
								}				
							}
						}
					}
			    }
			    //Remove an album
			    else if(subMenuChoice == 4)
			    {
			    	//If there are no albums stored
			    	if(albumList.size() == 0)
			    	{
			    		System.out.println("There are currently no albums stored.");
			    	}
			    	else
			    	{
				    	System.out.print("\nPress 1 to remove by album #. Press 2 to remove by date: ");
				    	String choice = user.nextLine();
				    	while(!isInt(choice) || Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 2)
				    	{
				    		System.out.print("Press 1 to remove by album #. Press 2 to remove by date: ");
					    	choice = user.nextLine();
				    	}
				    	if(Integer.parseInt(choice) == 1)
				    	{
				    		System.out.print("\nEnter the album # to remove: ");
				    		String removeNum = user.nextLine();
				    		Collections.sort(albumList);
				    		while(!isInt(removeNum) || Collections.binarySearch(albumList, new Album(Integer.parseInt(removeNum))) < 0)
				    		{
				    			System.out.print("Enter the album # to remove: ");
					    		removeNum = user.nextLine();
				    		}
				    		albumList.remove(Collections.binarySearch(albumList, new Album(Integer.parseInt(removeNum))));
				    	}
				    	else if(Integer.parseInt(choice) == 2)
				    	{
				    		System.out.print("\nEnter the album date to remove: ");
				    		String removeDate = user.nextLine();
				    		while(removeDate.length() != 10 || removeDate.charAt(2) != '/' || removeDate.charAt(5) != '/' || !isInt(removeDate.substring(0, 2)) || !isInt(removeDate.substring(3, 5)) || !isInt(removeDate.substring(6)) || albumList.indexOf(new Album(removeDate)) == -1)
				    		{
				    			System.out.print("Enter the album date to remove: ");
					    		removeDate = user.nextLine();
				    		}
				    		albumList.remove(albumList.indexOf(new Album(removeDate)));
				    		while(albumList.contains(new Album(removeDate)))
				    		{
				    			albumList.remove(albumList.indexOf(new Album(removeDate)));
				    		}
				    	}
			    	}
			    }
			    //Show statistics
			    else if(subMenuChoice == 5)
			    {
			    	//If there are no albums stored
			    	if(albumList.size() == 0)
			    	{
			    		System.out.println("There are currently no albums stored.");
			    	}
			    	else
			    	{
				    	int totalCards = 0;
				    	int totalCapacity = 0;
				    	int totalHealth = 0;
				    	for(Album a : albumList)
				    	{
				    		totalCards += a.getCardList().size();
				    		totalCapacity += a.getCapacity();
				    		totalHealth += a.getTotalHealth();
				    		System.out.println("Album Number: " + a.getNum());
				    		System.out.println(a.getCardList().size() + " card(s) out of " + a.getCapacity());
				    		System.out.println("Average HP: " + a.getTotalHealth() / (double)a.getCardList().size());
				    		System.out.println();
				    	}
				    	System.out.println("ALL Albums:");
				    	System.out.println(totalCards + " card(s) out of " + totalCapacity);
				    	System.out.println("Average HP: " + totalHealth / (double) totalCards);
			    	}
			    }
			    //Exit
			    else if(subMenuChoice == 6)
			    {
			    	mainMenuChoice = displayMenu(0, user);
			    }
			}
			//Particular album
			else if(mainMenuChoice == 2)
			{
				System.out.println();
				//If there are no albums
				if(albumList.size() == 0)
				{
					System.out.println("There are currently no albums stored.");
					mainMenuChoice = displayMenu(0, user);
				}
				else
				{
					//Prints out all the albums for the user to choose
					for(int i = 0; i < albumList.size(); i++)
			    	{
			    		System.out.println(i + 1 + ") Album Number: " + albumList.get(i).getNum());
			    	}
		    		System.out.print("\nEnter the number corresponding to the album to open: ");
		    		String albumChoice = user.nextLine();
		    		while(!isInt(albumChoice) || Integer.parseInt(albumChoice) > albumList.size() || Integer.parseInt(albumChoice) < 1)
					{
						System.out.print("Enter the number corresponding to the album to open: ");
						albumChoice = user.nextLine();
					}
		    		int albumNumber = Integer.parseInt(albumChoice) - 1;
				    subMenuChoice = displayMenu(2, user);
					
				    //Display all cards
				    if(subMenuChoice == 1)
				    {
				    	for(Card c : albumList.get(albumNumber).getCardList())
				    	{
				    		System.out.println("Name: " + c.getName());
				    		System.out.println("Date: " + c.getDate());
				    		System.out.println();
				    	}
				    }
				    //Display information on a card
				    else if(subMenuChoice == 2)
				    {
				    	System.out.println();
				    	for(int i = 0; i < albumList.get(albumNumber).getCardList().size(); i++)
				    	{
				    		System.out.println(i + 1 + ") Card Name: " + albumList.get(albumNumber).getCardList().get(i).getName());
				    	}
				    	System.out.print("\nEnter the number corresponding to the card to display: ");
				    	String cardChoice = user.nextLine();
			    		while(!isInt(cardChoice) || Integer.parseInt(cardChoice) > albumList.get(albumNumber).getCardList().size() || Integer.parseInt(cardChoice) < 1)
						{
							System.out.print("Enter the number corresponding to the card to display: ");
							cardChoice = user.nextLine();
						}
			    		int cardNum = Integer.parseInt(cardChoice) - 1;
			    		System.out.println();
			    		System.out.println(albumList.get(albumNumber).getCardList().get(cardNum));
				    }
				    //Add a card
				    else if(subMenuChoice == 3)
				    {
				    	//Checks if there is space in the album
				    	if(albumList.get(albumNumber).getCapacity() > albumList.get(albumNumber).getCardList().size())
				    	{
					    	System.out.println();
					    	System.out.print("Please enter a name: ");
					    	String name = user.nextLine().trim();
					    	while(name.length() == 0)
					    	{
					    		System.out.print("Please enter a name: ");
						    	name = user.nextLine().trim();
					    	}
					    	System.out.print("Please enter the HP: ");
					    	String hp = user.nextLine().trim();
					    	while(hp.length() == 0 || !isInt(hp) || Integer.parseInt(hp) < 1)
					    	{
					    		System.out.print("Please enter the HP: ");
						    	hp = user.nextLine().trim();
					    	}
					    	System.out.print("Please enter a type: ");
					    	String type = user.nextLine().trim();
					    	while(type.length() == 0)
					    	{
					    		System.out.print("Please enter a type: ");
						    	type = user.nextLine().trim();
					    	}
					    	System.out.print("Please enter a date: ");
					    	String date = user.nextLine().trim();
					    	while(date.length() != 10 || date.charAt(2) != '/' || date.charAt(5) != '/' || !isInt(date.substring(0, 2)) || Integer.parseInt(date.substring(0, 2)) < 1 || Integer.parseInt(date.substring(0, 2)) > 12 || !isInt(date.substring(3, 5)) || Integer.parseInt(date.substring(3, 5)) < 1 || Integer.parseInt(date.substring(3, 5)) > 31 || !isInt(date.substring(6)))
				    		{
				    			System.out.print("Please enter a date: ");
					    		date = user.nextLine().trim();
				    		}
					    	albumList.get(albumNumber).addCardList(new Card(name, Integer.parseInt(hp), type, date));
					    	System.out.print("Enter the number of attacks to add: ");
					    	String numAttacks = user.nextLine();
					    	while(!isInt(numAttacks) || Integer.parseInt(numAttacks) < 1)
					    	{
					    		System.out.print("Enter the number of attacks to add: ");
						    	numAttacks = user.nextLine();
					    	}
					    	for(int i = 0; i < Integer.parseInt(numAttacks); i++)
					    	{
					    		System.out.print("Enter the name of an attack: ");
					    		String attackName = user.nextLine().trim();
					    		while(attackName.length() == 0)
					    		{
					    			System.out.print("Enter the name of an attack: ");
						    		attackName = user.nextLine().trim();
					    		}
					    		System.out.print("Enter the description of an attack: ");
					    		String attackDescription = user.nextLine().trim();
					    		System.out.print("Enter the damage of an attack: ");
					    		String attackDamage = user.nextLine().trim();
					    		while(attackDamage.length() == 0)
					    		{
					    			System.out.print("Enter the damage of an attack: ");
						    		attackDamage = user.nextLine().trim();
					    		}
					    		if(attackDescription.length() == 0)
					    		{
					    			albumList.get(albumNumber).getCardList().get(albumList.get(albumNumber).getCardList().size() - 1).addAttackList(new Attack(attackName, attackDamage));
					    		}
					    		else
					    		{
					    			albumList.get(albumNumber).getCardList().get(albumList.get(albumNumber).getCardList().size() - 1).addAttackList(new Attack(attackName, attackDescription, attackDamage));
					    		}
					    	}
				    	}
				    	else
				    	{
				    		System.out.println("This album is full.");
				    	}
				    }
				    //Remove a card
				    else if(subMenuChoice == 4)
				    {
				    	System.out.println("1) Remove card by name");
				    	System.out.println("2) Remove card by HP");
				    	System.out.println("3) Remove fist card");
				    	System.out.println("4) Remove last card");
				    	System.out.print("\nEnter the number corresponding to the removal method: ");
				    	String remove = user.nextLine();
				    	while(!isInt(remove) || Integer.parseInt(remove) < 1 || Integer.parseInt(remove) > 4)
				    	{
				    		System.out.print("Enter the number corresponding to the removal method: ");
					    	remove = user.nextLine();
				    	}
				    	//Remove by name
				    	if(Integer.parseInt(remove) == 1)
				    	{
				    		System.out.print("\nEnter the name of the card to remove: ");
				    		String nameRemove = user.nextLine().trim();
				    		Collections.sort(albumList.get(albumNumber).getCardList());
				    		while(Collections.binarySearch(albumList.get(albumNumber).getCardList(), new Card(nameRemove)) < 0)
				    		{
				    			System.out.print("Enter the name of the card to remove: ");
					    		nameRemove = user.nextLine().trim();
				    		}
				    		int index = Collections.binarySearch(albumList.get(albumNumber).getCardList(), new Card(nameRemove));
				    		while(index > -1)
				    		{
				    			albumList.get(albumNumber).getCardList().remove(index);
				    			index = Collections.binarySearch(albumList.get(albumNumber).getCardList(), new Card(nameRemove));
				    		}
				    	}
				    	//Remove by HP
				    	else if(Integer.parseInt(remove) == 2)
				    	{
				    		System.out.print("\nEnter the HP of the card to remove: ");
				    		String healthRemove = user.nextLine().trim();
				    		Collections.sort(albumList.get(albumNumber).getCardList(), new SortCardByHealth());
				    		while(!isInt(healthRemove) || Collections.binarySearch(albumList.get(albumNumber).getCardList(), new Card(Integer.parseInt(healthRemove)), new SortCardByHealth()) < 0)
				    		{
				    			System.out.print("Enter the HP of the card to remove: ");
					    		healthRemove = user.nextLine().trim();
				    		}
				    		int index = Collections.binarySearch(albumList.get(albumNumber).getCardList(), new Card(Integer.parseInt(healthRemove)), new SortCardByHealth());
				    		while(index > -1)
				    		{
				    			albumList.get(albumNumber).getCardList().remove(index);
				    			index = Collections.binarySearch(albumList.get(albumNumber).getCardList(), new Card(Integer.parseInt(healthRemove)), new SortCardByHealth());
				    		}
				    	}
				    	//Remove first card
				    	else if(Integer.parseInt(remove) == 3)
				    	{
				    		albumList.get(albumNumber).getCardList().remove(0);
				    	}
				    	//Remove last card
				    	else if(Integer.parseInt(remove) == 4)
				    	{
				    		albumList.get(albumNumber).getCardList().remove(albumList.get(albumNumber).getCardList().size() - 1);
				    	}
				    }
				    //Edit attack
				    else if(subMenuChoice == 5)
				    {
				    	for(int i = 0; i < albumList.get(albumNumber).getCardList().size(); i++)
				    	{
				    		System.out.println(i + 1 + ") Name: " + albumList.get(albumNumber).getCardList().get(i).getName());			    		
				    	}
				    	System.out.print("\nEnter the number corresponding to the card to edit: ");
			    		String cardChoice = user.nextLine();
			    		while(!isInt(cardChoice) || Integer.parseInt(cardChoice) < 1 || Integer.parseInt(cardChoice) > albumList.get(albumNumber).getCardList().size())
				    	{
				    		System.out.print("Enter the number corresponding to the card to edit: ");
					    	cardChoice = user.nextLine();
				    	}
			    		for(int i = 0; i < albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().size(); i++)
				    	{
				    		System.out.println(i + 1 + ") Attack Name: " + albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(i).getName());			    		
				    	}
			    		System.out.print("\nEnter the number corresponding to the attack to edit: ");
			    		String attackChoice = user.nextLine();
			    		while(!isInt(attackChoice) || Integer.parseInt(attackChoice) < 1 || Integer.parseInt(attackChoice) > albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().size())
				    	{
				    		System.out.print("Enter the number corresponding to the attack to edit: ");
					    	attackChoice = user.nextLine();
				    	}
			    		System.out.println("1) Name: " + albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(Integer.parseInt(attackChoice) - 1).getName());
			    		System.out.println("2) Description: " + albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(Integer.parseInt(attackChoice) - 1).getDescription());
			    		System.out.println("3) Damage: " + albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(Integer.parseInt(attackChoice) - 1).getDamage());
			    		System.out.print("\nEnter the number corresponding to the field to edit: ");
			    		String fieldChoice = user.nextLine();
			    		while(!isInt(fieldChoice) || Integer.parseInt(fieldChoice) < 1 || Integer.parseInt(fieldChoice) > 3)
			    		{
			    			System.out.print("Enter the number corresponding to the field to edit: ");
				    		fieldChoice = user.nextLine();
			    		}
			    		String change;
			    		//Edit name
			    		if(Integer.parseInt(fieldChoice) == 1)
			    		{
			    			System.out.print("\nEnter the new name: ");
			    			change = user.nextLine().trim();
			    			while(change.length() == 0)
			    			{
			    				System.out.print("Enter the new name: ");
				    			change = user.nextLine().trim();
			    			}
			    			albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(Integer.parseInt(attackChoice) - 1).setName(change);
			    		}
			    		//Edit description
			    		else if(Integer.parseInt(fieldChoice) == 2)
			    		{
			    			System.out.print("\nEnter the new description: ");
			    			change = user.nextLine().trim();
			    			if(change.length() == 0)
			    			{
			    				albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(Integer.parseInt(attackChoice) - 1).setDescription("none");
			    			}
			    			else
			    			{
			    				albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(Integer.parseInt(attackChoice) - 1).setDescription(change);
			    			}
			    		}
			    		//Edit damage
			    		else if(Integer.parseInt(fieldChoice) == 3)
			    		{
			    			System.out.print("\nEnter the new damage: ");
			    			change = user.nextLine().trim();
			    			while(change.length() == 0)
			    			{
			    				System.out.print("Enter the new damage: ");
				    			change = user.nextLine().trim();
			    			}
			    			albumList.get(albumNumber).getCardList().get(Integer.parseInt(cardChoice) - 1).getAttackList().get(Integer.parseInt(attackChoice) - 1).setDamage(change);
			    		}
				    }
				    //Sort cards
				    else if(subMenuChoice == 6)
				    {
				    	System.out.println("1) Sort cards by name");
				    	System.out.println("2) Sort cards by HP");
				    	System.out.println("3) Sort card by date");
				    	System.out.print("\nEnter the number corresponding to the sorting order: ");
				    	String sort = user.nextLine();
				    	while(!isInt(sort) || Integer.parseInt(sort) < 1 || Integer.parseInt(sort) > 3)
				    	{
				    		System.out.print("Enter the number corresponding to the sorting order: ");
					    	sort = user.nextLine();
				    	}
				    	//Sort by name
				    	if(Integer.parseInt(sort) == 1)
				    	{
				    		Collections.sort(albumList.get(albumNumber).getCardList());
				    	}
				    	//Sort by HP
				    	else if(Integer.parseInt(sort) == 2)
				    	{
				    		Collections.sort(albumList.get(albumNumber).getCardList(), new SortCardByHealth());
				    	}
				    	//Sort by date
				    	else if(Integer.parseInt(sort) == 3)
				    	{
				    		Collections.sort(albumList.get(albumNumber).getCardList(), new SortCardByDate());
				    	}
				    	//Display cards
				    	for(Card c : albumList.get(albumNumber).getCardList())
				    	{
				    		System.out.println("Name: " + c.getName());
				    		System.out.println("Date: " + c.getDate());
				    		System.out.println();
				    	}
				    }
				    //Exit
				    else if(subMenuChoice == 7)
				    {
				    	mainMenuChoice = displayMenu(0, user);
				    }
				}
			}		
		}
		user.close();
		input.close();
	}	
}