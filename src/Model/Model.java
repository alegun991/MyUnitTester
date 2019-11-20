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
            isError = true;
            result = hc.getError();
            return result;
        }

        else {

            if(hc.validateClass(testClass)){
                isError = true;
                result = hc.getError();
                return result;
            }

            Object object = hc.instantiateClass();

            if (!hc.isError()) {
                result = hc.invokeMethod(object);

                if(hc.isError()){
                    isError = true;
                    result = hc.getError();
                    return result;
                }

            }

        }
        return result;
    }


    public boolean isError() {
        return isError;
    }

}

