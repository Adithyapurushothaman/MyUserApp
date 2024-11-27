package com.example.myuserapp.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myuserapp.data.room.User
import com.example.myuserapp.ui.viewmodel.UserViewModel

@Composable
fun LoginScreen(viewModel: UserViewModel,navController: NavHostController, onLoginSuccess: ()->Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())

        Row {
            Button(onClick = {
                viewModel.validateUser(username, password) { isValid ->
                    if (isValid) {
                        onLoginSuccess()
                    } else {
                        errorMessage = "Invalid username or password"
                    }
                }
            }) {
                Text("Login")
            }

            Button(onClick = { navController.navigate("signup") })
            {
                Text("SignUp")
            }
        }


        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
    }
}
@Composable
fun SignupScreen(viewModel: UserViewModel, navController: NavHostController, onSignupSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email or Phone") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())

        Button(onClick = {
            val user = User(username = username, email = email, password = password)
            viewModel.insertUser(user)
            navController.popBackStack()
            onSignupSuccess()
        }) {
            Text("Sign Up")
        }
    }
}
