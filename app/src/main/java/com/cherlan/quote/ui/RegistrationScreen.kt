package com.cherlan.quote.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cherlan.quote.R
import com.cherlan.quote.data.repository.Repository
import com.cherlan.quote.ui.components.ButtonComponent
import com.cherlan.quote.ui.components.DividerTextComponent
import com.cherlan.quote.ui.components.HeadingTextComponent
import com.cherlan.quote.ui.components.MyTextFieldComponent
import com.cherlan.quote.ui.components.PasswordTextFieldComponent
import com.cherlan.quote.utils.isValidEmail
import com.cherlan.quote.utils.isValidPassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(context: Context,repository: Repository) {
    val firstNameState = remember { mutableStateOf("") }
    val lastNameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordConfirmState = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            HeadingTextComponent(value = stringResource(id = R.string.create_account))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource = painterResource(id = R.drawable.person),
                textValue = firstNameState.value
            ) { newValue -> firstNameState.value = newValue }

            Spacer(modifier = Modifier.height(3.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.person),
                textValue = lastNameState.value
            ) { newValue -> lastNameState.value = newValue }
            Spacer(modifier = Modifier.height(3.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                textValue = emailState.value
            ) { newValue -> emailState.value = newValue }
            Spacer(modifier = Modifier.height(3.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.password),
                password = passwordState.value,
                login = false,
                onValueChanged = { newValue -> passwordState.value = newValue }
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.confirm_password),
                painterResource = painterResource(id = R.drawable.password),
                password = passwordConfirmState.value,
                login = false,
                onValueChanged = { newValue -> passwordConfirmState.value = newValue }
            )

            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(value = stringResource(id = R.string.register)) {
                val firstName: String = firstNameState.value
                val lastName: String = lastNameState.value
                val email: String = emailState.value
                val password: String = passwordState.value
                val passwordConfirm: String = passwordConfirmState.value

                if (firstName.isEmpty()) {
                    return@ButtonComponent
                }
                if (lastName.isEmpty()) {
                    return@ButtonComponent
                }
                if (!email.isValidEmail()) {
                    return@ButtonComponent
                }
                if (!password.isValidPassword()) {
                    return@ButtonComponent
                }
                if (passwordConfirm != password) {
                    return@ButtonComponent
                }

                CoroutineScope(Dispatchers.Main).launch {
                    repository.createUserWithEmailAndPassword(firstName, lastName, email, password)
                        ?.let {
                            Toast.makeText(context, "User created", Toast.LENGTH_SHORT).show()
                        }
                }
            }

            DividerTextComponent()


        }

    }
}