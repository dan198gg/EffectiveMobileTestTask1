package ru.mobile.effectivemobiletesttask.ui


import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.mobile.data.repository.MobileRepossitory
import ru.mobile.domain.MobileRepositoryImpl
import ru.mobile.effectivemobiletesttask.ui.loginScreens.LoginsViewModel
import ru.mobile.effectivemobiletesttask.ui.menuscreens.CoursesViewModel

val appModule = module {
    factory<MobileRepossitory> { MobileRepositoryImpl(get()) }
    viewModel { CoursesViewModel(get()) }
    viewModel { LoginsViewModel(get(), get()) }
}