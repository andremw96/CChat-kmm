package repository

interface LoginRepository {
    suspend fun register(
        name: String,
        email: String,
        pwd: String,
        publicKey: String,
        encryptedPrivKey: String,
    )
}