package controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquizcmr.R;

import java.io.Serializable;
import java.util.Arrays;

import model.Question;
import model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String RESULT_SCORE = "RESULT_SCORE";

    private static final String BUNDLE_STATE_SCORE = "BUNDLE_STATE_SCORE";
    private static final String BUNDLE_STATE_QUESTION_COUNT = "BUNDLE_STATE_QUESTION_COUNT";
    private static final String BUNDLE_STATE_QUESTION_BANK = "BUNDLE_STATE_QUESTION_BANK";

    private static final int INITIAL_QUESTION_COUNT = 13;

    //declaration des variable pour le branchement avec les vues xml
    private TextView mGameQuestionTextView;
    private Button mGameAnswerButtonOne;
    private Button mGameAnswerButtonTwo;
    private Button mGameAnswerButtonThree;
    private Button mGameAnswerButtonFour;

    //variable pour le score de l'utilisateur
    private int mScore;
    //
    private int mRemainingQuestionCount;
    //liste des questions a afficher pour le jeu qui est notre banque de question
    private QuestionBank mQuestionBank;
    // la bonne réponse pour chacune des questions
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mEnableTouchEvents = true; // on active la visible du click sur les boutons

        //branchement /initialisation de nos variable avec les views identifier dans notre fichier .xml
        mGameQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mGameAnswerButtonOne = findViewById(R.id.game_activity_button_1);
        mGameAnswerButtonTwo = findViewById(R.id.game_activity_button_2);
        mGameAnswerButtonThree = findViewById(R.id.game_activity_button_3);
        mGameAnswerButtonFour = findViewById(R.id.game_activity_button_4);


        // Tout les boutons serons ecouter par cette interface.
        // on va utiliser l'id des view des bouton pour distinguer et definir la provenance du click
        mGameAnswerButtonOne.setOnClickListener((View.OnClickListener) this);
        mGameAnswerButtonTwo.setOnClickListener((View.OnClickListener) this);
        mGameAnswerButtonThree.setOnClickListener((View.OnClickListener) this);
        mGameAnswerButtonFour.setOnClickListener((View.OnClickListener) this);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mRemainingQuestionCount = savedInstanceState.getInt(BUNDLE_STATE_QUESTION_COUNT);
            mQuestionBank = (QuestionBank) savedInstanceState.getSerializable(BUNDLE_STATE_QUESTION_BANK);
        } else {
            mScore = 0;
            mRemainingQuestionCount = INITIAL_QUESTION_COUNT;
            mQuestionBank = generateQuestionBank();
        }

        displayQuestion(mQuestionBank.getCurrentQuestion());
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION_COUNT, mRemainingQuestionCount);
        outState.putSerializable(BUNDLE_STATE_QUESTION_BANK, (Serializable) mQuestionBank); // je serialize en faisant un casting ou transtypage

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    // Notons que j'aurais pu ecrire ce code de maniere archaique en definissant pour chacun de nos 4 bouton sa methode d'ecoute d'evenement onClick Listener

    // on a un probleme avec ce @Override vu que cette interface ecoute directement sur elle les click ou evenement des ses bouton . pour resourdre cela je peux directement faire implementer la Classe GameActivity de View.OnClickListener : pour eviter de creer une classe Interface..
    // ecoute des evenement provenant des different boutons de cet UI : il faut creer une interface  donc cette classe va implementer en re-definissant la methode Onclick(View v) et ..


    @Override
    public void onClick(View v) {
        int index;

        if (v == mGameAnswerButtonOne) {
            index = 0;
        } else if (v == mGameAnswerButtonTwo) {
            index = 1;
        } else if (v == mGameAnswerButtonThree) {
            index = 2;
        } else if (v == mGameAnswerButtonFour) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view : " + v);
        }

        if (index == mQuestionBank.getCurrentQuestion().getAnswerIndex()) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                mRemainingQuestionCount--;

                if (mRemainingQuestionCount <= 0) {
                    endGame();
                } else {
                    displayQuestion(mQuestionBank.getNextQuestion()); // cette ligne fait appelle a la Methode appeler getNextQuestion() de la classe QuestionBank pour retourner la prochaine question
                }
            }
        }, 2_000);
    }

   // methode d'affichage ou de presentation d'une question au joueur avec ses differentes propositions de reponse

    private void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons
        mGameQuestionTextView.setText(question.getQuestion()); // j'obtien l'intitulé de la question courante
        mGameAnswerButtonOne.setText(question.getChoiceList().get(0)); // recupere la premiere proposition
        mGameAnswerButtonTwo.setText(question.getChoiceList().get(1)); // recupere la deuxieme proposition
        mGameAnswerButtonThree.setText(question.getChoiceList().get(2)); // recupere la Troisieme proposition
        mGameAnswerButtonFour.setText(question.getChoiceList().get(3));  // recupere la quatrieme proposition
    }

    // methode pour l'arret du jeu
    private void endGame() {
        // No question left, end the game
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + mScore)
                .setCancelable(false)

                // .setPositiveButton(...) sa marche bien mais je vais utiliser les fonctions anonymes [lambdas]

                //.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    //@Override
                    //public void onClick(DialogInterface dialog, int which) {
                    //    Intent intent = new Intent();
                    //    intent.putExtra(RESULT_SCORE, mScore);
                    //    setResult(Activity.RESULT_OK, intent);
                    //    finish();
                   // }
                //})

                .setPositiveButton("OK", (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.putExtra(RESULT_SCORE, mScore);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                 })
                .create()
                .show();
    }


    // ici la methode qui va nous permettre de creer et  generer les differentes questions de notre jeu
    private QuestionBank generateQuestionBank() {
        Question question1 = new Question(
                "Combien de Région dispose le Cameroun?",
                Arrays.asList(
                        "15",
                        "8",
                        "10",
                        "17"
                ),
                2
        );

        Question question2 = new Question(
                "Quelle est la plus grande Région du cameroun?",
                Arrays.asList(
                        "Centre",
                        "Sud",
                        "Ouest",
                        "Nord"
                ),
                3
        );

        Question question3 = new Question(
                "Qui Fut le prémier Président du Cameroun?",
                Arrays.asList(
                        "Sultan Njoya",
                        "Ahamdou Ahidjo",
                        "Ernest Ouandie",
                        "Runben Um Nyobe"
                ),
                1
        );

        Question question4 = new Question(
                "En Quelle année est décedé Ahamadou Ahidjo?",
                Arrays.asList(
                        "1989",
                        "1924",
                        "1990",
                        "1965"
                ),
                0
        );

        Question question5 = new Question(
                "Quelle de ces villes etait la prémiere capitale du cameroun?",
                Arrays.asList(
                        "Bafoussam",
                        "Yaoundé",
                        "Douala",
                        "Buea"
                ),
                2
        );

        Question question6 = new Question(
                "En quelle année Buea etait capitale du cameroun?",
                Arrays.asList(
                        "1900-19001",
                        "1909-2023",
                        "1901-1909",
                        "1885-1901"
                ),
                2
        );

        Question question7 = new Question(
                "En quelle année Douala etait capitale du cameroun?",
                Arrays.asList(
                        "1900-19001",
                        "1909-2023",
                        "1901-1909",
                        "1885-1901"
                ),
                3
        );

        Question question8 = new Question(
                "En quelle année Yaaoundé etait capitale du cameroun?",
                Arrays.asList(
                        "1900-19001",
                        "1909-2023+",
                        "1901-1909",
                        "1885-1901"
                ),1
        );

        Question question9 = new Question(
                "Que signifie la couleur Verte du Drapeau du cameroun?",
                Arrays.asList(
                        "La Foret du Sud",
                        "Le Feuille Verte",
                        "Le Soleil",
                        "Le Sang du Peuple"
                ),1
        );

        Question question10 = new Question(
                "En quelle année la radio Fm fut sont apparition au Cameroun?",
                Arrays.asList(
                        "1941",
                        "1918",
                        "1960",
                        "1980"
                ),0
        );

        Question question11= new Question(
                "Combien De Departement Compte Le cameroun?",
                Arrays.asList(
                        "50",
                        "360",
                        "180",
                        "58"
                ),3
        );

        Question question12 = new Question(
                "Combien D'arrondissements Compte Le cameroun??",
                Arrays.asList(
                        "360",
                        "202",
                        "404",
                        "234"
                ),0
        );

        Question question13 = new Question(
                "Combien D'ethnies Compte Le cameroun??",
                Arrays.asList(
                        "120",
                        "80",
                        "34",
                        "234"
                ),3
        );

        return new QuestionBank(
                Arrays.asList(
                        question1,
                        question2,
                        question3,
                        question4,
                        question5,
                        question6,
                        question7,
                        question8,
                        question9,
                        question10,
                        question11,
                        question12,
                        question13
                )
        );
    }
}