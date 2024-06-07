package repository.impl

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.database.FirebaseDatabase
import dev.gitlive.firebase.installations.FirebaseInstallations
import model.FirebaseDatabaseField.FIELD_USER_DEFAULT_PROFILE
import model.FirebaseDatabaseField.FIELD_USER_DEVICE_TOKEN
import model.FirebaseDatabaseField.FIELD_USER_NAME
import model.FirebaseDatabaseField.FIELD_USER_NAME_LOWERCASE
import model.FirebaseDatabaseField.FIELD_USER_PRIVATE_KEY
import model.FirebaseDatabaseField.FIELD_USER_PUBLIC_KEY
import model.FirebaseDatabaseField.FIELD_USER_STATUS
import model.FirebaseDatabaseField.FIELD_USER_THUMB_IMAGE
import model.FirebaseDatabasePath.PATH_USERS
import repository.LoginRepository

class LoginRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseInstallations: FirebaseInstallations,
) : LoginRepository {
    override suspend fun register(
        name: String,
        email: String,
        pwd: String,
        publicKey: String,
        encryptedPrivKey: String
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, pwd)
        val deviceToken = firebaseInstallations.getToken(true)

        val currentUserId = firebaseAuth.currentUser?.uid
        if (currentUserId != null) {
            val storeUserDataReference = firebaseDatabase.reference().child(PATH_USERS).child(currentUserId)
            storeUserDataReference.apply {
                child(FIELD_USER_NAME).setValue(name)
                child(FIELD_USER_NAME_LOWERCASE).setValue(name.lowercase())
                child(FIELD_USER_PUBLIC_KEY).setValue(publicKey)
                child(FIELD_USER_PRIVATE_KEY).setValue(encryptedPrivKey)
                child(FIELD_USER_STATUS).setValue("Hello World, I am using CChat")
                child(FIELD_USER_DEFAULT_PROFILE).setValue("default_profile")
                child(FIELD_USER_DEVICE_TOKEN).setValue(deviceToken)
                child(FIELD_USER_THUMB_IMAGE).setValue("default_image")
            }
        }
    }
}