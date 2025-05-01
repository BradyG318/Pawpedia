package edu.quinnipiac.ser210.pawpedia.SQL

import androidx.room.*

    @Dao
    interface DogDataAccess {
        @Insert
        suspend fun insertDog(dog: Dog)

        @Query("SELECT * FROM dog")
        suspend fun getAllDogs(): List<Dog>

        @Query("SELECT * FROM dog WHERE size_index = :sizeIndex")
        suspend fun getDogsBySize(sizeIndex: Int): List<Dog>

        @Query("SELECT * FROM dog WHERE id = :id")
        suspend fun getDogById(id: Int): Dog
    }
