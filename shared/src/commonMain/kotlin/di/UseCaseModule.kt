package di

import org.koin.dsl.module
import usecase.GenerateUserKey
import usecase.impl.GenerateUserKeyImpl
import usecase.RegisterDataValidator
import usecase.RegisterUser
import usecase.impl.RegisterDataValidatorImpl
import usecase.impl.RegisterUserImpl

val useCaseModule = module {
    single<RegisterUser> {
        RegisterUserImpl(get())
    }

    single<RegisterDataValidator> {
        RegisterDataValidatorImpl()
    }

    single<GenerateUserKey> {
        GenerateUserKeyImpl(get())
    }
}
