package at.fh.swengb.regula.homeexercise2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var db: NotesRoomDatabase
    private val noteAdapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }

    fun saveNote(view: View) {
        val title = editTitle.text.toString()
        val content = editContent.text.toString()

        val note = Note(title, content)
        db = NotesRoomDatabase.getDatabase(this)

        val roomList = db.noteDao.findAll()

        if (db.noteDao.findNote(title) != null) {
            db.noteDao.update(note)
            noteAdapter.updateList(roomList)

            finish()
        }
        else if (title == "" && content == "" ) {
            val textStart = "Please insert a valid note!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, textStart, duration)
            toast.show()
        }
        else {
            db.noteDao.insert(note)
            noteAdapter.updateList(roomList)

            finish()
        }



    }
}
