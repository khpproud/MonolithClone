package dev.patrick.monolithassignment.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.patrick.monolithassignment.data.repository.ReserveRepository
import dev.patrick.monolithassignment.domain.repository.IReserveRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsReserveRepository(repo: ReserveRepository): IReserveRepository
}