import org.apache.hadoop.io._
import org.apache.hadoop.mapreduce.Mapper

class MaxTemperatureMapper extends Mapper[Object, Text, Text, IntWritable] {
  def map (key : LongWritable, value : Text, context : Context) {
    def missing(temp : String) : Boolean = temp.equals("+9999")

    val line = value.toString
    val year = line.substring(15, 19)
    val temp = line.substring(87, 92)
    if (! missing(temp)) {
      val airTemperature = Integer.parseInt(line.substring(87, 92))
      context.write(new Text(year), new IntWritable(airTemperature))
    }
  }
}
