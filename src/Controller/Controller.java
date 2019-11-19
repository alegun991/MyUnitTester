package Controller;

import Model.Model;
import View.TestView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class Controller {
    private TestView view;
    private Model model;


    public Controller() {

        initView();

    }

    private void initView() {

        SwingUtilities.invokeLater(() -> {

            view = new TestView();
            view.setSize(500, 500);
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setVisible(true);
            initListeners();
        });
    }

    private void initListeners() {

        view.addRunTestListener(actionEvent -> runTests());
        view.clearTextListener(actionEvent -> clearText());
    }

    private void runTests() {
        String inputString = view.getTextField();
        model = new Model(inputString);
        new SwingWorker<ArrayList<String>, Object>() {

            @Override
            protected ArrayList<String> doInBackground() {

                return new ArrayList<>(model.testExecutor());

            }

            @Override
            protected void done() {
                try{
                    ArrayList<String> temp = get();

                    if(model.isError()){
                        SwingUtilities.invokeLater(() -> {
                            view.addText(temp);
                        });
                    }
                    else{

                        SwingUtilities.invokeLater(() -> {
                            view.addText(temp);
                        });
                    }

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.execute();

    }

    private void clearText() {
        view.clearText();

    }


}
