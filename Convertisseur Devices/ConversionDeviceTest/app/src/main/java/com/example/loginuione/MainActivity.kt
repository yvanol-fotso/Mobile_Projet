package com.example.loginuione


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
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



//Fonction de convertion de devices par defaut en fcfa

fun convertion(montantDollar: Double , device: String): Double{

    val valeurEchange : Double;

    if (device.equals("Euro")){

        //on sait que :  1$ =  0.92 euro ou sensiblement  a 1 donc

        valeurEchange = 0.92

        return montantDollar * valeurEchange;

    }else if(device.equals("Livre")){

        //on sait que :  1$ =  0.76 livre  donc

        valeurEchange = 0.76

        return montantDollar * valeurEchange;

    }else if(device.equals("Yen")){

        //on sait que :  1$ =  107.97 yen  donc

        valeurEchange = 107.97

        return montantDollar * valeurEchange;

    }else{
    // en FCFA

        //on sait que :  1$ =  563.63 FCFA  donc

        valeurEchange = 563.63

        return montantDollar * valeurEchange;

    }
}

@Composable
fun affichage(montant:Double,device:String){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()

    ) {

        // text pour l'affichage du resultat
        Text(
            text = " $montant  $device ",
            color = Color.Blue,
            textAlign = TextAlign.Center
        )


    }
}



@Preview(showBackground = true)
@Composable
fun LoginUiTwo(){


    //declarons une variable booleens pour le stockage l'orsque notre OutlinedField est Expand

    var mExpanded by remember { mutableStateOf(false)}

    // je creais la liste de mes devices

    val mDevices = listOf<String>("Euro","Livre","Yen","FCFA")

    //je creais ma variable mutable String pour stocker ma valeurs Device selectionnee
    var mSelectedText by remember {
        mutableStateOf("")
    }

    //je creais ma variable mutatble pour stocker le montant saisi
    var mInputTextPrice by remember {
        mutableStateOf("")
    }

    var mTextFieldsize by remember {
        mutableStateOf(Size.Zero)
    }

    //var resultat : Double = 0.0; //initialisation creation d'une variable resultat devant store la valeur de la fonction de convertion

    var resultat by remember {
        mutableStateOf(0.0)
    }

    // Up Icon When expanded (true) and Down icon when collapsed (fermeture)

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    // creation de notre Widget Coloumn Conteneur parent
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)
    ) {

        //creation de nos differents composant Interieurs

        //je met de l'espace entre le Haut de l'ecran et le text
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "CONVERTISSEUR DE DEVICES",
            fontSize = MaterialTheme.typography.h6.fontSize, // mon text aurais une police d'un titre h6
            fontWeight = FontWeight.Bold, //je met le text en gras
            textAlign = TextAlign.Center // JE Centralise mon text
        )

        //je met de l'espace entre l'image et le text
        Spacer(modifier = Modifier.height(2.dp))

        // je creais un outlinedField pour la saisi du prix
        OutlinedTextField(
                value = mInputTextPrice,
                onValueChange = { newValue -> mInputTextPrice = newValue },
                //label = {Text(text = "Saisir Le montant En Dolloars $")},
                placeholder = { Text(text = "Saisir Le montant En Dolloars $") },
                modifier = Modifier
                    .fillMaxWidth(),

                //ce champ de saisi ne doit contenir que les nombres (on active uniquement le clavier numerique)
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))  // espace entre les elements

        // on creait un deuxieme OutlinedField Pour l'attacher a notre DropDown
        
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = { newValue -> mSelectedText = newValue },
            placeholder = { Text(text = "Choisir une device ") },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    //this value is used to assign to the Dropdown the same width
                    mTextFieldsize = layoutCoordinates.size.toSize()
                },

            trailingIcon = {
                Icon(icon,"icone pour presenter l'ouverture et la fermeture du Dropdown",
                    Modifier.clickable { mExpanded = !mExpanded })
            },

        )

        DropdownMenu(
            expanded = mExpanded,
            //recuperation du choix de convertion
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.fillMaxWidth(),

        ) {
            mDevices.forEach { label->
                DropdownMenuItem(onClick = { 
                    mSelectedText = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }

        }

        //bouton de convertion

        Button(
            onClick = {
                 resultat = convertion(mInputTextPrice.toDouble(), mSelectedText.toString())

                },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = Blue, //ici je change la couleur de mon bouton
                contentColor = White,
            )


        ) {
            Text(
                text = "Convertir",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                )
            }



        //je met de l'espace entre les deux TextField
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "MONTANT CONVERTIS EN :",
            //fontSize = MaterialTheme.typography.h6.fontSize, // mon text aurais une police d'un titre h6
            //fontWeight = FontWeight.Bold, //je met le text en gras
            //textAlign = TextAlign.Center, // JE Centralise mon text
            //textDecoration = TextDecoration.Underline, // sa souligne Mon Texte

            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline,
            fontFamily = FontFamily.Serif,

        )

        //je met de l'espace entre les deux TextField
        Spacer(modifier = Modifier.height(2.dp))

        //appel du composant affichage pour afficher le resultat
        affichage(resultat,mSelectedText)


    }


    }




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginUiOneTheme {
        Greeting("Android")
    }
}