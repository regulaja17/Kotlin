package at.fh.swengb.regula.homeexercise2

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun saveUser(view: View) {
        val name = editName.text.toString()
        val age = editAge.text.toString().toIntOrNull() ?: 0

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val startNoteListActivity = Intent(this, NoteListActivity::class.java)

        sharedPreferences.edit().putString("name", name).apply()
        sharedPreferences.edit().putInt("age", age).apply()


        startActivity(startNoteListActivity)
    }

    fun itsMe(view: View) {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val startNoteListActivity = Intent(this, NoteListActivity::class.java)

        sharedPreferences.edit().putString("name", "Janik").apply()
        sharedPreferences.edit().putInt("age",  21).apply()

        //Toast für mich
        val textStart = "Welcome back Janik!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, textStart, duration)
        toast.show()

        startActivity(startNoteListActivity)
    }
}
