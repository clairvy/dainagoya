import org.apache.hadoop.io._
import org.apache.hadoop.mapred.MapReduceBase
import org.apache.hadoop.mapred.Reducer
import org.apache.hadoop.mapred.OutputCollector
import org.apache.hadoop.mapred.Reporter
import scala.collection.JavaConversions._

class MaxTemperatureReducer extends MapReduceBase with Reducer[Text, IntWritable, Text, IntWritable] {
  def reduce(key : Text, values : java.util.Iterator[IntWritable], output : OutputCollector[Text, IntWritable], reporter : Reporter) {
    val maxValue = values./:(Integer.MIN_VALUE)((i,v) => Math.max(i, v.get))
    output.collect(key, new IntWritable(maxValue))
  }
}
