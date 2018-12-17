package com.example.candi.cuentacalorias

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class AdminSQlite (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version)  {

    private lateinit var bd: SQLiteDatabase


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table if not exists alimentos(descripcion text primary key, calorias int)")
        db?.execSQL("create table if not exists persona(nombre text primary key, caloriasDia int)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS alimentos ");
        db?.execSQL("DROP TABLE IF EXISTS persona ");
        onCreate(db);
    }

    //Metodos de la tabla Alimentos

    fun leerSqlite():ArrayList<Calorias>{
        bd = writableDatabase
        val fila = bd.rawQuery("select * from alimentos", null)
        var datos : ArrayList<Calorias> = ArrayList()
        if ( fila.moveToFirst() ) {
            do {
                datos.add(Calorias(fila.getString(0),fila.getInt(1)))
            } while ( fila.moveToNext() )
        }
        bd.close()
        return datos
    }

    fun insertar( alimento:String, caloria:Int ) {
        bd = writableDatabase
        val registro = ContentValues()
        registro.put("descripcion", alimento)
        registro.put("calorias", caloria)
        bd.insert("alimentos", null, registro)
        bd.close()
    }

    fun borrarBD( alimento:String ):Boolean{
        bd = writableDatabase
        val cant = bd.delete("alimentos", "descripcion= '${alimento}'", null)
        bd.close()
        return cant == 1
    }

    //Metodos de la tabla perosna

    fun creaPersona(){
        try {
            bd = writableDatabase
            val registro = ContentValues()
            registro.put("nombre", "persona")
            registro.put("caloriasDia", 0)
            bd.insertOrThrow("persona", null, registro)
            bd.close()
        }catch (ex: Exception) {

        }
    }

    fun creaPerfil(){
        try {
            bd = writableDatabase
            val registro = ContentValues()
            registro.put("nombre", "persona2")
            registro.put("caloriasDia", 0)
            bd.insertOrThrow("persona", null, registro)
            bd.close()
        }catch (ex: Exception) {
            Log.d("valen" , "hola")
        }
    }

    fun consultaPersona ():Int {
        var caloria: Int
        bd = writableDatabase
        val fila = bd.rawQuery("select caloriasDia from persona where nombre= 'persona'", null)
        if ( fila.moveToFirst() ) {
            caloria = fila.getInt(0)
        } else {
            caloria = -1
        }
        bd.close()
        return caloria
    }

    fun consultaPerfil ():Int {
        var caloria: Int
        bd = writableDatabase
        val fila = bd.rawQuery("select caloriasDia from persona where nombre= 'persona2'", null)
        if ( fila.moveToFirst() ) {
            caloria = fila.getInt(0)
        } else {
            caloria = -1
        }
        bd.close()
        return caloria
    }

    fun actualizaPersona ( caloria:Int ):Boolean {
        var newCaloria: Int
        bd = writableDatabase
        val registro = ContentValues()
        val fila = bd.rawQuery("select caloriasDia from persona where nombre= 'persona'", null)
        if ( fila.moveToFirst() ) {
            newCaloria = fila.getInt(0) + caloria
        } else {
            newCaloria = fila.getInt(0)
        }
        registro.put("caloriasDia", newCaloria)
        val cant = bd.update("persona", registro, "nombre= 'persona'", null)
        bd.close()
        return cant == 1
    }

    fun pasarDia():Boolean{
        bd = writableDatabase
        val registro = ContentValues()
        registro.put("caloriasDia", 0)
        val cant = bd.update("persona", registro, "nombre= 'persona'", null)
        bd.close()
        return cant == 1
    }

    fun insertaPerfil( caloriasDia: Int ): Boolean{
        /*var newCaloria: Int
        bd = writableDatabase
        val registro = ContentValues()
        val fila = bd.rawQuery("select caloriasDia from persona where nombre= 'persona2'", null)
        if ( fila.moveToFirst() ) {
            newCaloria = fila.getInt(0) + caloriasDia
        } else {
            newCaloria = fila.getInt(0)
        }
        registro.put("caloriasDia", newCaloria)
        val cant = bd.update("persona", registro, "nombre= 'persona2'", null)
        bd.close()
        return cant == 1*/


        bd = writableDatabase
        val registro = ContentValues()
        registro.put("nombre", "persona2")
        registro.put("caloriasDia", caloriasDia)
        val cant = bd.update("persona", registro, "nombre= 'persona2'", null)
        bd.close()
        return cant == 1
    }


}