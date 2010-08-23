import org.apache.hadoop.io._
import org.apache.hadoop.mapred.MapReduceBase
import org.apache.hadoop.mapred.Mapper
import org.apache.hadoop.mapred.OutputCollector
import org.apache.hadoop.mapred.Reporter

class MaxTemperatureMapper extends MapReduceBase with Mapper[LongWritable, Text, Text, IntWritable] {
  def map (key : LongWritable, value : Text, output : OutputCollector[Text, IntWritable], reporter : Reporter) {
    def missing(temp : String) : Boolean = temp.equals("+9999")

    val line = value.toString
    val year = line.substring(15, 19)
    val temp = line.substring(87, 92)
    if (! missing(temp)) {
      val airTemperature = Integer.parseInt(line.substring(87, 92))
      output.collect(new Text(year), new IntWritable(airTemperature))
    }
  }
}
