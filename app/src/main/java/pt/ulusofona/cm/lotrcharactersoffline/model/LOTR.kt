package pt.ulusofona.cm.lotrcharactersoffline.model

import pt.ulusofona.cm.lotrcharactersoffline.data.local.LOTRGenderDB

abstract class LOTR {
    abstract fun getCharacters(onFinished: (Result<List<LOTRCharacter>>) -> Unit)
    abstract fun insertCharacters(characters: List<LOTRCharacter>, onFinished: () -> Unit)
    abstract fun clearAllCharacters(onFinished: () -> Unit)
    abstract fun getOrInsertGender(gender: LOTRCharacter.Gender, onFinished: (Result<LOTRCharacter.Gender>) -> Unit)
    abstract fun getGenders(onFinished: (Result<List<LOTRCharacter.Gender>>) -> Unit)

}