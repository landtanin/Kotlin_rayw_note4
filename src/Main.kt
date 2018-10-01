fun main(args: Array<String>) {

    class Grade(var letter: Char, var points: Double, var credits: Double)

    open class Person(var firstName: String, var lastName: String)

    open class Student(firstName: String, lastName: String,
                  var grades: ArrayList<Grade> = ArrayList()) : Person(firstName, lastName) {

        fun recordGrade(grade: Grade) {
            grades.add(grade)
        }

    }

    val john = Person("Johnny", "Appleased")
    val jane = Student("Jane", "Appleased")

    println(john.firstName)
    println(jane.firstName)

    val history = Grade('B'  , 9.0, 3.0)
    jane.recordGrade(history)

    // subclasses the subclass Student
    open class BandMember(firstName: String, lastName: String) : Student(firstName, lastName) {
        open var minimumPracticeTime = 2
    }

    class OboPlayer(firstName: String, lastName: String) : BandMember(firstName, lastName) {
        override var minimumPracticeTime: Int
            get(){
                return super.minimumPracticeTime * 2
            }
            set(value) {
                super.minimumPracticeTime = value/2
            }
    }

    fun phonebookName(person: Person) : String {
        return "${person.lastName}, ${person.firstName}"
    }

    val person = Person("johnny", "Appleseed")
    val oboePlayer = OboPlayer("jane", "Appleseed")

    // polymorphism, oboePlayer is treated as Person in this context as it's a subclass of Person
    println(phonebookName(person))
    println(phonebookName(oboePlayer))

    // casting
    var hallMonitor = Student("Jill", "Bananapeel")
    hallMonitor = oboePlayer

//    (oboePlayer as Student).minimumPracticeTime = 4
    // this won't work as Student class doesn't has minimumPracticeTime
    (oboePlayer as BandMember).minimumPracticeTime = 4

    // optional down casting
    (hallMonitor as? BandMember)?.let {
        println("""This hall monitor is a band member and practices at least
            ${hallMonitor.minimumPracticeTime} hours per week.""".trimMargin())
    }

}