package controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.topquizcmr.R;

import model.User;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_GAME_ACTIVITY = 42;

    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final String SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE";


//variable pour branchement avec vue

private TextView mGreetingTextView;
private EditText mNameEditText;
private Button mPlayButton;

// ajout d'un attribut de type user pour memoriser les information sur ce dernier (nom, reponse ..)
private User mUser;


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //branchement /initialisation de nos variable avec les views identifier dans notre fichier .xml
    mGreetingTextView = findViewById(R.id.main_textview_greeting);
    mNameEditText = findViewById(R.id.main_edittext_name);
    mPlayButton = findViewById(R.id.main_button_play);

    //desactivation du bouton
    mPlayButton.setEnabled(false);

    //on initialise l'attribut de type user

    mUser = new User("Fotso");

    //methode permettant de verifier si on a saisi un text dans le Widget reserver a la saisi du text
    mNameEditText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //on active le bouton si le user a saisi quelque chose dans le widget text
            mPlayButton.setEnabled(!s.toString().isEmpty());

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // This is where we'll check the user input
        }
    });


    //implementation de l'interface onClickListener pour ecouter les evenement / detection de click sur le boutton
    mPlayButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            // On utilise l'accesseur en modification/mutation ou le setter pour sauvegader le nom de l'utilisateur entrer
            mUser.setmFirstName(mNameEditText.getText().toString());


            //une fois que l'utilisateur a entrer son nom alors apres le click sur ce bouton on demarre la nouvelle activité grace a la classe Intent
            //son premier parametre est la classe appelant l'activité et son second la classe a demarrer / ou la nouvelle activité a demarrer

            Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(gameActivityIntent);
        }
    });

    greetUser();
}



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE_GAME_ACTIVITY == requestCode && RESULT_OK == resultCode && data != null) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.RESULT_SCORE, 0);

            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putInt(SHARED_PREF_USER_INFO_SCORE, score)
                    .apply();

            greetUser();
        }
    }


    //affichage du scrore et les information jouer, son nom et son score en point
    private void greetUser(){
        String firstName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        int score = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_SCORE, -1);

        if (firstName != null) {
            if (score != -1) {
                //on pointe vers les resources(res) String avec pour name = welcome_back_with_score et avec les parametre "String"=firstname et "decimal/entier"=Sore
                mGreetingTextView.setText(getString(R.string.welcome_back_with_score, firstName, score));
            } else {
                //On pointe vers la resource String avec le parametre "string" = firstname"
                mGreetingTextView.setText(getString(R.string.welcome_back, firstName));
            }

            mNameEditText.setText(firstName);
        }
    }

 }

