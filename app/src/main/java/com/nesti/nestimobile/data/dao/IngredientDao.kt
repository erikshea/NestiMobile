import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.nesti.nestimobile.data.model.Ingredient

//creating the database logic, extending the SQLiteOpenHelper base class  
class IngredientDao(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "NestiShoppingList"
        private val TABLE_NAME = "ingredient"
        private val KEY_ID_INGREDIENT = "idIngredient"
        private val KEY_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID_INGREDIENT + " INTEGER PRIMARY KEY," + KEY_NAME + ")")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    fun saveOrUpdate (ingredient:Ingredient){
        if (findById(ingredient.idIngredient) == null){
            save(ingredient)
        } else {
            update(ingredient)
        }
    }

    fun save(ingredient: Ingredient):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID_INGREDIENT, ingredient.idIngredient)
        contentValues.put(KEY_NAME, ingredient.name)
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }
    //method to update data
    fun update(ingredient: Ingredient):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID_INGREDIENT, ingredient.idIngredient)
        contentValues.put(KEY_NAME, ingredient.name)

        val success = db.update(TABLE_NAME, contentValues,"idIngredient="+ingredient.idIngredient,null)
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun delete(ingredient: Ingredient):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID_INGREDIENT, ingredient.idIngredient) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_NAME,"idIngredient="+ingredient.idIngredient,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun findAll():List<Ingredient>{
        val ingredients = ArrayList<Ingredient>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        if (cursor.moveToFirst()) {
            do {
                ingredients.add(cursorToEntity(cursor))
            } while (cursor.moveToNext())
        }
        return ingredients
    }

    fun findById(id:Int): Ingredient? {
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE idIngredient=$id"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return null
        }

        var ingredient:Ingredient? = null;

        if (cursor.moveToFirst()) {
            ingredient = cursorToEntity(cursor)
        }
        return ingredient
    }

    protected fun cursorToEntity(cursor:Cursor):Ingredient{
        val idIngredient = cursor.getInt(cursor.getColumnIndex("idIngredient"))
        val name = cursor.getString(cursor.getColumnIndex("name"))
        val ingredient = Ingredient()
        ingredient.idIngredient = idIngredient
        ingredient.name = name
        return ingredient
    }
}
