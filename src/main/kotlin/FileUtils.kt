import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

object FileUtils {

    /**
     * 执行指令，类似于终端。
     * Desktop.getDesktop().open(createNewFile)
     */
    fun exec(command: String="") {
        val process = Runtime.getRuntime().exec(command)
        process.waitFor()
        process.destroy()
    }

    /**
     * 文件转成流
     */
    fun getResourceAsText(path: String): BufferedReader {
        return object {}::class.java.getResourceAsStream(path).bufferedReader()
    }
    /**
     * 文件转成流
     */
    fun getResourcePath(path: String): String {
        return object {}::class.java.getResource(path).path
    }

    /**
     * 初始化文章内容
     */
    fun articleInit(time: String, title: String, description: String): String {
        return "---\nlastModified:\"$time\"\n---\n# $title\n$description"
    }

    /**
     * 获取项目根目录
     * user.name:用户的账户名称
     * user.home:用户的主目录
     * user.dir:用户的当前工作目录
     */
    fun rootPath(): String = System.getProperty("user.home")


    /**
     * 向指定文件写入指定内容
     */
    fun write2File(filePathName: String, str: String) {
        val fw = FileWriter(filePathName)
        try {
            fw.write(str)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fw.close()
        }
    }

    /**
     * 在指定目录下创建文件，若文件不存在，则创建；若已经存在，则不创建
     */
    fun createNewFile(filePathName: String): File {
        val file = File(filePathName)
        val parentFile = file.parentFile
        if (!parentFile.exists()){
            parentFile.mkdirs()
        }
        if (!file.exists()) {
             file.createNewFile()
        }
        return file
    }

    /**
     * 复制文件
     */
    fun copyFile2Dest(pathName: String, destName: String) {
        val fr = FileReader(File(pathName))
        val fw = FileWriter(File(destName))
        try {
            val buffer = CharArray(1024)
            var len = fr.read(buffer)
            while (len != -1) {
                fw.write(buffer, 0, len)
                len = fr.read(buffer)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fr.close()
            fw.close()
        }
    }

}