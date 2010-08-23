import org.specs.Specification
import org.specs.mock.Mockito
import org.mockito.Matchers._  // to use matchers like anyInt()

import org.apache.hadoop.io._
import org.apache.hadoop.mapreduce._
import org.apache.hadoop.mapred.OutputCollector

import java.util._

object spec extends Specification with Mockito {
  "process valid record" in {
    val mapper = new MaxTemperatureMapper
    //                    0123456789A123456789B123456789C123456789D123456789E     123456789F123456789G123456789H123456789 - 8 = H
    val value = new Text("0043011990999991950051518004+68750+023550FM-12+0382" + "99999V0203201N00261220001CN9999999N9-00111+999999999999")
    //                                   ^^^^ year                                                                    ^^^^^ temp
    val output = mock[OutputCollector[Text, IntWritable]]
    mapper.map(null, value, output, null);
    
    there was one(output).collect(new Text("1950"), new IntWritable(-11))
  }

  "ignores missing temperature record" in {
    val mapper = new MaxTemperatureMapper
    //                    0123456789A123456789B123456789C123456789D123456789E     123456789F123456789G123456789H123456789 - 8 = H
    val value = new Text("0043011990999991950051518004+68750+023550FM-12+0382" + "99999V0203201N00261220001CN9999999N9+99991+99999999999")
    //                                   ^^^^ year                                                                    ^^^^^ temp
    val output = mock[OutputCollector[Text, IntWritable]]
    mapper.map(null, value, output, null)

    there was no(output).collect(anyObject(), anyObject())
  }

  "returns maximum integer in values" in {
    val reducer = new MaxTemperatureReducer

    val key = new Text("1950")

    val values = Arrays.asList(
      new IntWritable(10), new IntWritable(5)).iterator
    val output = mock[OutputCollector[Text, IntWritable]]

    reducer.reduce(key, values, output, null)

    there was one(output).collect(key, new IntWritable(10))
  }
}
