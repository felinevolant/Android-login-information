/**
 * @id java/examples/detect-log
 * @name log detection
 * @description test for log detection
 * @problem.severity warning
 */

import java


predicate isLogger(MethodAccess call) {
    exists(Method method|call.getMethod() = method and
    method.getDeclaringType().hasQualifiedName("org.slf4j", "Logger") and 
    not method.getName().matches("%Enabled"))
}


from MethodAccess call
where isLogger(call)
select call
