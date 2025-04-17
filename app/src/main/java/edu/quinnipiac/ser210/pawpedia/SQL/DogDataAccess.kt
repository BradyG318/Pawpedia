package edu.quinnipiac.ser210.pawpedia.SQL

import androidx.room.*

    @Dao
    interface DogDataAccess {
        @Insert
        suspend fun insertDog(dog: Dog)

        @Query("SELECT * FROM dogs")
        suspend fun getAllDogs(): List<Dog>
    }
