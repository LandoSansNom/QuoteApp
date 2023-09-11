package com.cherlan.quote.utils

import java.util.regex.Pattern


fun String.isValidPassword(): Boolean {
    val letter: Pattern = Pattern.compile("[a-zA-Z]")
    val digit: Pattern = Pattern.compile("[0-9]")
    val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")

    val hasLetter = letter.matcher(this)
    val hasDigit = digit.matcher(this)
    val hasSpecial = special.matcher(this)

    return if (this.isEmpty()) {
        false
    } else !(this.length < 8 || !hasLetter.find() || !hasDigit.find() || !hasSpecial.find())
}

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
        .matches()
}