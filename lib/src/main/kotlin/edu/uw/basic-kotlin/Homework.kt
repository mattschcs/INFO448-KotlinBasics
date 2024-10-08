package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when(arg){
        "Hello" -> "world"
        is String -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int):Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person (var firstName: String, val lastName: String, var age: Int)
{
    val debugString: String = "[Person firstName:$firstName lastName:$lastName age:$age]"

}

// write a class "Money"
class Money (val amount: Int, val currency: String)
{
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount cannot be negative")
        }

        if (currency !in listOf("USD", "EUR", "CAN", "GBP")) {
            throw IllegalArgumentException("Currency must be one of USD, EUR, CAN, GBP")
        }
    }

    private val conversionRates = mapOf(
        "USD" to mapOf("GBP" to 1.0/2.0, "EUR" to 3.0/2.0, "CAN" to 5.0/4.0),
        "GBP" to mapOf("USD" to 2.0/1.0, "EUR" to 3.0/1.0, "CAN" to 5.0/2.0),
        "EUR" to mapOf("USD" to 2.0/3.0, "GBP" to 1.0/3.0, "CAN" to 6.0/5.0),
        "CAN" to mapOf("USD" to 4.0/5.0, "GBP" to 2.0/5.0, "EUR" to 5.0/6.0)
    )

    fun convert(convertCurrency: String): Money {
        if (currency !in listOf("USD", "EUR", "CAN", "GBP")) {
            throw IllegalArgumentException("Currency must be one of USD, EUR, CAN, GBP")
        }
        if (currency == convertCurrency) {
            return this
        }
        val rate: Double = conversionRates[currency]?.get(convertCurrency)
            ?: throw IllegalArgumentException("convertCurrency cannot be null")

        val newAmount = (amount * rate).toInt()
        return Money(newAmount, convertCurrency)
    }

    operator fun plus(other: Money): Money {
        val otherMoney = other.convert(this.currency)
        return Money(this.amount + otherMoney.amount, this.currency)
    }


}


