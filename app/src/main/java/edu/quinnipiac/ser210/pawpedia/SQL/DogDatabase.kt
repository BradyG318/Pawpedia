package edu.quinnipiac.ser210.pawpedia.SQL

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.quinnipiac.ser210.pawpedia.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Dog::class], version = 1)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDataAccess

    companion object {
        @Volatile private var INSTANCE: DogDatabase? = null
        fun getDatabase(context: Context): DogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogDatabase::class.java,
                    "dog_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getDatabase(context).dogDao()
                                dao.insertDog(
                                    Dog(
                                        breed_name = "Shih Tzu",
                                        cover_photo_res_id = R.drawable.shihtzu, // make sure this image is in drawable
                                        tag_list = "toy, companion",
                                        size_index = 0,
                                        allergy_info = "Low allergen",
                                        average_weight = 12.0,
                                        description = "The Shih Tzu is a toy dog breed known for its long, silky coat and sweet personality."
                                    )
                                )
                                dao.insertDog(
                                    Dog(
                                        breed_name = "Border Collie",
                                        cover_photo_res_id = R.drawable.bordercollie, // make sure this image is in drawable
                                        tag_list = "herding, intelligent",
                                        size_index = 1, // medium
                                        allergy_info = "Moderate allergen",
                                        average_weight = 40.0,
                                        description = "Border Collies are highly intelligent and energetic dogs originally bred for herding livestock. They excel in obedience and agility and thrive with active owners."
                                    )
                                )
                                dao.insertDog(
                                    Dog(
                                        breed_name = "Irish Wolfhound",
                                        cover_photo_res_id = R.drawable.irishwolfhound, // make sure this image is in drawable
                                        tag_list = "hound, giant",
                                        size_index = 2,
                                        allergy_info = "Moderate allergen",
                                        average_weight = 140.0,
                                        description = "Irish Wolfhounds are the tallest of all dog breeds, known for their gentle nature and giant size."
                                    )
                                )
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}