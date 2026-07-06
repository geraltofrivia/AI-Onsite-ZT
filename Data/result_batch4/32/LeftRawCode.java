// https://github.com/databricks/learning-spark/tree/6b34161e2c1351500784a0d500c664c90846cacf/src/main/java/com/oreilly/learningsparkexamples/java/BasicMapToDouble.java#L17-L35
public class TempClass {
  public static void main(String[] args) throws Exception {
		String master;
		if (args.length > 0) {
      master = args[0];
		} else {
			master = "local";
		}
		JavaSparkContext sc = new JavaSparkContext(
      master, "basicmaptodouble", System.getenv("SPARK_HOME"), System.getenv("JARS"));
    JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4));
    JavaDoubleRDD result = rdd.mapToDouble(
      new DoubleFunction<Integer>() {
        public double call(Integer x) {
          double y = (double) x;
          return y * y;
        }
      });
    System.out.println(StringUtils.join(result.collect(), ","));
	}

}