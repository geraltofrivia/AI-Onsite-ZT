// https://github.com/redis/jedis/tree/8cdc90f7cd170cbda2a25778233489a779177650/src/main/java/redis/clients/jedis/search/querybuilder/QueryNode.java#L71-L86
public class TempClass {
  @Override
  public String toString(Parenthesize parenMode) {
    StringBuilder sb = new StringBuilder();
    StringJoiner sj = new StringJoiner(getJoinString());
    if (shouldParenthesize(parenMode)) {
      sb.append('(');
    }
    for (Node n : children) {
      sj.add(n.toString(parenMode));
    }
    sb.append(sj.toString());
    if (shouldParenthesize(parenMode)) {
      sb.append(')');
    }
    return sb.toString();
  }

}