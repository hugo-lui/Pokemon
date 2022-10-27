public class Attack
{
	private String name;
	private String description;
	private String damage;
	//Constructors
	public Attack(String name, String description, String damage)
	{
		this.name = name;
		this.description = description;
		this.damage = damage;
	}
	public Attack(String name, String damage)
	{
		this.name = name;
		this.damage = damage;
		this.description = "none";
	}
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public String getDamage()
	{
		return damage;
	}
	public void setName(String n)
	{
		name = n;
	}
	public void setDescription(String d)
	{
		description = d;
	}
	public void setDamage(String d)
	{
		damage = d;
	}
	public String toString()
	{
		return String.format("Name: %s%nDescription: %s%nDamage: %s%n", name, description, damage);
	}
}