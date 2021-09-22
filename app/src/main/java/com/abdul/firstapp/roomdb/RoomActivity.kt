import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.abdul.firstapp.R
import com.abdul.firstapp.roomdb.InsertNoteAsyncTask
import com.abdul.firstapp.roomdb.Word
import com.abdul.firstapp.roomdb.WordDao
import com.abdul.firstapp.roomdb.WordDb

class RoomActivity : AppCompatActivity() {

    lateinit var etTitle:EditText
    lateinit var wordDao: WordDao
    lateinit var wordDb: WordDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        etTitle = findViewById(R.id.etWord)
        wordDb = WordDb.getNoteDb(this)
        wordDao = wordDb.wordDao()

    }

    fun roomHandler(view: View) {
        var word: String = etTitle.text.toString()
        insertWordAsync(Word(word))
    }

    private fun insertWordAsync(word: Word) {
        var insertTask = InsertNoteAsyncTask(word,wordDao)
        insertTask.execute()
    }
}