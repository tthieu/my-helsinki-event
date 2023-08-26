package com.tthieu.myhelsinki.common.di

import com.tthieu.myhelsinki.common.data.MyHelsinkiEventRepository
import com.tthieu.myhelsinki.common.domain.repositories.EventRepository
import com.tthieu.myhelsinki.common.utils.CoroutineDispatchersProvider
import com.tthieu.myhelsinki.common.utils.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindEventRepository(repository: MyHelsinkiEventRepository): EventRepository

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider): DispatchersProvider

}