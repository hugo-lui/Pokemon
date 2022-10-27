import java.util.*;

//Sorts card by HP
public class SortCardByHealth implements Comparator<Card>
{
	public int compare(Card a, Card b)
	{
		return a.getHealth() - b.getHealth();
	}
}