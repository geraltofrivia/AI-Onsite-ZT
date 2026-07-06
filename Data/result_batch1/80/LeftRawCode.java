// https://github.com/TheoKanning/openai-java/tree/269096609cb81dad5e21c8d19e669a656bebacf4/service/src/main/java/com/theokanning/openai/service/ChatFunctionCallArgumentsSerializerAndDeserializer.java#L40-L61
public class TempClass {
        @Override
        public JsonNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String json = p.getValueAsString();
            if (json == null || p.currentToken() == JsonToken.VALUE_NULL) {
                return null;
            }

            try {
                JsonNode node = null;
                try {
                    node = MAPPER.readTree(json);
                } catch (JsonParseException ignored) {
                }
                if (node == null || node.getNodeType() == JsonNodeType.MISSING) {
                    node = MAPPER.readTree(p);
                }
                return node;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

}