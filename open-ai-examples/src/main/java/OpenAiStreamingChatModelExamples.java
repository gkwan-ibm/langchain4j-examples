import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class OpenAiStreamingChatModelExamples {

    static class Simple_Prompt {

        public static void main(String[] args) {

            StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
                    .apiKey(ApiKeys.OPENAI_API_KEY)
                    .modelName(GPT_4_O_MINI)
                    .build();

            model.generate("Tell me a joke about Java", new StreamingResponseHandler<AiMessage>() {

                @Override
                public void onNext(String token) {
                    System.out.println("onNext(): " + token);
                }

                @Override
                public void onComplete(Response<AiMessage> response) {
                    System.out.println("onComplete(): " + response);
                }

                @Override
                public void onError(Throwable error) {
                    error.printStackTrace();
                }
            });
        }
    }
}
