package model;

import androidx.annotation.NonNull;

import java.util.List;

public class Question {

    //variable de classe declaree tous en final donc en constante non modifiable
    @NonNull
    private final String mIntitule; //intiltule de la questions
    @NonNull
    private final List <String> mPropositions; //la liste des propositions pour les elements de reponse
    private final int mAnsewrIndex;  // l'index de la reponse de la question

    //constructeur a 3 arguement d'un Object Question

    public Question(@NonNull  String mIntitule, @NonNull List<String> mPropositions, int mAnsewrIndex) {
        this.mIntitule = mIntitule;
        this.mPropositions = mPropositions;
        this.mAnsewrIndex = mAnsewrIndex;
    }

    //etant donnee que les element sont des constantes il ne peut y avvoir des setter /accesseur an mutation /modification

    public String getmIntitule() {
        return mIntitule;
    }

    public List<String> getmPropositions() {
        return mPropositions;
    }

    public int getmAnsewrIndex() {
        return mAnsewrIndex;
    }


    //methode qui va retourne a chaque fois la question /intituler de la question courante . NB :je peut egalement utiliser le Getter de l'Intituler
    @NonNull
    public String getQuestion() {
        return  this.getmIntitule();
    }

    // la methode GetChoiceList qui retourne la liste courante des proposition d'une question
    @NonNull
    public List<String> getChoiceList() {
        return this.mPropositions;
    }
    // NB: On peut egalement utiliser le getter d'ailleur c'est sa raison d'etre
    public int getAnswerIndex() {
        return this.mAnsewrIndex;
    }
}
