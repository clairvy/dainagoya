import org.apache.hadoop.mapreduce._
import org.apache.hadoop.io._
import org.apache.hadoop.mapred.OutputCollector
import org.apache.hadoop.mapred.Reporter

class MaxTemperatureMapper extends Mapper[LongWritable, Text, Text, IntWritable] {
  def map (key : LongWritable, value : Text, output : OutputCollector[Text, IntWritable], reporter : Reporter) {
    val line = value.toString
    val year = line.substring(15, 19)
    val airTemperature = Integer.parseInt(line.substring(87, 92))
    output.collect(new Text(year), new IntWritable(airTemperature))
  }
}
