package pt.ulusofona.cm.lotrcharactersoffline.model

import java.io.Serializable

/**
 * This must be a pure class, without dependencies of json, parcelable, etc..
 */
data class LOTRCharacter (
    val id: String,
    val birth: String,
    val death: String,
    val gender: Gender,  // gender is optional
    val name: String
) : Serializable {
    enum class Gender {
        MALE, FEMALE, UNKNOWN,
    }

}
