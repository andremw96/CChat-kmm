package repository

interface LoginRepository {
    suspend fun register(
        email: String,
        password: String,
    )
}