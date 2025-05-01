package edu.quinnipiac.ser210.pawpedia.SQL

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.quinnipiac.ser210.pawpedia.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Dog::class], version = 2)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDataAccess

    companion object {
        @Volatile private var INSTANCE: DogDatabase? = null
        fun getDatabase(context: Context): DogDatabase {
            return INSTANCE ?: synchronized(this) {
                val MIGRATION_1_2 = object: Migration(1, 2) {
                    override fun migrate(db: SupportSQLiteDatabase) {

                    }
                }
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
                                        breed_name = "Cavapoo",
                                        cover_photo_res_id = R.drawable.smoldog, // make sure this image is in drawable
                                        tag_list = "toy, companion, hypoallergenic",
                                        size_index = 0,
                                        allergy_info = "Low to moderate allergen",
                                        average_weight = 15.0,
                                        description = "The Cavapoo is a crossbreed between a Cavalier King Charles Spaniel and a Poodle, known for its friendly nature, soft curly coat, and suitability for families."
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
                                        breed_name = "Goldendoodle",
                                        cover_photo_res_id = R.drawable.mediumdog, // make sure this image is in drawable
                                        tag_list = "High-energy, Family-loving, Curious",
                                        size_index = 1,
                                        allergy_info = "Low allergen",
                                        average_weight = 30.0,
                                        description = "Goldendoodles are the cleaning-concious family's Retreiver. With very similar personality to their half cousin's and none of the shedding, these fluffballs are perfect for a family looking for an easier Retreiver."
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
                                dao.insertDog(
                                    Dog(
                                        breed_name = "Saint Bernard",
                                        cover_photo_res_id = R.drawable.saintbernard, // make sure this image is in drawable
                                        tag_list = "working, giant, rescue",
                                        size_index = 2,
                                        allergy_info = "High allergen",
                                        average_weight = 140.0,
                                        description = "Saint Bernards are large, powerful dogs originally bred for rescue work in the Swiss Alps, known for their loyalty and gentle temperament."
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