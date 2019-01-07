package at.fh.swengb.regula.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * FROM Note ORDER BY title, content")
    fun findAll(): List<Note>

    @Query("SELECT title FROM Note WHERE title = :title")
    fun findNote(title: String): String?

    @Query("DELETE FROM Note WHERE title = :title")
    fun deleteNote(title: String)

    @Query("SELECT COUNT (*) FROM Note")
    fun countAll(): Int // für Testzwecke

}