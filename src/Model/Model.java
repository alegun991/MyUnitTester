package Model;
import java.util.ArrayList;

public class Model {
    private String className;
    private ArrayList<String> result;
    private boolean isError = false;

    public Model(String className) {

        this.className = className;
    }


    public ArrayList<String> testExecutor() {
        result = new ArrayList<>();
        HelperClass hc = new HelperClass(className);
        var testClass = hc.getClassObject();

        if(testClass == null){
            result = hc.getError();
            return result;
        }

        else {
            hc.validateClass(testClass);
            Object object = hc.instantiateClass();

            if (hc.isError()) {
                isError = true;
                result = hc.getError();
                return result;

            } else {
                result = hc.invokeMethod(object);
            }
        }
        return result;
    }


    public boolean isError() {
        return isError;
    }

}

