package id.fishku.fishkuseller.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginPref private constructor(private val dataStore: DataStore<Preferences>){

    private val SELLER_ID = longPreferencesKey("seller_id")

    companion object {
        @Volatile
        private var INSTANCE: LoginPref? = null

        fun getInstance(dataStore: DataStore<Preferences>): LoginPref {
            return INSTANCE ?: synchronized(this){
                val instance = LoginPref(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    fun getSellerId(): Flow<Long> {
        return dataStore.data.map { preferences ->
            preferences[SELLER_ID] ?: 0
        }
    }

    suspend fun saveSellerId(sellerId: Long){
        dataStore.edit { preferences ->
            preferences[SELLER_ID] = sellerId
        }
    }

    suspend fun removeSellerId(){
        dataStore.edit { preferences ->
            preferences[SELLER_ID] = -1L
        }
    }


}