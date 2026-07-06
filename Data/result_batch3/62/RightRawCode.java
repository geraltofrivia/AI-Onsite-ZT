// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/dracoon/src/main/java/ch/cyberduck/core/sds/io/swagger/client/model/AuditNodeInfo.java#L177-L190
public class TempClass {
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuditNodeInfo {\n");
    
    sb.append("    nodeId: ").append(toIndentedString(nodeId)).append("\n");
    sb.append("    nodeName: ").append(toIndentedString(nodeName)).append("\n");
    sb.append("    nodeParentPath: ").append(toIndentedString(nodeParentPath)).append("\n");
    sb.append("    nodeParentId: ").append(toIndentedString(nodeParentId)).append("\n");
    sb.append("    nodeIsEncrypted: ").append(toIndentedString(nodeIsEncrypted)).append("\n");
    sb.append("    countChildren: ").append(toIndentedString(countChildren)).append("\n");
    sb.append("}");
    return sb.toString();
  }

}