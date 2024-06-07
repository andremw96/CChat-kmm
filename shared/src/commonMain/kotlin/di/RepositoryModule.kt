package di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.FirebaseDatabase
import dev.gitlive.firebase.database.database
import org.koin.dsl.module
import repository.LoginRepository
import repository.impl.LoginRepositoryImpl

val repositoryModule = module {
    single<FirebaseAuth> {
        Firebase.auth
    }

    single<FirebaseDatabase> {
        Firebase.database
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get(), get())
    }
}
