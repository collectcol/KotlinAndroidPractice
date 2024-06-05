package com.my.doha.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.my.doha.data.UserData

@Dao
interface UserDataDao {
    @Query("SELECT * FROM user_data WHERE uid = :uid")
    suspend fun getUserDataById(uid: String): UserData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userData: UserData)

    @Query("DELETE FROM user_data WHERE uid = :uid")
    suspend fun deleteUserDataById(uid: String)
}