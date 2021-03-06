package com.smeup.rpgparser.interpreter

import com.smeup.rpgparser.utils.isZero
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

import com.smeup.rpgparser.interpreter.DecEdit.*

internal fun DecimalValue.formatAs(format: String, type: Type, decedit: DecEdit, padChar: Char = ' '): StringValue {
    fun signumChar(empty: Boolean) = (if (this.value < ZERO) "-" else if (empty) "" else " ")
    fun commas(t: NumberType) = if (t.entireDigits <= 3) 0 else t.entireDigits / 3
    fun points(t: NumberType) = if (t.decimalDigits > 0) 1 else 0
    fun nrOfPunctuationsIn(t: NumberType): Int = commas(t) + points(t)

    fun standardDecimalFormat(type: NumberType, locale: Locale) =
        DecimalFormat(decimalPattern(type), DecimalFormatSymbolsRepository.getSymbols(locale)).format(this.value.abs())

    // The functions below correspond to the EDITC parameter, one function per value
    fun f1(decedit: DecEdit): String {
        if (type !is NumberType) throw UnsupportedOperationException("Unsupported type for %EDITC: $type")
        return when (decedit) {
            COMMA -> {
                standardDecimalFormat(type, Locale.ITALY).padStart(type.size + nrOfPunctuationsIn(type), padChar)
            }
            ZERO_COMMA -> {
                if (this.value.abs() < BigDecimal.ONE) {
                    buildString {
                        append("0")
                        append(standardDecimalFormat(type, Locale.ITALY))
                    }.padStart(type.size + nrOfPunctuationsIn(type), padChar)
                } else {
                    standardDecimalFormat(type, Locale.ITALY).padStart(type.size + nrOfPunctuationsIn(type), padChar)
                }
            }
            ZERO_DOT -> {
                if (this.value.abs() < BigDecimal.ONE) {
                    buildString {
                        append("0")
                        append(standardDecimalFormat(type, Locale.US))
                    }.padStart(type.size + nrOfPunctuationsIn(type), padChar)
                } else {
                    standardDecimalFormat(type, Locale.US).padStart(type.size + nrOfPunctuationsIn(type), padChar)
                }
            }
            DOT -> {
                standardDecimalFormat(type, Locale.US).padStart(type.size + nrOfPunctuationsIn(type), padChar)
            }
        }
    }

    fun f2(decedit: DecEdit): String {
        if (this.value.isZero()) {
            return "".padStart(type.size + nrOfPunctuationsIn(type as NumberType))
        } else {
            return f1(decedit)
        }
    }

    fun italianDecimalformatWithNoThounsandsSeparator(type: NumberType) =
        DecimalFormat(buildString { append("#"); append(decimalsFormatString(type)) }, DecimalFormatSymbolsRepository.italianSymbols).format(this.value.abs())

    fun usDecimalformatWithNoThounsandsSeparator(type: NumberType) =
        DecimalFormat(buildString { append("#"); append(decimalsFormatString(type)) }, DecimalFormatSymbolsRepository.usSymbols).format(this.value.abs())

    fun f3(decedit: DecEdit): String {
        if (type !is NumberType) throw UnsupportedOperationException("Unsupported type for %EDITC: $type")
        return when (decedit) {
            COMMA -> {
                italianDecimalformatWithNoThounsandsSeparator(type)
                    .padStart(type.size + points(type), padChar)
            }
            ZERO_COMMA -> {
                if (this.value.abs() < BigDecimal.ONE) {
                    buildString {
                        append("0")
                        append(standardDecimalFormat(type, Locale.ITALY))
                    }
                    .padStart(type.size + points(type), padChar)
                } else {
                    italianDecimalformatWithNoThounsandsSeparator(type)
                        .padStart(type.size + points(type), padChar)
                }
            }
            ZERO_DOT -> {
                if (this.value.abs() < BigDecimal.ONE) {
                    buildString {
                        append("0")
                        append(standardDecimalFormat(type, Locale.US))
                    }
                    .padStart(type.size + points(type), padChar)
                } else {
                    usDecimalformatWithNoThounsandsSeparator(type)
                        .padStart(type.size + points(type), padChar)
                }
            }
            DOT -> {
                DecimalFormat(buildString {
                    append("#")
                    append(decimalsFormatString(type))
                }, DecimalFormatSymbolsRepository.usSymbols)
                    .format(this.value.abs())
                    .padStart(type.size + points(type), padChar)
            }
        }
    }

    fun f4(decedit: DecEdit): String {
        if (this.value.isZero()) {
            return "".padStart(type.size + points(type as NumberType))
        } else
            return f3(decedit)
    }

    fun fA(decedit: DecEdit): String {
        return if (this.value < ZERO) {
            f1(decedit) + "CR"
        } else {
            f1(decedit)
        }
    }

    fun fB(decedit: DecEdit): String = fA(decedit)

    fun fC(decedit: DecEdit): String {
        return if (this.value < ZERO) {
            f3(decedit) + "CR"
        } else {
            f3(decedit)
        }
    }

    fun fD(decedit: DecEdit): String {
        return if (this.value < ZERO) {
            f3(decedit) + "CR"
        } else {
            f3(decedit)
        }
    }

    fun fJ(decedit: DecEdit): String = f1(decedit) + signumChar(true)

    fun fK(decedit: DecEdit): String = f2(decedit) + signumChar(true)

    fun fL(decedit: DecEdit): String = f3(decedit) + signumChar(true)

    fun fM(decedit: DecEdit): String = f4(decedit) + signumChar(true)

    fun fN(decedit: DecEdit): String = signumChar(false) + f1(decedit)

    fun fO(decedit: DecEdit): String = signumChar(false) + f2(decedit)

    fun fP(decedit: DecEdit): String = signumChar(false) + f3(decedit)

    fun fQ(decedit: DecEdit): String = signumChar(false) + f4(decedit)

    fun toBlnk(c: Char) = if (c == '0') ' ' else c

    fun fY(): String {
        var stringN = this.value.abs().unscaledValue().toString().trim()
        return if (type.elementSize() <= 6) {
            stringN = stringN.padStart(6, '0')
            "${toBlnk(stringN[0])}${stringN[1]}/${stringN[2]}${stringN[3]}/${stringN[4]}${stringN[5]}".padStart(type.size + 2)
        } else {
            stringN = stringN.padStart(8, '0')
            "${toBlnk(stringN[0])}${stringN[1]}/${stringN[2]}${stringN[3]}/${stringN[4]}${stringN[5]}${stringN[6]}${stringN[7]}".padStart(type.size + 2)
        }
    }

    fun handleInitialZero(decedit: DecEdit): String {
        return if (this.value.isZero()) {
            ""
        } else {
            f1(decedit).replace(".", "").replace(",", "").trim()
        }
    }

//    fun fX(decedit: DecEdit) = value.unscaledValue().abs().toString().padStart(type.size, '0')
    fun fX(decedit: DecEdit) = handleInitialZero(decedit).padStart(type.size, '0')

    fun fZ(decedit: DecEdit) = handleInitialZero(decedit).padStart(type.size)

    return when (format) {
        "1" -> StringValue(f1(decedit))
        "2" -> StringValue(f2(decedit))
        "3" -> StringValue(f3(decedit))
        "4" -> StringValue(f4(decedit))
        "A" -> StringValue(fA(decedit))
        "B" -> StringValue(fB(decedit))
        "C" -> StringValue(fC(decedit))
        "D" -> StringValue(fD(decedit))
        "X" -> StringValue(fX(decedit))
        "J" -> StringValue(fJ(decedit))
        "K" -> StringValue(fK(decedit))
        "L" -> StringValue(fL(decedit))
        "M" -> StringValue(fM(decedit))
        "N" -> StringValue(fN(decedit))
        "O" -> StringValue(fO(decedit))
        "P" -> StringValue(fP(decedit))
        "Q" -> StringValue(fQ(decedit))
        "Y" -> StringValue(fY())
        "Z" -> StringValue(fZ(decedit))
        else -> throw UnsupportedOperationException("Unsupported format for %EDITC: $format")
    }
}

internal fun DecimalValue.formatAsWord(format: String, type: Type): StringValue {
    fun isConst(formatChar: Char): Boolean =
        when (formatChar) {
            '0', '*' -> false // TODO
            ' ' -> false // TODO see if it's OK
            else -> true
        }

    fun CharArray.cleanZeroesUntil(lastPosition: Int): CharArray {
        loop@ for (i in 0..lastPosition) {
            if (this[i] == '0') {
                this[i] = ' '
            } else if (this[i] in '1'..'9') {
                break@loop
            }
        }
        return this
    }

    fun String.handleSignum(decimalValue: DecimalValue): String =
        if (!decimalValue.isPositive() || this.count { it == '-' } > 1) {
            this
        } else {
            this.replaceFirst("-", " ")
        }

    if (type !is NumberType) throw UnsupportedOperationException("Unsupported type for %EDITW: $type")
    val firstZeroInFormat = format.indexOfFirst { it == '0' }
    val wholeNumberAsString =
        this.significantDigitsAsStringJustDigits(type)
            .padStart(format.length)
            .mapIndexed { i, c -> if ((firstZeroInFormat > -1 && i > firstZeroInFormat) && c == ' ') '0' else c }
            .reversed()
            .iterator()
    val reversedResult = " ".repeat(format.length).toCharArray()
    format.reversed().forEachIndexed {
        i, formatChar ->
        if (isConst(formatChar)) {
            reversedResult[i] = formatChar
        } else {
            if (wholeNumberAsString.hasNext()) {
                reversedResult[i] = wholeNumberAsString.next()
            }
        }
    }
    val result =
        reversedResult
        .reversedArray()
        .cleanZeroesUntil(firstZeroInFormat)
        .joinToString(separator = "")
        .handleSignum(this)

    return StringValue(result)
}

object DecimalFormatSymbolsRepository {
    val italianSymbols = DecimalFormatSymbols(Locale.ITALY)
    val usSymbols = DecimalFormatSymbols(Locale.US)
    fun getSymbols(locale: Locale): DecimalFormatSymbols {
        return when (locale) {
            Locale.ITALY -> italianSymbols
            Locale.US -> usSymbols
            else -> DecimalFormatSymbols(locale)
        }
    }
}

private fun decimalPattern(type: NumberType) = buildString {
    append("#,###")
    append(decimalsFormatString(type))
}

fun decimalsFormatString(t: NumberType) =
    if (t.decimalDigits == 0) {
        ""
    } else buildString {
        append(".")
        append("".padEnd(t.decimalDigits, '0'))
    }

fun DecimalValue.significantDigitsAsStringJustDigits(t: NumberType): String = significantDigitsAsString(t).filter(Char::isDigit)
fun DecimalValue.significantDigitsAsString(t: NumberType): String = DecimalFormat(decimalsFormatString(t)).format(this.value)
