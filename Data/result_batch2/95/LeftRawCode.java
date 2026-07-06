// https://github.com/ssssssss-team/spider-flow/tree/c799cca99c7d064673dc79e6ef06a08bbc0e292d/spider-flow-core/src/main/java/org/spiderflow/core/executor/function/UrlFunctionExecutor.java#L33-L62
public class TempClass {
	@Comment("获取url全部参数")
	@Example("${url.parameterMap('http://www.baidu.com/s?wd=spider-flow&abbr=sf')}")
	public static Map<String,String> parameterMap(String url){
		Map<String,String> map = new HashMap<String,String>();
		int index = url.indexOf("?");
		if(index != -1) {
	        String param = url.substring(index+1);
	        if(StringUtils.isNotBlank(param)) {
		        String[] params = param.split("&");
		        for (String item : params) {
		            String[] kv = item.split("=");
		            if(kv.length > 0) {
		            	if(StringUtils.isNotBlank(kv[0])) {
		            		String value = "";
		            		if(StringUtils.isNotBlank(kv[1])) {
		            			int kv1Index = kv[1].indexOf("#");
		            			if(kv1Index != -1) {
		            				value = kv[1].substring(0,kv1Index);
		            			}else {
		            				value = kv[1];
		            			}
		            		}
		            		map.put(kv[0],value);
		            	}
		            }
		        }
	        }
		}
		return map;
	}

}