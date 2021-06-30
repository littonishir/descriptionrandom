import FileUtils.articleInit
import FileUtils.createNewFile
import FileUtils.getResourceAsText
import FileUtils.rootPath
import FileUtils.write2File
import java.awt.Desktop
import java.time.LocalDate
import java.util.*
import kotlin.system.exitProcess


fun main() {
    val currentTime = LocalDate.now() // 当前日期
    val targetPath = "/${rootPath()}/Documents/Markdown/"
    val book1 = "寄信人空缺.txt"
    getResourceAsText(book1).use {
        val readLines = it.readLines()
        var line: String
        while (true) {
            val nextInt = Random().nextInt(readLines.size)
            val content = readLines[nextInt].trim()
            if (content != "" && content.length > 10) {
                line = readLines[nextInt].trim()
                line = line.replace("""“""", "").replace("""”""", "")
                println("八月长安: $line")
                break
            }
        }
        //接收键盘录入
        val scanner = Scanner(System.`in`)
        println("请输入文件名：")
        val fileName = scanner.nextLine()
        val newFileName = "$targetPath$currentTime-$fileName.md"
        val createNewFile = createNewFile(newFileName)
        write2File(newFileName, articleInit(currentTime.toString(), fileName, line))
        try {
            Desktop.getDesktop().open(createNewFile)
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}






