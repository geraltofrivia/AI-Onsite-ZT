// https://github.com/PlexPt/chatgpt-java/tree/2ebd898240b082b65f41f86bfa11a282ecf7d7a2/src/main/java/com/plexpt/chatgpt/util/TokensUtil.java#L70-L109
public class TempClass {
        if (CollectionUtils.isEmpty(messages)) {
            return 0;
        }
        Encoding encodingUsed = encoding;
        //"gpt-3.5-turbo"
        // every message follows <|start|>{role/name}\n{content}<|end|>\n
        int tokensPerMessage = 4;
        // if there's a name, the role is omitted
        int tokensPerName = -1;

        if (StringUtils.startsWithIgnoreCase(model, ModelType.GPT_4.getName())) {
            tokensPerMessage = 3;
            tokensPerName = 1;
        }
        if (StringUtils.startsWithIgnoreCase(model, ModelType.GPT_4O.getName())) {
            encodingUsed = encoding4o;
        }

        int sum = 0;
        for (final Message message : messages) {
            sum += tokensPerMessage;
            sum += encodingUsed.countTokens(message.getContent());
            sum += encodingUsed.countTokens(message.getRole());
            if (!StringUtils.isEmpty(message.getName())) {
                sum += encodingUsed.countTokens(message.getName());
                sum += tokensPerName;
            }
        }

        // every reply is primed with <|start|>assistant<|message|>
        sum += 3;

        return sum;
    }

    /**
     * 计算tokens
     *
     * @param modelName 模型名称
     * @param messages  消息列表

}