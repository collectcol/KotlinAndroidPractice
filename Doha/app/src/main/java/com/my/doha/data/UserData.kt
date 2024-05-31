package com.my.doha.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserData(
    @PrimaryKey val uid: String,
    val email: String? = null,
    val displayName: String? = null
) {
    // 기본 생성자
    constructor() : this("", null, null)
}
