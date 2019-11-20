package ModelUnitTests;
import Model.HelperClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class HelperClassTest {
    private HelperClass hc;

    @Test
    public void shouldBeAbleToInitiate(){

        new HelperClass("Test1");
    }

    @Test
    public void shouldReturnNull() {
        Class<?> test = new HelperClass("test").getClassForName();
        Assert.assertNull(test);
    }


    @Test
    public void shouldDetectClassNotImplementingInterface(){
        hc = new HelperClass("Test2");
        Class<?> test = hc.getClassForName();

        Assert.assertTrue(hc.validateClass(test));

    }

    @Test
    public void shouldDetectConstructorWithParameters(){
        hc = new HelperClass("Test3");
        Class<?> test = hc.getClassForName();

        Assert.assertTrue(hc.validateClass(test));

    }

    @Test
    public void shouldBeAbleToInitiateClass(){
        Object object = new HelperClass("Test1").instantiateClass();

        Assert.assertNotEquals(null, object);

    }

    @Test
    public void shouldDetectIncorrectTestMethods(){
        ArrayList<String> temp;
        hc = new HelperClass("Test4");
        Object obj = hc.instantiateClass();
        temp = hc.invokeMethod(obj);

        //Should not contain any methods.
        Assert.assertEquals("Class does not contain valid test methods"
        , temp.get(0));

    }

}
