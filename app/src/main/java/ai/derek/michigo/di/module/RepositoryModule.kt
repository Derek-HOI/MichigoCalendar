package ai.derek.michigo.di.module

import ai.derek.michigo.business.ScheduleRepository
import ai.derek.michigo.business.ScheduleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: ScheduleRepositoryImpl): ScheduleRepository

}
