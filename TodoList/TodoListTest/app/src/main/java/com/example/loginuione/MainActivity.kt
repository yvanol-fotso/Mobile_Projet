package com.example.loginuione


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginuione.ui.theme.LoginUiOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginUiOneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    LoginUiTwo()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


//ici je desinne mon premier interface  login grace au @Preview() je peut obtenir une visibilité coté design
@Preview(showBackground = true)
@Composable
fun LoginUiTwo(){

    //composant parent ou le grand conteneur
//    Scaffold(
//        topBar = {
//
//            TopAppBar(
//                //title = { Text(text = "Longin UI") },
//                title = { Text(text = "") },
//
//                navigationIcon = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        //ici je creai un Menu
//                        //Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
//
//                    }
//                }
//
//            )
//        },

    //creation du contenue : les elements seront positionée tous dans un composant Coloumn ou on va les empiler les un a la suite des autre

    //content = {


    //declarons notre variable liste mutable qui va stovker notre liste de tache on declare ici pour que avoir une porter dans tous les composant

    val todoList = remember {
        mutableStateListOf<String>()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {

        //creation de nos differents composant


        //je met de l'espace entre lE Haut de l'ecran et le text
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "AJOUT DES TACHES",
            fontSize = MaterialTheme.typography.h6.fontSize, // mon text aurais une police d'un titre h6
            fontWeight = FontWeight.Bold, //je met le text en gras
            textAlign = TextAlign.Center // JE Centralise mon text
        )

        //je met de l'espace entre l'image et le text
        Spacer(modifier = Modifier.height(2.dp))


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {


            //creation du TextField et du bouton d'ajout de la task

            var textState by remember { mutableStateOf("") }

            OutlinedTextField(
                value = textState,
                onValueChange = { newValue -> textState = newValue },
                //label = {Text(text = "Saisir Une Tache")},
                placeholder = { Text(text = "Saisir une tache") },
                modifier = Modifier
                    .width(200.dp) // j'adapte une taille a mon composant au lieu de laisser la taille par defaut
                    .padding()

            )

            Spacer(modifier = Modifier.width(2.dp)) // je creait un peu /ou je met un peu d'espace entre les deux composant de maniere horizontal en utilisant le width ou largueur


            //bouton d'ajout de la task
            Button(
                onClick = {
                    val newTache = textState
                    todoList.add(newTache)
                },

                //Notons que il ne faut pas toujour regarder le preview ou le rendu il daut egalement le regarder sur le telephone  car ici sur le "Split/Desing" Mon Bouton depasse mon TextField mais sur le Telephone c'est propre

                modifier = Modifier
                    .fillMaxHeight(0.12f) // je donne une taille a mon bouton pourquelle soit identique a la taille de ,on TextField
                    .padding(0.5.dp),


                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Blue, //ici je change la couleur de mon bouton
                    contentColor = White,
                )


            ) {
                Text(
                    text = "ADD",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                )
            }

        }


        //je met de l'espace entre les deux TextField
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "LISTE DES TACHES PROGRAMMEES",
            //fontSize = MaterialTheme.typography.h6.fontSize, // mon text aurais une police d'un titre h6
            //fontWeight = FontWeight.Bold, //je met le text en gras
            //textAlign = TextAlign.Center, // JE Centralise mon text
            //textDecoration = TextDecoration.Underline, // sa souligne Mon Texte

            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        )


        Row(modifier = Modifier.fillMaxSize()) {


            // on supprime les parenthese de ce composant on rest seulement avec les accolade
            LazyColumn {
                itemsIndexed(todoList) { index, todo ->
                    Column(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.Blue, shape = CircleShape),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween

                    ) {


                        // text pour l'affichage des numero
                        Text(
                            text = (index + 1).toString(),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        // espace entre les numero et les taches name


                    }
                    //Spacer(modifier = Modifier.width(9.dp))

                    //Text pour afficher la tache proprement dite
                    Text(text = todo.toString())


                }
            }



        }


    }

}




//    )
//
//
//}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginUiOneTheme {
        Greeting("Android")
    }
}