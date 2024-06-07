package di

import org.koin.dsl.module
import repository.LoginRepository
import repository.LoginRepositoryImpl

val repositoryModule = module {
    single<LoginRepository> {
        LoginRepositoryImpl()
    }
}
