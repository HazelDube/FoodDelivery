package helpers

import java.io.*



val logger = KotlinLogging.logger {}
/**
 * this function will be written in to the json file
 */


fun write( fileName: String, data: String){
    val file = File(fileName)

    try {
        val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception){
        logger.error { "Cannot read file: $e" }
    }

}

/**
 * function to read the json file
 */
fun read(fileName: String): String{
    val file = File(fileName)
    var str = ""
    try {
        val inputStreamReader = InputStreamReader(FileInputStream(file))
        if (inputStreamReader != null){
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while(!done){
                var line = bufferedReader.readLine()
                done = (line == null)
                if(line != null) partialStr.append(line)
            }
            inputStreamReader.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException){
        logger.error { "Cannot find file: $e" }
    } catch (e: IOException){
        logger.error { "Cannot Read File: $e" }
    }
    return str
}

fun exists(fileName: String) : Boolean{
    val file = File(fileName)
    return file.exists()
}