package di

import org.koin.dsl.module
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
}
