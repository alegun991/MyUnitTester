package Model;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

class HelperClass {

    private Class<?> aClass;
    private Method[] methods;
    private Constructor[] constructors;
    private ArrayList<String> result;
    private String className;
    private int setUpIndex;
    private int tearDownIndex;
    private int successCount = 0;
    private int failCount = 0;
    private int exceptionCount = 0;


    HelperClass(String className) {
        this.className = className;
        aClass = getClassForName();
        result = new ArrayList<>();

    }

    /**
     * Looks for setUp method, if found saves the index for that method.
     *
     * @return returns true if setup method is found and has correct parameters
     */
    private boolean hasSetUp() {
        for (int i = 0; i < methods.length; i++) {

            if (methods[i].getName().equals("setUp") && methods[i].getParameterCount() == 0) {
                setUpIndex = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Looks for tearDown method, if found saves the index for that method.
     *
     * @return returns true if setup method is found and has correct parameters
     */
    private boolean hasTearDown() {
        for (int i = 0; i < methods.length; i++) {

            if (methods[i].getName().equals("tearDown") && methods[i].getParameterCount() == 0) {
                tearDownIndex = i;
                return true;
            }
        }
        return false;
    }

    public void invokeMethod(Object obj) {

        boolean success;
        methods = aClass.getMethods();

        ArrayList<Method> testMethods = validTestMethods();
        for (Method m : testMethods) {
            try {
                if (hasSetUp()) {
                    methods[setUpIndex].invoke(obj);
                }

                success = (boolean) m.invoke(obj);

                if (success) {
                    result.add(m.getName() + ": SUCCESS \n");
                    successCount++;
                } else {
                    result.add(m.getName() + ": FAIL \n");
                    failCount++;
                }

                if (hasTearDown()) {
                    methods[tearDownIndex].invoke(obj);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                exceptionCount++;
                result.add(m.getName() + ":" + " FAIL generated by a " + e.getCause().toString() +"\n");
            }
        }
    }

    public boolean validateClass(Class<?> cls){
        boolean isValid = true;
        if(cls.isInterface()){
            result.add("Class is interface, cannot be instantiated! \n");
            isValid = false;
        }

        Class[] interfaces = cls.getInterfaces();
        for (Class c : interfaces){

            if(!c.getName().equals("se.umu.cs.unittest.TestClass")){

                result.add("Class does not implement TestClass interface! \n");
                isValid = false;
            }
        }
        constructors = cls.getConstructors();
        for (Constructor c : constructors){

            if(c.getParameterCount() != 0){
                result.add("Class does not have empty constructor! \n");
                isValid = false;
            }
        }

        return isValid;
    }

    private ArrayList<Method> validTestMethods(){
        ArrayList<Method> testMethods = new ArrayList<>();
        for(Method m : methods){

            if (m.getName().startsWith("test") && (m.getReturnType() == boolean.class) &&
            m.getParameterCount() == 0){
                testMethods.add(m);

            }
        }
        return testMethods;
    }

    public Class<?> getClassForName(){
        Class<?> cls;
        try {
            cls = Class.forName(className);
            return cls;
        } catch (ClassNotFoundException e) {

            return null;
        }
    }

    public Object instantiateClass(){
        Object obj = null;
        try {
            obj = aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public ArrayList<String> getResult() {
        return result;
    }

}
