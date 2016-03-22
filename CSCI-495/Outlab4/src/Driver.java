public class Driver
{
    public static void main(String [] args)
    {
        FrootLoops x = new FrootLoops();
        x.red(1, 10, 1);
        x.green(1, 10, 1);

        x.red(2, 9, 3);
        x.green(2, 9, 3);

        x.purple();

        x.blue(4);
        x.orange(4);
    }
}
