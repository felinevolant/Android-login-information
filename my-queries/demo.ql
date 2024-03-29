/**
 * @id java/examples/eq-true
 * @name Equality test on boolean
 * @kind problem
 * @description Finds tests like `==true`, `==false`, `!=true`, `!=false`
 * @tags equals
 *       test
 *       boolean
 */

import java

from EqualityTest eq
where eq.getAnOperand() instanceof BooleanLiteral
select eq,"Find Equality test on boolean"
