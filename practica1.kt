fun main() {
    var num1:Int = 5
    var num2:Double = 7.5
    var num3:Double
    var num4:Float = 3.2f
    
    var dats1:String = "El valor de un numero: "
    
    num3 = num1 + num2
    num4 = num3.toFloat() + num4
    dats1 = dats1 + num1.toString()
    
    println(num3)
    println(num4)
    println(dats1)
}
