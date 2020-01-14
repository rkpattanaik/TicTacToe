package com.cognizant.tictactoe

import java.lang.Exception

class InvalidFieldException(message: String = "Invalid Field"): Exception(message)

class FieldOccupiedException(message: String = "Field already taken"): Exception(message)