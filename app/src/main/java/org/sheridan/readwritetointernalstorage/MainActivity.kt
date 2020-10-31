package org.sheridan.readwritetointernalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnSave.setOnClickListener { onSave() }

        btnView.setOnClickListener { onView() }


    }

    fun Context.showToast (text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    fun onSave() {
        val file = editFile.text.toString()
        val data = editData.text.toString()

        try {
            val fileOutputStream: FileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }

        showToast("Saved to file")
    }

    fun onView() {
        val filename = editFile.text.toString()

        if (filename.toString() != null && filename.trim() != "" ) {
            var fileInputStream: FileInputStream = openFileInput(filename)
            var inputStreamReader : InputStreamReader = InputStreamReader(fileInputStream
            )
            val bufferedReader : BufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder : StringBuilder = StringBuilder()
            var text : String? = null


            while ({text = bufferedReader.readLine(); text} () != null) {
                stringBuilder.append(text + "\n")
            }

            editData.setText(stringBuilder.toString()).toString()
        }
        else
            showToast("Name of the file cannot be blank")

    }

}
