import difficulty.Easy
import difficulty.Hard
import difficulty.Medium
import helpers.Colors
import java.awt.Desktop
import java.net.URI
import java.util.*


fun main() {
    println(Colors.BLUE_BOLD_BRIGHT + "Welcome to Party Runner." + Colors.RESET)
    println(Colors.WHITE_BOLD + "Source code at github.com/kaanf" + Colors.RESET)
    println(Colors.GREEN_BOLD_BRIGHT + "(1) Start Game" + Colors.RESET)
    println(Colors.YELLOW_BOLD_BRIGHT + "(2) Documentation" + Colors.RESET)
    val scanner = Scanner(System.`in`)
    while (true) {
        when (scanner.nextLine()) {
            "1" -> difficultyLevel()
            "2" -> openUrl()
        }
    }
}

fun openUrl() {
    try {
        println("Redirects in 10 seconds. If the site does not open, go to github.com/kaanf/party-runner-cli")
        val desktop = Desktop.getDesktop()
        val oURL = URI("http://www.github.com/kaanf/party-runner-cli")
        desktop.browse(oURL)
        main()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun difficultyLevel() {
    val sb = StringBuilder()
    sb.append(Colors.BLUE_BOLD_BRIGHT + "Choose difficulty level:" + Colors.RESET).append("\n")
    sb.append(Colors.GREEN_BOLD_BRIGHT + "(1)" + Colors.RESET + " Easy").append("\n")
    sb.append(Colors.YELLOW_BOLD_BRIGHT + "(2)" + Colors.RESET + " Medium").append("\n")
    sb.append(Colors.RED_BOLD_BRIGHT + "(3)" + Colors.RESET + " Hard").append("\n")
    sb.append(Colors.PURPLE_BOLD_BRIGHT + "Else, back to home." + Colors.RESET).append("\n")
    print(sb)
    val scanner = Scanner(System.`in`)
    when (scanner.nextLine()) {
        "1" -> Easy.main()
        "2" -> Medium.main()
        "3" -> Hard.main()
        else -> main()
    }
}