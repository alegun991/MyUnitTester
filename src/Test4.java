import se.umu.cs.unittest.TestClass;

/**
 * Test class with faulty test methods.
 * One does not return boolean
 * One have have parameters
 * One does not contain "test" in method name
 */

public class Test4 implements TestClass {

    private MyInt myInt;


    public Test4() {
    }

    public void setUp() {
        myInt=new MyInt();
    }

    public void tearDown() {
        myInt=null;
    }

    //Not valid return value
    public int testInitialisation() {

        return 3;

    }

    //not valid parameter count
    public boolean testIncrement(int i) {
        myInt.increment();
        myInt.increment();
        return myInt.value()==2;

    }

    //Not valid method name
    public boolean Decrement() {
        myInt.increment();
        myInt.decrement();
        return myInt.value()==0;
    }
}
