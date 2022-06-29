package ramizbek.aliyev.yakuniy.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDBService {

    companion object {
        const val DB_NAME = "Yakuniy"
        const val DB_VERSION = 1

        const val TABLE_NAME = "jadval"
        const val ID = "id"
        const val USERNAME = "userName"
        const val IMAGE = "rasim"
        const val PASSWORD = "password"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME ($ID integer not null primary key autoincrement unique, $USERNAME text not null,$IMAGE text not null, $PASSWORD )"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    override fun createUser(user: User) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USERNAME, user.userName)
        contentValues.put(IMAGE, user.image)
        contentValues.put(PASSWORD, user.password)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun readUser(): ArrayList<User> {
        val list = ArrayList<User>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {

                val user = User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )

                list.add(user)

            } while (cursor.moveToNext())
        }

        return list
    }

}
