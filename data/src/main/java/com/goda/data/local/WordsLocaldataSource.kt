package com.goda.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.goda.data.local.models.DataResponse
import com.goda.data.local.models.Word
import java.util.*

interface IWorkLocalDatasource{
    fun addWords(words: List<Word>)
    fun getWords() :DataResponse
    fun isTableEmpty() :Boolean
}

class WordsLocalDatasource(val context: Context):IWorkLocalDatasource {

    private lateinit var db: SQLiteDatabase

   override fun addWords(words: List<Word>) {
        db = DatabaseHandler(context).writableDatabase
        db.execSQL("delete from "+ DatabaseHandler.TABLE_WORDS)
        for (word in words) {
            val values = ContentValues()
            values.put(DatabaseHandler.KEY_WORD, word.content)
            values.put(DatabaseHandler.KEY_WORD_COUNT, word.repeatedNo)
            db.insert(DatabaseHandler.TABLE_WORDS, null, values)
        }
        db.close()
    }

    override fun getWords(): DataResponse {
        db = DatabaseHandler(context).writableDatabase
        val wordsList = ArrayList<Word>()
            val selectQuery = "SELECT  * FROM ${DatabaseHandler.TABLE_WORDS}"
            val cursor = db.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val word = Word(
                        cursor.getString(1), cursor.getInt(2)
                    )
                    wordsList.add(word)
                } while (cursor.moveToNext())
            }
            return DataResponse(wordsList)
        }

    override fun isTableEmpty(): Boolean{
        db = DatabaseHandler(context).writableDatabase
        val count = "SELECT count(*) FROM "+ DatabaseHandler.TABLE_WORDS
        val cursor = db.rawQuery(count, null)
        cursor.moveToFirst()
        val rowCount = cursor.getInt(0)
        return rowCount <= 0
    }
}