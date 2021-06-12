import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.nesti.nestimobile.data.model.Ingredient
import com.nesti.nestimobile.lib.ApplicationConfiguration
import com.nesti.nestimobile.lib.NestiMobileApplication

/**
 * Data access layer for ingredients in shopping list
 * @param context activity context
 */
class IngredientDao(context: Context): SQLiteOpenHelper(
    context,
    (context.applicationContext as NestiMobileApplication).configuration.getNode("localDatabase/@name").stringValue,
    null,
    (context.applicationContext as NestiMobileApplication).configuration.getNode("localDatabase/@version").stringValue.toInt())
{
    val tableName = Ingredient::class.simpleName
    val idColumn = Ingredient::idIngredient.name
    val nameColumn = Ingredient::name.name
    val isCheckedColumn = Ingredient::isChecked.name

    /**
     * Called only once when table is first created
     * @param db connection to database
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = (
                "CREATE TABLE $tableName($idColumn INTEGER PRIMARY KEY, $nameColumn TEXT, $isCheckedColumn INTEGER)")
        db?.execSQL(CREATE_TABLE)
    }

    /**
     * Called when version is changed. drops table to be created anew
     * @param db connection to database
     * @param oldVersion version before change
     * @param newVersion new version
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    /**
     * If ingredient exists in data source, updated it. Else saves a new one.
     * @param ingredient ingredient to save or update
     */
    fun saveOrUpdate (ingredient:Ingredient){
        if (findById(ingredient.idIngredient) == null){
            save(ingredient)
        } else {
            update(ingredient)
        }
    }

    /**
     * returns a ContentValues instance containing an entity's property names and values, ready for
     * database insertion or update
     * @param ingredient entity from which to create ContentValues instance
     */
    private fun getContentValues(ingredient: Ingredient):ContentValues{
        val contentValues = ContentValues()
        contentValues.put(idColumn, ingredient.idIngredient)
        contentValues.put(nameColumn, ingredient.name)
        contentValues.put(isCheckedColumn, ingredient.isChecked)
        return contentValues
    }

    /**
     * add entity to data source
     * @param ingredient to save
     */
    fun save(ingredient: Ingredient):Long{
        val db = this.writableDatabase
        val success = db.insert(tableName, null, getContentValues(ingredient))
        db.close()
        return success
    }

    /**
     * update existing entity in data source
     * @param ingredient to update
     */
    fun update(ingredient: Ingredient):Int{
        val db = this.writableDatabase
        val success = this.writableDatabase.update(
            tableName,
            getContentValues(ingredient),
            "idIngredient="+ingredient.idIngredient,null)
        db.close() // Closing database connection
        return success
    }

    /**
     * delete entity from data source
     * @param ingredient to delete
     */
    fun delete(ingredient: Ingredient):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(idColumn, ingredient.idIngredient) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(tableName,"$idColumn="+ingredient.idIngredient,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    /**
     * find all entities in data source
     */
    fun findAll():List<Ingredient>{
        val ingredients = ArrayList<Ingredient>()
        val selectQuery = "SELECT  * FROM $tableName"
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

    /**
     * find an entity by id
     * @param id to search for
     */
    fun findById(id:Int): Ingredient? {
        var ingredient:Ingredient? = null;
        val selectQuery = "SELECT  * FROM $tableName WHERE idIngredient=$id"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return null
        }

        if (cursor.moveToFirst()) {
            ingredient = cursorToEntity(cursor)
        }
        return ingredient
    }

    /**
     * transforms a query result cursor position into an entity
     * @param cursor result set cursor
     */
    private fun cursorToEntity(cursor:Cursor):Ingredient{
        val idIngredient = cursor.getInt(cursor.getColumnIndex(idColumn))
        val name = cursor.getString(cursor.getColumnIndex(nameColumn))
        val isChecked = cursor.getInt(cursor.getColumnIndex(isCheckedColumn))
        val ingredient = Ingredient()
        ingredient.idIngredient = idIngredient
        ingredient.name = name
        ingredient.isChecked = isChecked
        return ingredient
    }
}
