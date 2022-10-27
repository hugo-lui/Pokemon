import java.util.*;

public class Album implements Comparable<Album>
{
	private int num;
	private int capacity;
	private int totalHealth;
	private Date date;
	private ArrayList<Card> cardList = new ArrayList<>();
	private static int totalCards;
	//Constructors
	public Album(int num, int capacity, String date, int numOfCards)
	{
		this.num = num;
		this.capacity = capacity;
		this.date = new Date(date);
		totalCards += numOfCards;		
	}
	public Album(int num)
	{
		this.num = num;		
	}
	public Album(String date)
	{
		this.date = new Date(date);
	}
	public void addCardList(Card c)
	{
		cardList.add(c);
	}
	public int getNum()
	{
		return num;
	}
	public Date getDate()
	{
		return date;
	}
	public int getCapacity()
	{
		return capacity;
	}
	public int getTotalHealth()
	{
		totalHealth = 0;
		for(Card c : cardList)
		{
			this.totalHealth += c.getHealth();
		}
		return totalHealth;
	}
	public ArrayList<Card> getCardList()
	{
		return cardList;
	}
	//Sorts album by number
	public int compareTo(Album a)
	{
		return a.num - this.num;
	}
	//Sorts album by date
	public boolean equals(Object o)
	{
		Album a = (Album) o;
		return a.date.getDay().compareToIgnoreCase(this.date.getDay()) == 0 && a.date.getMonth().compareToIgnoreCase(this.date.getMonth()) == 0 && a.date.getYear().compareToIgnoreCase(this.date.getYear()) == 0;
	}
	public String toString()
	{
		totalHealth = 0;
		for(Card c : cardList)
		{
			this.totalHealth += c.getHealth();
		}
		return String.format("Album Number: %d%nDate: "+ date + "%nMax Capacity: %d%nNumber of Cards: %d%nTotal Health: %d", num, capacity, totalCards, totalHealth);
	}
}