package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.interfaces.TestRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.jetbrains.kmpapp.repository.TestRepositoryImpl
import com.jetbrains.kmpapp.viewmodels.TestViewModel

val appModule = module {
    single {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens("abc123", "xyz111")
                    }
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
            }
        }
    }
    single<TestRepository> {
        TestRepositoryImpl(get())
    }
}
val screenModelsModule = module {
    factoryOf(::TestViewModel)
//    factory { TestViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(
            appModule,
            screenModelsModule,
        )
    }
}
