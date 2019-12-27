package com.smeup.rpgparser.db.sql

import com.smeup.rpgparser.interpreter.*
import java.sql.ResultSet

fun DBField.sqlType(): String =
    when (this.type) {
        is StringType -> "CHAR(${this.type.size}) DEFAULT '' NOT NULL"
        is NumberType -> "DECIMAL(${this.type.size}, ${this.type.decimalDigits}) DEFAULT 0 NOT NULL"
        else -> TODO("Conversion to SQL Type not yet implemented: ${this.type}")
    }

fun typeFor(sqlType: String, columnSize: Int, decimalDigits: Int): Type =
    when (sqlType) {
        "VARCHAR" -> StringType(columnSize.toLong(), true)
        "CHARACTER" -> StringType(columnSize.toLong(), false)
        "DECIMAL", "INTEGER" -> NumberType(columnSize - decimalDigits, decimalDigits)
        else -> TODO("Conversion from SQL Type not yet implemented: $sqlType")
    }

fun Value.toDBValue(type: Type): Any =
    when (this) {
        is StringValue -> this.value
        is IntValue -> this.value
        is DecimalValue -> this.value
        is LowValValue -> type.lowValue()
        is HiValValue -> type.hiValue()
        else -> TODO("Conversion to DB Obejct not yet implemented: $this")
    }

fun Type.toValue(rs: ResultSet, fieldIndex: Int): Value =
    when (this) {
        is StringType -> StringValue(rs.getString(fieldIndex))
        is NumberType -> DecimalValue(rs.getBigDecimal(fieldIndex))
        else -> TODO("Conversion to Value not yet implemented: $this")
    }
