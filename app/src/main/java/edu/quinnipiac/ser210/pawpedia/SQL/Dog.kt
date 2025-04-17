package edu.quinnipiac.ser210.pawpedia.SQL
import androidx.room.PrimaryKey

data class Dog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val breed_name: String,
    val cover_photo_res_id: Int,
    val tag_list: String,
    val size_index: Int,
    val allergy_info: String,
    val average_weight: Double,
    val description: String
)