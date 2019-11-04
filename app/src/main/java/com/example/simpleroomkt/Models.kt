package com.example.simpleroomkt

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class CarNote(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var year: String,
    var mark: String,
    var producer: String,
    var classModel: String,
    var body: String
) {
    override fun toString(): String {
        return "CarNote(uid=$uid, year='$year', mark='$mark', producer='$producer', classModel='$classModel', body='$body')"
    }
}

@Dao
interface CarDao {

    @Query("select * from CarNote")
    fun fetchAll(): LiveData<List<CarNote>>

    @Insert
    suspend fun insert(arg: CarNote)

    @Update
    suspend fun update(arg: CarNote)

    @Delete
    suspend fun delete(arg: CarNote)

}