package com.example.loginuione

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    LoginUiOne()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


//ici je desinne mon premier interface  login
@Preview(showBackground = true)
@Composable
fun LoginUiOne(){

    //composant parent ou le grand conteneur
    Scaffold(
        topBar = {

            TopAppBar(
                //title = { Text(text = "Longin UI") },
                title = { Text(text = "") },

                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        //ici je creai un Menu
                        //Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")

                    }
                }

            )
        },

        //creation du contenue : les elements seront positionée tous dans un composant Coloumn ou on va les empiler les un a la suite des autre

       content = {

           Column(
               verticalArrangement = Arrangement.spacedBy(8.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .fillMaxHeight()
                   .padding(16.dp)
           ) {

               //creation de nos differents composant
               
               Image(
                   painter = painterResource(id = R.mipmap.login),
                   contentDescription ="Image Login" ,
                   contentScale = ContentScale.Crop,
                   modifier = Modifier.size(160.dp),
               )

               //je met de l'espace entre l'image et le text
               Spacer(modifier = Modifier.height(6.dp))

               //creation des Deux Input / TextField

               var textState by remember { mutableStateOf("") }
               
               OutlinedTextField(
                   value = textState,
                   onValueChange = {newValue -> textState = newValue},
                   //label = {Text(text = "Telephone")},
                   placeholder = {Text(text = "Telephone")},

               )

               //je met de l'espace entre les deux TextField
               Spacer(modifier = Modifier.height(6.dp))

               OutlinedTextField(
                   value = textState,
                   onValueChange = {newValue -> textState = newValue},
                   //label = {Text(text = "PassWord")},
                   placeholder = {Text(text = "Mot de passe")},

                   )


               //je met de l'espace entre les deux TextField
               Spacer(modifier = Modifier.height(5.dp))

               //je position les deux boutons d'inscription et de connexion dans une row'
               
               Row(
                   modifier = Modifier.padding(8.dp),
                   horizontalArrangement = Arrangement.Center,


               ) {

                     //premier bouton
                      Button(
                         onClick = { /*TODO*/ } ,
                          //Rq ici il faut eviter de mettre le fillMaxWidth sur le Modifier
                          //modifier = Modifier.padding(8.dp),
                          modifier = Modifier
                              .padding(10.dp),

                          colors = ButtonDefaults.buttonColors(
                              backgroundColor = Green,
                          )

                      ) {
                          Text(
                              text = "Connexion",
                              fontWeight = FontWeight.Bold
                          )
                      }

                     //deuxieme bouton
                          Button(
                             onClick = { /*TODO*/ } ,
                              //Rq ici il faut eviter de mettre le fillMaxWidth sur le Modifier
                             modifier = Modifier
                                 .padding(10.dp),

                              colors = ButtonDefaults.buttonColors(
                                  backgroundColor = Red,
                              )
                          ) {
                              Text(
                                  text = "Inscription",
                                  fontWeight = FontWeight.Bold,

                                  )
                          }

                       }

               //je sort du Row je revient dans la column

               //je met de l'espace entre les deux boutons precedent et mon text mot de passe oublie

               Spacer(modifier = Modifier.height(4.dp))

               Text(
                   text = "Mot de Passe oublié ?",
                   style = MaterialTheme.typography.body1
               )

           }

       }
    )
    

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginUiOneTheme {
        Greeting("Android")
    }
}