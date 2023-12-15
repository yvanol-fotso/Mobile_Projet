package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuestionBank  implements Serializable {

    //    cette classe permettra de generer les question de manier aleatoire pareil durant le jeu que au commencement de chaque nouvelle partie

    //private ArrayList<Question> mGameQuestion; //un questionBank c'est  une liste de question

    private final List<Question> mGameQuestion;
    private int nextQuestionIndex;


    // constructeur a deux agrument d'un Bean/Object QuestionBank

//    public QuestionBank(ArrayList<Question> mGameQuestion, int nextQuestionIndex) {
//        this.mGameQuestion = mGameQuestion;
//        this.nextQuestionIndex = nextQuestionIndex;
//    }

    //accesseur en consultation et en mutation

//    public List<Question> getmGameQuestion() {
//        return mGameQuestion;
//    }

    //puisque "mGameQuestion" est  declarer en final on ne peut utiliser this pour pointer sur l'object courant

//    public void setmGameQuestion(List<Question> mGameQuestion) {
//        mGameQuestion = mGameQuestion;
//    }

//    public int getNextQuestionIndex() {
//        return nextQuestionIndex;
//    }

//    public void setNextQuestionIndex(int nextQuestionIndex) {
//        this.nextQuestionIndex = nextQuestionIndex;
//    }

    //fin avec les accesseur ou getter and setter



    //Constructeur a un argument
    public QuestionBank(List<Question> questionList) {
        // Shuffle the question list before storing it
        mGameQuestion = questionList;

        Collections.shuffle(mGameQuestion);
    }


    // Cette methode va nous retourner la prochaine question a poster chaque fois
    public Question getNextQuestion() {
        //nextQuestionIndex +=1; //ou
        nextQuestionIndex++;
        return  this.mGameQuestion.get(nextQuestionIndex); // cette ligne dit que : retourne moi sur l'objet courant List des questions l'element donc sont index correspond a l'index de la question suivante
        // vu que on a un attribut classe qui designe l'index de la question next (suivante)
    }

    //methode appeler dans le onCreate du GameActivity appliquer a un objet QuestionBank qui est passer coe paramettre a la methode DisplayQuestion()

    // Qui est charger d'afficher une question au joueur
    public Question getCurrentQuestion() {
        return this.mGameQuestion.get(this.nextQuestionIndex);
    }
}
