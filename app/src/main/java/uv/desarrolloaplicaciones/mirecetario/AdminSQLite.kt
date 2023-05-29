import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory

class AdminSQLite (context: Context, name: String, factory: CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table recetas (codigo integer primary key autoincrement, nombre text, porciones integer, ingredientes text, instrucciones text, categoria text, sitios text, imagen text)");
        db.execSQL("create table usuarios (codigoUsuario integer primary key autoincrement, username text, password text, rol text)");
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion:Int, newversion: Int) {
        db.execSQL("DROP TABLE IF EXISTS recetas");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db)
    }
}