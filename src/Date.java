public class Date
{
	private String month;
	private String day;
	private String year;
	//Constructor
	public Date(String d)
	{
		month = d.substring(0, 2);
		day = d.substring(3, 5);
		year = d.substring(6);
	}
	public String getMonth()
	{
		return month;
	}
	public String getDay()
	{
		return day;
	}
	public String getYear()
	{
		return year;
	}
	public String toString()
	{
		return month + "/" + day + "/" + year;
	}
}