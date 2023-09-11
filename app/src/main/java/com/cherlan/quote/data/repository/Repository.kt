package com.cherlan.quote.data.repository

import com.cherlan.quote.data.model.Quote
import com.google.firebase.auth.FirebaseUser

interface Repository{

    val currentUser: FirebaseUser?

    suspend fun loginWithEmailAndPassword(email: String, password: String): FirebaseUser?
    suspend fun createUserWithEmailAndPassword(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser?

    suspend fun getQuotes(): List<Quote>
}