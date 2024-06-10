package di

import org.koin.dsl.module
import usecase.RegisterUser
import usecase.RegisterUserImpl

val useCaseModule = module {
    single<RegisterUser> {
        RegisterUserImpl(get())
    }
}
