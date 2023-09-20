package com.example.myaffirmations.JournalScreen.DI

import android.app.Application
import androidx.room.Room
import com.example.myaffirmations.JournalScreen.DB.NoteDAO
import com.example.myaffirmations.JournalScreen.DB.NoteDatabase
import com.example.myaffirmations.JournalScreen.Repository.IGetNoteRepository
import com.example.myaffirmations.JournalScreen.Repository.noteRepository
import com.example.myaffirmations.JournalScreen.Usecase.AddNotes.IAddNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.DeleteNotes.IDeleteNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.GetNotesByID.IGetNotebyIdUsecase
import com.example.myaffirmations.JournalScreen.Usecase.GetNotesByID.IGetNotesByIDUsecase
import com.example.myaffirmations.JournalScreen.Usecase.GetNotes.IGetNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.AddNotes.addNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.DeleteNotes.deleteNotesUsecase
import com.example.myaffirmations.JournalScreen.Usecase.GetNotes.getNotesUsecase
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
        fun provideNoteGetUsecase(getnoteusecase: getNotesUsecase): IGetNotesUsecase

        @Binds
        @Singleton
        fun provideNoteaddUsecase(addNotesUsecase: addNotesUsecase): IAddNotesUsecase

        @Binds
        @Singleton
        fun provideNotebyIdUsecase(getNotesByIDUsecase: IGetNotesByIDUsecase): IGetNotebyIdUsecase

        @Binds
        @Singleton
        fun provideNotedeleteUsecase(deletenotesusecase: deleteNotesUsecase): IDeleteNotesUsecase
    }
}