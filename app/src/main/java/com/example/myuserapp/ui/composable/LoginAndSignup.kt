package com.example.myuserapp.ui.composable

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myuserapp.data.room.User
import com.example.myuserapp.ui.viewmodel.UserViewModel


@Composable
fun LoginScreen(viewModel: UserViewModel,navController: NavHostController, onLoginSuccess: ()->Unit) {

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }


    Card(
        modifier = Modifier
            .padding(30.dp,200.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xfff0cc9f))
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("Username") }
            )


            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation())


            Row(modifier = Modifier.padding(20.dp,20.dp)) {
                Button(onClick = {
                    val isValid = viewModel.validateUser(email,password)
                    Log.e("$isValid", "value of isValid")
                    if (isValid){
                        onLoginSuccess()
                    }else {
                        Toast.makeText(context, "User Credentials are not matching", Toast.LENGTH_SHORT).show()
                    }
                },
                ) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.width(50.dp))

                Button(
                    onClick = { navController.navigate("signup") })
                {
                    Text("SignUp")
                }
            }

            Text(
                text = "Forgot Password?",
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate("updateUser") }
            )


            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
        }

    }
}
@Composable
fun SignupScreen(viewModel: UserViewModel, navController: NavHostController, onSignupSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(30.dp,140.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xfff0cc9f))
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("Username") }
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("Email or Phone") }
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation())

            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(100.dp),
                onClick =
                {
                val user = User(username = username, email = email, password = password)
                viewModel.insertUser(user)
                navController.popBackStack()
                onSignupSuccess()
            }) {
                Text("Sign Up")
            }
        }

    }
}

@Composable
fun UpdatePasswordScreen(viewModel: UserViewModel, navController: NavHostController,onUpdateSuccess: () -> Unit) {

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var newPassword by remember {
        mutableStateOf("")
    }
    var errorMessage by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .padding(30.dp,140.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xfff0cc9f))
    ){
        Column(modifier = Modifier.padding(16.dp)) {

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("Email") }
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("Current Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            TextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                modifier = Modifier.padding(10.dp,20.dp),
                label = { Text("New Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(100.dp),
                onClick = {
                    val isValid = viewModel.validateUser(email,password)
                    if (isValid.equals(true)){
                        viewModel.updateUser(email, newPassword)
                        onUpdateSuccess()
                        Toast.makeText(context, "User updated successfully", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(context, "User Credentials are not matching", Toast.LENGTH_SHORT).show()
                    }

//                    { isValid ->
//                        run {
//                            if (isValid) {
//                                viewModel.updateUser(email, newPassword)
//                                onUpdateSuccess()
//                                Toast.makeText(context, "User updated successfully", Toast.LENGTH_SHORT).show()
//                                navController.popBackStack()
//                            }
//                            else{
//                               errorMessage = "Invalid username or password"
//                            }
//                        }
//
//                    }
            }) {
                Text("Submit")
            }
        }

    }

}

