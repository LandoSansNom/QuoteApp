package com.cherlan.quote.data.repository

import com.cherlan.quote.data.model.Quote
import com.cherlan.quote.data.remote.ApiCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiCall: ApiCall,
    private val firebaseAuth: FirebaseAuth
) : Repository {

    override suspend fun loginWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        firebaseAuth.signInWithEmailAndPassword(email, password).await().user?.let {
            return it
        }
        return null
    }

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun createUserWithEmailAndPassword(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser? {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await().user?.let {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName("$firstName $lastName")
                .build()
            it.updateProfile(profileUpdates).await()
            return it
        }

        return null
    }

    override suspend fun getQuotes(): List<Quote> {
        return apiCall.getGuotes()
    }

}