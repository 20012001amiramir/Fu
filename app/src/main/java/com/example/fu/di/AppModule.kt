package com.example.fu.di

import android.content.Context
import toothpick.config.Module

class AppModule(context: Context) : Module() {

    init {
        bind(Context::class.java).toInstance(context)
//
//
//        bind(String::class.java)
//            .withName(Hostname::class.java)
//            .toInstance(BuildConfig.HOSTNAME)
//
//        bind(String::class.java)
//            .withName(ServerApiUrl::class.java)
//            .toInstance(BuildConfig.SERVER_API_URL)
//
//        bind(String::class.java)
//            .withName(AuthApiUrl::class.java)
//            .toInstance(BuildConfig.AUTH_API_URL)
//
//        bind(Moshi::class.java)
//            .toProvider(MoshiProvider::class.java)
//
//        bind(PrimitiveWrapper::class.java)
//            .withName(UseMockApi::class.java)
//            .toInstance(PrimitiveWrapper(BuildConfig.USE_MOCK_API))
//
//        bind(PrimitiveWrapper::class.java)
//            .withName(TrustAllCertificatesQualifier::class.java)
//            .toInstance(PrimitiveWrapper(BuildConfig.TRUST_ALL_CERTIFICATES))
//
//        bind(OkHttpClient::class.java)
//            .withName(OkHttpQualifier::class.java)
//            .toProvider(OkHttpProvider::class.java)
//            .providesSingleton()
//
//        bind(OkHttpClient::class.java)
//            .withName(AuthOkHttpQualifier::class.java)
//            .toProvider(AuthOkHttpProvider::class.java)
//            .providesSingleton()
//
//        bind(Api::class.java)
//            .toProvider(ApiProvider::class.java)
//            .providesSingleton()
//
//        bind(AuthApi::class.java)
//            .toProvider(AuthApiProvider::class.java)
//            .providesSingleton()
//
//        bind(AcademicTermInteractors::class.java)
//        bind(HomeworkInteractors::class.java)
//        bind(PerformanceInteractors::class.java)
//        bind(ScheduleInteractors::class.java)
//        bind(SessionInteractors::class.java)
//        bind(SettingsInteractors::class.java)
//        bind(SettingSendAverageInteractor::class.java)
//        bind(StoryArchiveRepository::class.java)
//
//        bind(AcademicTermRepository::class.java).singleton()
//        bind(HomeworkRepository::class.java).singleton()
//        bind(MarkRepository::class.java).singleton()
//        bind(OAuthRepository::class.java).singleton()
//        bind(ProfileRepository::class.java).singleton()
//        bind(ScheduleRepository::class.java)
//        bind(SessionRepository::class.java)
//        bind(SettingsRepository::class.java).singleton()
//        bind(LessonRepository::class.java)
//        bind(SettingSendRepository::class.java)
//        bind(JournalRepository::class.java).singleton()
//        bind(StoryRepository::class.java).singleton()
//
//        bind(LocalStorage::class.java).singleton()
//        bind(TokenRepository::class.java).singleton()
//
//        bind(TokenInterceptor::class.java).singleton()
//        bind(TokenAuthenticator::class.java).singleton()
//        bind(RequestIdInterceptor::class.java).singleton()
//        bind(LanguageInterceptor::class.java).singleton()
//
//        bind(ErrorHandler::class.java).singleton()
    }
}