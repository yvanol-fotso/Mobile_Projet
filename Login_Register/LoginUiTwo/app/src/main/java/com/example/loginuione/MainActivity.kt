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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

               Text(
                   text = "CREATION D'UN NOUVEAU MOT DE PASSE",
                   fontSize =  MaterialTheme.typography.h6.fontSize, // mon text aurais une police d'un titre h6
                   fontWeight = FontWeight.Bold, //je met le text en gras
                   textAlign = TextAlign.Center // JE Centralise mon text
               )

               //je met de l'espace entre l'image et le text
               Spacer(modifier = Modifier.height(2.dp))

               //creation des Deux Input / TextField

               var textState by remember { mutableStateOf("") }
               
               OutlinedTextField(
                   value = textState,
                   onValueChange = {newValue -> textState = newValue},
                   //label = {Text(text = "Nouveau mot de passe")},
                   placeholder = {Text(text = "Nouveau mot de passe")},

                   //placement de l'icon dans le placeholder avant le text
                   leadingIcon = {
                       IconButton(onClick = { /*TODO*/ }) {
                           Icon(imageVector = Icons.Filled.Lock, contentDescription ="Icon Lock ou Cadenas pour" )

                       }
                   },

                   //placement de l'icon apres le Text Du Placeholder
                   trailingIcon = {
                       IconButton(onClick = { /*TODO*/ }) {
                           Icon(imageVector = Icons.Filled.Info, contentDescription ="Icon Lock ou Cadenas pour" )

                       }
                   }

               )

               //je met de l'espace entre les deux TextField
               Spacer(modifier = Modifier.height(6.dp))

               OutlinedTextField(
                   value = textState,
                   onValueChange = {newValue -> textState = newValue},
                   //label = {Text(text = "confirmer le mot de passe")},
                   placeholder = {
                       Text(
                           text = "confirmer le mot de passe",
                           maxLines = 1,//ici le coupe le texte vu que c'est trop long pour  entrer dans le placeholder et comme sa occupait deux ligne j'ai couper sur la premier ligne
                           //overflow = TextOverflow.Visible
                       )

                   },
                   singleLine = true,//le texte  saisi par le user ici sera contenue sur une seule ligne
                   maxLines = 1, // nombre de ligne devant contenir le text de saisi



                   //placement de l'icon dans le placeholder avant le text
                   leadingIcon = {
                       IconButton(onClick = { /*TODO*/ }) {
                           Icon(imageVector = Icons.Filled.Lock, contentDescription ="Icon Lock ou Cadenas pour" )

                       }
                   },

                   //placement de l'icon apres le Text Du Placeholder
                   trailingIcon = {
                       IconButton(onClick = { /*TODO*/ }) {
                           Icon(imageVector = Icons.Filled.Info, contentDescription ="Icon Lock ou Cadenas pour" )

                       }
                   }

                   )


               //je met de l'espace entre les deux TextField
               Spacer(modifier = Modifier.height(5.dp))


               //bouton

               Button(
                   onClick = { /*TODO*/ } ,
                   //Rq ici il faut eviter de mettre le fillMaxWidth sur le Modifier
                   //modifier = Modifier.padding(8.dp),

                   modifier = Modifier
                       .padding(10.dp)
                       .fillMaxWidth(),

                   colors = ButtonDefaults.buttonColors(
                   //backgroundColor = Green, //ici je change la couleur de mon bouton
               )

               ) {
                   Text(
                       text = "Enregistrer",
                       fontWeight = FontWeight.Bold
                   )
               }

               //je met de l'espace entre les deux boutons precedent et mon text mot de passe oublie

               Spacer(modifier = Modifier.height(4.dp))

               Text(
                   text = "Se Connecter",
                   style = MaterialTheme.typography.body1
               )

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