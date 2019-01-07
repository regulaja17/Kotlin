package at.fh.swengb.regula.homeexercise2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private val noteAdapter = NoteAdapter()
    lateinit var db: NotesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        // Log.i("MyActivity", "onCreate")
        // Log.i("Test", "${db.noteDao.countAll().toString()}") noch zu früh
        recycler_view.adapter = noteAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("name", null)
        val savedInt = sharedPreferences.getInt("age", 0)

        textUser.text = "Notes for " + savedString + ", " + savedInt

        db = NotesRoomDatabase.getDatabase(this)

        noteAdapter.updateList(db.noteDao.findAll())

        Log.i("Test", "${db.noteDao.countAll().toString()}")
            // für Testzwecke, wollte ursprünglich eine ID definieren
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.updateList(db.noteDao.findAll())
    }

    fun addNote(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)

    }

    fun deleteNote(view: View) {
        val title = editTitle.text.toString()
        if (db.noteDao.findNote(title) != null) {
            db.noteDao.deleteNote(title)
            noteAdapter.updateList(db.noteDao.findAll())
        }
    }
}
