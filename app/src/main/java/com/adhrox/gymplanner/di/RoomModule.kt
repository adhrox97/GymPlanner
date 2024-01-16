package com.adhrox.gymplanner.di

import android.content.Context
import androidx.room.Room
import com.adhrox.gymplanner.data.PlanRepository
import com.adhrox.gymplanner.data.database.PlanDatabase
import com.adhrox.gymplanner.data.database.dao.PlanDao
import com.adhrox.gymplanner.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val PLAN_DATABASE_NAME = "plan_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PlanDatabase::class.java, PLAN_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePlanDao(db: PlanDatabase) = db.getPlanDao()

    @Provides
    fun provideRepository(planDao: PlanDao): Repository{
        return PlanRepository(planDao)
    }

}