
/**
 * Write a description of class Cat here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */
public class Cat extends Pet implements Animal
{
    public Cat (String inName)
    {
        super(inName);
    }
    
    public String getName()
    {
        return super.getName();
    }
    
    public String talk()
    {
        return getName() + " says meow!";
    }
}
