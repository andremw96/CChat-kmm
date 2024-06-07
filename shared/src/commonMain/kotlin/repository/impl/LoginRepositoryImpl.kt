package repository.impl

import dev.gitlive.firebase.FirebaseException
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.database.FirebaseDatabase
import dev.gitlive.firebase.installations.FirebaseInstallations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
import repository.Result

class LoginRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseInstallations: FirebaseInstallations,
) : LoginRepository {
    override suspend fun register(
        name: String,
        email: String,
        password: String,
        publicKey: String,
        encryptedPrivKey: String
    ): Flow<Result<AuthResult>> {
        return flow {
            emit(Result.Loading)

            try {
                val registeredUser = firebaseAuth.createUserWithEmailAndPassword(email, password)
                val deviceToken = firebaseInstallations.getToken(true)

                if (registeredUser.user != null) {
                    val currentUserId = firebaseAuth.currentUser?.uid
                    if (currentUserId != null) {
                        val storeUserDataReference =
                            firebaseDatabase.reference().child(PATH_USERS).child(currentUserId)
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
                        emit(Result.Success(registeredUser))
                    } else {
                        emit(Result.Error(Exception("currentUserId not found")))
                    }
                } else {
                    emit(Result.Error(Exception("registered user not found")))
                }
            } catch (firebaseException: FirebaseException) {
                emit(Result.Error(firebaseException))
            }
        }
    }

    override suspend fun loginUserAccount(
        email: String,
        password: String
    ): Flow<Result<AuthResult>> {
        return flow {
            emit(Result.Loading)
            try {
                val user = firebaseAuth.signInWithEmailAndPassword(email, password)
                if (user.user != null) {
                    val onlineUserId = firebaseAuth.currentUser?.uid
                    val deviceToken = firebaseInstallations.getToken(true)
                    if (onlineUserId != null) {
                        firebaseDatabase.reference()
                            .child(PATH_USERS)
                            .child(onlineUserId)
                            .child(FIELD_USER_DEVICE_TOKEN)
                            .setValue(deviceToken)
                        emit(Result.Success(user))
                    } else {
                        emit(Result.Error(Exception("currentUserId not found")))
                    }
                } else {
                    emit(Result.Error(Exception("registered user not found")))
                }
            } catch (firebaseException: FirebaseException) {
                emit(Result.Error(firebaseException))
            }
        }

    }
}