// https://github.com/Discord4J/Discord4J/tree/c1086f2072539c6062553e2ff1f0d6a7dc00e735/voice/src/main/java/discord4j/voice/json/VoiceGatewayPayloadDeserializer.java#L38-L66
public class TempClass {
    @Nullable
    @Override
    public VoiceGatewayPayload<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode json = p.getCodec().readTree(p);
        int op = json.get("op").asInt();
        JsonNode d = json.get("d");

        switch (op) {
            case Hello.OP:
                return new Hello(d.get("heartbeat_interval").asLong());
            case Ready.OP:
                return new Ready(d.get("ssrc").asInt(), d.get("ip").asText(), d.get("port").asInt());
            case HeartbeatAck.OP:
                return new HeartbeatAck(d.asLong());
            case SessionDescription.OP:
                ArrayNode arrayNode = ((ArrayNode) d.get("secret_key"));
                byte[] secret_key = p.getCodec().readValue(arrayNode.traverse(p.getCodec()), byte[].class);
                return new SessionDescription(d.get("mode").asText(), secret_key);
            case Speaking.OP:
                return new Speaking(d.get("user_id").asText(), d.get("ssrc").asInt(), d.get("speaking").asBoolean());
            case VoiceDisconnect.OP:
                return new VoiceDisconnect(d.get("user_id").asText());
            case Resumed.OP:
                return new Resumed(d.asText()); // actually "d": null
            default:
                LOG.debug("Received voice gateway payload with unhandled OP: {}", op);
                return null;
        }
    }

}