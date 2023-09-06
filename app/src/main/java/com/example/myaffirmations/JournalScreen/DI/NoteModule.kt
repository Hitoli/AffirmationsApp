package com.example.myaffirmations.JournalScreen.DI

import android.app.Application
import androidx.room.Room
import com.example.myaffirmations.JournalScreen.DB.NoteDAO
import com.example.myaffirmations.JournalScreen.DB.NoteDatabase
import com.example.myaffirmations.JournalScreen.Repository.IGetNoteRepository
import com.example.myaffirmations.JournalScreen.Repository.noteRepository
import com.example.myaffirmations.JournalScreen.Usecase.IDeleteNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.IGetNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.deleteNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.getNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.noteUsecase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoteModule {

    @Provides
    @Singleton
    fun noteDatabaseRoom(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,NoteDatabase::class.java,"Note_DB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideChannelDao(noteDatabase:NoteDatabase):NoteDAO{
        return noteDatabase.noteDao
    }



    @Module
    @InstallIn(SingletonComponent::class)
    interface NoteMod{
        @Binds
        @Singleton
        fun provideNoteRepo(noterepo:noteRepository):IGetNoteRepository

        @Binds
        @Singleton
        fun provideNoteGetUsecase(getnoteusecase:getNotesUsecase):IGetNotesUsecase

        @Binds
        @Singleton
        fun provideNotedeleteUsecase(deletenotesusecase: deleteNotesUsecase):IDeleteNotesUsecase
    }
}