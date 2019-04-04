package com.smeup.rpgparser

import com.strumenta.kolasu.model.*

fun CompilationUnit.resolve() {
    this.assignParents()

    this.specificProcess(DataRefExpr::class.java) { dre ->
        if (!dre.variable.resolved) {
            require(dre.variable.tryToResolve(this.dataDefinitonsAndFields)) {
                "Data reference not resolved: ${dre.variable.name}"
            }
        }
    }
    this.specificProcess(ExecuteSubroutine::class.java) { esr ->
        if (!esr.subroutine.resolved) {
            require(esr.subroutine.tryToResolve(this.subroutines)) {
                "Subroutine call not resolved: ${esr.subroutine.name}"
            }
        }
    }
    // replace FunctionCall with ArrayAccessExpr where it makes sense
    this.specificProcess(FunctionCall::class.java) { fc ->
        if (fc.args.size == 1) {
            val data = this.dataDefinitonsAndFields.firstOrNull { it.name == fc.function.name }
            if (data != null) {
                fc.replace(ArrayAccessExpr(
                        array = DataRefExpr(ReferenceByName(fc.function.name, referred = data)),
                        index = fc.args[0],
                        position = fc.position))
            }
        }
    }

    // replace equality expr in Eval with Assignments
    this.specificProcess(EvalStmt::class.java) { fc ->
        if (fc.expression is EqualityExpr) {
            val ee = fc.expression as EqualityExpr
            fc.expression.replace(AssignmentExpr(ee.left as AssignableExpression, ee.right, ee.position))
        }
    }

}