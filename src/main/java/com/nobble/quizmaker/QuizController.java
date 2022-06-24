package com.nobble.quizmaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class QuizController implements Initializable {
    int grade = 0;
    @FXML
    private Label result_label;
    @FXML
    private TextField answer_text;
    @FXML
    private ComboBox<Integer> comboBox;
    private final Integer[] numbers = {5,10,15,20,25,30,35,40,45,50};
    @FXML
    private Label question_label;
    private final ArrayList<String> randomQuestions  = new ArrayList<>();
    private final Set<Integer> randomNumbers = new HashSet<>();

    private final int correct  = 0;
    private int total = 0;
    private int x = -1;
    private int y = -1;
    private final Random ran = new Random();
    private final ArrayList<String> questions = new ArrayList<>();
    private final ArrayList<String> answers = new ArrayList<>();
    private final HashMap<Integer,Integer> index = new HashMap<>();


    @Override
    public void initialize(URL arq0, ResourceBundle arg1){
            comboBox.getItems().addAll(numbers);
    }

    public int generate_random(){
        int i = ran.nextInt(0,total);
        while (randomNumbers.contains(i))
            i = ran.nextInt(0,total);
        randomNumbers.add(i);
        return i;
    }
    public void start(ActionEvent e) throws IOException {

        int n = comboBox.getValue();

            for(int i =0; i< n;i++) {
                int j = generate_random();
                randomQuestions.add(questions.get(j));
                question_label.setText(randomQuestions.get(0));
            }
    }

    public void next(ActionEvent e ){
        try {

            String q = question_label.getText();
            int i = randomQuestions.indexOf(q);
            i++;
            String next = randomQuestions.get(i);
            question_label.setText(next);
            result_label.setText("");

        }catch (IndexOutOfBoundsException ex){
            result_label.setText("You answered all the questions");
        }

    }
    public void submit(ActionEvent e){

        String quest = question_label.getText();
        int key = questions.indexOf(quest);
        String userAnswer = answer_text.getText();
        Integer AnswerIndex = index.get(key);
        String correctAnswer = answers.get(AnswerIndex);

        if(userAnswer.equals(correctAnswer)){
            grade++;
            result_label.setText("Correct answer.");
        }
        else{
            result_label.setText("Wrong answer.");
        }
    }
    public void get_Result(ActionEvent e){

        int n = comboBox.getValue();
        result_label.setText(grade + "/" + n);
    }
    public void load_questions(ActionEvent e){
        boolean done = false;
        try{

            InputStream q = QuizApplication.class.getResourceAsStream("/questions.txt");
            BufferedReader qreader = new BufferedReader(new InputStreamReader(q));

            InputStream a = QuizApplication.class.getResourceAsStream("/answers.txt");
            BufferedReader areader = new BufferedReader(new InputStreamReader(a));

            String question;
            String answer;

            while(   ((question = qreader.readLine()) !=null  ) &&   ((answer = areader.readLine() ) != null  )) {

                questions.add(question);
                x++;
                answers.add(answer);
                y++;
                index.put(x,y);
                total++;
                index.put(x,y);

                done = true;
            }
        }catch (IOException ioException){

            done = false;
        }
        if(done){
            result_label.setText("Questions were loaded successfully");
        }
    }

}