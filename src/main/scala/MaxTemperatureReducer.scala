import org.apache.hadoop.io._
import org.apache.hadoop.mapred.MapReduceBase
import org.apache.hadoop.mapred.Reducer
import org.apache.hadoop.mapred.OutputCollector
import org.apache.hadoop.mapred.Reporter
import java.util._

class MaxTemperatureReducer extends MapReduceBase with Reducer[Text, IntWritable, Text, IntWritable] {
  def reduce(key : Text, values : Iterator[IntWritable], output : OutputCollector[Text, IntWritable], reporter : Reporter) {
    var maxValue = Integer.MIN_VALUE

    while (values.hasNext) {
      maxValue = Math.max(maxValue, values.next.get)
    }
    output.collect(key, new IntWritable(maxValue))
  }
}
