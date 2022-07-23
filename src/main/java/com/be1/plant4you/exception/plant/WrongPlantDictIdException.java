package com.be1.plant4you.exception.plant;

public class WrongPlantDictIdException extends PlantException {

    public WrongPlantDictIdException(PlantErrorCode errorCode) {
        super(errorCode);
    }
}
