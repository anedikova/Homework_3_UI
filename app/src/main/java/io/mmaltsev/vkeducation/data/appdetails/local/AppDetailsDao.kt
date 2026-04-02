package io.mmaltsev.vkeducation.data.appdetails.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDetailsDao {

    @Query("SELECT * FROM app_details WHERE id = :id LIMIT 1")
    suspend fun getAppDetailsById(id: String): AppDetailsEntity?

    @Query("SELECT * FROM app_details WHERE id = :id LIMIT 1")
    fun observeAppDetails(id: String): Flow<AppDetailsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppDetails(appDetails: AppDetailsEntity)

    @Query("UPDATE app_details SET isInWishlist = :isInWishlist WHERE id = :id")
    suspend fun updateWishlistStatus(id: String, isInWishlist: Boolean)
}