import java.util.*;

public class Card implements Comparable<Card>
{
	private String name;
	private int health;
	private String type;
	private Date date;
	private ArrayList<Attack> attackList = new ArrayList<>();
	//Constructors
	public Card(String name, int health, String type, String date)
	{
		this.name = name;
		this.health = health;
		this.type = type;
		this.date = new Date(date);
	}
	public Card(String name)
	{
		this.name = name;
	}
	public Card(int health)
	{
		this.health = health;
	}
	public String getName()
	{
		return name;
	}
	public Date getDate()
	{
		return date;
	}
	public int getHealth()
	{
		return health;
	}
	public ArrayList<Attack> getAttackList()
	{
		return attackList;
	}
	public void addAttackList(Attack a)
	{
		attackList.add(a);
	}
	//Sorts card by name
	public int compareTo(Card c)
	{
		return this.name.compareToIgnoreCase(c.name);
	}
	public String toString()
	{
		String temp = "";
		for(Attack a : attackList)
		{
			temp += a;
		}
		return String.format("Name: %s%nHP: %d%nType: %s%nAttacks:%n%sDate: " + date, name, health, type, temp);
	}
}