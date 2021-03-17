import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sin

/**
 * Created by Matvey on Mar, 2021
 */


fun rectangularRule(
    func: (x: Double) -> Double,
    a: Double,
    b: Double,
    ns: Int,
    frac: Double = 0.5
): Double {
    val dx: Double = 1.0 * (b - a) / ns // 1.0 - to Double everywhere
    var sum = 0.0
    val xStart = a + frac * dx

    for (i in 0..ns) {
        sum += func(xStart + i * dx)
    }

    return sum * dx
}

fun trapezoidRule(
    func: (x: Double) -> Double,
    a: Double,
    b: Double,
    ns: Int
): Double {
    val dx = 1.0 * (b - a) / ns
    var sum = 0.0
    sum += 0.5 * (func(a) + func(b))
    for (i in 1..ns) {
        sum += func(a + i * dx)
    }
    return sum * dx
}

fun trapezoidRuleEstControl(
    func: (x: Double) -> Double,
    a: Double,
    b: Double,
    rTol: Double = 1e-5,
    ns0: Int = 1
): Double {
    var ns = ns0
    var oldSum = 0.0
    val dx = 1.0 * (b - a) / ns
    var sum = 0.5 * (func(a) + func(b))
    for (i in 1..ns) {
        sum += func(a + i * dx)
    }
    sum *= dx

    // Error estimation
    var errEst = max(1.0, abs(sum))
    while (errEst > rTol) { // Runge rule
        oldSum = sum
        sum = 0.5 * (sum + rectangularRule(func, a, b, ns))

        ns *= 2
        errEst = abs(sum - oldSum) / 3 // Runge for trapezoid and rect
    }
    return sum
}

fun simpsonRuleBruteForce(
    func: (x: Double) -> Double,
    a: Double,
    b: Double,
    ns: Int
): Double {
    val dx = 1.0 * (b - a) / ns
    val nsOpt = if (ns % 2 == 0) ns else ns + 1
    var sum : Double = (func(a) + 4 * func(a + dx) + func(b))
    for (i in 1..ns/2){
        sum += 2 * func(a + (2 * i) * dx) + 4 * func(a + (2 * i + 1) * dx)
    }
    return sum * dx / 3
}

fun main() { // Test
    val rectangularRule = rectangularRule(func = { x -> sin(x) }, 0.0, 10.0, 600000, 0.5)
    val trapezoidRule = trapezoidRule(func = { x -> sin(x) }, 0.0, 10.0, 600000)
    val trapezoidRuleEstControl = trapezoidRuleEstControl(func = { x -> sin(x) }, 0.0, 10.0, 1e-4)
    val simpsonRuleBruteForce = simpsonRuleBruteForce(func = {x -> sin(x)}, 0.0, 10.0, 600000)
    println(rectangularRule)
    println(trapezoidRule)
    println(trapezoidRuleEstControl)
    println(simpsonRuleBruteForce)
}