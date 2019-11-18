package Model;
import java.util.ArrayList;

public class Model {
    private String className;
    private ArrayList<String> result;


    private boolean testsExecuted = false;
    public Model(String className) {

        this.className = className;
    }


    public void testExecutor() {

        HelperClass hc = new HelperClass(className);
        Class<?> testClass = hc.getClassForName();
        boolean validated = hc.validateClass(testClass);
        result = hc.getResult();
        if (validated){
            Object object = hc.instantiateClass();
            hc.invokeMethod(object);
            testsExecuted = true;

        }

    }

    public ArrayList<String> getResult() {

        return result;
    }


    public boolean isTestsExecuted() {
        return testsExecuted;
    }
}

