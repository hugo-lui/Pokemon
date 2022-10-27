import java.util.*;

//Sorts card by date
public class SortCardByDate implements Comparator<Card>
{
	public int compare(Card a, Card b)
	{
		if(Integer.parseInt(a.getDate().getYear()) - Integer.parseInt(b.getDate().getYear()) == 0)
		{
			if(Integer.parseInt(a.getDate().getMonth()) - Integer.parseInt(b.getDate().getMonth()) == 0)
			{
				return Integer.parseInt(a.getDate().getDay()) - Integer.parseInt(b.getDate().getDay());
			}
			return Integer.parseInt(a.getDate().getMonth()) - Integer.parseInt(b.getDate().getMonth());
		}
		return Integer.parseInt(a.getDate().getYear()) - Integer.parseInt(b.getDate().getYear());
	}
}