import org.apache.hadoop.io._
import org.apache.hadoop.mapreduce._

import scala.math._
import scala.collection.JavaConversions._

class MaxTemperatureReducer extends Reducer[Text, IntWritable, Text, IntWritable] {
  def reduce(key : Text, values : java.util.Iterator[IntWritable], context : Context) {
    val maxValue = values./:(Integer.MIN_VALUE)((i,v) => max(i, v.get))
    context.write(key, new IntWritable(maxValue))
  }
}
