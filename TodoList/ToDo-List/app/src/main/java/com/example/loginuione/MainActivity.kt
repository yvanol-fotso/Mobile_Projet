package com.example.loginuione

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
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

@Composable
fun LoginUiOne(){

    Scaffold(
        topBar = {

            TopAppBar(
                title = { Text(text = "Longin UI") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")

                    }
                }

            )
        },

       content = {

           Column(
               verticalArrangement = Arrangement.spacedBy(8.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .fillMaxHeight()
                   .padding(16.dp)
           ) {
               Button(
                   onClick = { /*TODO*/ } ,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(16.dp)) {
                   Text(text = "Connexion")

               }

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