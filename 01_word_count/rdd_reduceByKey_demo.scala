// 导包
import org.apache.spark.rdd.RDD


// 打开文件
val file: String = "./wikiOfSpark.txt"

// 读取文件内容
val lineRDD: RDD[String] = spark.sparkContext.textFile(file)

// 以行为单位进行分词
val wordRDD: RDD[String] = lineRDD.flatMap(line => line.split(" "))
// 去掉多余的空字符串
val cleanWordRDD: RDD[String] = wordRDD.filter(word => !word.equals(""))

// 把RDD元素转化成（key, value）形式
val kvRDD: RDD[(String, Int)] = cleanWordRDD.map(word => (word,1))

//按照单词做分组计数
val wordCountRDD: RDD[(String, Int)] = kvRDD.reduceByKey((x, y) => x + y)
